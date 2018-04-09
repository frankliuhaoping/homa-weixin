package cn.cnyirui.homaweixin.service.weixin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.weixin.WxMarketingExperienceCommentDao;
import cn.cnyirui.homaweixin.model.po.MarketingExperienceComment;

@Service
public class WxMarketingExperienceCommentService extends BaseService<MarketingExperienceComment> {
	@Resource
	private WxMarketingExperienceCommentDao wxMarketingExperienceCommentDao;
	
	@Override
	public BaseDao<MarketingExperienceComment> getBaseDao() {
		return wxMarketingExperienceCommentDao;
	}

}
