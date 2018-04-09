package cn.cnyirui.homaweixin.model.po;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import cn.cnyirui.framework.model.po.base.SequenceableEntity;
import cn.cnyirui.framework.utils.Constants;

/**
 * The persistent class for the news database table.业务表—其他资料
 * 
 */
@Entity
@Table(name = "other_info")
public class OtherInfo extends SequenceableEntity {
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
	 * 标题
	 */
	@Column(length = 300)
	private String title;

	/**
	 * 点击数
	 */
	private Long clickCount;

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
