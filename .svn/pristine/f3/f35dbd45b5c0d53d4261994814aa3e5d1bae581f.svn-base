package cn.cnyirui.framework.controller;

import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.service.rbac.SysUserService;
import cn.cnyirui.framework.utils.Constants;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.service.backend.SalesOrderService;
import cn.cnyirui.homaweixin.service.weixin.ChatSessionMemberService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.PropertiesUtils;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
public class IndexController {

	public static String webSocketPushCenterUrl = PropertiesUtils.getValueByKeyFromWebPushConfig("PUSH_CENTER_URL")
	        + "/iker_web_socket_push_center";

	@Resource
	private SalesOrderService salesOrderService;
	@Resource
	private ChatSessionMemberService chatSessionMemberService;
	@Resource
	private SysUserService sysUserService;

	/**
	 * 权限框架首页
	 * 
	 * @return
	 */
	@RequestMapping("/rbac/index")
	public ModelAndView rbacIndex() {
		return new ModelAndView("/rbac/index");
	}

	/**
	 * 后台首页
	 * 
	 * @return
	 */
	@RequestMapping("/backend/index")
	public ModelAndView backendIndex() {
		return new ModelAndView("/backend/index");
	}

	/**
	 * 微信工作台首页
	 * 
	 * @return
	 */
	@RequestMapping("/weixin/workbench/index")
	public ModelAndView weiXinWorkbenchIndex() {
		if (SessionUtils.getAttribute(Constants.WEIXIN_OPENID_SESSION_KEY) == null) {
			return new ModelAndView("redirect:/weixin/workbench/login/logout");
		}
		Calendar date = Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		Employee employee = CurrentUserUtils.getEmployee(false);
		Double countMonth = salesOrderService.countByEmployee(employee.getId(), year, month);
		Double countYear = salesOrderService.countByEmployee(employee.getId(), year, null);
		Integer unReadNum = chatSessionMemberService.sumUnReadNum(employee.getId());
		if (unReadNum == null) {
			unReadNum = 0;
		}

		ModelAndView m = new ModelAndView("/weixin/workbench/index");
		m.addObject("employee", CurrentUserUtils.getEmployee(true));
		m.addObject("weiXinUser", CurrentUserUtils.getWeiXinUser(true));
		m.addObject("menuList", CurrentUserUtils.getPermissionList());
		m.addObject("countMonth", countMonth);
		m.addObject("countYear", countYear);
		m.addObject("unReadNum", unReadNum);
		m.addObject("webSocketPushCenterUrl", webSocketPushCenterUrl);
		m.addObject("isDgy", SysRoleType.SALER.getValue().equalsIgnoreCase(employee.getSysRoleType()));
		return m;
	}

	@RequestMapping("/weixin/workbench/changePassword")
	@ResponseBody
	public JsonResponse changePassword(String oldWord, String newWord) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		jsonResponse.setMsg("修改成功");

		try {

			if (StringUtils.isEmpty(oldWord) || StringUtils.isEmpty(newWord)) {
				jsonResponse.setMsg("新密码不能为空");
				return jsonResponse;
			}

			SysUser sysUser = CurrentUserUtils.getSysUser(false);
			String encodePassword = DigestUtils.md5Hex(oldWord.toLowerCase());

			if (!sysUser.getPassword().equals(encodePassword)) {
				jsonResponse.setMsg("旧密码不匹配");
				return jsonResponse;
			}

			if (oldWord.toLowerCase().equals(newWord.toLowerCase())) {
				jsonResponse.setMsg("新密码和旧密码不能相同");
				return jsonResponse;
			}
			encodePassword = DigestUtils.md5Hex(newWord.toLowerCase());
			sysUser.setPassword(encodePassword);
			sysUserService.save(sysUser);
			CurrentUserUtils.setSysUser(sysUser);
			jsonResponse.setMsg("修改密码成功");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setMsg("服务器异常");
		}
		return jsonResponse;
	}

}
