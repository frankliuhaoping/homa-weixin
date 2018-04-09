package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.Customer;
import cn.cnyirui.homaweixin.service.backend.CustomerService;

@Controller
@RequestMapping("/backend/customer")
public class CustomerController extends BaseCRUDController<Customer>{

	@Resource
	private CustomerService customerService;
	
	@Override
	protected BaseService<Customer> getBaseService() {
		return customerService;
	}

}
