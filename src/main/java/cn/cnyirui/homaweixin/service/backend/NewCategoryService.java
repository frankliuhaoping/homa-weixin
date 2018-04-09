package cn.cnyirui.homaweixin.service.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.backend.NewsCategoryDao;
import cn.cnyirui.homaweixin.model.po.NewsCategory;

@Service
public class NewCategoryService extends BaseService<NewsCategory> {

	@Resource
	private NewsCategoryDao newsCategoryDao;

	@Override
	public BaseDao<NewsCategory> getBaseDao() {
		return newsCategoryDao;
	}

	/***
	 * 查询所有的团队，用于后台学员的团队熟悉下拉选择
	 */
	public List<ObjectNode> getCategory() {
		List<NewsCategory> list = newsCategoryDao.findAll();
		List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
		for (NewsCategory newsCategory : list) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("id", newsCategory.getId());
			objectNode.put("name", newsCategory.getName());
			objectNodes.add(objectNode);
		}
		return objectNodes;
	}
}
