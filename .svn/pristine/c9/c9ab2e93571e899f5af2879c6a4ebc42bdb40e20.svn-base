package cn.cnyirui.framework.controller.weixin.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.cnyirui.framework.service.weixin.WeiXinUserSubscribeRecordService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 取消关注事件处理
 * 
 * @author pengzhihua
 *
 */
@Component("weiXinUnSubscribeHandler")
public class WeiXinUnSubscribeHandler implements WxMpMessageHandler {

    @Resource
    private WeiXinUserSubscribeRecordService weiXinUserSubscribeRecordService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        // 保存取消关注记录
        weiXinUserSubscribeRecordService.addWeiXinUserSubscribeRecord(wxMessage.getFromUserName(), false);
        return null;
    }

}
