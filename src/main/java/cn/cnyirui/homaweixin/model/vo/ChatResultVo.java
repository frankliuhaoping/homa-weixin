package cn.cnyirui.homaweixin.model.vo;

/**
 * 发送消息成功后返回的对象数据
 * @author zhoujuhui
 *
 */
public class ChatResultVo {

	private String uuid;
	
	private String sessionId;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
