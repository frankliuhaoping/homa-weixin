package cn.cnyirui.framework.model.po.rbac;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.extension.excel.ExcelImportable;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.homaweixin.model.po.Employee;

/**
 * 系统表_账号
 */
@Entity
@Table(name = "SYS_USER")
@DynamicInsert
@DynamicUpdate
public class SysUser extends BaseEntity {
	private static final long serialVersionUID = 7919497351895757484L;

	/**
	 * 登录名
	 */
	@ExcelImportable(index = 0, title = "登录名", comment = "不能为空，不允许重复", validateMethodName = "checkLoginName")
	@Column(length = 100)
	private String loginName;

	/**
	 * 旧的登录名
	 */
	@Column(length = 100)
	private String oldLoginName;

	/**
	 * 真实姓名
	 */
	@ExcelImportable(index = 1, title = "真实姓名")
	@Column(length = 100)
	private String realName;

	/**
	 * 密码
	 */
	@ExcelImportable(index = 2, title = "登录密码", parseMethodName = "md5Password", comment = "不能为空")
	@Column(length = 100)
	private String password;

	/**
	 * 是否超级管理员, 默认不是
	 */
	@Column
	private Boolean isAdmin = Boolean.FALSE;

	/**
	 * 是否屏蔽，默认不屏蔽
	 */
	@Column
	private Boolean isDisabled = Boolean.FALSE;

	/**
	 * 帐号的角色列表
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_USER_ROLE", joinColumns = { @JoinColumn(name = "sysUserId") }, inverseJoinColumns = {
	        @JoinColumn(name = "sysRoleId") })
	private List<SysRole> sysRoles = new ArrayList<SysRole>(0);

	/**
	 * 帐号对应的员工
	 */
	@OneToMany(mappedBy = "sysUser")
	private List<Employee> employeeList;

	/**
	 * 帐号对应的微信用户
	 */
	@OneToMany(mappedBy = "sysUser")
	private List<WeiXinUser> weiXinUserList;

	/**
	 * 登录名
	 * 
	 * @return loginName 登录名
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 登录名
	 * 
	 * @param loginName 登录名
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 密码
	 * 
	 * @return password 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 * 
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 是否超级管理员默认不是
	 * 
	 * @return isAdmin 是否超级管理员默认不是
	 */
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	/**
	 * 是否超级管理员默认不是
	 * 
	 * @param isAdmin 是否超级管理员默认不是
	 */
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * 是否屏蔽，默认不屏蔽
	 * 
	 * @return isDisabled 是否屏蔽，默认不屏蔽
	 */
	public Boolean getIsDisabled() {
		return isDisabled;
	}

	/**
	 * 是否屏蔽，默认不屏蔽
	 * 
	 * @param isDisabled 是否屏蔽，默认不屏蔽
	 */
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	/**
	 * 真实姓名
	 * 
	 * @return realName 真实姓名
	 */
	public String getRealName() {
		if (StringUtils.isEmpty(realName)) {
			return getLoginName();
		}
		return realName;
	}

	/**
	 * 真实姓名
	 * 
	 * @param realName 真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 帐号的角色列表
	 * 
	 * @return sysRoles 帐号的角色列表
	 */
	public List<SysRole> getSysRoles() {
		return sysRoles;
	}

	/**
	 * 帐号的角色列表
	 * 
	 * @param sysRoles 帐号的角色列表
	 */
	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	/**
	 * 添加角色
	 * 
	 * @param sysRole
	 */
	public void addSysRole(SysRole sysRole) {
		List<SysRole> sysRoles = getSysRoles();
		if (sysRoles.indexOf(sysRole) == -1) {
			sysRoles.add(sysRole);
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param sysRole
	 */
	public void removeSysRole(SysRole sysRole) {
		List<SysRole> sysRoles = getSysRoles();
		sysRoles.remove(sysRole);
	}

	/**
	 * 帐号对应的员工
	 * 
	 * @return employeeList 帐号对应的员工
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * 帐号对应的员工
	 * 
	 * @return
	 */
	@Transient
	public Employee getEmployee() {
		if (!getEmployeeList().isEmpty()) {
			return getEmployeeList().get(0);
		}
		return null;
	}

	/**
	 * 帐号对应的员工
	 * 
	 * @param employeeList 帐号对应的员工
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	@Transient
	public Object md5Password(Object value, String propertyName) {
		if (value != null && StringUtils.isNotEmpty(value.toString())) {
			return DigestUtils.md5Hex(value.toString());
		}
		return null;
	}

	/**
	 * 帐号对应的微信用户
	 * 
	 * @return weiXinUserList 帐号对应的微信用户
	 */
	public List<WeiXinUser> getWeiXinUserList() {
		return weiXinUserList;
	}

	/**
	 * 帐号对应的微信用户
	 * 
	 * @param weiXinUserList 帐号对应的微信用户
	 */
	public void setWeiXinUserList(List<WeiXinUser> weiXinUserList) {
		this.weiXinUserList = weiXinUserList;
	}

	@Transient
	public WeiXinUser getWeiXinUser() {
		List<WeiXinUser> weiXinUserList = getWeiXinUserList();
		if (!weiXinUserList.isEmpty()) {
			return weiXinUserList.get(0);
		}
		return null;
	}

}
