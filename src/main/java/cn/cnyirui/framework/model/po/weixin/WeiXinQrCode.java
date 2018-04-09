package cn.cnyirui.framework.model.po.weixin;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.eo.WeiXinQrCodeSceneType;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.homaweixin.model.po.SalesOrder;

/**
 * 微信二信码
 * 
 * @author pengzhihua
 *
 */
@Entity
@Table(name = "WEIXIN_QRCODE")
@DynamicInsert
@DynamicUpdate
public class WeiXinQrCode extends BaseEntity {
	private static final long serialVersionUID = 8116808549116069737L;

	/**
	 * 二维码场景ID
	 */
	@Column
	private Long sceneId;

	/**
	 * 场景类型
	 * 
	 * @see WeiXinQrCodeSceneType
	 */
	@Column(length = 100)
	private String sceneType;

	/**
	 * 二维码类型(1=永久二码，0=临时二维码)
	 */
	@Column
	private Integer qrCodeType;

	/**
	 * 获取的二维码ticket
	 */
	@Column(length = 500)
	private String ticket;

	/**
	 * 二维码有效时间，以秒为单位
	 */
	@Column
	private Long expireSeconds;

	/**
	 * 二维码图片解析后的地址
	 */
	@Column(length = 500)
	private String url;

	/**
	 * 订单列表
	 */
	@OneToMany(mappedBy = "weiXinQrCode")
	private List<SalesOrder> salesOrderList;

	/**
	 * 二维码场景ID
	 * 
	 * @return sceneId 二维码场景ID
	 */
	public Long getSceneId() {
		return sceneId;
	}

	/**
	 * 二维码场景ID
	 * 
	 * @param sceneId 二维码场景ID
	 */
	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	/**
	 * 场景类型
	 * 
	 * @return sceneType 场景类型
	 */
	public String getSceneType() {
		return sceneType;
	}

	/**
	 * 场景类型
	 * 
	 * @param sceneType 场景类型
	 */
	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}

	/**
	 * 二维码类型(1=永久二码，0=临时二维码)
	 * 
	 * @return qrCodeType 二维码类型(1=永久二码，0=临时二维码)
	 */
	public Integer getQrCodeType() {
		return qrCodeType;
	}

	/**
	 * 二维码类型(1=永久二码，0=临时二维码)
	 * 
	 * @param qrCodeType 二维码类型(1=永久二码，0=临时二维码)
	 */
	public void setQrCodeType(Integer qrCodeType) {
		this.qrCodeType = qrCodeType;
	}

	/**
	 * 获取的二维码ticket
	 * 
	 * @return ticket 获取的二维码ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * 获取的二维码ticket
	 * 
	 * @param ticket 获取的二维码ticket
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * 二维码有效时间，以秒为单位
	 * 
	 * @return expireSeconds 二维码有效时间，以秒为单位
	 */
	public Long getExpireSeconds() {
		return expireSeconds;
	}

	/**
	 * 二维码有效时间，以秒为单位
	 * 
	 * @param expireSeconds 二维码有效时间，以秒为单位
	 */
	public void setExpireSeconds(Long expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	/**
	 * 二维码图片解析后的地址
	 * 
	 * @return url 二维码图片解析后的地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 二维码图片解析后的地址
	 * 
	 * @param url 二维码图片解析后的地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 订单列表
	 * 
	 * @return salesOrderList 订单列表
	 */
	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	/**
	 * 订单列表
	 * 
	 * @param salesOrderList 订单列表
	 */
	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

}
