package cn.cnyirui.homaweixin.model.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.model.po.base.LogicDeleteableEntity;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.utils.Constants;

/**
 * The persistent class for the employee database table.系统表_员工
 * 
 */
@Entity
@Table(name = "employee")
@DynamicInsert
@DynamicUpdate
public class Employee extends LogicDeleteableEntity implements IMSImportedEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 地址
	 */
	@Column(length = 300)
	private String address;

	/**
	 * 出生日期
	 */
	@DateTimeFormat(pattern = Constants.DATE_FORMAT)
	@Column
	private Date birthdate;

	/**
	 * 角色类型
	 * 
	 * @see SysRoleType
	 */
	@Column
	private String sysRoleType;

	/**
	 * 兼职/专职 类型
	 * 
	 * 0 = 专职，1 = 兼职
	 */
	@Column
	private Integer salerType;

	/**
	 * IMS系统员工ID
	 */
	@Column
	private Long IMSId;

	/**
	 * IMS员工类型(0=员工，1=导购员)
	 */
	@Column
	private Integer IMSType;

	/**
	 * 入职日期
	 */
	@DateTimeFormat(pattern = Constants.DATE_FORMAT)
	@Column
	private Date inDate;

	/**
	 * 是否是IMS数据
	 */
	@Column
	private Boolean isIMSData = Boolean.FALSE;

	/**
	 * 手机号码
	 */
	@Column(length = 100)
	private String mobileNo;

	/**
	 * MSN
	 */
	@Column(length = 100)
	private String msn;

	/**
	 * 工号
	 */
	private String code;

	/**
	 * 姓名
	 */
	@Column(length = 100)
	private String name;

	/**
	 * 昵称
	 */
	@Column(length = 100)
	private String nickName;

	/**
	 * 办公电话
	 */
	@Column(length = 100)
	private String officeTel;

	/**
	 * QQ
	 */
	@Column(length = 100)
	private String qq;

	/**
	 * 邮箱
	 */
	@Column(length = 100)
	private String eMail;

	/**
	 * 备注
	 */
	@Column(length = 100)
	private String remark;

	/**
	 * 性别 0--男 1--女 2--保密
	 */
	@Column
	private Integer sex = 2;

	/**
	 * 微信号
	 */
	@Column(length = 100)
	private String weixinNo;
	/**
	 * 头像
	 */
	@Column(length = 500)
	private String headImgUrl;

	/**
	 * 身份证号
	 */
	@Column(length = 20)
	private String idNumber;
	/**
	 * 顾客
	 */
	// bi-directional many-to-one association to Customer
	@OneToMany(mappedBy = "saler")
	private List<Customer> customerList;

	/**
	 * 登录帐号
	 */
	// bi-directional many-to-one association to SysUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sysUserId")
	private SysUser sysUser;

	/**
	 * 组织架构
	 */
	// bi-directional many-to-one association to Organization
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId")
	private Organization organization;

	/**
	 * 员工工资
	 */
	// bi-directional many-to-one association to EmployeeWage
	@OneToMany(mappedBy = "employee")
	private List<EmployeeWage> employeeWageList;

	/**
	 * 销售订单
	 */
	// bi-directional many-to-one association to SalesOrder
	@OneToMany(mappedBy = "saler")
	private List<SalesOrder> salesOrderList;

	/**
	 * 销售任务
	 */
	// bi-directional many-to-one association to SalesTask
	@OneToMany(mappedBy = "employee")
	private List<SalesTask> salesTaskList;

	/**
	 * 签到记录
	 */
	// bi-directional many-to-one association to SignRecord
	@OneToMany(mappedBy = "employee")
	private List<SignRecord> signRecordList;

	/**
	 * 可查看数据的组织架构列表
	 */
	@ManyToMany(mappedBy = "allowViewEmployeeList", fetch = FetchType.LAZY)
	private List<Organization> canViewOrganizationList;

	/**
	 * 工号
	 * 
	 * @return code 工号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 工号
	 * 
	 * @param code
	 *            工号
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * 出生日期
	 * 
	 * @return birthdate 出生日期
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * 出生日期
	 * 
	 * @param birthdate
	 *            出生日期
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * iMSId
	 * 
	 * @return iMSId iMSId
	 */
	public Long getIMSId() {
		return IMSId;
	}

	/**
	 * iMSId
	 * 
	 * @param iMSId iMSId
	 */
	public void setIMSId(Long iMSId) {
		IMSId = iMSId;
	}

	/**
	 * 入职日期
	 * 
	 * @return inDate 入职日期
	 */
	public Date getInDate() {
		return inDate;
	}

	/**
	 * 入职日期
	 * 
	 * @param inDate
	 *            入职日期
	 */
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	/**
	 * 是否是IMS数据
	 * 
	 * @return isIMSData 是否是IMS数据
	 */
	public Boolean getIsIMSData() {
		return isIMSData;
	}

	/**
	 * 是否是IMS数据
	 * 
	 * @param isIMSData
	 *            是否是IMS数据
	 */
	public void setIsIMSData(Boolean isIMSData) {
		this.isIMSData = isIMSData;
	}

	/**
	 * 手机号码
	 * 
	 * @return mobileNo 手机号码
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * 手机号码
	 * 
	 * @param mobileNo
	 *            手机号码
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * MSN
	 * 
	 * @return msn MSN
	 */
	public String getMsn() {
		return msn;
	}

	/**
	 * MSN
	 * 
	 * @param msn
	 *            MSN
	 */
	public void setMsn(String msn) {
		this.msn = msn;
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
	 * 昵称
	 * 
	 * @return nickName 昵称
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * 昵称
	 * 
	 * @param nickName
	 *            昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * 办公电话
	 * 
	 * @return officeTel 办公电话
	 */
	public String getOfficeTel() {
		return officeTel;
	}

	/**
	 * 办公电话
	 * 
	 * @param officeTel
	 *            办公电话
	 */
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	/**
	 * QQ
	 * 
	 * @return qq QQ
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * QQ
	 * 
	 * @param qq
	 *            QQ
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 性别
	 * 
	 * @return sex 性别
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 性别
	 * 
	 * @param sex
	 *            性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 微信号
	 * 
	 * @return weixinNo 微信号
	 */
	public String getWeixinNo() {
		return weixinNo;
	}

	/**
	 * 微信号
	 * 
	 * @param weixinNo
	 *            微信号
	 */
	public void setWeixinNo(String weixinNo) {
		this.weixinNo = weixinNo;
	}

	/**
	 * 顾客
	 * 
	 * @return customerList 顾客
	 */
	public List<Customer> getCustomerList() {
		return customerList;
	}

	/**
	 * 顾客
	 * 
	 * @param customerList
	 *            顾客
	 */
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	/**
	 * 登录帐号
	 * 
	 * @return sysUser 登录帐号
	 */
	public SysUser getSysUser() {
		return sysUser;
	}

	/**
	 * 登录帐号
	 * 
	 * @param sysUser
	 *            登录帐号
	 */
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
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
	 * 员工工资
	 * 
	 * @return employeeWageList 员工工资
	 */
	public List<EmployeeWage> getEmployeeWageList() {
		return employeeWageList;
	}

	/**
	 * 员工工资
	 * 
	 * @param employeeWageList
	 *            员工工资
	 */
	public void setEmployeeWageList(List<EmployeeWage> employeeWageList) {
		this.employeeWageList = employeeWageList;
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

	/**
	 * 签到记录
	 * 
	 * @return signRecordList 签到记录
	 */
	public List<SignRecord> getSignRecordList() {
		return signRecordList;
	}

	/**
	 * 签到记录
	 * 
	 * @param signRecordList
	 *            签到记录
	 */
	public void setSignRecordList(List<SignRecord> signRecordList) {
		this.signRecordList = signRecordList;
	}

	/**
	 * 邮箱
	 * 
	 * @return eMail 邮箱
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * 邮箱
	 * 
	 * @param eMail
	 *            邮箱
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * 备注
	 * 
	 * @return reMark 备注
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

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * 可查看数据的组织架构列表
	 * 
	 * @return canViewOrganizationList 可查看数据的组织架构列表
	 */
	public List<Organization> getCanViewOrganizationList() {
		return canViewOrganizationList;
	}

	/**
	 * 可查看数据的组织架构列表
	 * 
	 * @param canViewOrganizationList
	 *            可查看数据的组织架构列表
	 */
	public void setCanViewOrganizationList(List<Organization> canViewOrganizationList) {
		this.canViewOrganizationList = canViewOrganizationList;
	}

	/**
	 * 角色类型
	 * 
	 * @return sysRoleType 角色类型
	 */
	public String getSysRoleType() {
		return sysRoleType;
	}

	public String getSysRoleTypeText() {
		sysRoleType = getSysRoleType();
		if (sysRoleType != null) {
			return SysRoleType.valueOf(getSysRoleType()).getText();
		} else {
			return null;
		}

	}

	/**
	 * 角色类型
	 * 
	 * @param sysRoleType 角色类型
	 */
	public void setSysRoleType(String sysRoleType) {
		this.sysRoleType = sysRoleType;
	}

	/**
	 * 兼职专职类型0=专职，1=兼职
	 * 
	 * @return salerType 兼职专职类型0=专职，1=兼职
	 */
	public Integer getSalerType() {
		return salerType;
	}

	/**
	 * 兼职专职类型0=专职，1=兼职
	 * 
	 * @param salerType 兼职专职类型0=专职，1=兼职
	 */
	public void setSalerType(Integer salerType) {
		this.salerType = salerType;
	}

	/**
	 * 身份证号
	 * 
	 * @return idNumber 身份证号
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * 身份证号
	 * 
	 * @param idNumber 身份证号
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * iMSType
	 * 
	 * @return iMSType iMSType
	 */
	public Integer getIMSType() {
		return IMSType;
	}

	/**
	 * iMSType
	 * 
	 * @param iMSType iMSType
	 */
	public void setIMSType(Integer iMSType) {
		IMSType = iMSType;
	}

	@Transient
	public WeiXinUser getWeiXinUser() {
		WeiXinUser weiXinUser = null;
		SysUser sysUser = getSysUser();
		if (sysUser != null) {
			weiXinUser = sysUser.getWeiXinUser();
		}
		return weiXinUser;
	}

}
