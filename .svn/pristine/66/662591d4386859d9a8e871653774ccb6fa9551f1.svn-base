package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.controller.bind.annotation.PageableDefaults;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.EmployeeWage;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.backend.EmployeeWageService;

@Controller
@RequestMapping("/backend/employeeWage/")
public class EmployeeWageController extends BaseCRUDController<EmployeeWage> {

	@Resource
	private EmployeeWageService employeeWageService;

	@Resource
	private EmployeeService employeeService;

	@Override
	protected BaseService<EmployeeWage> getBaseService() {
		return employeeWageService;
	}

	@PageableDefaults(sort = {})
	@Override
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.jsonList(searchable, request);
	}

}
