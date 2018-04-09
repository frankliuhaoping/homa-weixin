package cn.cnyirui.framework.extension.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.Assert;

import cn.cnyirui.framework.extension.weixin.WxMpSpringCacheConfigStorge;
import cn.cnyirui.framework.utils.Constants;
import cn.cnyirui.framework.utils.ServletUtils;

/**
 * 检查session中是否有微信openId，如果无进行Oauth2静默授权
 * 
 * @author pengzhihua
 *
 */
public class WeiXinUserFilter extends AccessControlFilter {

	private WxMpService wxMpService;
	private WxMpConfigStorage wxMpConfigStorage;
	private String weiXinDebugOpenId;

	public void setWeiXinDebugOpenId(String weiXinDebugOpenId) {
		this.weiXinDebugOpenId = weiXinDebugOpenId;
	}

	public void setWxMpService(WxMpService wxMpService) {
		this.wxMpService = wxMpService;
	}

	public void setWxMpConfigStorage(WxMpConfigStorage wxMpConfigStorage) {
		this.wxMpConfigStorage = wxMpConfigStorage;
	}

	public void setWeiXinBaseUrl(ServletRequest request) {
		if (!(wxMpConfigStorage instanceof WxMpSpringCacheConfigStorge)) {
			return;
		}
		WxMpSpringCacheConfigStorge wxMpSpringCacheConfigStorge = (WxMpSpringCacheConfigStorge) wxMpConfigStorage;
		if (StringUtils.isEmpty(wxMpSpringCacheConfigStorge.getBaseUrl())) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			StringBuffer stringBuffer = httpServletRequest.getRequestURL();
			String baseUrl = stringBuffer
			        .delete(stringBuffer.length() - httpServletRequest.getRequestURI().length(), stringBuffer.length())
			        .append(request.getServletContext().getContextPath()).append("/").toString();
			wxMpSpringCacheConfigStorge.setBaseUrl(baseUrl);
		}
	}

	protected String getWeiXinOpenId(ServletRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		setWeiXinBaseUrl(request);
		 if (!ServletUtils.isWeiXinBrowser(httpServletRequest)) { // 不是微信游览器访问
		 setWeiXinDebugOpenId("o-CSowYKppZwq4OiYoQaEHzqmTLE");
		 if (StringUtils.isNotBlank(weiXinDebugOpenId)) {
		 httpServletRequest.getSession().setAttribute(Constants.WEIXIN_OPENID_SESSION_KEY,
		 weiXinDebugOpenId);
		 return weiXinDebugOpenId;
		 }
		 }

		Object object = httpServletRequest.getSession().getAttribute(Constants.WEIXIN_OPENID_SESSION_KEY);
		if (object != null) {
			return object.toString();
		}
		return null;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
	        throws Exception {
		return StringUtils.isNotBlank(getWeiXinOpenId(request));
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		setWeiXinBaseUrl(request);
		StringBuilder sb = new StringBuilder(wxMpConfigStorage.getOauth2redirectUri());
		Assert.hasLength(sb.toString(), "回调链接地址不能为空！！");

		WebUtils.saveRequest(request);
		SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
		if (StringUtils.indexOf(sb, "?") == -1) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		sb.append("redirectUrl=" + savedRequest.getRequestUrl());

		System.out.println(wxMpService.oauth2buildAuthorizationUrl(sb.toString(), WxConsts.OAUTH2_SCOPE_BASE, null));
		WebUtils.issueRedirect(request, response,
		        wxMpService.oauth2buildAuthorizationUrl(sb.toString(), WxConsts.OAUTH2_SCOPE_BASE, null));
		return false;
	}

}
