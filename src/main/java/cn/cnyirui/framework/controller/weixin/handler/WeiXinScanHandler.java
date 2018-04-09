package cn.cnyirui.framework.controller.weixin.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cnyirui.framework.service.weixin.WeiXinOutMessageService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 扫二维码事件处理
 * 
 * @author pengzhihua
 *
 */
@Component("weiXinScanHandler")
public class WeiXinScanHandler implements WxMpMessageHandler {

	@Resource
	private WeiXinOutMessageService weiXinOutMessageService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
	        WxSessionManager sessionManager) throws WxErrorException {
		WxMpXmlOutMessage wxMpXmlOutMessage = null;
		// 带参数的关注
		String eventKey = wxMessage.getEventKey();
		if (eventKey != null) {
			wxMpXmlOutMessage = weiXinOutMessageService.buildQrCodeOutMessage(eventKey);
		}
		if (wxMpXmlOutMessage != null) {
			wxMpXmlOutMessage.setFromUserName(wxMessage.getToUserName());
			wxMpXmlOutMessage.setToUserName(wxMessage.getFromUserName());
			return wxMpXmlOutMessage;
		}
		return null;
	}

}
