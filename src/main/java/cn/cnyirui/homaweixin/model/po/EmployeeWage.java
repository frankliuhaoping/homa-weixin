package cn.cnyirui.homaweixin.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * The persistent class for the employee_wage database table.业务表_员工工资
 * 
 */
@Entity
@Table(name = "employee_wage")
public class EmployeeWage extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 实发工资
	 */
	@Column
	private Double actualWage = 0.0;

	/**
	 * 工资是否异常
	 * true为有异常，false为无异常
	 * 默认为false(无异常)
	 */
	@Column
	private Boolean isAbnormal = Boolean.FALSE;

	/**
	 * 是否已阅
	 * true为已阅，false为未阅
	 */
	private Boolean isRead = Boolean.FALSE;

	/**
	 * 工龄工资
	 */
	@Column
	private Double ageWage = 0.0;

	/**
	 * 奖励工资
	 */
	@Column
	private Double awardWage = 0.0;

	/**
	 * 基本底薪
	 */
	@Column
	private Double basicSalary = 0.0;

	/**
	 * 社保(公司)
	 */
	@Column
	private Double companyInsurance = 0.0;

	/**
	 * 审核工资
	 */
	@Column
	private Double examineWage = 0.0;

	/**
	 * 过节费
	 */
	@Column
	private Double holidayFee = 0.0;

	/**
	 * 其他费用
	 */
	@Column
	private Double otherFee = 0.0;

	/**
	 * 加班工资
	 */
	@Column
	private Double overtimeWage = 0.0;

	/**
	 * 提成工资
	 */
	@Column
	private Double royaltyWage = 0.0;

	/**
	 * 社保(个人)
	 */
	@Column
	private Double socialInsurance = 0.0;

	/**
	 * 扣税
	 */
	@Column
	private Double taxDeduction = 0.0;

	/**
	 * 应发工资
	 */
	@Column
	private Double virtualWage = 0.0;

	/**
	 * 工资月份
	 */
	@Column
	private Integer wageMonth;

	/**
	 * 第几次工资
	 */
	@Column
	private Integer wageVersion;

	/**
	 * 工资年份
	 */
	@Column
	private Integer wageYear;

	/**
	 * 员工
	 */
	// bi-directional many-to-one association to Employee
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	/**
	 * 是否有微信通知
	 */
	private Boolean isInformed = Boolean.FALSE;

	/**
	 * 实发工资
	 * 
	 * @return actualWage 实发工资
	 */
	public Double getActualWage() {
		return actualWage;
	}

	/**
	 * 实发工资
	 * 
	 * @param actualWage
	 *            实发工资
	 */
	public void setActualWage(Double actualWage) {
		this.actualWage = actualWage;
	}

	/**
	 * 工龄工资
	 * 
	 * @return ageWage 工龄工资
	 */
	public Double getAgeWage() {
		return ageWage;
	}

	/**
	 * 工龄工资
	 * 
	 * @param ageWage
	 *            工龄工资
	 */
	public void setAgeWage(Double ageWage) {
		this.ageWage = ageWage;
	}

	/**
	 * 奖励工资
	 * 
	 * @return awardWage 奖励工资
	 */
	public Double getAwardWage() {
		return awardWage;
	}

	/**
	 * 奖励工资
	 * 
	 * @param awardWage
	 *            奖励工资
	 */
	public void setAwardWage(Double awardWage) {
		this.awardWage = awardWage;
	}

	/**
	 * 基本底薪
	 * 
	 * @return basicSalary 基本底薪
	 */
	public Double getBasicSalary() {
		return basicSalary;
	}

	/**
	 * 基本底薪
	 * 
	 * @param basicSalary
	 *            基本底薪
	 */
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	/**
	 * 社保(公司)
	 * 
	 * @return companyInsurance 社保(公司)
	 */
	public Double getCompanyInsurance() {
		return companyInsurance;
	}

	/**
	 * 社保(公司)
	 * 
	 * @param companyInsurance
	 *            社保(公司)
	 */
	public void setCompanyInsurance(Double companyInsurance) {
		this.companyInsurance = companyInsurance;
	}

	/**
	 * 审核工资
	 * 
	 * @return examineWage 审核工资
	 */
	public Double getExamineWage() {
		return examineWage;
	}

	/**
	 * 审核工资
	 * 
	 * @param examineWage
	 *            审核工资
	 */
	public void setExamineWage(Double examineWage) {
		this.examineWage = examineWage;
	}

	/**
	 * 过节费
	 * 
	 * @return holidayFee 过节费
	 */
	public Double getHolidayFee() {
		return holidayFee;
	}

	/**
	 * 过节费
	 * 
	 * @param holidayFee
	 *            过节费
	 */
	public void setHolidayFee(Double holidayFee) {
		this.holidayFee = holidayFee;
	}

	/**
	 * 其他费用
	 * 
	 * @return otherFee 其他费用
	 */
	public Double getOtherFee() {
		return otherFee;
	}

	/**
	 * 其他费用
	 * 
	 * @param otherFee
	 *            其他费用
	 */
	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}

	/**
	 * 加班工资
	 * 
	 * @return overtimeWage 加班工资
	 */
	public Double getOvertimeWage() {
		return overtimeWage;
	}

	/**
	 * 加班工资
	 * 
	 * @param overtimeWage
	 *            加班工资
	 */
	public void setOvertimeWage(Double overtimeWage) {
		this.overtimeWage = overtimeWage;
	}

	/**
	 * 提成工资
	 * 
	 * @return royaltyWage 提成工资
	 */
	public Double getRoyaltyWage() {
		return royaltyWage;
	}

	/**
	 * 提成工资
	 * 
	 * @param royaltyWage
	 *            提成工资
	 */
	public void setRoyaltyWage(Double royaltyWage) {
		this.royaltyWage = royaltyWage;
	}

	/**
	 * 社保(个人)
	 * 
	 * @return socialInsurance 社保(个人)
	 */
	public Double getSocialInsurance() {
		return socialInsurance;
	}

	/**
	 * 社保(个人)
	 * 
	 * @param socialInsurance
	 *            社保(个人)
	 */
	public void setSocialInsurance(Double socialInsurance) {
		this.socialInsurance = socialInsurance;
	}

	/**
	 * 扣税
	 * 
	 * @return taxDeduction 扣税
	 */
	public Double getTaxDeduction() {
		return taxDeduction;
	}

	/**
	 * 扣税
	 * 
	 * @param taxDeduction
	 *            扣税
	 */
	public void setTaxDeduction(Double taxDeduction) {
		this.taxDeduction = taxDeduction;
	}

	/**
	 * 应发工资
	 * 
	 * @return virtualWage 应发工资
	 */
	public Double getVirtualWage() {
		return virtualWage;
	}

	/**
	 * 应发工资
	 * 
	 * @param virtualWage
	 *            应发工资
	 */
	public void setVirtualWage(Double virtualWage) {
		this.virtualWage = virtualWage;
	}

	/**
	 * 工资月份
	 * 
	 * @return wageMonth 工资月份
	 */
	public Integer getWageMonth() {
		return wageMonth;
	}

	/**
	 * 工资月份
	 * 
	 * @param wageMonth
	 *            工资月份
	 */
	public void setWageMonth(Integer wageMonth) {
		this.wageMonth = wageMonth;
	}

	/**
	 * 第几次工资
	 * 
	 * @return wageVersion 第几次工资
	 */
	public Integer getWageVersion() {
		return wageVersion;
	}

	/**
	 * 第几次工资
	 * 
	 * @param wageVersion
	 *            第几次工资
	 */
	public void setWageVersion(Integer wageVersion) {
		this.wageVersion = wageVersion;
	}

	/**
	 * 工资年份
	 * 
	 * @return wageYear 工资年份
	 */
	public Integer getWageYear() {
		return wageYear;
	}

	/**
	 * 工资年份
	 * 
	 * @param wageYear
	 *            工资年份
	 */
	public void setWageYear(Integer wageYear) {
		this.wageYear = wageYear;
	}

	/**
	 * 员工
	 * 
	 * @return employee 员工
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * 员工
	 * 
	 * @param employee
	 *            员工
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the 工资是否异常true为有异常，false为无异常默认为false(无异常)
	 */
	public Boolean getIsAbnormal() {
		return isAbnormal;
	}

	/**
	 * @param 工资是否异常true为有异常，false为无异常默认为false(无异常) the isAbnormal to set
	 */
	public void setIsAbnormal(Boolean isAbnormal) {
		this.isAbnormal = isAbnormal;
	}

	/**
	 * 是否已阅true为已阅，false为未阅
	 * 
	 * @return isRead 是否已阅true为已阅，false为未阅
	 */

	public Boolean getIsRead() {
		return isRead;
	}

	/**
	 * 是否已阅true为已阅，false为未阅
	 * 
	 * @param isRead 是否已阅true为已阅，false为未阅
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	/**
	 * 是否有微信通知
	 * 
	 * @return isInformed 是否有微信通知
	 */
	public Boolean getIsInformed() {
		return isInformed;
	}

	/**
	 * 是否有微信通知
	 * 
	 * @param isInformed 是否有微信通知
	 */
	public void setIsInformed(Boolean isInformed) {
		this.isInformed = isInformed;
	}

}
