package cn.cnyirui.homaweixin.model.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinQrCode;

/**
 * The persistent class for the sales_order database table.销售订单
 * 
 */
@Entity
@Table(name = "sales_order")
public class SalesOrder extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(length = 300)
	private String OrderNoN;

	/**
	 * 订单总价
	 */
	@Column
	private Double salesMoney;

	/**
	 * 返现金额
	 */
	@Column
	private Double retMoney;

	/**
	 * 顾客地址
	 */
	@Column(length = 300)
	private String customerAddress;

	/**
	 * 顾客姓名
	 */
	@Column(length = 100)
	private String customerName;

	/**
	 * 顾客电话
	 */
	@Column(length = 100)
	private String customerTel;

	/**
	 * 发票照片URL
	 */
	@Column(length = 300)
	private String invoiceImageUrl;

	/**
	 * 发票号
	 */
	@Column(length = 100)
	private String invoiceNo;

	/**
	 * 是否审核通过
	 */
	@Column
	private Boolean isApproved = Boolean.FALSE;

	/**
	 * 微信二维码
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weixinQrCodeId")
	private WeiXinQrCode weiXinQrCode;

	/**
	 * 销售时间
	 */
	@Column
	private Date salesTime;

	/**
	 * 审核人
	 */
	// bi-directional many-to-one association to SysUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approvedBy")
	private SysUser approvedBy;

	/**
	 * 顾客
	 */
	// bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId")
	private Customer customer;

	/**
	 * 导购员
	 */
	// bi-directional many-to-one association to Employee
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salerId")
	private Employee saler;

	/**
	 * 
	 * 导购员的组织架构
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId")
	private Organization organization;

	/**
	 * 销售订单明细
	 */
	// bi-directional many-to-one association to SalesOrderDetail
	@OneToMany(mappedBy = "salesOrder")
	private List<SalesOrderDetail> salesOrderDetailList;

	/**
	 * 是否已经上报到内销系统(true=已经上传)
	 */
	private Boolean isUpload = Boolean.FALSE;

	/**
	 * 顾客地址
	 * 
	 * @return customerAddress 顾客地址
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * 顾客地址
	 * 
	 * @param customerAddress
	 *            顾客地址
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * 顾客姓名
	 * 
	 * @return customerName 顾客姓名
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 顾客姓名
	 * 
	 * @param customerName
	 *            顾客姓名
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 顾客电话
	 * 
	 * @return customerTel 顾客电话
	 */
	public String getCustomerTel() {
		return customerTel;
	}

	/**
	 * 顾客电话
	 * 
	 * @param customerTel
	 *            顾客电话
	 */
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	/**
	 * 发票照片URL
	 * 
	 * @return invoiceImageUrl 发票照片URL
	 */
	public String getInvoiceImageUrl() {
		return invoiceImageUrl;
	}

	/**
	 * 发票照片URL
	 * 
	 * @param invoiceImageUrl
	 *            发票照片URL
	 */
	public void setInvoiceImageUrl(String invoiceImageUrl) {
		this.invoiceImageUrl = invoiceImageUrl;
	}

	/**
	 * 发票号
	 * 
	 * @return invoiceNo 发票号
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * 发票号
	 * 
	 * @param invoiceNo
	 *            发票号
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * 是否审核通过
	 * 
	 * @return isApproved 是否审核通过
	 */
	public Boolean getIsApproved() {
		return isApproved;
	}

	/**
	 * 是否审核通过
	 * 
	 * @param isApproved
	 *            是否审核通过
	 */
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	/**
	 * 微信二维码
	 * 
	 * @return weiXinQrCode 微信二维码
	 */
	public WeiXinQrCode getWeiXinQrCode() {
		return weiXinQrCode;
	}

	/**
	 * 微信二维码
	 * 
	 * @param weiXinQrCode 微信二维码
	 */
	public void setWeiXinQrCode(WeiXinQrCode weiXinQrCode) {
		this.weiXinQrCode = weiXinQrCode;
	}

	/**
	 * 销售时间
	 * 
	 * @return salesTime 销售时间
	 */
	public Date getSalesTime() {
		return salesTime;
	}

	/**
	 * 销售时间
	 * 
	 * @param salesTime
	 *            销售时间
	 */
	public void setSalesTime(Date salesTime) {
		this.salesTime = salesTime;
	}

	/**
	 * 审核人
	 * 
	 * @return approvedBy 审核人
	 */
	public SysUser getApprovedBy() {
		return approvedBy;
	}

	/**
	 * 审核人
	 * 
	 * @param approvedBy
	 *            审核人
	 */
	public void setApprovedBy(SysUser approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * 顾客
	 * 
	 * @return customer 顾客
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * 顾客
	 * 
	 * @param customer
	 *            顾客
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * 导购员
	 * 
	 * @return saler 导购员
	 */
	public Employee getSaler() {
		return saler;
	}

	/**
	 * 导购员
	 * 
	 * @param saler
	 *            导购员
	 */
	public void setSaler(Employee saler) {
		this.saler = saler;
	}

	/**
	 * 销售订单明细
	 * 
	 * @return salesOrderDetailList 销售订单明细
	 */
	public List<SalesOrderDetail> getSalesOrderDetailList() {
		return salesOrderDetailList;
	}

	/**
	 * 销售订单明细
	 * 
	 * @param salesOrderDetailList
	 *            销售订单明细
	 */
	public void setSalesOrderDetailList(List<SalesOrderDetail> salesOrderDetailList) {
		this.salesOrderDetailList = salesOrderDetailList;
	}

	public SalesOrderDetail addSalesOrderDetailList(SalesOrderDetail salesOrderDetailList) {
		getSalesOrderDetailList().add(salesOrderDetailList);
		salesOrderDetailList.setSalesOrder(this);

		return salesOrderDetailList;
	}

	public SalesOrderDetail removeSalesOrderDetailList(SalesOrderDetail salesOrderDetailList) {
		getSalesOrderDetailList().remove(salesOrderDetailList);
		salesOrderDetailList.setSalesOrder(null);

		return salesOrderDetailList;
	}

	/**
	 * 组织架构
	 * 
	 * @return organization 组织架构
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * 组织架构
	 * 
	 * @param organization
	 *            组织架构
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Double getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(Double salesMoney) {
		this.salesMoney = salesMoney;
	}

	public Double getRetMoney() {
		return retMoney;
	}

	public void setRetMoney(Double retMoney) {
		this.retMoney = retMoney;
	}

	public String getOrderNoN() {
		return OrderNoN;
	}

	public void setOrderNoN(String orderNoN) {
		OrderNoN = orderNoN;
	}

	/**
	 * 是否已经上报到内销系统(true=已经上传)
	 * 
	 * @return isUpload 是否已经上报到内销系统(true=已经上传)
	 */
	public Boolean getIsUpload() {
		return isUpload;
	}

	/**
	 * 是否已经上报到内销系统(true=已经上传)
	 * 
	 * @param isUpload 是否已经上报到内销系统(true=已经上传)
	 */
	public void setIsUpload(Boolean isUpload) {
		this.isUpload = isUpload;
	}

}
