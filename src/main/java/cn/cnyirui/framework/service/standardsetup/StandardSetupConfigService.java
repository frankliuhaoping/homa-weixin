package cn.cnyirui.framework.service.standardsetup;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.standardsetup.StandardSetupConfigDao;
import cn.cnyirui.framework.model.po.standardsetup.StandardSetupConfig;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;

@Service
public class StandardSetupConfigService extends BaseService<StandardSetupConfig> {

	@Resource
	private StandardSetupConfigDao standardSetupConfigDao;

	@Override
	public BaseDao<StandardSetupConfig> getBaseDao() {
		return standardSetupConfigDao;
	}

	@Override
	public ObjectNode entityToObjectNode(StandardSetupConfig entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		objectNode.put("displayCode", BooleanUtils.isFalse(entity.getDisplayCode()) ? "不显示" : "显示");
		objectNode.put("displayName", BooleanUtils.isFalse(entity.getDisplayName()) ? "不显示" : "显示");
		objectNode.put("displayRemark", BooleanUtils.isFalse(entity.getDisplayRemark()) ? "不显示" : "显示");
		return objectNode;
	}

}
