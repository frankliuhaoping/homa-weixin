package cn.cnyirui.homaweixin.model.po;

import java.util.Date;

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
 * The persistent class for the typical_case_comment database table.业务表_典型案例评论
 * 
 */
@Entity
@Table(name = "typical_case_comment")
public class TypicalCaseComment extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 评论时间
	 */
	@Column
	private Date commentTime;

	/**
	 * 内容
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "CLOB")
	private String content;

	/**
	 * 典型案列
	 */
	// bi-directional many-to-one association to TypicalCase
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typicalCaseId")
	private TypicalCase typicalCase;

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
	 * 典型案列
	 * 
	 * @return typicalCase 典型案列
	 */
	public TypicalCase getTypicalCase() {
		return typicalCase;
	}

	/**
	 * 典型案列
	 * 
	 * @param typicalCase
	 *            典型案列
	 */
	public void setTypicalCase(TypicalCase typicalCase) {
		this.typicalCase = typicalCase;
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

}
