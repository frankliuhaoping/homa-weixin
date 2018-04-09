package cn.cnyirui.ims.entity;

import java.util.Date;

public interface IMSEmployeeEntity extends IMSReadedEntity {

	/**
	 * 证件号码
	 * 
	 * @return cardNo 证件号码
	 */
	String getCardNo();

	/**
	 * 证件号码
	 * 
	 * @param cardNo 证件号码
	 */
	void setCardNo(String cardNo);

	/**
	 * 入职日期
	 * 
	 * @return inDate 入职日期
	 */
	Date getInDate();

	/**
	 * 入职日期
	 * 
	 * @param inDate 入职日期
	 */
	void setInDate(Date inDate);

	/**
	 * 出生日期
	 * 
	 * @return birthdate 出生日期
	 */
	Date getBirthdate();

	/**
	 * 出生日期
	 * 
	 * @param birthdate 出生日期
	 */
	void setBirthdate(Date birthdate);

	/**
	 * 手机号码
	 * 
	 * @return mobileNo 手机号码
	 */
	String getMobileNo();

	/**
	 * 手机号码
	 * 
	 * @param mobileNo 手机号码
	 */
	void setMobileNo(String mobileNo);

	/**
	 * 办公号码
	 * 
	 * @return officeTel 办公号码
	 */
	String getOfficeTel();

	/**
	 * 办公号码
	 * 
	 * @param officeTel 办公号码
	 */
	void setOfficeTel(String officeTel);

	/**
	 * 联系地址
	 * 
	 * @return address 联系地址
	 */
	String getAddress();

	/**
	 * 联系地址
	 * 
	 * @param address 联系地址
	 */
	void setAddress(String address);

	/**
	 * 性别
	 * 
	 * @return sex 性别
	 */
	String getSex();

	/**
	 * 性别
	 * 
	 * @param sex 性别
	 */
	void setSex(String sex);

	/**
	 * 邮件
	 * 
	 * @return email 邮件
	 */
	String getEmail();

	/**
	 * 邮件
	 * 
	 * @param email 邮件
	 */
	void setEmail(String email);
}
