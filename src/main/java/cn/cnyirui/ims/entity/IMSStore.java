package cn.cnyirui.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 门店
 * 
 * @author pengzhihua
 *
 */
@Entity
@Table(name = "CRM_CTM_STORE_MB_V")
public class IMSStore implements IMSReadedEntity {

	private static final long serialVersionUID = -8053749939974699630L;

	@Id
	@Column(name = "STORE_ID")
	private Long id;

	/**
	 * 编码
	 */
	@Column(name = "STORE_CODE")
	private String code;

	/**
	 * 名称
	 */
	@Column(name = "STORE_NAME")
	private String name;

	/**
	 * 业务组织ID
	 */
	@Column(name = "BUSI_ORG_ID")
	private Long parentId;

	/**
	 * 业务组织名称
	 */
	@Column(name = "BUSI_ORG_NAME")
	private String parentName;

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

	@Override
	public String getParentName() {
		return parentName;
	}

	@Override
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
