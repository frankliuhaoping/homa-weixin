package cn.cnyirui.homaweixin.service.weixin;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;
import cn.cnyirui.homaweixin.dao.backend.OrganizationDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.po.OrganizationTreeTemp;

@Service
public class OrganizationTreeTempService extends BaseService {

	@Resource
	private EmployeeDao baseDao;
	
	@Override
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public Page<OrganizationTreeTemp> getOrganizationTreeTemp(String callId, Searchable searchable) {

		String sql = "select * from organization_tree_temp o where o.callId='" + callId
		        + "' order by o.parentnames";
		// Page<OrganizationTreeTemp> page1 = (Page<OrganizationTreeTemp>)
		// baseDao.findAll(sql, null, true, OrganizationTreeTemp.class);
		Page<OrganizationTreeTemp> page = (Page<OrganizationTreeTemp>) baseDao.findAll(sql, searchable, true,
		        OrganizationTreeTemp.class);
		return page;

	}

	/**
	 * 当前用户能查看数据的组织架构的顶层组织架构,parentId为空时查询最顶级架构
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrganizationTreeTemp> getCanViewOrganizationList(String parentId) {
		String callId = SessionUtils.getSession().getId().toString();
		Searchable searchable = Searchable.newSearchable().addSearchFilter("t.callId", SearchOperator.eq,
				callId);
		String sql = "select t.* from organization_tree_temp t left join organization o "
				+ "on t.organizationid = o.id left join organization_tree_temp tt "
				+ " on o.parentid = tt.organizationid and tt.callid = '"+callId+"'"
				+ " where (o.parentid is null or tt.id is null) order by t.depth,t.organizationname ";
		
		System.out.println(SessionUtils.getSession().getId().toString());
		if(StringUtils.isNotBlank(parentId)){
			searchable.addSearchFilter("o.parentid", SearchOperator.eq, parentId);
			sql = "select t.* from organization_tree_temp t left join organization o "
					+ "on t.organizationid = o.id order by t.depth,t.organizationname ";
		}
		List<OrganizationTreeTemp> treeTemp =  (List<OrganizationTreeTemp>) baseDao.findAll(sql, searchable, true, OrganizationTreeTemp.class).getContent();
		return treeTemp;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizationTreeTemp> searchCanViewOrganizationList(String keyword) {
		Searchable searchable = Searchable.newSearchable().addSearchFilter("t.callId", SearchOperator.eq,
		        SessionUtils.getSession().getId().toString());
		if(StringUtils.isNotBlank(keyword)){
			searchable.addSearchFilter("t.organizationname", SearchOperator.like, keyword);
		}
		String sql = "select t.* from organization_tree_temp t ";
		List<OrganizationTreeTemp> treeTemp =  (List<OrganizationTreeTemp>) baseDao.findAll(sql, searchable, true, OrganizationTreeTemp.class).getContent();
		return treeTemp;
	}
	
	
	public List<Employee> findEmployeeByOId(String organId) {
		return baseDao.findEmployeeByOId(organId);

	}

	/**
	 * 部门统计
	 * 
	 * @param callId
	 * @return
	 */
	public Page<HashMap<String, Object>> getCountOfDepartment(String callId, String oid, String year, String month) {
		String sql = "select * from (SELECT to_char(d.createdTime, 'yyyy') as year ,to_char(d.createdTime， 'mm') as month ,to_char(d.createdTime, 'dd') as day ,sum(d.QTY*d.PRICE) as salesMoney,sum(d.qty) as nums from organization_tree_temp o, sales_order s ,sales_order_detail d"
		        +
		        " where o.callId='" + callId + "' " +
		        " and o.parentIds like('%" + oid + "%') " +
		        " and o.organizationId = s.organizationId " +
		        " and s.id = d.salesOrderId " +
		        " and to_char(d.createdTime, 'yyyy')=" + year +
		        " and to_char(d.createdTime, 'mm')=" + month +
		        " group by to_char(d.createdTime, 'yyyy')," +
		        " to_char(d.createdTime, 'mm')," +
		        " to_char(d.createdTime, 'dd')) order by day";
		Page<HashMap<String, Object>> page = (Page<HashMap<String, Object>>) baseDao.findAll(sql, null, true,
		        HashMap.class);
		return page;

	}

}
