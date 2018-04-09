package cn.cnyirui.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 办事处
 * 
 * @author pengzhihua
 *
 */
@Entity
@Table(name = "OM_OFFICE_MB_V")
public class IMSOffice implements IMSReadedEntity {

	private static final long serialVersionUID = -3865173014909533773L;

	@Id
	@Column(name = "OFFICE_ID")
	private Long id;

	/**
	 * 编码
	 */
	@Column(name = "OFFICE_CODE")
	private String code;

	/**
	 * 名称
	 */
	@Column(name = "OFFICE_NAME")
	private String name;

	/**
	 * 业务组织ID
	 */
	@Column(name = "ORG_ID")
	private Long parentId;

	/**
	 * 业务组织名称
	 */
	@Column(name = "ORGNAME")
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
