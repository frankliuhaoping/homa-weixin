package cn.cnyirui.homaweixin.model.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.StandardSetupEntity;

/**
 * The persistent class for the notice_category database table.标准设置_企业通知分类
 * 
 */
@Entity
@Table(name = "notice_category")
public class NoticeCategory extends StandardSetupEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 企业通知
	 */
	// bi-directional many-to-one association to Notice
	@OneToMany(mappedBy = "category")
	private List<Notice> noticeList;

	/**
	 * 企业通知
	 * 
	 * @return noticeList 企业通知
	 */
	public List<Notice> getNoticeList() {
		return noticeList;
	}

	/**
	 * 企业通知
	 * 
	 * @param noticeList
	 *            企业通知
	 */
	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

	public Notice addNoticeList(Notice noticeList) {
		getNoticeList().add(noticeList);
		noticeList.setCategory(this);

		return noticeList;
	}

	public Notice removeNoticeList(Notice noticeList) {
		getNoticeList().remove(noticeList);
		noticeList.setCategory(null);

		return noticeList;
	}
}
