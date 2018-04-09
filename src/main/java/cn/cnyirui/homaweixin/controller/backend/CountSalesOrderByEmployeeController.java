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
import cn.cnyirui.homaweixin.model.po.SalesOrder;
import cn.cnyirui.homaweixin.service.backend.SalesOrderService;

@Controller
@RequestMapping("/backend/countSalesOrderByEmployee")
public class CountSalesOrderByEmployeeController extends BaseCRUDController<SalesOrder> {

	@Resource
	private SalesOrderService salesOrderService;

	@Override
	protected BaseService<SalesOrder> getBaseService() {
		return salesOrderService;
	}

	@Override
	@PageableDefaults(sort = {})
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		return super.jsonList(searchable, request);
	}

	@Override
	public ObjectNode doJsonList(Searchable searchable, HttpServletRequest request) {
		return salesOrderService.countSalesOrderByEmployee(searchable, request);
	}

}
