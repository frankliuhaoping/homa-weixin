package cn.cnyirui.homaweixin.service.backend;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.NoticeDao;
import cn.cnyirui.homaweixin.model.po.Notice;

@Service
public class NoticeService extends BaseService<Notice>{

	@Resource
	private NoticeDao noticeDao;
	
	@Override
	public BaseDao<Notice> getBaseDao() {
		return noticeDao;
	}
	
	@Override
	public ObjectNode entityToObjectNode(Notice entity, EntityConfig config) {
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
