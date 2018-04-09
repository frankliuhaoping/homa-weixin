package cn.cnyirui.framework.service.rbac;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.rbac.SysRoleDao;
import cn.cnyirui.framework.dao.rbac.SysUserDao;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;

@Service
public class SysUserService extends BaseService<SysUser> {

	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	private EmployeeDao employeeDao;

	@Override
	public BaseDao<SysUser> getBaseDao() {
		return sysUserDao;
	}

	public SysUser findByLoginName(String loginName) {
		if (!StringUtils.isEmpty(loginName)) {
			return sysUserDao.findByLoginName(loginName);
		}
		return null;
	}

	/**
	 * 按登录名或工号或手机号码找
	 * 
	 * @param queryValue
	 * @return
	 */
	public SysUser findByLoginNameOrEmployeeCodeOrEmployeeMobile(String queryValue) {
		// 按登录名
		SysUser sysUser = sysUserDao.findByLoginName(queryValue);
		if (sysUser != null) {
			return sysUser;
		}
		// 按登录名
		sysUser = sysUserDao.findByOldLoginName(queryValue);
		if (sysUser != null) {
			return sysUser;
		}

		Pageable pageable = new PageRequest(0, 1);
		// 按工号
		List<SysUser> sysUserList = sysUserDao.findByEmployeeCode(queryValue, pageable);
		if (!sysUserList.isEmpty()) {
			return sysUserList.get(0);
		}
		// 按手机号
		sysUserList = sysUserDao.findByEmployeeMobileNo(queryValue, pageable);
		if (!sysUserList.isEmpty()) {
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public ObjectNode entityToObjectNode(SysUser sysUser, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(sysUser, config);
		objectNode.put("isAdmin", (sysUser.getIsAdmin() == null || !sysUser.getIsAdmin()) ? "否" : "是");
		objectNode.put("isDisabled", (sysUser.getIsDisabled() == null || !sysUser.getIsDisabled()) ? "否" : "是");
		return objectNode;
	}

	@Override
	public EntityConfig getCopyEntityConfig() {
		EntityConfig entityConfig = super.getCopyEntityConfig();
		entityConfig.addExcludePropertyName("password");
		return entityConfig;
	}

	@Override
	public SysUser doSavePageModel(String action, SysUser pageModel, SysUser toSavePageModel,
	        HttpServletRequest request) {
		if (!StringUtils.equalsIgnoreCase(toSavePageModel.getPassword(), pageModel.getPassword())) {
			toSavePageModel.setPassword(DigestUtils.md5Hex(pageModel.getPassword()));
		}
		return super.doSavePageModel(action, pageModel, toSavePageModel, request);
	}

}
