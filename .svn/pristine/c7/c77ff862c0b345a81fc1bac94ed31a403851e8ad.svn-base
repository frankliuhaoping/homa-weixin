package cn.cnyirui.homaweixin.model.po;

import javax.persistence.*;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * The persistent class for the chat_session_member database table.业务表_会话成员
 * 
 */
@Entity
@Table(name="chat_session_member")
public class ChatSessionMember  extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 会话成员
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	/**
	 * 所属群组
	 */
	//bi-directional many-to-one association to ChatGroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="groupId")
	private ChatGroup chatGroup;

	/**
	 * 所属会话
	 */
	//bi-directional many-to-one association to ChatSession
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sessionId")
	private ChatSession chatSession;
	
	/**
	 * 未读条数
	 */
	private int unReadNum;

	public ChatSessionMember() {
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ChatGroup getChatGroup() {
		return this.chatGroup;
	}

	public void setChatGroup(ChatGroup chatGroup) {
		this.chatGroup = chatGroup;
	}

	public ChatSession getChatSession() {
		return this.chatSession;
	}

	public void setChatSession(ChatSession chatSession) {
		this.chatSession = chatSession;
	}

	public int getUnReadNum() {
		return unReadNum;
	}

	public void setUnReadNum(int unReadNum) {
		this.unReadNum = unReadNum;
	}

}