package cn.cnyirui.ims.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

/**
 * 内销系统订单
 * 
 * @author pengzhihua
 *
 */
@Entity
@Table(name = "CRM_MKT_TERMINAL_SALES_I")
public class IMSSalesOrder implements Persistable<Long> {
	private static final long serialVersionUID = 5409419833526667838L;

	/**
	 * 终端销量接口ID
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRM_MKT_TERMINAL_SALES_I_S")
	@SequenceGenerator(name = "CRM_MKT_TERMINAL_SALES_I_S", sequenceName = "CRM_MKT_TERMINAL_SALES_I_S", allocationSize = 1, initialValue = 1)
	@Column(name = "TERMINAL_SALES_I_ID")
	private Long id;

	/**
	 * 门店编号(IMS)
	 */
	@Column(name = "STORE_CODE")
	private String organizationCode;

	/**
	 * 产品型号
	 * 
	 */
	@Column(name = "PRODUCT_MODEL")
	private String productCode;

	/**
	 * 产品型号名称
	 */
	@Column(name = "PRODUCT_MODEL_NAME")
	private String prodcuctName;

	/**
	 * 导购员编号(IMS)
	 * 
	 */
	@Column(name = "SALER_CODE")
	private String employeeCode;

	/**
	 * 销量
	 */
	@Column(name = "SALE_QTY")
	private Double qty;

	/**
	 * 销售日期
	 */
	@Column(name = "SALE_DATE")
	private Date salesTime;

	/**
	 * 来源
	 */
	@Column(name = "SOURCE_ID")
	private Long sourceId = 1L;

	/**
	 * 账套编号
	 */
	@Column(name = "ORG_CODE")
	private Long orgCode = 1L;
	/**
	 * 导入数据的时间
	 */
	@Column(name = "IMPORT_DATE")
	private Date importDate;
	/**
	 * 导入的状态
	 */
	@Column(name = "IMPORT_FLAG")
	private String importFlag;
	/**
	 * 终端销量接口ID
	 * 
	 * @return id 终端销量接口ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 终端销量接口ID
	 * 
	 * @param id 终端销量接口ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 产品型号
	 * 
	 * @return productCode 产品型号
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * 产品型号
	 * 
	 * @param productCode 产品型号
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * 产品型号名称
	 * 
	 * @return prodcuctName 产品型号名称
	 */
	public String getProdcuctName() {
		return prodcuctName;
	}

	/**
	 * 产品型号名称
	 * 
	 * @param prodcuctName 产品型号名称
	 */
	public void setProdcuctName(String prodcuctName) {
		this.prodcuctName = prodcuctName;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * 销量
	 * 
	 * @return qty 销量
	 */
	public Double getQty() {
		return qty;
	}

	/**
	 * 销量
	 * 
	 * @param qty 销量
	 */
	public void setQty(Double qty) {
		this.qty = qty;
	}

	/**
	 * 销售日期
	 * 
	 * @return salesTime 销售日期
	 */
	public Date getSalesTime() {
		return salesTime;
	}

	/**
	 * 销售日期
	 * 
	 * @param salesTime 销售日期
	 */
	public void setSalesTime(Date salesTime) {
		this.salesTime = salesTime;
	}

	/**
	 * 来源
	 * 
	 * @return sourceId 来源
	 */
	public Long getSourceId() {
		return sourceId;
	}

	/**
	 * 来源
	 * 
	 * @param sourceId 来源
	 */
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * 账套编号
	 * 
	 * @return orgCode 账套编号
	 */
	public Long getOrgCode() {
		return orgCode;
	}

	/**
	 * 账套编号
	 * 
	 * @param orgCode 账套编号
	 */
	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public String getImportFlag() {
		return importFlag;
	}

	public void setImportFlag(String importFlag) {
		this.importFlag = importFlag;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}

}
