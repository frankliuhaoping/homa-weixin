package cn.cnyirui.homaweixin.service.backend;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.MarketingExperienceDao;
import cn.cnyirui.homaweixin.model.po.MarketingExperience;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MarketingExperienceService extends BaseService<MarketingExperience> {
	@Resource
	private MarketingExperienceDao marketingExperienceDao;

	@Override
	public BaseDao<MarketingExperience> getBaseDao() {
		return marketingExperienceDao;
	}
	
	@Override
	public ObjectNode entityToObjectNode(MarketingExperience entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		
		if(entity.getCategory()!=null){
			objectNode.put("category", entity.getCategory().getName());
		}
		
		objectNode.put("isTop", entity.getIsTop()== false ? "否" : "是");
		return objectNode;
	}
	
	/**
	 * 删除营销经验
	 * @param ids
	 */
	@Transactional
	public void deleteExperience(String[] ids){
		if(ids != null){
			for(String id : ids){
				EntityManager em = marketingExperienceDao.getEntityManager();
				/**
				 * 删除评论
				 */
				String sql = "delete from MarketingExperienceComment where marketingExperience.id = ?";
				Query query = em.createQuery(sql);
				query.setParameter(1, id);
				query.executeUpdate();
				/**
				 * 删除图片
				 */
				sql = "delete from MarketingExperienceImages where marketingExperience.id = ?";
				query = em.createQuery(sql);
				query.setParameter(1, id);
				query.executeUpdate();
				
				marketingExperienceDao.delete(id);
			}
		}
		
	}
}
