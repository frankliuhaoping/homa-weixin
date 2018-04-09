package cn.cnyirui.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 产品
 * 
 * @author pengzhihua
 *
 */
@Entity
@Table(name = "CRM_PUB_MATERIALS_MB_V")
public class IMSProduct implements IMSReadedEntity {
	private static final long serialVersionUID = -3898207938830782366L;

	@Id
	@Column(name = "MATERIAL_ID")
	private Long id;

	/**
	 * 编码
	 */
	@Column(name = "MATERIAL_CODE")
	private String code;

	/**
	 * 名称
	 */
	@Column(name = "MATERIAL_NAME")
	private String name;

	/**
	 * 描述
	 */
	@Column(name = "MATERIAL_DESC")
	private String description;

	/**
	 * 分类ID
	 */
	@Column(name = "PRODUCT_TYPE_ID")
	private Long IMSProductCategoryId;

	/**
	 * 箱体分类
	 */
	@Column(name = "BOX_TYPE")
	private String boxType;

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
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 分类ID
	 * 
	 * @return iMSProductCategoryId 分类ID
	 */
	public Long getIMSProductCategoryId() {
		return IMSProductCategoryId;
	}

	/**
	 * 分类ID
	 * 
	 * @param iMSProductCategoryId 分类ID
	 */
	public void setIMSProductCategoryId(Long iMSProductCategoryId) {
		IMSProductCategoryId = iMSProductCategoryId;
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

	@Transient
	@Override
	public Long getParentId() {
		throw new UnsupportedOperationException();
	}

	@Transient
	@Override
	public void setParentId(Long parentId) {
		throw new UnsupportedOperationException();
	}

	@Transient
	@Override
	public String getParentName() {
		throw new UnsupportedOperationException();
	}

	@Transient
	@Override
	public void setParentName(String parentName) {
		throw new UnsupportedOperationException();

	}

	public IMSProduct(String name, String boxType) {
		super();
		this.name = name;
		this.boxType = boxType;
	}

	/**
	 * 箱体分类
	 * 
	 * @return boxType 箱体分类
	 */
	public String getBoxType() {
		return boxType;
	}

	/**
	 * 箱体分类
	 * 
	 * @param boxType 箱体分类
	 */
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	public IMSProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

}
