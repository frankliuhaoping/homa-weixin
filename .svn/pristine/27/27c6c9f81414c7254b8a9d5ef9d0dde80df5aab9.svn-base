package cn.cnyirui.framework.controller.weixin.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
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
@Component("weiXinLocationHandler")
public class WeiXinLocationHandler implements WxMpMessageHandler {

	@Resource
	private WeiXinOutMessageService weiXinOutMessageService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
	        WxSessionManager sessionManager) throws WxErrorException {
		System.out.println(wxMessage.getLongitude() + ":" + wxMessage.getLatitude() + ":"
		        + DateFormatUtils.format(wxMessage.getCreateTime() * 1000, "yyyy-MM-dd HH:mm:ss") + ":"
		        + wxMessage.getMsgId());
		return null;
	}

}
