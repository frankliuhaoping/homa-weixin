package cn.cnyirui.homaweixin.dao.backend;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.ChatGroup;
import cn.cnyirui.homaweixin.model.po.ChatSession;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;
import cn.cnyirui.homaweixin.model.po.Employee;

/**
 * 会话列表
 * 
 * @author jiny
 *
 */
public interface ChatHistoryDao extends BaseDao<ChatSession> {
	
	/**
	 * 根据会话id获取两个员工号(个人聊天)
	 * 
	 * @return jiny
	 */
	@Query("from ChatSessionMember o where o.chatSession.id=?1")
	public List<ChatSessionMember> findEnployeeNoByHhId(String hhid);
	
	/**
	 * 根据两个员工号查询到两个员工对象
	 * 
	 * @return jiny
	 */
	@Query("from Employee o where o.id=?1 or o.id=?2")
	public List<Employee> findEnployeeByEmloyeeNo(String employeeNo1,String employeeNo2);
	
	/**
	 * 如果是群聊天则根据会话id获取群对象
	 * select distinct(a.groupId) from chat_session_member a left join chat_group b on a.groupId=b.id where a.sessionId=2
	 * @return jiny
	 */
	@Query("select distinct a.chatGroup from ChatSessionMember a where a.chatSession.id=?1")
	public List<ChatGroup> findGroupByHhId(String hhid);
	
	

		
}
