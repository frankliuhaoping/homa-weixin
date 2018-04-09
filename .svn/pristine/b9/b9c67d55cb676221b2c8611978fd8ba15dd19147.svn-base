package cn.cnyirui.homaweixin.model.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import cn.cnyirui.framework.model.po.base.SequenceableEntity;
import cn.cnyirui.framework.utils.Constants;

/**
 * The persistent class for the typical_case database table.业务表_典型案例
 * 
 */
@Entity
@Table(name = "typical_case")
public class TypicalCase extends SequenceableEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 内容
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "CLOB")
	private String content;

	/**
	 * 封面图片
	 */
	@Column(length = 300)
	private String coverPictureUrl;

	/**
	 * 是否置顶显示
	 */
	@Column
	private Boolean isTop = Boolean.FALSE;

	/**
	 * 标题
	 */
	@Column(length = 300)
	private String title;
	
	/**
	 * 是否发布
	 * 0是发布
	 */
	@Column
	private Boolean isRelease = Boolean.FALSE;
	
	/**
	 * 发布时间
	 * 
	 */

	
	@DateTimeFormat(pattern = Constants.DATE_TIME_FORMAT)
	@CreatedDate
	private Date releaseTime;
	

	/**
	 * 点击数
	 */
	private Long clickCount;

	/**
	 * 标准设置_典型案例分类
	 */
	// bi-directional many-to-one association to TypicalCaseCategory
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private TypicalCaseCategory category;

	/**
	 * 业务表_典型案例评论
	 */
	// bi-directional many-to-one association to TypicalCaseComment
	@OneToMany(mappedBy = "typicalCase")
	private List<TypicalCaseComment> commentList;

	/**
	 * 内容
	 * 
	 * @return content 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 封面图片
	 * 
	 * @return coverPictureUrl 封面图片
	 */
	public String getCoverPictureUrl() {
		return coverPictureUrl;
	}

	/**
	 * 封面图片
	 * 
	 * @param coverPictureUrl
	 *            封面图片
	 */
	public void setCoverPictureUrl(String coverPictureUrl) {
		this.coverPictureUrl = coverPictureUrl;
	}

	/**
	 * 是否置顶显示
	 * 
	 * @return isTop 是否置顶显示
	 */
	public Boolean getIsTop() {
		return isTop;
	}

	/**
	 * 是否置顶显示
	 * 
	 * @param isTop
	 *            是否置顶显示
	 */
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	/**
	 * 标题
	 * 
	 * @return title 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 点击数
	 * 
	 * @return clickCount 点击数
	 */
	public Long getClickCount() {
		return clickCount;
	}

	/**
	 * 点击数
	 * 
	 * @param clickCount 点击数
	 */
	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}

	/**
	 * 标准设置_典型案例分类
	 * 
	 * @return category 标准设置_典型案例分类
	 */
	public TypicalCaseCategory getCategory() {
		return category;
	}

	/**
	 * 标准设置_典型案例分类
	 * 
	 * @param category
	 *            标准设置_典型案例分类
	 */
	public void setCategory(TypicalCaseCategory category) {
		this.category = category;
	}

	/**
	 * 业务表_典型案例评论
	 * 
	 * @return commentList 业务表_典型案例评论
	 */
	public List<TypicalCaseComment> getCommentList() {
		return commentList;
	}

	/**
	 * 业务表_典型案例评论
	 * 
	 * @param commentList
	 *            业务表_典型案例评论
	 */
	public void setCommentList(List<TypicalCaseComment> commentList) {
		this.commentList = commentList;
	}

	public TypicalCaseComment addCommentList(TypicalCaseComment commentList) {
		getCommentList().add(commentList);
		commentList.setTypicalCase(this);

		return commentList;
	}

	public TypicalCaseComment removeCommentList(TypicalCaseComment commentList) {
		getCommentList().remove(commentList);
		commentList.setTypicalCase(null);

		return commentList;
	}

	/**
	 * 是否发布0是发布
	 * @return isRelease 是否发布0是发布
	 */
	public Boolean getIsRelease() {
		return isRelease;
	}

	/**
	 * 是否发布0是发布
	 * @param isRelease 是否发布0是发布
	 */
	public void setIsRelease(Boolean isRelease) {
		this.isRelease = isRelease;
	}

	/**
	 * 发布时间
	 * @return releaseTime 发布时间
	 */
	public Date getReleaseTime() {
		return releaseTime;
	}

	/**
	 * 发布时间
	 * @param releaseTime 发布时间
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

}
