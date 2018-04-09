package cn.cnyirui.homaweixin.model.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 用于createSubOrganizationList存储过程
 * 
 * <pre>
 * createSubOrganizationList 生成组织架构树某个节点的所有子节点，生成的数据储在ORGANIZATION_TREE_TEMP表
 * 每次调用需要传parentId和callId，callId用于查询每次调用后生成的数据
 * </pre>
 * 
 * 组织架构-临时表
 * 
 */
@Entity
@Table(name = "ORGANIZATION_TREE_TEMP")
public class OrganizationTreeTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORG_TREE_TEMP_S")
	@SequenceGenerator(name = "ORG_TREE_TEMP_S", sequenceName = "ORG_TREE_TEMP_S", allocationSize = 1, initialValue = 1)
	private Long id;

	/***
	 * 临时数据标识ID
	 */
	@Column(length = 36)
	private String callId;

	/***
	 * 根父ID
	 */
	@Column(length = 36)
	private String rootParentId;

	/**
	 * 组织架构
	 */
	@Column(length = 36)
	private String organizationId;

	/**
	 * 组织架构名称
	 */
	@Column(length = 100)
	private String organizationName;

	/**
	 * 树的层次
	 */
	@Column
	private Integer depth;

	/**
	 * 父ID
	 */
	@Column(length = 2000)
	private String parentIds;

	/**
	 * 父名称
	 */
	@Column(length = 2000)
	private String parentNames;

	/**
	 * id
	 * 
	 * @return id id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * id
	 * 
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 临时数据标识ID
	 * 
	 * @return callId 临时数据标识ID
	 */
	public String getCallId() {
		return callId;
	}

	/**
	 * 临时数据标识ID
	 * 
	 * @param callId 临时数据标识ID
	 */
	public void setCallId(String callId) {
		this.callId = callId;
	}

	/**
	 * 组织架构
	 * 
	 * @return organizationId 组织架构
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * 组织架构
	 * 
	 * @param organizationId 组织架构
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * 组织架构名称
	 * 
	 * @return organizationName 组织架构名称
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 组织架构名称
	 * 
	 * @param organizationName 组织架构名称
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 树的层次
	 * 
	 * @return depth 树的层次
	 */
	public Integer getDepth() {
		return depth;
	}

	/**
	 * 树的层次
	 * 
	 * @param depth 树的层次
	 */
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	/**
	 * 父ID
	 * 
	 * @return parentIds 父ID
	 */
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * 父ID
	 * 
	 * @param parentIds 父ID
	 */
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * 父名称
	 * 
	 * @return parentNames 父名称
	 */
	public String getParentNames() {
		return parentNames;
	}

	/**
	 * 父名称
	 * 
	 * @param parentNames 父名称
	 */
	public void setParentNames(String parentNames) {
		this.parentNames = parentNames;
	}

	/**
	 * 根父ID
	 * 
	 * @return rootParentId 根父ID
	 */
	public String getRootParentId() {
		return rootParentId;
	}

	/**
	 * 根父ID
	 * 
	 * @param rootParentId 根父ID
	 */
	public void setRootParentId(String rootParentId) {
		this.rootParentId = rootParentId;
	}

}
