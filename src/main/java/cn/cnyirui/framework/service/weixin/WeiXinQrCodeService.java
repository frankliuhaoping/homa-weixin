package cn.cnyirui.framework.service.weixin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.weixin.WeiXinQrCodeDao;
import cn.cnyirui.framework.model.eo.WeiXinQrCodeSceneType;
import cn.cnyirui.framework.model.eo.WeiXinQrCodeType;
import cn.cnyirui.framework.model.po.weixin.WeiXinQrCode;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.SalesOrderDao;
import cn.cnyirui.homaweixin.model.po.SalesOrder;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

/**
 * 微信二维码
 * 
 * @author pengzhihua
 *
 */
@Service
public class WeiXinQrCodeService extends BaseService<WeiXinQrCode> {

	@Resource
	private WeiXinQrCodeDao weiXinQrCodeDao;

	@Resource
	private WxMpService wxMpService;

	@Resource
	private SalesOrderDao salesOrderDao;

	@Override
	public BaseDao<WeiXinQrCode> getBaseDao() {
		return weiXinQrCodeDao;
	}

	/**
	 * 创建微信二维码
	 * 
	 * @param sceneType 场景类型，枚举类型
	 * @param qrCodeType 二维码类型，枚举类型(永久二维码，临时二维码)
	 * @return
	 */
	@Transactional
	public WeiXinQrCode createWeiXinQrCode(WeiXinQrCodeSceneType sceneType, WeiXinQrCodeType qrCodeType,
	        Long expireSeconds) {

		WeiXinQrCode weiXinQrCode = new WeiXinQrCode();
		weiXinQrCode.setQrCodeType(qrCodeType.getValue());
		weiXinQrCode.setSceneType(sceneType.getValue());
		weiXinQrCodeDao.saveAndFlush(weiXinQrCode);
		weiXinQrCodeDao.getEntityManager().refresh(weiXinQrCode);
		try {
			WxMpQrCodeTicket wxMpQrCodeTicket = null;
			if (qrCodeType == WeiXinQrCodeType.TMEP) {
				if (expireSeconds == null) {
					expireSeconds = Long.valueOf(30 * 24 * 60 * 60 * 1000); // 30天
				}
				wxMpQrCodeTicket = wxMpService.qrCodeCreateTmpTicket(weiXinQrCode.getSceneId().intValue(),
				        expireSeconds.intValue());
			} else if (qrCodeType == WeiXinQrCodeType.PERMANENT) {
				wxMpQrCodeTicket = wxMpService.qrCodeCreateLastTicket(weiXinQrCode.getSceneId().toString());
			}
			weiXinQrCode.setTicket(wxMpQrCodeTicket.getTicket());
			weiXinQrCode.setUrl(wxMpQrCodeTicket.getUrl());
			weiXinQrCode.setExpireSeconds(Long.valueOf(wxMpQrCodeTicket.getExpire_seconds()));
			weiXinQrCodeDao.save(weiXinQrCode);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return weiXinQrCode;
	}

	/**
	 * 销售订单的二维码
	 * 
	 * @param salesOrderId
	 * @return
	 */
	@Transactional
	public WeiXinQrCode findBySalesOrderId(String salesOrderId) {
		List<WeiXinQrCode> weiXinQrCodeList = weiXinQrCodeDao.findBySalesOrderId(salesOrderId, new PageRequest(0, 1));
		if (!weiXinQrCodeList.isEmpty()) {
			return weiXinQrCodeList.get(0);
		} else {
			SalesOrder salesOrder = salesOrderDao.findOne(salesOrderId);
			if (salesOrder != null) {
				WeiXinQrCode weiXinQrCode = createWeiXinQrCode(WeiXinQrCodeSceneType.SALESORDER, WeiXinQrCodeType.TMEP,
				        null);
				salesOrder.setWeiXinQrCode(weiXinQrCode);
				salesOrderDao.save(salesOrder);
				return weiXinQrCode;
			}
		}
		return null;
	}

	/**
	 * 按场景ID查找二维码
	 * 
	 * @param salesOrderId
	 * @return
	 */
	public WeiXinQrCode findBySceneId(Long sceneId) {
		List<WeiXinQrCode> weiXinQrCodeList = weiXinQrCodeDao.findBySceneId(sceneId, new PageRequest(0, 1));
		if (!weiXinQrCodeList.isEmpty()) {
			return weiXinQrCodeList.get(0);
		}
		return null;
	}
}
