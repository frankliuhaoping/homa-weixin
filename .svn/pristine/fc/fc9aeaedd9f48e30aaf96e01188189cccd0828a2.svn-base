package cn.cnyirui.homaweixin.model.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.cnyirui.framework.model.po.base.LogicDeleteableEntity;

/**
 * 产品
 * 
 */
@Entity
public class Product extends LogicDeleteableEntity implements IMSImportedEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 编码
	 */
	@Column(length = 100)
	private String code;

	/**
	 * 描述
	 */
	@Column(length = 500)
	private String description;

	/**
	 * IMS分类id
	 */
	@Column
	private Long IMSId;

	/**
	 * 是否是IMS数据
	 */
	@Column
	private Boolean isIMSData = Boolean.FALSE;

	/**
	 * 名称
	 */
	@Column(length = 300)
	private String name;

	/**
	 * 产品分类
	 */
	// bi-directional many-to-one association to ProductCategory
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private ProductCategory category;

	/**
	 * 业务表_销售订单明细
	 */
	// bi-directional many-to-one association to SalesOrderDetail
	@OneToMany(mappedBy = "product")
	private List<SalesOrderDetail> salesOrderDetailList;

	/**
	 * 编码
	 * 
	 * @return code 编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 编码
	 * 
	 * @param code
	 *            编码
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

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
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 产品分类
	 * 
	 * @return category 产品分类
	 */
	public ProductCategory getCategory() {
		return category;
	}

	/**
	 * 产品分类
	 * 
	 * @param category
	 *            产品分类
	 */
	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	/**
	 * 业务表_销售订单明细
	 * 
	 * @return salesOrderDetailList 业务表_销售订单明细
	 */
	public List<SalesOrderDetail> getSalesOrderDetailList() {
		return salesOrderDetailList;
	}

	/**
	 * 业务表_销售订单明细
	 * 
	 * @param salesOrderDetailList
	 *            业务表_销售订单明细
	 */
	public void setSalesOrderDetailList(List<SalesOrderDetail> salesOrderDetailList) {
		this.salesOrderDetailList = salesOrderDetailList;
	}

	@Override
	public Long getIMSId() {
		return IMSId;
	}

	@Override
	public void setIMSId(Long iMSId) {
		this.IMSId = iMSId;
	}

	@Override
	public Boolean getIsIMSData() {
		return isIMSData;
	}

	@Override
	public void setIsIMSData(Boolean isIMSData) {
		this.isIMSData = isIMSData;
	}

}
