package cn.cnyirui.homaweixin.service.backend;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.OtherInfoDao;
import cn.cnyirui.homaweixin.model.po.OtherInfo;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class OtherInfoService extends BaseService<OtherInfo> {

	@Resource
	private OtherInfoDao otherInfoDao;

	@Override
	public BaseDao<OtherInfo> getBaseDao() {
		return otherInfoDao;
	}
	
	@Override
	public ObjectNode entityToObjectNode(OtherInfo entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		if(entity.getReleaseTime()!=null){
			DateFormatUtils.format(entity.getReleaseTime(), "yyyy-MM-dd HH-mm-ss");
		}
		if(entity.getIsRelease()==true){
			objectNode.put("isRelease", "否");
		}else{
			objectNode.put("isRelease", "是");
		}
		
		return objectNode;
	}
}
