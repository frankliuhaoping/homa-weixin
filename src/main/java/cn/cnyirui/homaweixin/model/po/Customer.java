package cn.cnyirui.homaweixin.model.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.utils.CurrentUserUtils;

/**
 * The persistent class for the customer database table. 业务表_顾客
 */
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 地址
	 */
	@Column(length = 300)
	private String address;

	/**
	 * 姓名
	 */
	@Column(length = 100)
	private String name;

	/**
	 * 电话
	 */
	@Column(length = 100)
	private String telephone;

	/**
	 * 导购员
	 */
	// bi-directional many-to-one association to Employee
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "salerId")
	private Employee saler;

	/**
	 * 微信用户
	 */
	// bi-directional many-to-one association to WeiXinUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weixinUserId")
	private WeiXinUser weiXinUser;
	
	

	/**
	 * 销售订单
	 */
	// bi-directional many-to-one association to SalesOrder
	@OneToMany(mappedBy = "customer")
	private List<SalesOrder> salesOrderList;

	/**
	 * 地址
	 * 
	 * @return address 地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 地址
	 * 
	 * @param address
	 *            地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 姓名
	 * 
	 * @return name 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 姓名
	 * 
	 * @param name
	 *            姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 电话
	 * 
	 * @return telephone 电话
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 电话
	 * 
	 * @param telephone
	 *            电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * 微信用户
	 * 
	 * @return weiXinUser 微信用户
	 */
	public WeiXinUser getWeiXinUser() {
		return weiXinUser;
	}

	/**
	 * 微信用户
	 * 
	 * @param weiXinUser
	 *            微信用户
	 */
	public void setWeiXinUser(WeiXinUser weiXinUser) {
		this.weiXinUser = weiXinUser;
	}

	/**
	 * 销售订单
	 * 
	 * @return salesOrderList 销售订单
	 */
	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	/**
	 * 销售订单
	 * 
	 * @param salesOrderList
	 *            销售订单
	 */
	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public SalesOrder addSalesOrderList(SalesOrder salesOrderList) {
		getSalesOrderList().add(salesOrderList);
		salesOrderList.setCustomer(this);

		return salesOrderList;
	}

	public SalesOrder removeSalesOrderList(SalesOrder salesOrderList) {
		getSalesOrderList().remove(salesOrderList);
		salesOrderList.setCustomer(null);

		return salesOrderList;
	}
}
