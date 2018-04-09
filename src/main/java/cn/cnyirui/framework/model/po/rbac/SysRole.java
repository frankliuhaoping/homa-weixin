package cn.cnyirui.framework.model.po.rbac;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.StandardSetupEntity;

/**
 * 系统表_角色
 */
@Entity
@Table(name = "SYS_ROLE")
@DynamicInsert
@DynamicUpdate
public class SysRole extends StandardSetupEntity {
	private static final long serialVersionUID = -1067834205409580081L;

	/**
	 * 帐号列表
	 */
	@ManyToMany(mappedBy = "sysRoles", fetch = FetchType.LAZY)
	private List<SysUser> sysUsers = new ArrayList<SysUser>(0);

	/**
	 * 权限列表
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "sysRoleId") }, inverseJoinColumns = {
			@JoinColumn(name = "sysPermissionId") })
	@OrderBy("seq")
	private List<SysPermission> sysPermissions = new ArrayList<SysPermission>(0);

	/**
	 * 帐号列表
	 * 
	 * @return sysUsers 帐号列表
	 */
	public List<SysUser> getSysUsers() {
		return sysUsers;
	}

	/**
	 * 帐号列表
	 * 
	 * @param sysUsers 帐号列表
	 */
	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	/**
	 * 权限列表
	 * 
	 * @return sysPermissions 权限列表
	 */
	public List<SysPermission> getSysPermissions() {
		return sysPermissions;
	}

	/**
	 * 权限列表
	 * 
	 * @param sysPermissions 权限列表
	 */
	public void setSysPermissions(List<SysPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}

}