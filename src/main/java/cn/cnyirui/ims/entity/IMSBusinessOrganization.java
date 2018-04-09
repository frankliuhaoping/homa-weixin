package cn.cnyirui.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 业务组织
 * 
 * @author pengzhihua
 *
 */
@Entity
@Table(name = "OM_BUSIORG_MB_V")
public class IMSBusinessOrganization implements IMSReadedEntity {

	private static final long serialVersionUID = -8893171487685682782L;

	/**
	 * 业务组织ID
	 */
	@Id
	@Column(name = "BUSIORGID")
	private Long id;

	/**
	 * 业务组织名称
	 */
	@Column(name = "ORGNAME")
	private String name;

	/**
	 * 业务组织编码
	 */
	@Column(name = "ORGCODE")
	private String code;

	/**
	 * 业务组织描述
	 */
	@Column(name = "ORG_DESC")
	private String description;

	/**
	 * 父组织ID
	 */
	@Column(name = "PARENTID")
	private Long parentId;

	/**
	 * 父组织名称
	 */
	@Column(name = "PARENTNAME")
	private String parentName;

	/**
	 * 业务组织描述
	 * 
	 * @return description 业务组织描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 业务组织描述
	 * 
	 * @param description 业务组织描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

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
