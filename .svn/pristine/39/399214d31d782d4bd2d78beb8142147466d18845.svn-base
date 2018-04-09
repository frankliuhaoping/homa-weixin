package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.SalesOrder;
import cn.cnyirui.homaweixin.service.backend.SalesOrderService;

@Controller
@RequestMapping("/backend/salesOrderDb")
public class SalesOrderDbController extends BaseCRUDController<SalesOrder> {

	@Resource
	private SalesOrderService salesOrderService;

	@Override
	protected BaseService<SalesOrder> getBaseService() {
		return salesOrderService;
	}

	@Override
	public ObjectNode doJsonList(Searchable searchable, javax.servlet.http.HttpServletRequest request) {
		return salesOrderService.findOrdersDb(searchable, request);
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		super.handleSearchFilter(searchable);
	}

}
