package cn.cnyirui.homaweixin.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * The persistent class for the marketing_experience_comment database
 * table.业务表_营销经验的图片
 * 
 */
@Entity
@Table(name = "marketing_experience_images")
public class MarketingExperienceImages extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 营销经验
	 */
	// bi-directional many-to-one association to MarketingExperience
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marketingExperienceId")
	private MarketingExperience marketingExperience;

	/**
	 * 图片URL
	 */
	@Column(length = 500)
	private String imageUrl;

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
	 * @param marketingExperience 营销经验
	 */
	public void setMarketingExperience(MarketingExperience marketingExperience) {
		this.marketingExperience = marketingExperience;
	}

	/**
	 * 图片URL
	 * 
	 * @return imageUrl 图片URL
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * 图片URL
	 * 
	 * @param imageUrl 图片URL
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
