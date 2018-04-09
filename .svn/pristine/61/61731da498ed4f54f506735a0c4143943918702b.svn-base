package cn.cnyirui.homaweixin.model.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * The persistent class for the sign_record database table.签到记录
 * 
 */
@Entity
@Table(name = "sign_record")
public class SignRecord extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 签到时间
	 */
	@Column
	private Date signTime;

	/**
	 * 地理位置
	 * 
	 */
	@Column
	private String address;

	/**
	 * 签到类型 1下班，0上班
	 */
	@Column
	private Integer signType = 0;

	/**
	 * 签到的地理位置
	 */
	private String location;

	/**
	 * 签到人（员工）
	 */
	// bi-directional many-to-one association to Employee
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	/**
	 * 签到人的组织架构
	 */
	// bi-directional many-to-one association to Employee
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId")
	private Organization organization;

	/**
	 * 签到时间
	 * 
	 * @return signTime 签到时间
	 */
	public Date getSignTime() {
		return signTime;
	}

	/**
	 * 签到时间
	 * 
	 * @param signTime
	 *            签到时间
	 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	/**
	 * 签到类型
	 * 
	 * @return signType 签到类型
	 */
	public Integer getSignType() {
		return signType;
	}

	/**
	 * 签到类型
	 * 
	 * @param signType
	 *            签到类型
	 */
	public void setSignType(Integer signType) {
		this.signType = signType;
	}

	/**
	 * 签到人（员工）
	 * 
	 * @return employee 签到人（员工）
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * 签到人（员工）
	 * 
	 * @param employee
	 *            签到人（员工）
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * 签到的地理位置
	 * 
	 * @return location 签到的地理位置
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 签到的地理位置
	 * 
	 * @param location 签到的地理位置
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 签到人的组织架构
	 * 
	 * @return organization 签到人的组织架构
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * 签到人的组织架构
	 * 
	 * @param organization 签到人的组织架构
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * 地理位置
	 * 
	 * @return address 地理位置
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 地理位置
	 * 
	 * @param address 地理位置
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
