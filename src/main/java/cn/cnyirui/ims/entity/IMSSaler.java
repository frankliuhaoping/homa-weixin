package cn.cnyirui.ims.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 导购员
 * 
 * @author pengzhihua
 *
 */
@Entity
@Table(name = "CRM_MAK_SALER_MB_V")
public class IMSSaler implements IMSEmployeeEntity {
	private static final long serialVersionUID = -7806044937317516302L;

	@Id
	@Column(name = "EMPID")
	private Long id;

	/**
	 * 编码
	 */
	@Column(name = "SALER_CODE")
	private String code;

	/**
	 * 名称
	 */
	@Column(name = "EMPNAME")
	private String name;

	/**
	 * 证件号码
	 */
	@Column(name = "CARDNO")
	private String cardNo;

	/**
	 * 入职日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INDATE")
	private Date inDate;

	/**
	 * 出生日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BIRTHDATE")
	private Date birthdate;

	/**
	 * 手机号码
	 */
	@Column(name = "MOBILENO")
	private String mobileNo;

	/**
	 * 办公号码
	 */
	@Column(name = "OTEL")
	private String officeTel;

	/**
	 * 联系地址
	 */
	@Column(name = "HADDRESS")
	private String address;

	/**
	 * 性别
	 */
	@Column(name = "GENDER")
	private String sex;

	/**
	 * 职位
	 */
	@Column(name = "POSITION")
	private String position;

	/**
	 * IMS门店ID
	 */
	@Column(name = "STORE_ID")
	private Long parentId;

	/**
	 * IMS的门店名称
	 */
	@Column(name = "STORE_NAME")
	private String parentName;

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

	@Override
	public Long getParentId() {
		return parentId;
	}

	@Override
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getParentName() {
		return parentName;
	}

	@Override
	public void setParentName(String parentName) {
		this.parentName = name;
	}

	@Override
	public String getCardNo() {
		return cardNo;
	}

	@Override
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public Date getInDate() {
		return inDate;
	}

	@Override
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	@Override
	public Date getBirthdate() {
		return birthdate;
	}

	@Override
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String getMobileNo() {
		return mobileNo;
	}

	@Override
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String getOfficeTel() {
		return officeTel;
	}

	@Override
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getSex() {
		return sex;
	}

	@Override
	public void setSex(String sex) {
		this.sex = sex;
	}

	@Transient
	@Override
	public String getEmail() {
		return null;
	}

	@Transient
	public void setEmail(String email) {
	}

	/**
	 * 职位 1=专职 2=兼职
	 * 
	 * @return position 职位
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 职位
	 * 
	 * @param position 职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}

}
