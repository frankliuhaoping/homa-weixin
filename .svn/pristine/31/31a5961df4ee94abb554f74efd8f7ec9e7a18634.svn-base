package cn.cnyirui.homaweixin.model.po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.LogicDeleteableEntity;

/**
 * 组织架构
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Organization extends LogicDeleteableEntity implements IMSImportedEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 主管
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "directorId")
	private Employee director;

	/**
	 * IMS组织架构id
	 */
	@Column
	private Long IMSId;

	/**
	 * 是否是IMS数据
	 */
	@Column
	private Boolean isIMSData = Boolean.FALSE;;

	/**
	 * IMS组织架构类型(0=业务组织，1=办事处，2=门店)
	 */
	@Column
	private Integer IMSType;

	@Column(length = 100)
	private String IMSCode;
	/**
	 * 组织架构名称
	 */
	@Column(length = 100)
	private String name;

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String remark;

	/**
	 * 员工
	 */
	// bi-directional many-to-one association to Employee
	@OneToMany(mappedBy = "organization")
	private List<Employee> employeeList;

	/**
	 * 上级组织架构
	 */
	// bi-directional many-to-one association to Organization
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Organization parent;

	/**
	 * 下级组织架构
	 */
	// bi-directional many-to-one association to Organization
	@OneToMany(mappedBy = "parent")
	@OrderBy("name")
	private List<Organization> subOrganizationList = new ArrayList<Organization>(0);

	/**
	 * 层级数
	 */
	@Column
	private Integer depth;

	/**
	 * 所有parentId
	 */
	@Column(length = 2000)
	private String parentIds;

	/**
	 * 所有parentName
	 */
	@Column(length = 2000)
	private String parentNames;

	/**
	 * 销售任务
	 */
	// bi-directional many-to-one association to SalesTask
	@OneToMany(mappedBy = "organization")
	private List<SalesTask> salesTaskList;

	/**
	 * 允许查看的员工列表
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinTable(name = "EMP_ORG_PERMISSION", joinColumns = {
	        @JoinColumn(name = "organizationId") }, inverseJoinColumns = { @JoinColumn(name = "employeeId") })
	private List<Employee> allowViewEmployeeList;

	/**
	 * 主管
	 * 
	 * @return director 主管
	 */
	public Employee getDirector() {
		return director;
	}

	/**
	 * 主管
	 * 
	 * @param director
	 *            主管
	 */
	public void setDirector(Employee director) {
		this.director = director;
	}

	/**
	 * 组织架构名称
	 * 
	 * @return name 组织架构名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 组织架构名称
	 * 
	 * @param name
	 *            组织架构名称
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
	 * 员工
	 * 
	 * @return employeeList 员工
	 */
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	/**
	 * 员工
	 * 
	 * @param employeeList
	 *            员工
	 */
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	/**
	 * 上级组织架构
	 * 
	 * @return parent 上级组织架构
	 */
	public Organization getParent() {
		return parent;
	}

	/**
	 * 上级组织架构
	 * 
	 * @param parent
	 *            上级组织架构
	 */
	public void setParent(Organization parent) {
		this.parent = parent;
	}

	/**
	 * 下级组织架构
	 * 
	 * @return subOrganizationList 下级组织架构
	 */
	public List<Organization> getSubOrganizationList() {
		return subOrganizationList;
	}

	/**
	 * 下级组织架构
	 * 
	 * @param subOrganizationList
	 *            下级组织架构
	 */
	public void setSubOrganizationList(List<Organization> subOrganizationList) {
		this.subOrganizationList = subOrganizationList;
	}

	/**
	 * 销售任务
	 * 
	 * @return salesTaskList 销售任务
	 */
	public List<SalesTask> getSalesTaskList() {
		return salesTaskList;
	}

	/**
	 * 销售任务
	 * 
	 * @param salesTaskList
	 *            销售任务
	 */
	public void setSalesTaskList(List<SalesTask> salesTaskList) {
		this.salesTaskList = salesTaskList;
	}

	/**
	 * IMS组织架构类型(0=业务组织，1=办事处，2=门店)
	 * 
	 * @return iMSType IMS组织架构类型(0=业务组织，1=办事处，2=门店)
	 */
	public Integer getIMSType() {
		return IMSType;
	}

	/**
	 * IMS组织架构类型(0=业务组织，1=办事处，2=门店)
	 * 
	 * @param iMSType IMS组织架构类型(0=业务组织，1=办事处，2=门店)
	 */
	public void setIMSType(Integer iMSType) {
		IMSType = iMSType;
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

	/**
	 * 允许查看的员工列表
	 * 
	 * @return allowViewEmployeeList 允许查看的员工列表
	 */
	public List<Employee> getAllowViewEmployeeList() {
		return allowViewEmployeeList;
	}

	/**
	 * 允许查看的员工列表
	 * 
	 * @param allowViewEmployeeList 允许查看的员工列表
	 */
	public void setAllowViewEmployeeList(List<Employee> allowViewEmployeeList) {
		this.allowViewEmployeeList = allowViewEmployeeList;
	}

	/**
	 * 所有parentId
	 * 
	 * @return parentIds 所有parentId
	 */
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * 所有parentId
	 * 
	 * @param parentIds 所有parentId
	 */
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * 所有parentName
	 * 
	 * @return parentNames 所有parentName
	 */
	public String getParentNames() {
		return parentNames;
	}

	/**
	 * 所有parentName
	 * 
	 * @param parentNames 所有parentName
	 */
	public void setParentNames(String parentNames) {
		this.parentNames = parentNames;
	}

	/**
	 * 层级数
	 * 
	 * @return depth 层级数
	 */
	public Integer getDepth() {
		return depth;
	}

	/**
	 * 层级数
	 * 
	 * @param depth 层级数
	 */
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getIMSCode() {
		return IMSCode;
	}

	public void setIMSCode(String iMSCode) {
		IMSCode = iMSCode;
	}

}
