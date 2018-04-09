package cn.cnyirui.framework.controller.rbac;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.extension.log.SysLog;
import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.model.po.rbac.SysRole;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.rbac.SysRoleService;

@Controller
@RequestMapping("/rbac/sysRole")
public class SysRoleController extends BaseCRUDController<SysRole> {

	@Resource
	private SysRoleService sysRoleService;

	@Override
	protected BaseService<SysRole> getBaseService() {
		return sysRoleService;
	}

	@Override
	public JsonResult doSave(String action, SysRole voModel, HttpServletRequest request) {
		SysRole sysRole = sysRoleService.findByName(voModel.getName());
		if (assertExists(action, voModel, sysRole)) {
			return JsonResult.error("名称：" + voModel.getName() + " 已经被占用，请更换一个！");
		}
		return super.doSave(action, voModel, request);
	}

	@Override
	protected void setCommonModel(ModelAndView m, String viewName) {
		// TODO Auto-generated method stub
		m.addObject("sysRoleTypes", SysRoleType.values());
		super.setCommonModel(m, viewName);
	}

	@RequestMapping("/showAuthorizationForm/{sysRoleId}")
	public ModelAndView showAuthorizationForm(@PathVariable("sysRoleId") SysRole sysRole) {
		ModelAndView m = new ModelAndView("/rbac/sysRole/authorization_form");
		m.addObject("sysRole", sysRole);
		return m;
	}

	@RequestMapping("/getAuthorizationJson/{sysRoleId}")
	@ResponseBody
	public ObjectNode getAuthorizationJson(@PathVariable("sysRoleId") SysRole sysRole) {
		return sysRoleService.getAuthorizationJson(sysRole);
	}

	@SysLog(description = "授权")
	@RequestMapping("/saveAuthorizationForm/{sysRoleId}")
	@ResponseBody
	public JsonResult saveAuthorizationForm(@PathVariable("sysRoleId") SysRole sysRole,
	        @RequestParam("sysPermissionIds") List<String> sysPermissionIdList) {
		try {
			sysRoleService.saveAuthorizationForm(sysRole, sysPermissionIdList);
			return JsonResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.error("授权失败，请重试！");
		}

	}
}
