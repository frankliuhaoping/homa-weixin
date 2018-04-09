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
 * 用于createSubProductCategoryList存储过程
 * 
 * <pre>
 * createSubProductCategoryList 生成产品分类树某个节点的所有子节点，生成的数据储在PRODUCT_CATEGORY_TREE_TEMP表
 * 每次调用需要传parentId和callId，callId用于查询每次调用后生成的数据
 * </pre>
 * 
 * 组织架构-临时表
 * 
 */
@Entity
@Table(name = "PRODUCT_CATEGORY_TREE_TEMP")
public class ProductCategoryTreeTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_CAT_TREE_TEMP_S")
	@SequenceGenerator(name = "PRO_CAT_TREE_TEMP_S", sequenceName = "PRO_CAT_TREE_TEMP_S", allocationSize = 1, initialValue = 1)
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
	 * 产品分类
	 */
	@Column(length = 36)
	private String productCategoryId;

	/**
	 * 分类名称
	 */
	@Column(length = 100)
	private String productCategoryName;

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
	 * 产品分类
	 * 
	 * @return productCategoryId 产品分类
	 */
	public String getProductCategoryId() {
		return productCategoryId;
	}

	/**
	 * 产品分类
	 * 
	 * @param productCategoryId 产品分类
	 */
	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	/**
	 * 分类名称
	 * 
	 * @return productCategoryName 分类名称
	 */
	public String getProductCategoryName() {
		return productCategoryName;
	}

	/**
	 * 分类名称
	 * 
	 * @param productCategoryName 分类名称
	 */
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
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

}
