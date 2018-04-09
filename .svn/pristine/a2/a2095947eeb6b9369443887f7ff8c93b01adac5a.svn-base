package cn.cnyirui.homaweixin.controller.weixin.workbench;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cnyirui.framework.controller.BaseLoginController;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.service.weixin.WeiXinUserService;
import cn.cnyirui.framework.utils.Constants;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;

@Controller
@RequestMapping("/weixin/workbench/login")
public class WxLoginController extends BaseLoginController {

	@Resource
	private WeiXinUserService weiXinUserService;
	@Resource
	private EmployeeService employeeService;

	@Override
	public Boolean getCaptchaEnabled() {
		return false;
	}

	@Override
	protected String getLoginView() {
		return "/weixin/workbench/login";
	}

	@Override
	protected String getLoginUrl() {
		return "/weixin/workbench/login/showForm";
	}

	@Override
	protected String getIndexUrl() {
		return "/weixin/workbench/index";
	}

	@Override
	public String logout() {
		WeiXinUser weiXinUser = CurrentUserUtils.getWeiXinUser(false);
		if (weiXinUser != null) {
			weiXinUser.setSysUser(null);
			weiXinUserService.save(weiXinUser);
		}
		return super.logout();
	}

	/**
	 * 微信openId与sysUser绑定
	 */
	@Override
	protected void onLoginSuccess() {
		String wxOpenId = SessionUtils.getAttribute(Constants.WEIXIN_OPENID_SESSION_KEY);//获取openID session
		WeiXinUser weiXinUser = weiXinUserService.findOrCreateByWxOpenId(wxOpenId, true);//查找微信用户 没有就从微信获取
		SysUser sysUser = CurrentUserUtils.getSysUser();//当前登录用户
		Employee employee = sysUser.getEmployee();//账号对应的员工
		if (employee != null) {
			employee.setHeadImgUrl(weiXinUser.getHeadImgUrl());//设置用户头像
			employeeService.save(employee);//保存
		}
		weiXinUser.setSysUser(sysUser);//绑定对应PC后台账号
		weiXinUserService.save(weiXinUser);//保存
	}

}
