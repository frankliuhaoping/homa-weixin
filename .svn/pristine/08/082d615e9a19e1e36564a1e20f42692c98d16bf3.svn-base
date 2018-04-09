package cn.cnyirui.homaweixin.model.po;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * The persistent class for the chat_session database table.业务表_聊天会话
 * 
 */
@Entity
@Table(name = "chat_session")
public class ChatSession extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 最后一天聊天内容
	 */
	@Lob
	@Column(columnDefinition = "CLOB")
	private String lastContent;
	/**
	 * 最后发送时间
	 */
	private long lastSendTime;
	/**
	 * 会话类型 1.单聊 2.群聊
	 */
	private int type;

	/**
	 * 聊天内容
	 */
	// bi-directional many-to-one association to ChatContent
	@OneToMany(mappedBy = "chatSession")
	private List<ChatContent> chatContents;

	/**
	 * 会话成员
	 */
	// bi-directional many-to-one association to ChatSessionMember
	@OneToMany(mappedBy = "chatSession")
	private List<ChatSessionMember> chatSessionMembers;

	public ChatSession() {
	}

	public String getLastContent() {
		return this.lastContent;
	}

	public void setLastContent(String lastContent) {
		this.lastContent = lastContent;
	}

	public long getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(long lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<ChatContent> getChatContents() {
		return this.chatContents;
	}

	public void setChatContents(List<ChatContent> chatContents) {
		this.chatContents = chatContents;
	}

	public ChatContent addChatContent(ChatContent chatContent) {
		getChatContents().add(chatContent);
		chatContent.setChatSession(this);

		return chatContent;
	}

	public ChatContent removeChatContent(ChatContent chatContent) {
		getChatContents().remove(chatContent);
		chatContent.setChatSession(null);

		return chatContent;
	}

	public List<ChatSessionMember> getChatSessionMembers() {
		return this.chatSessionMembers;
	}

	public void setChatSessionMembers(List<ChatSessionMember> chatSessionMembers) {
		this.chatSessionMembers = chatSessionMembers;
	}

	public ChatSessionMember addChatSessionMember(ChatSessionMember chatSessionMember) {
		getChatSessionMembers().add(chatSessionMember);
		chatSessionMember.setChatSession(this);

		return chatSessionMember;
	}

	public ChatSessionMember removeChatSessionMember(ChatSessionMember chatSessionMember) {
		getChatSessionMembers().remove(chatSessionMember);
		chatSessionMember.setChatSession(null);

		return chatSessionMember;
	}

}