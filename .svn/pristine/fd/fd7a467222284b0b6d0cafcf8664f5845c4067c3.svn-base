package cn.cnyirui.homaweixin.model.po;

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

import cn.cnyirui.framework.model.po.base.SequenceableEntity;

/**
 * The persistent class for the marketing_experience database table.业务表_营销经验
 * 
 */
@Entity
@Table(name = "marketing_experience")
public class MarketingExperience extends SequenceableEntity {
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
	 * 营销经验分类
	 */
	// bi-directional many-to-one association to MarketingExperienceCategory
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private MarketingExperienceCategory category;

	/**
	 * 营销经验评论
	 */
	// bi-directional many-to-one association to MarketingExperienceComment
	@OneToMany(mappedBy = "marketingExperience")
	private List<MarketingExperienceComment> commentList;

	/**
	 * 图片
	 */
	@OneToMany(mappedBy = "marketingExperience")
	private List<MarketingExperienceImages> imageList;

	public MarketingExperienceComment addCommentList(MarketingExperienceComment commentList) {
		getCommentList().add(commentList);
		commentList.setMarketingExperience(this);

		return commentList;
	}

	public MarketingExperienceComment removeCommentList(MarketingExperienceComment commentList) {
		getCommentList().remove(commentList);
		commentList.setMarketingExperience(null);

		return commentList;
	}

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
	 * 营销经验分类
	 * 
	 * @return category 营销经验分类
	 */
	public MarketingExperienceCategory getCategory() {
		return category;
	}

	/**
	 * 营销经验分类
	 * 
	 * @param category
	 *            营销经验分类
	 */
	public void setCategory(MarketingExperienceCategory category) {
		this.category = category;
	}

	/**
	 * 营销经验评论
	 * 
	 * @return commentList 营销经验评论
	 */
	public List<MarketingExperienceComment> getCommentList() {
		return commentList;
	}

	/**
	 * 营销经验评论
	 * 
	 * @param commentList
	 *            营销经验评论
	 */
	public void setCommentList(List<MarketingExperienceComment> commentList) {
		this.commentList = commentList;
	}

	/**
	 * 图片
	 * 
	 * @return imageList 图片
	 */

	public List<MarketingExperienceImages> getImageList() {
		return imageList;
	}

	/**
	 * 图片
	 * 
	 * @param imageList 图片
	 */
	public void setImageList(List<MarketingExperienceImages> imageList) {
		this.imageList = imageList;
	}

}
