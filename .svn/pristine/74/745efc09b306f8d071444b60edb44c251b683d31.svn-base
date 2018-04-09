package cn.cnyirui.homaweixin.model.po;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * The persistent class for the sales_task database table.销售任务
 * 
 */
@Entity
@Table(name = "sales_task")
public class SalesTask extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 一月
	 */
	@Column
	private Long month1 = 0L;

	/**
	 * 十月
	 */
	@Column
	private Long month10 = 0L;

	/**
	 * 十一月
	 */
	@Column
	private Long month11 = 0L;

	/**
	 * 十二月
	 */
	@Column
	private Long month12 = 0L;

	/**
	 * 二月
	 */
	@Column
	private Long month2 = 0L;

	/**
	 * 三月
	 */
	@Column
	private Long month3 = 0L;

	/**
	 * 四月
	 */
	@Column
	private Long month4 = 0L;

	/**
	 * 五月
	 */
	@Column
	private Long month5 = 0L;

	/**
	 * 六月
	 */
	@Column
	private Long month6 = 0L;

	/**
	 * 七月
	 */
	@Column
	private Long month7 = 0L;

	/**
	 * 八月
	 */
	@Column
	private Long month8 = 0L;

	/**
	 * 九月
	 */
	@Column
	private Long month9 = 0L;

	/**
	 * 年
	 */
	Calendar a = Calendar.getInstance();
	@Column
	private Integer year = a.get(Calendar.YEAR);

	/**
	 * 类型 0表示金额，1表示数量
	 */
	@Column
	private Integer type = 1;

	/**
	 * 组织架构
	 */
	// bi-directional many-to-one association to Organization
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId")
	private Organization organization;

	/**
	 * 员工
	 */
	// bi-directional many-to-one association to Employee
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	/**
	 * 一月
	 * 
	 * @return month1 一月
	 */
	public Long getMonth1() {
		return month1;
	}

	/**
	 * 一月
	 * 
	 * @param month1
	 *            一月
	 */
	public void setMonth1(Long month1) {
		this.month1 = month1;
	}

	/**
	 * 十月
	 * 
	 * @return month10 十月
	 */
	public Long getMonth10() {
		return month10;
	}

	/**
	 * 十月
	 * 
	 * @param month10
	 *            十月
	 */
	public void setMonth10(Long month10) {
		this.month10 = month10;
	}

	/**
	 * 十一月
	 * 
	 * @return month11 十一月
	 */
	public Long getMonth11() {
		return month11;
	}

	/**
	 * 十一月
	 * 
	 * @param month11
	 *            十一月
	 */
	public void setMonth11(Long month11) {
		this.month11 = month11;
	}

	/**
	 * 十二月
	 * 
	 * @return month12 十二月
	 */
	public Long getMonth12() {
		return month12;
	}

	/**
	 * 十二月
	 * 
	 * @param month12
	 *            十二月
	 */
	public void setMonth12(Long month12) {
		this.month12 = month12;
	}

	/**
	 * 二月
	 * 
	 * @return month2 二月
	 */
	public Long getMonth2() {
		return month2;
	}

	/**
	 * 二月
	 * 
	 * @param month2
	 *            二月
	 */
	public void setMonth2(Long month2) {
		this.month2 = month2;
	}

	/**
	 * 三月
	 * 
	 * @return month3 三月
	 */
	public Long getMonth3() {
		return month3;
	}

	/**
	 * 三月
	 * 
	 * @param month3
	 *            三月
	 */
	public void setMonth3(Long month3) {
		this.month3 = month3;
	}

	/**
	 * 四月
	 * 
	 * @return month4 四月
	 */
	public Long getMonth4() {
		return month4;
	}

	/**
	 * 四月
	 * 
	 * @param month4
	 *            四月
	 */
	public void setMonth4(Long month4) {
		this.month4 = month4;
	}

	/**
	 * 五月
	 * 
	 * @return month5 五月
	 */
	public Long getMonth5() {
		return month5;
	}

	/**
	 * 五月
	 * 
	 * @param month5
	 *            五月
	 */
	public void setMonth5(Long month5) {
		this.month5 = month5;
	}

	/**
	 * 六月
	 * 
	 * @return month6 六月
	 */
	public Long getMonth6() {
		return month6;
	}

	/**
	 * 六月
	 * 
	 * @param month6
	 *            六月
	 */
	public void setMonth6(Long month6) {
		this.month6 = month6;
	}

	/**
	 * 七月
	 * 
	 * @return month7 七月
	 */
	public Long getMonth7() {
		return month7;
	}

	/**
	 * 七月
	 * 
	 * @param month7
	 *            七月
	 */
	public void setMonth7(Long month7) {
		this.month7 = month7;
	}

	/**
	 * 八月
	 * 
	 * @return month8 八月
	 */
	public Long getMonth8() {
		return month8;
	}

	/**
	 * 八月
	 * 
	 * @param month8
	 *            八月
	 */
	public void setMonth8(Long month8) {
		this.month8 = month8;
	}

	/**
	 * 九月
	 * 
	 * @return month9 九月
	 */
	public Long getMonth9() {
		return month9;
	}

	/**
	 * 九月
	 * 
	 * @param month9
	 *            九月
	 */
	public void setMonth9(Long month9) {
		this.month9 = month9;
	}

	/**
	 * 年
	 * 
	 * @return year 年
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * 年
	 * 
	 * @param year
	 *            年
	 */
	public void setYear(Integer year) {
		this.year = year;
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
	 * 类型0表示金额，1表示数量
	 * 
	 * @return type 类型0表示金额，1表示数量
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型0表示金额，1表示数量
	 * 
	 * @param type
	 *            类型0表示金额，1表示数量
	 */
	public void setType(Integer type) {
		this.type = type;
	}

}
