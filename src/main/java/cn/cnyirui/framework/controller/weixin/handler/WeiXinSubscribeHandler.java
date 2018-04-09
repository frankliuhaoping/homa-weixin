package cn.cnyirui.framework.controller.weixin.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.cnyirui.framework.service.weixin.WeiXinOutMessageService;
import cn.cnyirui.framework.service.weixin.WeiXinUserSubscribeRecordService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 关注事件处理
 * 
 * @author pengzhihua
 *
 */
@Component("weiXinSubscribeHandler")
public class WeiXinSubscribeHandler implements WxMpMessageHandler {

	@Resource
	private WeiXinUserSubscribeRecordService weiXinUserSubscribeRecordService;
	@Resource
	private WeiXinOutMessageService weiXinOutMessageService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
	        WxSessionManager sessionManager) throws WxErrorException {
		// 保存关注记录
		weiXinUserSubscribeRecordService.addWeiXinUserSubscribeRecord(wxMessage.getFromUserName(), true);
		WxMpXmlOutMessage wxMpXmlOutMessage = null;
		// 带参数的关注
		String eventKey = wxMessage.getEventKey();
		if (eventKey != null&& !eventKey.equals(""))//非空有BUG 怎么做开发的 if (eventKey != null）
			{
			// 带参数的二维码
			if (eventKey.startsWith("qrscene_")) {
				String sceneId = StringUtils.substringAfter(eventKey, "qrscene_");
				wxMpXmlOutMessage = weiXinOutMessageService.buildQrCodeOutMessage(sceneId);
			}
		} 
		else {
			wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT().content("欢迎关注").build();
		}
		if (wxMpXmlOutMessage != null) {
			wxMpXmlOutMessage.setFromUserName(wxMessage.getToUserName());
			wxMpXmlOutMessage.setToUserName(wxMessage.getFromUserName());
			return wxMpXmlOutMessage;
		}
		return null;
	}

}
