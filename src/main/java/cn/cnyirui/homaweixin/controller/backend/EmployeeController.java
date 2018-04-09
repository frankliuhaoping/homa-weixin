package cn.cnyirui.homaweixin.controller.backend;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.rbac.SysUserService;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;

@Controller
@RequestMapping("/backend/employee")
public class EmployeeController extends BaseCRUDController<Employee> {

	@Resource
	private EmployeeService employeeService;

	@Resource
	private SysUserService sysUserService;

	@Override
	protected BaseService<Employee> getBaseService() {
		return employeeService;
	}

	@Override
	protected void setCommonModel(ModelAndView m, String viewName) {
		m.addObject("sysRoleTypes", SysRoleType.values());
		super.setCommonModel(m, viewName);
	}

	@RequestMapping("/getEmployeeByOrganization")
	@ResponseBody
	public List<ObjectNode> getEmployeeByOrganization(@RequestParam("id") String id) {
		return employeeService.getEmployeeByOrganization(id);
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		searchable.addSearchFilter("e.deleted", SearchOperator.eq, "0");
		super.handleSearchFilter(searchable);
	}

	@Override
	public JsonResult doSave(String action, Employee pageModel, HttpServletRequest request) {
		String loginName = null;
		if (StringUtils.isNotEmpty(pageModel.getCode())) {
			Employee findedEmployee = employeeService.findByCode(pageModel.getCode());
			if (assertExists(action, pageModel, findedEmployee)) {
				return JsonResult.error(String.format("工号：%s 已经存在！", pageModel.getCode()));
			}
			loginName = pageModel.getCode();
		} else if (StringUtils.isNotEmpty(pageModel.getMobileNo())) {
			Employee findedEmployee = employeeService.findByTel(pageModel.getMobileNo());
			if (assertExists(action, pageModel, findedEmployee)) {
				return JsonResult
				        .error(String.format("手机号：%s 已被 %s 占用！", pageModel.getMobileNo(), findedEmployee.getName()));
			}
			loginName = pageModel.getMobileNo();
		}
		if (StringUtils.isEmpty(loginName)) {
			return JsonResult.error("工号和手机号码不能同时为空！");
		}

		SysUser sysUser = sysUserService.findByLoginName(loginName);
		if (sysUser != null) {
			Employee findedEmployee = sysUser.getEmployee();
			if (findedEmployee == null || assertExists(action, pageModel, findedEmployee)) {
				return JsonResult.error("添加账号失败，后台账号已被占用！");
			}
		}
		return super.doSave(action, pageModel, request);
	}

	// 如果是IMS数据，则删除失败
	@Override
	@Transactional
	public JsonResult doDelete(String[] ids) {
		for (String id : ids) {
			Employee e = employeeService.findOne(id);
			if (e.getIsIMSData() == true) {
				return JsonResult.error(String.format("工号：%s 为IMS数据，删除失败，请重试！", e.getCode()));
			}
		}

		return super.doDelete(ids);
	}

}
