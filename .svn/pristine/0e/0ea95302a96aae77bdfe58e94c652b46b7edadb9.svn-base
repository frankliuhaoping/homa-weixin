package cn.cnyirui.homaweixin.service.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.SalesTaskDao;
import cn.cnyirui.homaweixin.model.po.SalesTask;

@Service
public class SalesTaskService extends BaseService<SalesTask> {
	@Resource
	private SalesTaskDao salesTaskDao;

	@Override
	public BaseDao<SalesTask> getBaseDao() {
		return salesTaskDao;
	}

	@Override
	public ObjectNode entityToObjectNode(SalesTask entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		if (entity.getType() == 0) {
			objectNode.put("type", "金额");
		}
		if (entity.getType() == 1) {
			objectNode.put("type", "数量");
		}
		return objectNode;
	}

}
