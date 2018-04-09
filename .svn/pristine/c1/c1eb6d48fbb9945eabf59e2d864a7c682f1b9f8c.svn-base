package cn.cnyirui.homaweixin.service.webPush;

import java.io.UnsupportedEncodingException;

import cn.cnyirui.homaweixin.utils.PropertiesUtils;


/*** 
 * @category IChatContentPushService.java
 * @Company 中山爱科数字科技股份有限公司
 * @author eric
 * @Copyright Copyright(c) 2013
 * @version V1.0
 * @date 2014年9月25日 下午3:47:10
 * 
 */
public interface IContentPushService {
	
	public static String ikerWebSocketPushCenterPushMessageUrl = PropertiesUtils.getValueByKeyFromWebPushConfig("PUSH_CENTER_URL") + "/pushMessage";
	public static String ikerWebSocketPushCenterEndPoint = PropertiesUtils.getValueByKeyFromWebPushConfig("PUSH_CENTER_URL") + "/iker_web_socket_push_center";
	public static String sign_sec_key = PropertiesUtils.getValueByKeyFromWebPushConfig("SIGN_SEC_KEY");
	
	/**
	 * 使用ikerWebSocketPushCenter推送聊天信息
	* @category
	* @author eric
	* @date 2014年9月25日 下午4:06:45
	* @param 指定推送给那个订阅地址
	* @param json_message,中文需要用URLEncoder.encode("中文", "utf-8")转换。
	 * @throws UnsupportedEncodingException 
	 */
	public void pushMessage(String push_dest, String json_message);
	
	
}