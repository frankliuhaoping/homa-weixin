package cn.cnyirui.homaweixin.service.backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.backend.EmployeeWageDao;
import cn.cnyirui.homaweixin.model.po.EmployeeWage;

@Service
public class EmployeeWageService extends BaseService<EmployeeWage> {

	@Resource
	private EmployeeWageDao employeeWageDao;

	@Resource
	private OrganizationService organizationService;

	@Override
	public BaseDao<EmployeeWage> getBaseDao() {
		return employeeWageDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<EmployeeWage> findAll(Searchable searchable) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			StringBuilder ql = new StringBuilder("select w.* from employee_wage w, employee e");
			SearchFilterHelper.addOrganizationLimitSearchFilter(searchable, ql, "e");
			ql.append(" and w.employeeId = e.id");
			searchable.addSort(
			        new Sort(new Sort.Order(Direction.ASC, "t.parentNames"),
			                new Sort.Order(Direction.ASC, "e.name")));
			return (Page<EmployeeWage>) employeeWageDao.findAll(ql.toString(), searchable, true, EmployeeWage.class);
		} else {
			return super.findAll(searchable);
		}
	}

	public List<ObjectNode> findEmployeeWageByYear(Integer year, String employeeId) {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "wageMonth"),
		        new Sort.Order(Direction.DESC, "wageVersion"));
		Searchable searchable = Searchable.newSearchable().addSearchFilter("wageYear", SearchOperator.eq, year)
		        .addSearchFilter("employee.id", SearchOperator.eq, employeeId).addSort(sort);
		Page<EmployeeWage> pageContent = employeeWageDao.findAll(searchable);

		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		for (EmployeeWage s : pageContent.getContent()) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("id", s.getId());
			SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
			objectNode.put("createdTime", ss.format(s.getCreatedTime()));
			objectNode.put("isAbnormal", s.getIsAbnormal());
			objectNode.put("wageVersion", s.getWageVersion());
			objectNode.put("wageMonth", s.getWageMonth());
			objectNode.put("isRead", s.getIsRead());
			objectNodeList.add(objectNode);
		}

		return objectNodeList;
	}
}
