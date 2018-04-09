package cn.cnyirui.homaweixin.model.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.LogicDeleteableEntity;

/**
 * 产品分类
 * 
 */
@Entity
@Table(name = "product_category")
@DynamicInsert
@DynamicUpdate
public class ProductCategory extends LogicDeleteableEntity implements IMSImportedEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * IMS产品id
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
	@Column(length = 100)
	private String name;

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String remark;

	/**
	 * 产品
	 */
	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "category")
	private List<Product> productList;

	/**
	 * 上级分类
	 */
	// bi-directional many-to-one association to ProductCategory
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private ProductCategory parent;

	/**
	 * 下级分类
	 */
	// bi-directional many-to-one association to ProductCategory
	@OneToMany(mappedBy = "parent")
	private List<ProductCategory> subCategory;

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
	 * @param remark
	 *            备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 产品
	 * 
	 * @return productList 产品
	 */
	public List<Product> getProductList() {
		return productList;
	}

	/**
	 * 产品
	 * 
	 * @param productList
	 *            产品
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	/**
	 * 上级分类
	 * 
	 * @return parent 上级分类
	 */
	public ProductCategory getParent() {
		return parent;
	}

	/**
	 * 上级分类
	 * 
	 * @param parent
	 *            上级分类
	 */
	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}

	/**
	 * 下级分类
	 * 
	 * @return subCategory 下级分类
	 */
	public List<ProductCategory> getSubCategory() {
		return subCategory;
	}

	/**
	 * 下级分类
	 * 
	 * @param subCategory
	 *            下级分类
	 */
	public void setSubCategory(List<ProductCategory> subCategory) {
		this.subCategory = subCategory;
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
