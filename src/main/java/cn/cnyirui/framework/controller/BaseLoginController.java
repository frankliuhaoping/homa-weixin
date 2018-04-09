package cn.cnyirui.framework.controller;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.extension.shiro.CaptchaUsernamePasswordToken;
import cn.cnyirui.framework.extension.shiro.IncorrectCaptchaException;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.rbac.SysLogService;
import cn.cnyirui.framework.service.rbac.SysUserService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.ServletUtils;
import cn.cnyirui.homaweixin.controller.weixin.workbench.WxLoginController;

public abstract class BaseLoginController extends BaseController<SysUser> {

	@Resource
	private SysUserService sysUserService;

	@Resource
	private SysLogService sysLogService;

	@Value("${shiro.captcha.enabled}")
	private Boolean captchaEnabled;

	abstract protected String getLoginView();

	abstract protected String getLoginUrl();

	abstract protected String getIndexUrl();

	abstract protected void onLoginSuccess();

	@Override
	protected BaseService<SysUser> getBaseService() {
		return sysUserService;
	}

	/**
	 * captchaEnabled
	 * 
	 * @return captchaEnabled captchaEnabled
	 */
	public Boolean getCaptchaEnabled() {
		return captchaEnabled;
	}

	/**
	 * 显示登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/showForm")
	public ModelAndView showForm() {
		ModelAndView m = new ModelAndView(getLoginView());
		Subject subject = SecurityUtils.getSubject();
		if (subject != null && subject.getPrincipal() != null) {
			String redirectUrl = getRedirectUrl();
			if (redirectUrl.indexOf(getRequest().getContextPath()) != -1) {
				redirectUrl = StringUtils.remove(redirectUrl, getRequest().getContextPath());
			}
			m.setViewName("redirect:" + redirectUrl);
		} else {
			m.addObject("captchaEnabled", getCaptchaEnabled());
		}

		return m;
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @param captcha
	 * @param rememberMe
	 * @return
	 */
	@RequestMapping("/tologin")
	@ResponseBody
	public JsonResult login(String username, String password, String captcha, boolean rememberMe) {
		if (StringUtils.isEmpty(username)) {
			return JsonResult.error("登录名不能为空！");
		}
		if (StringUtils.isEmpty(password)) {
			return JsonResult.error("密码不能为空！");
		}

		String loginDialog = ServletRequestUtils.getStringParameter(getRequest(), "loginDialog", null);
		Subject currentUser = SecurityUtils.getSubject();
		String encodePassword = DigestUtils.md5Hex(password.toLowerCase());//跳过登录 

		UsernamePasswordToken token = null;
		if (loginDialog == null && getCaptchaEnabled()) {
			token = new CaptchaUsernamePasswordToken(username.toLowerCase(), encodePassword, rememberMe,
			        ServletUtils.getRemortIP(getRequest()), captcha);
		} else {
			token = new UsernamePasswordToken(username.toLowerCase(), encodePassword, rememberMe,
			        ServletUtils.getRemortIP(getRequest()));
		}
		try {
			currentUser.login(token);
		} catch (UnknownAccountException uae) {
			return new JsonResult("no", uae.getMessage());
		} catch (IncorrectCredentialsException ice) {
			return new JsonResult("no", "密码错误！");
		} catch (LockedAccountException lae) {
			return new JsonResult("no", lae.getMessage());
		} catch (ExcessiveAttemptsException eae) {
			return new JsonResult("no", "多次输入密码错误！");
		} catch (IncorrectCaptchaException e) {
			return new JsonResult("no", "验证码错误！");
		} catch (AccountException ace) {
			ace.printStackTrace();
			return new JsonResult("no", ace.getMessage());
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			return new JsonResult("no", "其它登录错误！\r\n" + ExceptionUtils.getRootCause(ae).getMessage());
		}
		// 验证是否成功登录的方法
		if (currentUser.isAuthenticated()) {
			CurrentUserUtils.setSysUser(null);
			CurrentUserUtils.initPermission();
			String description = (this instanceof WxLoginController) ? "微信端登录" : "PC端登录";
			sysLogService.saveSysLog(getClass().getSimpleName() + ".login", username, description,
			        ServletUtils.getRemortIP(getRequest()));
			onLoginSuccess();
			return JsonResult.success(getRedirectUrl());
		}
		return JsonResult.error("登录时发生错误 ，请与管理员联系！");
	}

	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			CurrentUserUtils.setSysUser(null);//设置session为空
			subject.logout();
		}
		return "redirect:" + getLoginUrl();
	}

	private String getRedirectUrl() {
		SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(getRequest());
		if (savedRequest != null) {
			return savedRequest.getRequestUrl();
		}
		return getRequest().getContextPath() + getIndexUrl();
	}
}
