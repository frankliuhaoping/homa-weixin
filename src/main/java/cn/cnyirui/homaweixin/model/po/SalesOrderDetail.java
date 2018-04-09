package cn.cnyirui.homaweixin.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * The persistent class for the sales_order_detail database table.业务表_销售订单明细
 * 
 */
@Entity
@Table(name = "sales_order_detail")
public class SalesOrderDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 价格
	 */
	@Column
	private Double price;

	/**
	 * 数量
	 */
	@Column
	private Integer qty;

	/**
	 * 返现金额
	 */
	private Double cashBack = 0.00;

	/**
	 * 产品
	 */
	// bi-directional many-to-one association to Product
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	/**
	 * 销售订单
	 */
	// bi-directional many-to-one association to SalesOrder
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salesOrderId")
	private SalesOrder salesOrder;

	/**
	 * 价格
	 * 
	 * @return price 价格
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 价格
	 * 
	 * @param price
	 *            价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 数量
	 * 
	 * @return qty 数量
	 */
	public Integer getQty() {
		return qty;
	}

	/**
	 * 数量
	 * 
	 * @param qty
	 *            数量
	 */
	public void setQty(Integer qty) {
		this.qty = qty;
	}

	/**
	 * 产品
	 * 
	 * @return product 产品
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * 产品
	 * 
	 * @param product
	 *            产品
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * 销售订单
	 * 
	 * @return salesOrder 销售订单
	 */
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	/**
	 * 销售订单
	 * 
	 * @param salesOrder
	 *            销售订单
	 */
	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	/**
	 * 返现金额
	 * 
	 * @return cashBack 返现金额
	 */
	public Double getCashBack() {
		return cashBack;
	}

	/**
	 * 返现金额
	 * 
	 * @param cashBack
	 *            返现金额
	 */
	public void setCashBack(Double cashBack) {
		this.cashBack = cashBack;
	}

}
