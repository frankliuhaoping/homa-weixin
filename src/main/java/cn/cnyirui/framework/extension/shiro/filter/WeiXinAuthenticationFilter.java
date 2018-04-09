package cn.cnyirui.framework.extension.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.service.rbac.SysLogService;
import cn.cnyirui.framework.service.weixin.WeiXinUserService;
import cn.cnyirui.framework.utils.Constants;
import cn.cnyirui.framework.utils.ServletUtils;

/**
 * 微信的用户与SysUser绑定，实现自动登录
 * 
 * @author pengzhihua
 *
 */
public class WeiXinAuthenticationFilter extends WeiXinUserFilter {
	private static final Logger log = LoggerFactory.getLogger(WeiXinAuthenticationFilter.class);
	private WeiXinUserService weiXinUserService;
	private SysLogService sysLogService;

	public void setWeiXinUserService(WeiXinUserService weiXinUserService) {
		this.weiXinUserService = weiXinUserService;
	}

	public void setSysLogService(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	@Override
	public String getLoginUrl() {
		return "/weixin/workbench/login/showForm";
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
	        throws Exception {
		Subject subject = getSubject(request, response);
		return subject.isAuthenticated();
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (!super.isAccessAllowed(request, response, null)) {
			return super.onAccessDenied(request, response);
		}

		String wxOpenId = getWeiXinOpenId(request);
		WeiXinUser weiXinUser = weiXinUserService.findOrCreateByWxOpenId(wxOpenId, false);
		SysUser sysUser = weiXinUser.getSysUser();

		String errorMessage = null;
		if (sysUser != null) {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
			        sysUser.getLoginName().toLowerCase(), sysUser.getPassword());
			try {
				Subject subject = getSubject(request, response);
				subject.login(usernamePasswordToken);
				if (subject.isAuthenticated() && sysLogService != null) {
					sysLogService.saveSysLog(getClass().getSimpleName() + ".login", sysUser.getLoginName(), "微信端登录",
					        ServletUtils.getRemortIP((HttpServletRequest) request));
				}
				return true;
			} catch (AuthenticationException e) {
				if (e instanceof AccountException) {
					errorMessage = e.getMessage();
				} else {
					errorMessage = "其它登录错误！";
				}
				log.error(e.getMessage());
			}
		}
		request.setAttribute(Constants.WEIXIN_AUTHC_ERROR_MESSAGE, errorMessage);
		saveRequestAndRedirectToLogin(request, response);
		return false;
	}

}
