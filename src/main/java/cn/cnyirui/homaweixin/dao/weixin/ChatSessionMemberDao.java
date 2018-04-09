package cn.cnyirui.homaweixin.dao.weixin;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;

/**
 * 会话成员
 * 
 * @author zhoujuhui
 *
 */
public interface ChatSessionMemberDao extends BaseDao<ChatSessionMember> {

	/**
	 * 更新会话成员的未读条数
	 * @param sessionId
	 * @param extraEmployeeId
	 * @return
	 */
	@Modifying
	@Query("update ChatSessionMember o set o.unReadNum=o.unReadNum + 1 where o.chatSession.id = ?1 and o.employee.id != ?2 ")
	public int updateMemberUnReadNum(String sessionId, String extraEmployeeId);
	
	/**
	 * 设置我的未读条数为0
	 * @param sessionId
	 * @param employeeId
	 * @return
	 */
	@Modifying
	@Query("update ChatSessionMember o set o.unReadNum=0 where o.chatSession.id = ?1 and o.employee.id = ?2 ")
	public int updateUnReadNumZero(String sessionId, String employeeId);
	
	/**
	 * 根据会话id查询会话成员
	 * @param sessionId
	 * @return
	 */
	@Query("from ChatSessionMember o where o.chatSession.id = ?1 ")
	public List<ChatSessionMember> getMemberBySessionId(String sessionId);
	
	/**
	 * 根据群组id删除所有会话成员
	 * @param groupId
	 * @return
	 */
	@Modifying
	@Query("delete ChatSessionMember o where o.chatGroup.id = ?1 ")
	public int deleteMemberByGroup(String groupId);
	
	/**
	 * 更新成员的所属会话
	 * @param sessionId
	 * @param extraEmployeeId
	 * @return
	 */
	@Modifying
	@Query("update ChatSessionMember o set o.chatSession.id = ?1 where o.chatGroup.id = ?2 ")
	public int updateMemberByGroup(String sessionId, String groupId);
	
	/**
	 * 获取我的总未读条数
	 * @param employeeId
	 * @return
	 */
	@Query("select sum(o.unReadNum) from ChatSessionMember o where o.employee.id=?1")
	public Integer sumUnReadNum(String employeeId);
	
	/**
	 * 设置组的未读条数为0
	 * @param groupId
	 * @return
	 */
	@Modifying
	@Query("update ChatSessionMember o set o.unReadNum=0 where o.chatGroup.id = ?1 ")
	public int updateUnReadNumZeroByGroup(String groupId);
}
