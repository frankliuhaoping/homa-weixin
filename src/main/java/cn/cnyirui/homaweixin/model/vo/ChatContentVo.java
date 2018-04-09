package cn.cnyirui.homaweixin.model.vo;


public class ChatContentVo {

	/**
	 * 聊天内容
	 */
	private String content;
	/**
	 * 内容类型  1.文本   2.图片   3.语音
	 */
	private int contentType;
	/**
	 * 发送时间戳
	 */
	private long sendTime;
	/**
	 * 语音时长  
	 */
	private int voiceTime;
	
	/**
	 * 发送人id
	 */
	private String senderId;
	/**
	 * 发送人姓名
	 */
	private String name;
	/**
	 * 发送人头像
	 */
	private String headImgUrl;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getContentType() {
		return contentType;
	}
	public void setContentType(int contentType) {
		this.contentType = contentType;
	}
	public long getSendTime() {
		return sendTime;
	}
	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
	public int getVoiceTime() {
		return voiceTime;
	}
	public void setVoiceTime(int voiceTime) {
		this.voiceTime = voiceTime;
	}
	
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
}
