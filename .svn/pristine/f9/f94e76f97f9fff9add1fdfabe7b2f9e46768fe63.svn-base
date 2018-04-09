package cn.cnyirui.homaweixin.dao.backend;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.ChatContent;

/**
 * 会话列表
 * 
 * @author jiny
 *
 */
public interface ChatContentDao extends BaseDao<ChatContent> {
	
	/**
	 *  根据会话id获取聊天内容
	 * 
	 * @return jiny
	 */
	@Query("from ChatContent a where a.chatSession.id=?1 Order By a.sendTime asc")
	public List<ChatContent> findChatContentByHhId(String hhid);
			
}
