package cn.cnyirui.framework.controller.rbac;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.controller.BaseLoginController;

@Controller
@RequestMapping("/rbac/login")
public class LoginController extends BaseLoginController {

	/**
	 * 显示登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/showDialog")
	public ModelAndView showDialog(@RequestParam("message") String message) {
		ModelAndView m = new ModelAndView("rbac/login_form");
		m.addObject("message", message);
		m.addObject("captchaEnabled", getCaptchaEnabled());
		return m;
	}

	@Override
	protected String getLoginView() {
		return "/rbac/login";
	}

	@Override
	protected String getLoginUrl() {
		return "/rbac/login/showForm";
	}

	@Override
	protected String getIndexUrl() {
		return "/rbac/index";
	}

	@Override
	protected void onLoginSuccess() {
		// TODO Auto-generated method stub

	}

}
