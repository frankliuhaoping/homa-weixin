package cn.cnyirui.homaweixin.model.vo;

import java.util.List;

/**
 * 包含分页信息的组织架构
 * @author zhoujuhui
 *
 */
public class ChatOrgPageVo {

	/**
	 * 总页数
	 */
	private int totalPages;
	/**
	 * 当前页数
	 */
	private int page;
	/**
	 * 列表
	 */
	private List<ChatOrgVo> chatOrgVoList;
	
	
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<ChatOrgVo> getChatOrgVoList() {
		return chatOrgVoList;
	}
	public void setChatOrgVoList(List<ChatOrgVo> chatOrgVoList) {
		this.chatOrgVoList = chatOrgVoList;
	}
}
