package cn.cnyirui.homaweixin.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * The persistent class for the chat_content database table.业务表_聊天内容
 * 
 */
@Entity
@Table(name = "chat_content")
public class ChatContent extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 聊天内容
	 */
	@Lob
	@Column(columnDefinition = "CLOB")
	private String content;
	/**
	 * 内容类型 1.文本 2.图片 3.语音
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
	 * 会话
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sessionId")
	private ChatSession chatSession;

	/**
	 * 发送人
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "senderId")
	private Employee sender;

	/**
	 * 发送人头像
	 */
	@Transient
	private String name;
	@Transient
	private String headImg;
	/**
	 * 接收人id
	 */
	@Transient
	private String receiverId;
	@Transient
	private String groupId;

	public ChatContent() {
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getContentType() {
		return this.contentType;
	}

	public void setContentType(int contentType) {
		this.contentType = contentType;
	}

	public int getVoiceTime() {
		return this.voiceTime;
	}

	public void setVoiceTime(int voiceTime) {
		this.voiceTime = voiceTime;
	}

	public ChatSession getChatSession() {
		return this.chatSession;
	}

	public void setChatSession(ChatSession chatSession) {
		this.chatSession = chatSession;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}