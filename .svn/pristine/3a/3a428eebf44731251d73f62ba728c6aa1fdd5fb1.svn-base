package cn.cnyirui.framework.model.vo;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import cn.cnyirui.framework.model.po.rbac.SysMenu;
import cn.cnyirui.framework.model.po.rbac.SysPermission;

public class ControllerConfig {
	/**
	 * 页面标题
	 */
	private String pageTitle;

	private String urlPrefix;
	/**
	 * 列表页
	 */
	private String listViewName;
	/**
	 * 编辑页
	 */
	private String editViewName;
	/**
	 * 添加页
	 */
	private String addViewName;
	/**
	 * 查看页
	 */
	private String viewViewName;
	/**
	 * 选择页
	 */
	private String selectViewName;
	/**
	 * 添加权限
	 */
	private String addPermission;
	/**
	 * 修改权限
	 */
	private String editPermission;
	/**
	 * 删除权限
	 */
	private String delPermission;
	/**
	 * 查看权限
	 */
	private String viewPermission;
	/**
	 * 导入权限
	 */
	private String importPermission;
	/**
	 * 导出权限
	 */
	private String exportPermission;

	/**
	 * 页面标题
	 * 
	 * @return pageTitle 页面标题
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * 页面标题
	 * 
	 * @param pageTitle 页面标题
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * urlPrefix
	 * 
	 * @return urlPrefix urlPrefix
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}

	/**
	 * urlPrefix
	 * 
	 * @param urlPrefix urlPrefix
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	/**
	 * 列表页
	 * 
	 * @return listViewName 列表页
	 */
	public String getListViewName() {
		return listViewName;
	}

	/**
	 * 列表页
	 * 
	 * @param listViewName 列表页
	 */
	public void setListViewName(String listViewName) {
		this.listViewName = listViewName;
	}

	/**
	 * 编辑页
	 * 
	 * @return editViewName 编辑页
	 */
	public String getEditViewName() {
		return editViewName;
	}

	/**
	 * 编辑页
	 * 
	 * @param editViewName 编辑页
	 */
	public void setEditViewName(String editViewName) {
		this.editViewName = editViewName;
	}

	/**
	 * 添加页
	 * 
	 * @return addViewName 添加页
	 */
	public String getAddViewName() {
		return addViewName;
	}

	/**
	 * 添加页
	 * 
	 * @param addViewName 添加页
	 */
	public void setAddViewName(String addViewName) {
		this.addViewName = addViewName;
	}

	/**
	 * 查看页
	 * 
	 * @return viewViewName 查看页
	 */
	public String getViewViewName() {
		return viewViewName;
	}

	/**
	 * 查看页
	 * 
	 * @param viewViewName 查看页
	 */
	public void setViewViewName(String viewViewName) {
		this.viewViewName = viewViewName;
	}

	/**
	 * 选择页
	 * 
	 * @return selectViewName 选择页
	 */
	public String getSelectViewName() {
		return selectViewName;
	}

	/**
	 * 选择页
	 * 
	 * @param selectViewName 选择页
	 */
	public void setSelectViewName(String selectViewName) {
		this.selectViewName = selectViewName;
	}

	/**
	 * 添加权限
	 * 
	 * @return addPermission 添加权限
	 */
	public String getAddPermission() {
		return addPermission;
	}

	/**
	 * 添加权限
	 * 
	 * @param addPermission 添加权限
	 */
	public void setAddPermission(String addPermission) {
		this.addPermission = addPermission;
	}

	/**
	 * 修改权限
	 * 
	 * @return editPermission 修改权限
	 */
	public String getEditPermission() {
		return editPermission;
	}

	/**
	 * 修改权限
	 * 
	 * @param editPermission 修改权限
	 */
	public void setEditPermission(String editPermission) {
		this.editPermission = editPermission;
	}

	/**
	 * 删除权限
	 * 
	 * @return delPermission 删除权限
	 */
	public String getDelPermission() {
		return delPermission;
	}

	/**
	 * 删除权限
	 * 
	 * @param delPermission 删除权限
	 */
	public void setDelPermission(String delPermission) {
		this.delPermission = delPermission;
	}

	/**
	 * 查看权限
	 * 
	 * @return viewPermission 查看权限
	 */
	public String getViewPermission() {
		return viewPermission;
	}

