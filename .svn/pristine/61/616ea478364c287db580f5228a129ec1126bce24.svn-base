package cn.cnyirui.framework.model.po.weixin;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.rbac.SysUser;

/**
 * 微信用户
 */
@Entity
@Table(name = "weixin_user")
@DynamicInsert
@DynamicUpdate
public class WeiXinUser extends BaseEntity {

	private static final long serialVersionUID = 3355597334617111159L;

	/**
	 * 用户所在城市
	 */
	@Column(length = 100)
	private String city;

	/**
	 * 用户所在国家
	 */
	@Column(length = 100)
	private String country;

	/**
	 * 用户所在的分组ID
	 */
	@Column
	private Integer groupId;

	/**
	 * 用户头像
	 */
	@Column(length = 300)
	private String headImgUrl;

	/**
	 * 用户的昵称
	 */
	@Column(length = 100)
	private String nickname;

	/**
	 * 用户的标识，对当前公众号唯一
	 */
	@Column(length = 100)
	private String openId;

	/**
	 * 用户所在省份
	 */
	@Column(length = 100)
	private String province;

	/**
	 * 公众号运营者对粉丝的备注
	 */
	@Column(length = 300)
	private String remark;

	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	@Column
	private Integer sex;

	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	@Column
	private Date subscribeTime;

	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	 */
	@Column(length = 100)
	private String unionId;

	/**
	 * 关注、取消关注流水记录
	 */
	@OneToMany(mappedBy = "weiXinUser")
	private List<WeiXinUserSubscribeRecord> subscribeRecordList;

	/**
	 * 对应的PC后台登录帐号
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sysUserId")
	private SysUser sysUser;

	/**
	 * 用户所在城市
	 * 
	 * @return city 用户所在城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 用户所在城市
	 * 
	 * @param city 用户所在城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 用户所在国家
	 * 
	 * @return country 用户所在国家
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 用户所在国家
	 * 
	 * @param country 用户所在国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 用户所在的分组ID
	 * 
	 * @return groupId 用户所在的分组ID
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * 用户所在的分组ID
	 * 
	 * @param groupId 用户所在的分组ID
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * 用户头像
	 * 
	 * @return headImgUrl 用户头像
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * 用户头像
	 * 
	 * @param headImgUrl 用户头像
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * 用户的昵称
	 * 
	 * @return nickname 用户的昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 用户的昵称
	 * 
	 * @param nickname 用户的昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 用户的标识，对当前公众号唯一
	 * 
	 * @return openId 用户的标识，对当前公众号唯一
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * 用户的标识，对当前公众号唯一
	 * 
	 * @param openId 用户的标识，对当前公众号唯一
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 用户所在省份
	 * 
	 * @return province 用户所在省份
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 用户所在省份
	 * 
	 * @param province 用户所在省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 公众号运营者对粉丝的备注
	 * 
	 * @return remark 公众号运营者对粉丝的备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 公众号运营者对粉丝的备注
	 * 
	 * @param remark 公众号运营者对粉丝的备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 * 
	 * @return sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 * 
	 * @param sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 * 
	 * @return subscribeTime 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	public Date getSubscribeTime() {
		return subscribeTime;
	}

	/**
	 * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 * 
	 * @param subscribeTime 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	 * 
	 * @return unionId 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	 */
	public String getUnionId() {
		return unionId;
	}

	/**
	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	 * 
	 * @param unionId 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	 */
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	/**
	 * 关注、取消关注流水记录
	 * 
	 * @return subscribeRecordList 关注、取消关注流水记录
	 */
	public List<WeiXinUserSubscribeRecord> getSubscribeRecordList() {
		return subscribeRecordList;
	}

	/**
	 * 关注、取消关注流水记录
	 * 
	 * @param subscribeRecordList 关注、取消关注流水记录
	 */
	public void setSubscribeRecordList(List<WeiXinUserSubscribeRecord> subscribeRecordList) {
		this.subscribeRecordList = subscribeRecordList;
	}

	/**
	 * 对应的PC后台登录帐号
	 * 
	 * @return sysUser 对应的PC后台登录帐号
	 */
	public SysUser getSysUser() {
		return sysUser;
	}

	/**
	 * 对应的PC后台登录帐号
	 * 
	 * @param sysUser 对应的PC后台登录帐号
	 */
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

}
