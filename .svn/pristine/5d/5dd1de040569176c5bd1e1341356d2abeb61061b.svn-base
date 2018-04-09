package cn.cnyirui.framework.model.po.rbac;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.extension.excel.ExcelExportable;
import cn.cnyirui.framework.model.po.base.SequenceableEntity;

/**
 * 系统表_菜单资源
 */
@Entity
@Table(name = "SYS_MENU")
@DynamicInsert
@DynamicUpdate
public class SysMenu extends SequenceableEntity {
	private static final long serialVersionUID = 7796208658178834967L;

	/**
	 * 编号
	 */
	@Column(length = 100)
	private String code;

	/**
	 * 名称
	 */
	@ExcelExportable(index = 0, titles = "菜单名称")
	@Column(length = 100)
	private String name;

	/**
	 * 资源URL
	 */
	@ExcelExportable(index = 1, titles = "菜单URL")
	@Column(length = 300)
	private String url;

	/**
	 * 图标class
	 */
	@Column(length = 100)
	private String icon;

	/**
	 * 权限代码
	 */
	@Column(length = 100)
	private String permissionCode;

	/**
	 * 备注
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "CLOB")
	private String remark;

	/**
	 * 是否屏蔽，默认不屏蔽
	 */
	@Column
	private Boolean isDisabled = Boolean.FALSE;

	/**
	 * 0=PC端使用，1=微信端使用
	 */
	@Column
	private Integer useOf;

	/**
	 * 所属资源id(父id)
	 */
	@ExcelExportable(index = 2, titles = { "父菜单", "id" }, propertyNames = { "parent.name", "parent.id" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private SysMenu parent;

	/**
	 * 子资源
	 */
	@OneToMany(mappedBy = "parent")
	@OrderBy("seq")
	private List<SysMenu> children = new ArrayList<SysMenu>(0);

	/**
	 * 菜单资源权限
	 */
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "sysMenu")
	@OrderBy("seq")
	private List<SysPermission> sysPermissions;

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
	 * 编号
	 * 
	 * @return code 编号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 编号
	 * 
	 * @param code 编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 资源URL
	 * 
	 * @return url 资源URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 资源URL
	 * 
	 * @param url 资源URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 权限代码
	 * 
	 * @return permissionCode 权限代码
	 */
	public String getPermissionCode() {
		return permissionCode;
	}

	/**
	 * 权限代码
	 * 
	 * @param permissionCode 权限代码
	 */
	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	/**
	 * 备注
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * 
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 图标class
	 * 
	 * @return icon 图标class
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 图标class
	 * 
	 * @param icon 图标class
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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
	 * 所属资源id(父id)
	 * 
	 * @return parent 所属资源id(父id)
	 */
	public SysMenu getParent() {
		return parent;
	}

	/**
	 * 所属资源id(父id)
	 * 
	 * @param parent 所属资源id(父id)
	 */
	public void setParent(SysMenu parent) {
		this.parent = parent;
	}

	/**
	 * 子资源
	 * 
	 * @return children 子资源
	 */
	public List<SysMenu> getChildren() {
		return children;
	}

	/**
	 * 子资源
	 * 
	 * @param children 子资源
	 */
	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	/**
	 * 菜单资源权限
	 * 
	 * @return sysPermissions 菜单资源权限
	 */
	public List<SysPermission> getSysPermissions() {
		if (sysPermissions == null) {
			sysPermissions = new ArrayList<SysPermission>();
		}
		return sysPermissions;
	}

	/**
	 * 菜单资源权限
	 * 
	 * @param sysPermissions 菜单资源权限
	 */
	public void setSysPermissions(List<SysPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}

	/**
	 * 0=PC端使用，1=微信端使用
	 * 
	 * @return useOf 0=PC端使用，1=微信端使用
	 */
	public Integer getUseOf() {
		return useOf;
	}

	/**
	 * 0=PC端使用，1=微信端使用
	 * 
	 * @param useOf 0=PC端使用，1=微信端使用
	 */
	public void setUseOf(Integer useOf) {
		this.useOf = useOf;
	}

}