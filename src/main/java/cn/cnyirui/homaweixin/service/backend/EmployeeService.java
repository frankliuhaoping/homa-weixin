package cn.cnyirui.homaweixin.service.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.rbac.SysRoleDao;
import cn.cnyirui.framework.dao.rbac.SysUserDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.search.filter.SearchFilterHelper;
import cn.cnyirui.framework.model.po.rbac.SysRole;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;
import cn.cnyirui.homaweixin.dao.backend.OrganizationDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Organization;

@Service
public class EmployeeService extends BaseService<Employee> {
	@Resource
	private EmployeeDao employeeDao;

	@Resource
	private SysUserDao sysUserDao;

	@Resource
	private SysRoleDao sysRoleDao;

	@Resource
	private OrganizationDao organizationDao;

	@Override
	public BaseDao<Employee> getBaseDao() {
		return employeeDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Employee> findAll(Searchable searchable) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			StringBuilder ql = new StringBuilder("select e.* from employee e");
			SearchFilterHelper.addOrganizationLimitSearchFilter(searchable, ql, "e");
			return (Page<Employee>) employeeDao.findAll(ql.toString(), searchable, true, Employee.class);
		} else {
			return super.findAll(searchable);
		}
	}

	/**
	 * 按内销系统的ID查找
	 * 
	 * @param imsId
	 * @return
	 */
	public Employee findByIMSIdAndType(Long imsId, Integer imsType) {
		return employeeDao.findByIMSIdAndType(imsId, imsType);
	}

	public List<Employee> findByName(String name) {
		Searchable searchable = Searchable.newSearchable()
		        .addSearchFilter("name", SearchOperator.eq, name);
		return employeeDao.findAll(searchable).getContent();
	}

	/**
	 * 按员工工号查找
	 * 
	 * @param code
	 * @return
	 */
	public Employee findByCode(String code) {
		Searchable searchable = Searchable.newSearchable().addSearchFilter("code", SearchOperator.eq, code);
		return employeeDao.findOne(searchable);
	}

	/**
	 * 按员工手机号查找
	 * 
	 * @param code
	 * @return
	 */
	public Employee findByTel(String tel) {
		Searchable searchable = Searchable.newSearchable().addSearchFilter("MobileNo", SearchOperator.eq, tel);
		return employeeDao.findOne(searchable);
	}

	public List<ObjectNode> getEmployeeByOrganization(String id) {
		List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
		// Organization o = CurrentUserUtils.getOrganization(false);
		String id2 = CurrentUserUtils.getEmployeeId();
		Organization o = organizationDao.findOne(id);
		if (o == null) {
			return objectNodes;
		}
		Searchable searchable = Searchable.newSearchable().addSearchFilter("organization.id", SearchOperator.eq,
		        o.getId());
		searchable.addSearchFilter("id", SearchOperator.ne, id2);
		searchable.addSearchFilter("deleted", SearchOperator.eq, "0");
		List<Employee> list = employeeDao.findAll(searchable).getContent();
		for (Employee employee : list) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("id", employee.getId());
			objectNode.put("name", employee.getName());
			objectNodes.add(objectNode);
		}
		return objectNodes;
	}

	@Override
	public ObjectNode entityToObjectNode(Employee entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		if (entity.getSex() == null || entity.getSex() == 2) {
			objectNode.put("sex", "保密");
		} else if (entity.getSex() == 0) {
			objectNode.put("sex", "男");
		} else if (entity.getSex() == 1) {
			objectNode.put("sex", "女");
		}

		if (entity.getSalerType() == null || entity.getSalerType() == 0) {
			objectNode.put("salerType", "专职");
		} else if (entity.getSalerType() == 1) {
			objectNode.put("salerType", "兼职");
		}

		objectNode.put("sysRoleType", entity.getSysRoleTypeText());
		objectNode.put("isIMSData", entity.getIsIMSData());
		return objectNode;
	}

	@Override
	public EntityConfig getCopyEntityConfig() {
		EntityConfig entityConfig = super.getCopyEntityConfig();
		entityConfig.addExcludePropertyName("sysRoleType");
		return entityConfig;
	}

	/**
	 * 添加员工的默认登录帐号，角色
	 * 
	 * @param employee
	 */
	@Override
	public Employee doSavePageModel(String action, Employee pageModel, Employee toSavePageModel,
	        HttpServletRequest request) {

		setEmployeeSysUser(toSavePageModel, pageModel.getSysRoleType());
		sysUserDao.save(toSavePageModel.getSysUser());
		return super.doSavePageModel(action, pageModel, toSavePageModel, request);
	}

	/**
	 * 添加员工的默认登录帐号，角色
	 * 
	 * @param employee
	 */
	public void setEmployeeSysUser(Employee employee, String newSysRoleType) {
		// 登录帐号
		// 有编号的用编号作登录名，没编号的用手机号码
		String loginName = null;
		if (StringUtils.isNotBlank(employee.getCode())) {
			loginName = employee.getCode();
		} else {
			loginName = employee.getMobileNo();
		}
		SysUser sysUser = null;
		if (!employee.isNew()) {
			sysUser = employee.getSysUser();
		}
		if (sysUser == null) {
			sysUser = new SysUser();
			employee.setSysUser(sysUser);
		}
		if (loginName != null) {
			sysUser.setLoginName(loginName);
			sysUser.setPassword(DigestUtils.md5Hex(loginName.toLowerCase()));
		}
		sysUser.setRealName(employee.getName());

		// 原来角色
		String sysRoleType = employee.getSysRoleType();
		if (!StringUtils.equalsIgnoreCase(sysRoleType, newSysRoleType)) {
			employee.setSysRoleType(newSysRoleType);
			List<SysRole> sysRoleList = sysRoleDao.findByCode(sysRoleType);
			// 删除原来
			for (SysRole sysRole : sysRoleList) {
				sysUser.removeSysRole(sysRole);
			}

			// 添加新的
			sysRoleList = sysRoleDao.findByCode(newSysRoleType);
			for (SysRole sysRole : sysRoleList) {
				sysUser.addSysRole(sysRole);
			}

		}
	}

}
