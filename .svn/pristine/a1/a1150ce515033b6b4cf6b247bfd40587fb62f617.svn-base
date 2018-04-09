package cn.cnyirui.framework.controller.rbac;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.filter.SearchFilter;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.model.po.rbac.SysLogEntity;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.rbac.SysLogService;

@Controller
@RequestMapping("/rbac/sysLog")
public class SysLogController extends BaseCRUDController<SysLogEntity> {

    @Resource
    private SysLogService sysLogService;

    @Override
    protected BaseService<SysLogEntity> getBaseService() {
        return sysLogService;
    }

    @Override
    protected void handleSearchFilter(Searchable searchable) {
        super.handleSearchFilter(searchable);
        SearchFilterHelper.wrapperDateTimeSearchFilter(searchable.getSearchFilters(), "createdTime");

        SearchFilter searchFilter = searchable.findOneSearchFilter("createdBy.realName", SearchOperator.like);
        if (searchFilter != null) {
            String realName = searchFilter.asCondition().getValue().toString();
            searchable.removeSearchFilter("createdBy.realName", SearchOperator.like);
            searchable.or(SearchFilterHelper.newCondition("createdBy.realName", SearchOperator.like, realName),
                    SearchFilterHelper.newCondition("createdBy.loginName", SearchOperator.like, realName));
        }

    }
}
