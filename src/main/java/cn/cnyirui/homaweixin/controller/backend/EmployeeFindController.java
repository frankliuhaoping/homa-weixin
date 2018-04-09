package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;

@Controller
@RequestMapping("/backend/employeeFind")
public class EmployeeFindController extends BaseCRUDController<Employee> {

	@Resource
	private EmployeeService employeeService;

	@Override
	protected BaseService<Employee> getBaseService() {
		return employeeService;
	}

}
