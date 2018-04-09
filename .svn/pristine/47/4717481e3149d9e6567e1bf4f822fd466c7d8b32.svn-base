package cn.cnyirui.homaweixin.model.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.StandardSetupEntity;

/**
 * The persistent class for the marketing_experience_category database
 * table.标准设置_营销经验分类
 * 
 */
@Entity
@Table(name = "marketing_experience_category")
public class MarketingExperienceCategory extends StandardSetupEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 营销经验
	 */
	// bi-directional many-to-one association to MarketingExperience
	@OneToMany(mappedBy = "category")
	private List<MarketingExperience> marketingExperienceList;

	/**
	 * 营销经验
	 * 
	 * @return marketingExperienceList 营销经验
	 */
	public List<MarketingExperience> getMarketingExperienceList() {
		return marketingExperienceList;
	}

	/**
	 * 营销经验
	 * 
	 * @param marketingExperienceList
	 *            营销经验
	 */
	public void setMarketingExperienceList(List<MarketingExperience> marketingExperienceList) {
		this.marketingExperienceList = marketingExperienceList;
	}

	public MarketingExperience addMarketingExperienceList(MarketingExperience marketingExperienceList) {
		getMarketingExperienceList().add(marketingExperienceList);
		marketingExperienceList.setCategory(this);

		return marketingExperienceList;
	}

	public MarketingExperience removeMarketingExperienceList(MarketingExperience marketingExperienceList) {
		getMarketingExperienceList().remove(marketingExperienceList);
		marketingExperienceList.setCategory(null);

		return marketingExperienceList;
	}

}
