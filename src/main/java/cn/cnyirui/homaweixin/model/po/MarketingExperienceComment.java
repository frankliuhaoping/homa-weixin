package cn.cnyirui.homaweixin.model.po;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.po.rbac.SysUser;

/**
 * The persistent class for the marketing_experience_comment database
 * table.业务表_营销经验评论
 * 
 */
@Entity
@Table(name = "marketing_experience_comment")
public class MarketingExperienceComment extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 评论时间
	 */
	@Column
	private Date commentTime;

	/**
	 * 评论内容
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "CLOB")
	private String content;

	/**
	 * 营销经验
	 */
	// bi-directional many-to-one association to MarketingExperience
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marketingExperienceId")
	private MarketingExperience marketingExperience;

	/**
	 * 评论人
	 */
	// bi-directional many-to-one association to SysUser
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commentBy")
	private SysUser commentBy;

	/**
	 * 评论时间
	 * 
	 * @return commentTime 评论时间
	 */
	public Date getCommentTime() {
		return commentTime;
	}

	/**
	 * 评论时间
	 * 
	 * @param commentTime
	 *            评论时间
	 */
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	/**
	 * 评论内容
	 * 
	 * @return content 评论内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 评论内容
	 * 
	 * @param content
	 *            评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 营销经验
	 * 
	 * @return marketingExperience 营销经验
	 */
	public MarketingExperience getMarketingExperience() {
		return marketingExperience;
	}

	/**
	 * 营销经验
	 * 
	 * @param marketingExperience
	 *            营销经验
	 */
	public void setMarketingExperience(MarketingExperience marketingExperience) {
		this.marketingExperience = marketingExperience;
	}

	/**
	 * 评论人
	 * 
	 * @return commentBy 评论人
	 */
	public SysUser getCommentBy() {
		return commentBy;
	}

	/**
	 * 评论人
	 * 
	 * @param commentBy
	 *            评论人
	 */
	public void setCommentBy(SysUser commentBy) {
		this.commentBy = commentBy;
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}

}
