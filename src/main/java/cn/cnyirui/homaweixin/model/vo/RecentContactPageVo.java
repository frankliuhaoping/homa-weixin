package cn.cnyirui.homaweixin.model.vo;

import java.util.List;

/**
 * 包含分页信息的最近联系人
 * @author zhoujuhui
 *
 */
public class RecentContactPageVo {

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
	private List<RecentContactVo> recentContactVoList;
	
	
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
	public List<RecentContactVo> getRecentContactVoList() {
		return recentContactVoList;
	}
	public void setRecentContactVoList(List<RecentContactVo> recentContactVoList) {
		this.recentContactVoList = recentContactVoList;
	}
	
}
