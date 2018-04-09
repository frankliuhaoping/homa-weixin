package cn.cnyirui.framework.service.weixin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.weixin.WeiXinUserSubscribeRecordDao;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinUserSubscribeRecord;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;

@Service
public class WeiXinUserSubscribeRecordService extends BaseService<WeiXinUserSubscribeRecord> {

	@Resource
	private WeiXinUserSubscribeRecordDao weiXinUserSubscribeRecordDao;
	@Resource
	private WeiXinUserService weiXinUserService;

	@Override
	public BaseDao<WeiXinUserSubscribeRecord> getBaseDao() {
		return weiXinUserSubscribeRecordDao;
	}

	@Override
	public ObjectNode entityToObjectNode(WeiXinUserSubscribeRecord entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		objectNode.put("isSubscribe", boolTrueOrFalse(entity.getIsSubscribe()));
		return objectNode;
	}

	private String boolTrueOrFalse(boolean the) {
		if (the == true) {
			return "关注公从号";
		} else {
			return "取消关注公众号";
		}
	}

	/**
	 * 添加微信关注、取消关注 记录
	 * 
	 * @param wxOpenId
	 * @param isSubscribe true为关注，false为取消关注
	 */
	public void addWeiXinUserSubscribeRecord(String wxOpenId, boolean isSubscribe) {
		WeiXinUser weiXinUser = weiXinUserService.findOrCreateByWxOpenId(wxOpenId, isSubscribe);
		if (weiXinUser != null) {
			WeiXinUserSubscribeRecord weiXinUserSubscribeRecord = new WeiXinUserSubscribeRecord();
			weiXinUserSubscribeRecord.setIsSubscribe(isSubscribe);
			weiXinUserSubscribeRecord.setWeiXinUser(weiXinUser);
			weiXinUserSubscribeRecordDao.save(weiXinUserSubscribeRecord);
		}
	}

}
