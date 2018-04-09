package cn.cnyirui.homaweixin.service.webPush;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AjaxHttpRequestUtils {

	/**
	 * 例子,中文一定要URLEncoder.encode("中文", "utf-8");
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
    	String url = "http://127.0.0.1:8080/pushMessage";
    	String dest = "/userChat/chat1";
    	String content = "{\"name\":\"system\",\"chatContent\":\""+URLEncoder.encode("你们好", "utf-8")+"\",\"sessionId\":\"123456789\"}";
		Map params = new HashMap();
		params.put("dest", dest);
		params.put("content", content);
		AjaxHttpRequestUtils.ajaxPost(url, params);
	}
	
	/**
	 * 发送post请求
	 * @param url
	 * @param params
	 * @throws IOException
	 */
	public static void ajaxPost(String url, Map params) throws IOException{
		// 以上为 调用google翻译服务所需要的参数.
		// 下面 是用java 来调用这个 翻译服务.
		// 在 AjaxHttpRequest 类的 帮助下 我们可以使用 类似操作浏览器XHR对象的方式来 实现该功能.
		// 创建 AjaxHttpRequest对象 相当于 创建 XHR对象.
		// 这里的final 主要是为了"使监听器内部能够调用ajax对象"而加的.
		final AjaxHttpRequest ajax = new AjaxHttpRequest();

		// 设置状态监听器,类似 XHR对象的 onreadystatechange 属性.
		// 由于js 和java的本质区别 导致这里可能和 xhr有点不一样,但是应该不难理解.
		ajax.setReadyStateChangeListener(
		// 监听器, 根据需求 实现onReadyStateChange方法即可.
		new AjaxHttpRequest.ReadyStateChangeListener() {
			public void onReadyStateChange() {
				int readyState = ajax.getReadyState();
				// 判断状态 然后做出相应处理.
				if (readyState == AjaxHttpRequest.STATE_COMPLETE) {
					if("success".equals(ajax.getResponseText())){
						
					}else{					
						System.out.println(ajax.getResponseText());
					}
				}
			}
		});
		// 这里就和 xhr 几乎完全一样了
		ajax.open("POST", url, true);
		// 这里这个send方法接受的参数是一个map ,当然AjaxHttpRequest类也提供了string的方法
		ajax.send(params);
	}
	


}
