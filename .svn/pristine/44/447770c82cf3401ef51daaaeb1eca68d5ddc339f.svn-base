package cn.cnyirui.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 产品分类
 * 
 * @author pengzhihua
 *
 */
@Entity
@Table(name = "CRM_PUB_PRODUCT_TYPE_MB_V")
public class IMSProductCategory implements IMSReadedEntity {
	private static final long serialVersionUID = -5998665922309496927L;

	/**
	 * ID
	 */
	@Id
	@Column(name = "PRODUCT_TYPE_ID")
	private Long id;

	/**
	 * 编码
	 */
	@Column(name = "PRODUCT_TYPE_CODE")
	private String code;

	/**
	 * 名称
	 */
	@Column(name = "PRODUCT_TYPE_NAME")
	private String name;

	/**
	 * 上级ID
	 */
	@Column(name = "PARENTSID")
	private Long parentId;

	/**
	 * 上级编码
	 */
	@Column(name = "PARENTSCODE")
	private Long parentCode;

	/**
	 * 上级名称
	 */
	@Column(name = "PARENTSNAME")
	private String parentName;

	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String reamrk;

	/**
	 * 组织ID
	 */
	@Column(name = "ORG_ID")
	private Long orgId;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 上级编码
	 * 
	 * @return parentCode 上级编码
	 */
	public Long getParentCode() {
		return parentCode;
	}

	/**
	 * 上级编码
	 * 
	 * @param parentCode 上级编码
	 */
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * 备注
	 * 
	 * @return reamrk 备注
	 */
	public String getReamrk() {
		return reamrk;
	}

	/**
	 * 备注
	 * 
	 * @param reamrk 备注
	 */
	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}

	/**
	 * 组织ID
	 * 
	 * @return orgId 组织ID
	 */
	public Long getOrgId() {
		return orgId;
	}

	/**
	 * 组织ID
	 * 
	 * @param orgId 组织ID
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public IMSProductCategory(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getParentName() {
		return parentName;
	}

	@Override
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
