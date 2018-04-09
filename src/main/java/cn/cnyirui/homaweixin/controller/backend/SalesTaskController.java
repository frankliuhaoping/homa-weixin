package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.po.SalesTask;
import cn.cnyirui.homaweixin.service.backend.SalesTaskService;

@Controller
@RequestMapping("/backend/salesTask")
public class SalesTaskController extends BaseCRUDController<SalesTask> {

	@Resource
	private SalesTaskService salesTaskService;

	@Override
	protected BaseService<SalesTask> getBaseService() {
		return salesTaskService;
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		SysUser sysUser = CurrentUserUtils.getSysUser();
		Organization o = CurrentUserUtils.getOrganization(false);
		if (o != null && o.getParent() != null) {
			searchable.addSearchFilter("createdBy.id", SearchOperator.eq, sysUser.getId());
		}
		super.handleSearchFilter(searchable);
	}

}
