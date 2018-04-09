package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.controller.bind.annotation.PageableDefaults;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.SignRecord;
import cn.cnyirui.homaweixin.service.backend.SignRecordService;

@Controller
@RequestMapping("/backend/signRecord")
public class SignRecordController extends BaseCRUDController<SignRecord> {

	@Resource
	private SignRecordService signRecordService;

	@Override
	protected BaseService<SignRecord> getBaseService() {
		return signRecordService;
	}

	@Override
	protected void setCommonModel(ModelAndView m, String viewName) {
		m.addObject("sysRoleTypes", SysRoleType.values());
		super.setCommonModel(m, viewName);
	}

	@PageableDefaults(sort = {})
	@Override
	public ObjectNode jsonList(Searchable searchable, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.jsonList(searchable, request);
	}

	@Override
	public ObjectNode doJsonList(Searchable searchable, HttpServletRequest request) {
		// TODO Auto-generated method stub
		ObjectNode objectNode = super.doJsonList(searchable, request);
		objectNode.putArray("footer").addAll(signRecordService.countAll(searchable));
		return objectNode;
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		SearchFilterHelper.wrapperDateTimeSearchFilter(searchable.getSearchFilters(), "signTime");
		super.handleSearchFilter(searchable);

	}

}
