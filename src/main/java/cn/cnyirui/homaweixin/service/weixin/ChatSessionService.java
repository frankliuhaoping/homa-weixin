package cn.cnyirui.homaweixin.service.weixin;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.weixin.ChatSessionDao;
import cn.cnyirui.homaweixin.model.po.ChatSession;

/**
 * 会话接口
 * @author zhoujuhui
 *
 */
@Service
public class ChatSessionService extends BaseService<ChatSession> {
    
	@Resource
	private ChatSessionDao chatSessionDao;
	
	@Override
	public BaseDao<ChatSession> getBaseDao() {
		// TODO Auto-generated method stub
		return chatSessionDao;
	}
	
}
