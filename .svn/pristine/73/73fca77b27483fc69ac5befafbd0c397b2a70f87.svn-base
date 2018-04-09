package cn.cnyirui.framework.service.rbac;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.rbac.SysMenuDao;
import cn.cnyirui.framework.dao.rbac.SysPermissionDao;
import cn.cnyirui.framework.dao.rbac.SysRoleDao;
import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.model.po.rbac.SysMenu;
import cn.cnyirui.framework.model.po.rbac.SysPermission;
import cn.cnyirui.framework.model.po.rbac.SysRole;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;

@Service
public class SysRoleService extends BaseService<SysRole> {

	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	private SysMenuDao sysMenuDao;
	@Resource
	private SysPermissionDao sysPermissionDao;

	@Override
	public BaseDao<SysRole> getBaseDao() {
		return sysRoleDao;
	}

	public SysRole findByName(String name) {
		return sysRoleDao.findByName(name);
	}

	@Override
	public ObjectNode entityToObjectNode(SysRole entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		if (StringUtils.isNotEmpty(entity.getCode())) {
			objectNode.put("code", SysRoleType.valueOf(entity.getCode()).getText());
		}
		return objectNode;
	}

	/**
	 * 角色授权的datagridtree json
	 * 
	 * @param sysRole
	 * @return
	 */
	public ObjectNode getAuthorizationJson(SysRole sysRole) {
		Sort sort = new Sort("seq", "parent.id", "name", "id");
		List<SysMenu> sysMenuList = sysMenuDao.findAll(sort);

		ObjectNode result = JsonUtil.getObjectMapper().createObjectNode();
		ArrayNode rows = result.putArray("rows");
		result.put("total", sysMenuList.size());
		for (SysMenu sysMenu : sysMenuList) {
			ObjectNode objectNode = rows.addObject();
			objectNode.put("id", sysMenu.getId());
			String name = sysMenu.getName();
			if (StringUtils.isNotEmpty(sysMenu.getRemark())) {
				name = name + "(" + sysMenu.getRemark() + ")";
			}
			objectNode.put("sysMenu.name", name);
			objectNode.put("iconCls", sysMenu.getIcon());
			SysMenu parent = sysMenu.getParent();
			objectNode.put("_parentId", parent != null ? parent.getId() : null);
			// 权限
			List<SysPermission> sysPermissionList = sysMenu.getSysPermissions();
			ArrayNode arrayNode = objectNode.putArray("sysPermissions");
			for (SysPermission sysPermission : sysPermissionList) {
				ObjectNode sysPermissionObjectNode = arrayNode.addObject();
				sysPermissionObjectNode.put("id", sysPermission.getId());
				sysPermissionObjectNode.put("name", sysPermission.getName());
				if (sysRole != null) {
					// 角色是否有这个权限
					List<SysRole> sysRoleList = sysPermission.getSysRoles();
					sysPermissionObjectNode.put("checked", sysRoleList.indexOf(sysRole) != -1);
				}
			}

		}
		return result;
	}

	public void saveAuthorizationForm(SysRole sysRole, List<String> sysPermissionIdList) {
		List<SysPermission> sysPermissionList = sysPermissionDao.findAll(sysPermissionIdList);
		sysRole.setSysPermissions(sysPermissionList);
		sysRoleDao.save(sysRole);
	}
}
