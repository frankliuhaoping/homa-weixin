package cn.cnyirui.framework.extension.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.cnyirui.framework.extension.shiro.CaptchaUsernamePasswordToken;
import cn.cnyirui.framework.utils.ServletUtils;

/**
 * 扩展FormAuthenticationFilter
 * 实现<br/>
 * 1、ajax请求中登录失败，响应401，<br/>
 * 2、记住我功能(cookie中有用户名)
 * 
 * @author pengzhihua
 *
 */
public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {

	/**
	 * 验证码参数名
	 */
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;

	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String captcha = getCaptcha(request);
		if (StringUtils.hasLength(captcha)) {
			String username = getUsername(request);
			String password = getPassword(request);
			String host = getHost(request);
			boolean rememberMe = isRememberMe(request);
			return new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
		}
		return super.createToken(request, response);
	}

	/**
	 * ajax请求中登录失效
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if (isLoginRequest(request, response)) {
			if (isLoginSubmission(request, response)) {
				return executeLogin(request, response);
			} else {
				return true;
			}
		} else {
			///
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			if (!ServletUtils.isAjax(httpServletRequest)) {// 不是ajax请求
				saveRequestAndRedirectToLogin(request, response);
			} else {
				saveRequest(request);
				httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			return false;
		}
	}

	/**
	 * 从UserFilter复制过来的，实现rememberMe
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginRequest(request, response)) {
			return true;
		} else {
			Subject subject = getSubject(request, response);
			// If principal is not null, then the user is known and should be
			// allowed access.
			return subject.getPrincipal() != null;
		}
	}

}
