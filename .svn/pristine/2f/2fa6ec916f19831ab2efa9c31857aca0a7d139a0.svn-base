package cn.cnyirui.homaweixin.service.weixin;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.weixin.ChatSessionMemberDao;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;

/**
 * 会话成员接口
 * @author zhoujuhui
 *
 */
@Service
public class ChatSessionMemberService extends BaseService<ChatSessionMember> {
    
	@Resource
	private ChatSessionMemberDao chatSessionMemberDao;
	
	/**
	 * 更新会话成员的未读条数
	 * @param sessionId
	 * @param extraEmployeeId
	 * @return
	 */
	public int updateMemberUnReadNum(String sessionId, String extraEmployeeId){
		return chatSessionMemberDao.updateMemberUnReadNum(sessionId, extraEmployeeId);
	}
	
	/**
	 * 设置当前会话的已读条数为0
	 * @param sessionId
	 * @param employeeId
	 * @return
	 */
	@Transactional
	public int updateUnReadNumZero(String sessionId, String employeeId){
		return chatSessionMemberDao.updateUnReadNumZero(sessionId, employeeId);
	}
	
	/**
	 * 设置组的未读条数为0
	 * @param groupId
	 * @return
	 */
	@Transactional
	public int updateUnReadNumZeroByGroup(String groupId){
		return chatSessionMemberDao.updateUnReadNumZeroByGroup(groupId);
	}
	
	/**
	 * 获取我的总未读条数
	 * @param employeeId
	 * @return
	 */
	public Integer sumUnReadNum(String employeeId){
		return chatSessionMemberDao.sumUnReadNum(employeeId);
	}
	
	/**
	 * 根据群组id删除所有会话成员
	 * @param groupId
	 * @return
	 */
	public int deleteMemberByGroup(String groupId){
		return chatSessionMemberDao.deleteMemberByGroup(groupId);
	}
	
	/**
	 * 根据会话id查询会话成员
	 * @param sessionId
	 * @return
	 */
	public List<ChatSessionMember> getMemberBySessionId(String sessionId){
		return chatSessionMemberDao.getMemberBySessionId(sessionId);
	}
	
	/**
	 * 更新成员的所属会话
	 * @param sessionId
	 * @param extraEmployeeId
	 * @return
	 */
	public int updateMemberByGroup(String sessionId, String groupId){
		return chatSessionMemberDao.updateMemberByGroup(sessionId, groupId);
	}
	
	/**
	 * 根据聊天人id查询会话id
	 * @param myEmployeeId
	 * @param otherEmployeeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getSessionIdByUser(String myEmployeeId, String otherEmployeeId){
		String sessionId = null;
		EntityManager em = chatSessionMemberDao.getEntityManager();
		String sql = "select a.sessionId from chat_session_member a left join chat_session_member b "
				+ " on a.sessionId = b.sessionId where a.employeeId = ? and b.employeeId = ? and a.groupId is null ";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, myEmployeeId);
		query.setParameter(2, otherEmployeeId);
		List<Object[]> objList = query.getResultList();
		if(objList != null && !objList.isEmpty()){
			sessionId = String.valueOf(objList.get(0));
		}
		return sessionId;
	}
	
	/**
	 * 根据群组id查询会话id
	 * @param groupId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getSessionIdByGroup(String groupId){
		String sessionId = null;
		EntityManager em = chatSessionMemberDao.getEntityManager();
		String sql = "select a.sessionId from chat_session_member a where a.groupId = ? and rownum = 1 ";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, groupId);
		List<Object[]> objList = query.getResultList();
		if(objList != null && !objList.isEmpty()){
			Object obj = objList.get(0);
			if(obj != null){
				sessionId = String.valueOf(objList.get(0));
			}
		}
		return sessionId;
	}
	
	/**
	 * 根据会话id查询群组id
	 * @param groupId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getGroupIdBySession(String sessionId){
		String groupId = null;
		EntityManager em = chatSessionMemberDao.getEntityManager();
		String sql = "select a.groupId from chat_session_member a where a.sessionId = ? and rownum = 1 ";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, sessionId);
		List<Object[]> objList = query.getResultList();
		if(objList != null && !objList.isEmpty()){
			groupId = String.valueOf(objList.get(0));
		}
		return groupId;
	}
	
	
	
	@Override
	public BaseDao<ChatSessionMember> getBaseDao() {
		// TODO Auto-generated method stub
		return chatSessionMemberDao;
	}
	
}
