package cn.cnyirui.homaweixin.service.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.EntityUtils;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;
import cn.cnyirui.homaweixin.dao.backend.OrganizationDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Organization;

/**
 * 组织架构
 * 
 * @author pengzhihua
 *
 */
@Service
public class OrganizationService extends BaseService<Organization> {
	@Resource
	private OrganizationDao organizationDao;

	@Resource
	private EmployeeDao employeeDao;

	@Override
	public BaseDao<Organization> getBaseDao() {
		return organizationDao;
	}

	/**
	 * 当前用户能查看数据的组织架构的顶层组织架构
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getRootCanViewOrganizationList() {
		String sql = "select min(t.depth) from organization_tree_temp t";
		Searchable searchable = Searchable.newSearchable().addSearchFilter("t.callId", SearchOperator.eq,
		        SessionUtils.getSession().getId().toString());
		Long minDepth = NumberUtils.toLong(String.valueOf(organizationDao.getScalarValue(sql, searchable, true)));

		searchable.addSearchFilter("o.depth", SearchOperator.eq, minDepth);
		sql = "select o.* from organization o, organization_tree_temp t"
		        + " where o.id = t.organizationId order by o.name";
		return (List<Organization>) organizationDao.findAll(sql, searchable, true, Organization.class).getContent();
	}

	/**
	 * 树形json数据
	 * 
	 * @param parentId
	 *            父ID
	 * @param recursively
	 *            true 递归获取所有数据
	 * @param recursively
	 *            true treeGrid的数据
	 * @return
	 */
	public List<ObjectNode> getOrganizationTreeJson(String parentId, boolean recursively, boolean forTreeGrid,
	        HttpServletRequest request) {
		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		List<Organization> organizationList = null;
		if (parentId == null) {
			if (SecurityUtils.getSubject().isAuthenticated() && !CurrentUserUtils.getSysUser().getIsAdmin()) {
				organizationList = getRootCanViewOrganizationList();
			} else {
				organizationList = organizationDao.findRootOrganization();
			}
		} else {
			organizationList = findSubOrganization(parentId);
		}
		for (Organization organization : organizationList) {
			ObjectNode objectNode = null;
			if (forTreeGrid) {
				objectNode = EntityUtils.toObjectNode(organization,
				        EntityUtils.defaultToObjectNodeEntityConfig(request));
			} else {
				objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNode.put("id", organization.getId());
				objectNode.put("text", organization.getName());

			}
			objectNode.put("state",
			        (recursively || organization.getSubOrganizationList().isEmpty()) ? "open" : "closed");
			objectNodeList.add(objectNode);
			if (recursively) {
				objectNode.putArray("children")
				        .addAll(getOrganizationTreeJson(organization.getId(), recursively, forTreeGrid, request));
			}
		}
		return objectNodeList;
	}

	/**
	 * 查询parentId组织架构下的所有子组织架构，数据存储在organization_tree_temp表，通过查询callId获取
	 * 
	 * @param parentId
	 * @return 返回callId
	 */
	@Transactional
	public String createSubOrganizationList(String parentId) {
		List<String> parentIdList = new ArrayList<String>();
		parentIdList.add(parentId);
		return createSubOrganizationList(parentIdList, null);
	}

	/**
	 * 查询parentIdList组织架构下的所有子组织架构，数据存储在organization_tree_temp表，通过查询callId获取
	 * 
	 * @param parentIdList
	 * @param callId
	 * @return 返回callId
	 */
	@Transactional
	public String createSubOrganizationList(List<String> parentIdList, String callId) {
		if (!parentIdList.isEmpty()) {
			if (StringUtils.isBlank(callId)) {
				callId = UUID.randomUUID().toString();
			}
			EntityManager entityManager = getBaseDao().getEntityManager();
			entityManager.createNativeQuery("delete from organization_tree_temp where callId = :callId")
			        .setParameter("callId", callId).executeUpdate();
			Query query = entityManager.createNativeQuery("CALL createSubOrganizationList(:parentId, :callId)");
			for (String parentId : parentIdList) {
				query.setParameter("parentId", parentId);
				query.setParameter("callId", callId);
				query.executeUpdate();
			}
			return callId;
		}
		return "";
	}

