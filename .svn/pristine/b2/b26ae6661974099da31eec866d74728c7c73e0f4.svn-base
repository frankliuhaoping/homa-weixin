package cn.cnyirui.homaweixin.model.po;

import javax.persistence.*;
import cn.cnyirui.framework.model.po.base.BaseEntity;
import java.util.List;


/**
 * The persistent class for the chat_group database table.业务表_群组
 * 
 */
@Entity
@Table(name="chat_group")
public class ChatGroup extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 头像
	 */
	private String headImg;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 群组成员
	 */
	//bi-directional many-to-one association to ChatSessionMember
	@OneToMany(mappedBy="chatGroup")
	private List<ChatSessionMember> chatSessionMembers;
	/**
	 * 是否删除 0.未删除  1.已删除
	 */
	private int isDel;
	
	public ChatGroup() {
	}

	public String getHeadImg() {
		return this.headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ChatSessionMember> getChatSessionMembers() {
		return this.chatSessionMembers;
	}

	public void setChatSessionMembers(List<ChatSessionMember> chatSessionMembers) {
		this.chatSessionMembers = chatSessionMembers;
	}

	public ChatSessionMember addChatSessionMember(ChatSessionMember chatSessionMember) {
		getChatSessionMembers().add(chatSessionMember);
		chatSessionMember.setChatGroup(this);

		return chatSessionMember;
	}

	public ChatSessionMember removeChatSessionMember(ChatSessionMember chatSessionMember) {
		getChatSessionMembers().remove(chatSessionMember);
		chatSessionMember.setChatGroup(null);

		return chatSessionMember;
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

}