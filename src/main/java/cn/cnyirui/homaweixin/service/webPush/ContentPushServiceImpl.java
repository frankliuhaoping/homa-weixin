/**
 * 
 */
package cn.cnyirui.homaweixin.service.webPush;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.cnyirui.homaweixin.utils.Encrypt;
import cn.cnyirui.homaweixin.utils.PropertiesUtils;

/*** 
 * @category ChatContentPushServiceImpl.java
 * @Company 中山爱科数字科技股份有限公司
 * @author eric
 * @Copyright Copyright(c) 2013
 * @version V1.0
 * @date 2014年9月25日 下午3:48:25
 * 
 */
@Service("contentPushServiceImpl")
public class ContentPushServiceImpl implements IContentPushService{

	public static String ikerWebSocketPushCenterPushMessageUrl = PropertiesUtils.getValueByKeyFromWebPushConfig("PUSH_CENTER_URL") + "/pushMessage";
	public static String ikerWebSocketPushCenterEndPoint = PropertiesUtils.getValueByKeyFromWebPushConfig("PUSH_CENTER_URL") + "/iker_web_socket_push_center";
	public static String sign_sec_key = PropertiesUtils.getValueByKeyFromWebPushConfig("SIGN_SEC_KEY");

	
	/**
	 * 使用ikerWebSocketPushCenter推送信息
	* @category  
	* @author eric
	* @date 2014年9月25日 下午4:06:45
	* @param json_message,中文需要用URLEncoder.encode("中文", "utf-8")转换。
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void pushMessage(String push_dest, String json_message) {		
		// TODO Auto-generated method stub
    	String url = ikerWebSocketPushCenterPushMessageUrl;
    	//System.out.println("url:"+url);
    	//System.out.println("push_dest:"+push_dest);
    	//System.out.println("json_message:"+json_message);
    	//String dest = messageSubscribePath;
    	String content = "编码异常";
		try {
			content = URLEncoder.encode(json_message, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		@SuppressWarnings("rawtypes")
		Map params = new HashMap();
		params.put("dest", push_dest);
		params.put("content", content);
		params.put("sign", Encrypt.MD5(push_dest+content+sign_sec_key));
		try {
			AjaxHttpRequestUtils.ajaxPost(url, params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