	/**
	 * 所有根节点的子组织架构
	 * 
	 * @return
	 */
	@Transactional
	public String createRootSubOrganizationList(String callId) {
		List<Organization> organizationList = findRootOrganization();
		List<String> parentIdList = new ArrayList<String>();
		for (Organization organization : organizationList) {
			parentIdList.add(organization.getId());
		}
		return createSubOrganizationList(parentIdList, callId);
	}

	public List<Organization> findRootOrganization() {
		return organizationDao.findRootOrganization();
	}

	public List<Organization> findSubOrganization(String parentId) {
		return organizationDao.findSubOrganization(parentId);
	}

	public List<ObjectNode> findSubOrganization() {
		List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
		// Organization o = CurrentUserUtils.getOrganization(false);
		// if (o == null) {
		// return objectNodes;
		// }
		// List<Organization> subOrganizations =
		// organizationDao.findSubOrganization(o.getId());
		String userid = CurrentUserUtils.getEmployeeId();
		List<Organization> subOrganizations = organizationDao.findSubOrganization2(userid);
		for (Organization subO : subOrganizations) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("id", subO.getId());
			objectNode.put("name", subO.getName());
			objectNodes.add(objectNode);
		}
		return objectNodes;
	}

	public List<ObjectNode> findSubOrg(String id) {
		List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
		List<Organization> subOrg = organizationDao.findSubOrganization(id);
		for (Organization subO : subOrg) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("id", subO.getId());
			objectNode.put("name", subO.getName());
			objectNodes.add(objectNode);
		}
		return objectNodes;
	}

	/**
	 * 保存允许查看数据的人员
	 * 
	 * @param organization
	 * @param employeeIdList
	 */
	public Organization saveOrganizationPermissionForm(Organization organization, List<String> employeeIdList,
	        String directorId) {
		List<Employee> employeeList = employeeDao.findAll(employeeIdList);
		organization.setAllowViewEmployeeList(employeeList);
		if (StringUtils.isNotEmpty(directorId)) {
			organization.setDirector(employeeDao.findOne(directorId));
		}
		return organizationDao.save(organization);
	}

	public void setParents(Organization organization) {
		if (organization != null) {
			Organization parent = organization.getParent();
			String parentIds = ",";
			String parentNames = "\\";
			Integer depth = null;
			if (parent != null) {
				depth = parent.getDepth();
				parentIds = parent.getParentIds();
				parentNames = parent.getParentNames();
			} else {
				depth = -1;
			}
			organization.setDepth(depth + 1);
			parentIds = StringUtils.appendIfMissing(parentIds, ",");
			parentNames = StringUtils.appendIfMissing(parentNames, "\\");
			organization.setParentIds(parentIds + organization.getId() + ",");
			organization.setParentNames(parentNames + organization.getName() + "\\");
		}
	}

	@Override
	public Organization doSavePageModel(String action, Organization pageModel, Organization toSavePageModel,
	        HttpServletRequest request) {
		Organization organization = super.doSavePageModel(action, pageModel, toSavePageModel, request);
		setParents(organization);
		organizationDao.save(organization);
		return organization;
	}

	/**
	 * 根据名称查询一个组织架构
	 * @param name
	 * @return
	 */
	public Organization findOneByName(String name){
		EntityManager em = organizationDao.getEntityManager();
		String sql = "from Organization where name = ?";
		Query query = em.createQuery(sql);
		query.setParameter(1, name);
		List<Organization> allOrg = query.getResultList();
		if(allOrg != null && !allOrg.isEmpty()){
			return allOrg.get(0);
		}
		return null;
	}
}
