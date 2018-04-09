package cn.cnyirui.framework.model.po.rbac;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.SequenceableEntity;

/**
 * 系统表_菜单资源的权限
 */
@Entity
@Table(name = "SYS_PERMISSION")
@DynamicInsert
@DynamicUpdate
public class SysPermission extends SequenceableEntity {
	private static final long serialVersionUID = 3206039256917982844L;

	/**
	 * 名称
	 */
	@Column(name = "`name`", length = 100)
	private String name;

	/**
	 * 描述
	 */
	@Column(length = 300)
	private String description;

	/**
	 * 权限值
	 */
	@Column(length = 100)
	private String permissionValue;

	/**
	 * 所属资源
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sysMenuId")
	private SysMenu sysMenu;

	/**
	 * 角色列表
	 */
	@ManyToMany(mappedBy = "sysPermissions", fetch = FetchType.LAZY)
	private List<SysRole> sysRoles = new ArrayList<SysRole>(0);

	/**
	 * 名称
	 * 
	 * @return name 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 * 
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述
	 * 
	 * @return description 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述
	 * 
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 权限值
	 * 
	 * @return permissionValue 权限值
	 */
	public String getPermissionValue() {
		return permissionValue;
	}

	/**
	 * 权限值
	 * 
	 * @param permissionValue 权限值
	 */
	public void setPermissionValue(String permissionValue) {
		this.permissionValue = permissionValue;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	/**
	 * 所属资源
	 * 
	 * @return sysMenu 所属资源
	 */
	public SysMenu getSysMenu() {
		return sysMenu;
	}

	/**
	 * 所属资源
	 * 
	 * @param sysMenu 所属资源
	 */
	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	/**
	 * 角色列表
	 * 
	 * @return sysRoles 角色列表
	 */
	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	/**
	 * 角色列表
	 * 
	 * @param sysRoles 角色列表
	 */
	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

}
