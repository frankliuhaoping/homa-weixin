package cn.cnyirui.homaweixin.dao.backend;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.MarketingExperience;
import cn.cnyirui.homaweixin.model.po.MarketingExperienceImages;

public interface MarketingExperienceDao extends BaseDao<MarketingExperience>{
	
	//根据id查询某营销经验的所有图片
	@Query("from MarketingExperienceImages m where m.marketingExperience.id = ?1")
	public List<MarketingExperienceImages> getMarketingExperienceImagesByMarketingExperienceId(String markId);
	
	
	
}
