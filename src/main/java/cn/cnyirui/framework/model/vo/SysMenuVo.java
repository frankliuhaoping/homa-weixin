package cn.cnyirui.framework.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysMenuVo implements Serializable {
	private static final long serialVersionUID = 1336269315467100533L;

	private String id;
	private String name;
	private String url;
	private String icon;
	private String permissionValue;
	private String parentId;
	private List<SysMenuVo> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<SysMenuVo> getChildren() {
		if (children == null) {
			children = new ArrayList<SysMenuVo>();
		}
		return children;
	}

	public void addChild(SysMenuVo menu) {
		getChildren().add(menu);
	}

	public boolean hasChildren() {
		return (getChildren().size() > 0);
	}

	public SysMenuVo() {

	}

	public SysMenuVo(String id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public SysMenuVo(String id, String name, String url, String icon, String permissionValue, String parentId) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.permissionValue = permissionValue;
		this.parentId = parentId;
	}

	public boolean childExists(String id) {
		for (SysMenuVo m : getChildren()) {
			if (m.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static SysMenuVo getMenuById(List<SysMenuVo> menus, String id) {
		for (SysMenuVo menu : menus) {
			if (id.equals(menu.getId())) {
				return menu;
			}
		}
		return null;
	}
}
