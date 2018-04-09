package cn.cnyirui.framework.service.rbac;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.rbac.SysMenuDao;
import cn.cnyirui.framework.dao.rbac.SysPermissionDao;
import cn.cnyirui.framework.model.eo.SysMenuUseOf;
import cn.cnyirui.framework.model.po.rbac.SysMenu;
import cn.cnyirui.framework.model.po.rbac.SysPermission;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;

@Service
public class SysMenuService extends BaseService<SysMenu> {
	@Resource
	private SysMenuDao sysMenuDao;

	@Resource
	private SysPermissionDao sysPermissionDao;

	@Override
	public BaseDao<SysMenu> getBaseDao() {
		return sysMenuDao;
	}

	public SysMenu findByUrl(String url) {
		List<SysMenu> sysMenus = sysMenuDao.findByUrl(url);
		if (!sysMenus.isEmpty()) {
			return sysMenus.get(0);
		}
		return null;
	}

	@Override
	public SysMenu doSavePageModel(String action, SysMenu pageModel, SysMenu toSavePageModel,
	        HttpServletRequest request) {
		toSavePageModel = super.doSavePageModel(action, pageModel, toSavePageModel, request);
		// 默认权限
		if ("add".equalsIgnoreCase(action)) {
			if (ServletRequestUtils.getBooleanParameter(request, "viewPermission", false)) {
				addSysPermission(toSavePageModel, "查看", "view");
			}
			if (ServletRequestUtils.getBooleanParameter(request, "addPermission", false)) {
				addSysPermission(toSavePageModel, "添加", "add");
			}
			if (ServletRequestUtils.getBooleanParameter(request, "editPermission", false)) {
				addSysPermission(toSavePageModel, "修改", "edit");
			}
			if (ServletRequestUtils.getBooleanParameter(request, "delPermission", false)) {
				addSysPermission(toSavePageModel, "删除", "del");
			}
			if (ServletRequestUtils.getBooleanParameter(request, "importPermission", false)) {
				addSysPermission(toSavePageModel, "导入", "import");
			}
			if (ServletRequestUtils.getBooleanParameter(request, "exportPermission", false)) {
				addSysPermission(toSavePageModel, "导出", "export");
			}
		}
		return toSavePageModel;
	}

	private void addSysPermission(SysMenu sysMenu, String permissionName, String permissionValue) {
		SysPermission sysPermission = new SysPermission();
		sysPermission.setName(permissionName);
		sysPermission.setDescription(sysMenu.getName() + " - " + permissionName);
		sysPermission.setSysMenu(sysMenu);
		sysPermission.setPermissionValue(sysMenu.getPermissionCode() + ":" + permissionValue);
		sysPermissionDao.save(sysPermission);
	}

	@Override
	public ObjectNode entityToObjectNode(SysMenu sysMenu, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(sysMenu, config);
		objectNode.put("iconCls", sysMenu.getIcon());
		if (sysMenu.getUseOf() != null) {
			objectNode.put("useOf", SysMenuUseOf.valueOf(sysMenu.getUseOf()).getText());
		}
		SysMenu parent = sysMenu.getParent();
		objectNode.put("_parentId", parent != null ? parent.getId() : null);
		objectNode.put("_hasChildren", !sysMenu.getChildren().isEmpty());
		return objectNode;
	}

	public List<ObjectNode> getSysMenuTreeJson(String parentId) {
		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		List<SysMenu> sysMenuList = null;
		if (parentId == null) {
			sysMenuList = sysMenuDao.findRootSysMenu();
		} else {
			sysMenuList = sysMenuDao.findSubSysMenu(parentId);
		}
		for (SysMenu sysMenu : sysMenuList) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNodeList.add(objectNode);
			objectNode.put("id", sysMenu.getId());
			objectNode.put("text", sysMenu.getName());
			objectNode.put("iconCls", sysMenu.getIcon());
			objectNode.putArray("children").addAll(getSysMenuTreeJson(sysMenu.getId()));
		}
		return objectNodeList;
	}
}