	/**
	 * 查看权限
	 * 
	 * @param viewPermission 查看权限
	 */
	public void setViewPermission(String viewPermission) {
		this.viewPermission = viewPermission;
	}

	/**
	 * 导入权限
	 * 
	 * @return importPermission 导入权限
	 */
	public String getImportPermission() {
		return importPermission;
	}

	/**
	 * 导入权限
	 * 
	 * @param importPermission 导入权限
	 */
	public void setImportPermission(String importPermission) {
		this.importPermission = importPermission;
	}

	/**
	 * 导出权限
	 * 
	 * @return exportPermission 导出权限
	 */
	public String getExportPermission() {
		return exportPermission;
	}

	/**
	 * 导出权限
	 * 
	 * @param exportPermission 导出权限
	 */
	public void setExportPermission(String exportPermission) {
		this.exportPermission = exportPermission;
	}

	/**
	 * 添加权限
	 * 
	 * @return true 有权限
	 */
	public boolean hasAddPermission() {
		return StringUtils.isNotEmpty(getAddPermission()) && SecurityUtils.getSubject().isPermitted(getAddPermission());
	}

	/**
	 * 修改权限
	 * 
	 * @return true 有权限
	 */
	public boolean hasEditPermission() {
		return StringUtils.isNotEmpty(getEditPermission())
		        && SecurityUtils.getSubject().isPermitted(getEditPermission());
	}

	/**
	 * 删除权限
	 * 
	 * @return true 有权限
	 */
	public boolean hasDelPermission() {
		return StringUtils.isNotEmpty(getDelPermission())
		        && SecurityUtils.getSubject().isPermitted(getDelPermission());
	}

	/**
	 * 查看权限
	 * 
	 * @return true 有权限
	 */
	public boolean hasViewPermission() {
		return StringUtils.isEmpty(getViewPermission())
		        || SecurityUtils.getSubject().isPermitted(getViewPermission());
	}

	/**
	 * 导入权限
	 * 
	 * @return true 有权限
	 */
	public boolean hasImportPermission() {
		return StringUtils.isNotEmpty(getImportPermission())
		        && SecurityUtils.getSubject().isPermitted(getImportPermission());
	}

	/**
	 * 导出权限
	 * 
	 * @return true 有权限
	 */
	public boolean hasExportPermission() {
		return StringUtils.isNotEmpty(getExportPermission())
		        && SecurityUtils.getSubject().isPermitted(getExportPermission());
	}

	/**
	 * 
	 * @param viewNamePrefix
	 * @return
	 */
	public static ControllerConfig newControllerConfig(String viewNamePrefix, SysMenu sysMenu) {
		ControllerConfig controllerConfig = new ControllerConfig();
		controllerConfig.setUrlPrefix("/" + StringUtils.substringBeforeLast(viewNamePrefix, "/") + "/");
		controllerConfig.setAddViewName(viewNamePrefix + "edit");
		controllerConfig.setEditViewName(viewNamePrefix + "edit");
		controllerConfig.setViewViewName(viewNamePrefix + "edit");
		controllerConfig.setListViewName(viewNamePrefix + "list");
		controllerConfig.setSelectViewName(viewNamePrefix + "select");
		if (sysMenu != null) {
			controllerConfig.setPageTitle(sysMenu.getName());
			for (SysPermission sysPermission : sysMenu.getSysPermissions()) {
				String permissionValue = sysPermission.getPermissionValue();
				if (StringUtils.indexOf(permissionValue, ":view") != -1) {
					controllerConfig.setViewPermission(permissionValue);
				} else if (StringUtils.indexOf(permissionValue, ":add") != -1) {
					controllerConfig.setAddPermission(permissionValue);
				} else if (StringUtils.indexOf(permissionValue, ":edit") != -1) {
					controllerConfig.setEditPermission(permissionValue);
				} else if (StringUtils.indexOf(permissionValue, ":del") != -1) {
					controllerConfig.setDelPermission(permissionValue);
				} else if (StringUtils.indexOf(permissionValue, ":import") != -1) {
					controllerConfig.setImportPermission(permissionValue);
				} else if (StringUtils.indexOf(permissionValue, ":export") != -1) {
					controllerConfig.setExportPermission(permissionValue);
				}
			}
		}
		return controllerConfig;
	}

}
