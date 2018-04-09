package cn.cnyirui.framework.service.weixin;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cnyirui.framework.extension.weixin.WxMpSpringCacheConfigStorge;
import cn.cnyirui.framework.model.eo.WeiXinQrCodeSceneType;
import cn.cnyirui.framework.model.po.weixin.WeiXinQrCode;
import cn.cnyirui.homaweixin.model.po.Product;
import cn.cnyirui.homaweixin.model.po.SalesOrder;
import cn.cnyirui.homaweixin.model.po.SalesOrderDetail;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;

@Service
public class WeiXinOutMessageService {

	@Resource
	private WeiXinQrCodeService weiXinQrCodeService;
	@Resource
	private WxMpInMemoryConfigStorage wxMpConfigStorage;

	/**
	 * 二维码的响应消息
	 * 
	 * @param sceneId
	 * @return
	 */
	public WxMpXmlOutMessage buildQrCodeOutMessage(String sceneId) {
		WeiXinQrCode weiXinQrCode = weiXinQrCodeService.findBySceneId(Long.valueOf(sceneId));
		// 订单的
		if (WeiXinQrCodeSceneType.SALESORDER.getValue().equalsIgnoreCase(weiXinQrCode.getSceneType())) {
			List<SalesOrder> salesOrderList = weiXinQrCode.getSalesOrderList();
			if (!salesOrderList.isEmpty()) {
				SalesOrder salesOrder = salesOrderList.get(0);
				StringBuilder s = new StringBuilder();
				s.append("您好，欢迎购买奥马冰箱，请确认你的订单信息：\r\n");
				s.append("\r\n");
				Double totalAmount = 0d;
				List<SalesOrderDetail> salesOrderDetailList = salesOrder.getSalesOrderDetailList();
				DecimalFormat decimalFormat = new DecimalFormat("#0.00");
				for (SalesOrderDetail salesOrderDetail : salesOrderDetailList) {
					Product product = salesOrderDetail.getProduct();
					s.append("产品名称：").append(product != null ? product.getName() : "").append("\r\n");
					s.append("购买数量：").append(salesOrderDetail.getQty()).append("\r\n");
					s.append("购买价格：").append(decimalFormat.format(salesOrderDetail.getPrice())).append("\r\n");
					totalAmount += salesOrderDetail.getQty() * salesOrderDetail.getPrice();
					s.append("\r\n");
				}
				s.append("总计金额：").append(decimalFormat.format(totalAmount));
				s.append("\r\n");
				s.append("请及时完善您的订单收货信息，谢谢您的配合！");

				WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
				item.setTitle("请完善订单信息");
				item.setDescription(s.toString());
				if (wxMpConfigStorage instanceof WxMpSpringCacheConfigStorge) {
					item.setUrl(((WxMpSpringCacheConfigStorge) wxMpConfigStorage).getBaseUrl() +
					        "weixin/customer/salesOrder/customerRecv?orderId="
					        + salesOrder.getId());
				}

				return WxMpXmlOutMessage.NEWS().addArticle(item).build();
			}
		}
		return null;
	}
}
