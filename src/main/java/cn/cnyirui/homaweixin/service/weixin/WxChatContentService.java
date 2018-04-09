package cn.cnyirui.homaweixin.service.weixin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;
import cn.cnyirui.homaweixin.dao.weixin.WxChatContentDao;
import cn.cnyirui.homaweixin.model.po.ChatContent;
import cn.cnyirui.homaweixin.model.po.ChatSession;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.vo.ChatContentVo;
import cn.cnyirui.homaweixin.model.vo.ChatDetailVo;

/**
 * 聊天信息接口
 * @author zhoujuhui
 *
 */
@Service
public class WxChatContentService extends BaseService<ChatContent> {
    
	@Resource
	private WxChatContentDao wxChatContentDao;
	@Resource
	private EmployeeDao employeeDao;
	@Resource
	private ChatSessionService chatSessionService;
	@Resource
	private ChatSessionMemberService chatSessionMemberService;
	/**
	 * 获取聊天内容
	 * @param searchable
	 * @param employeeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ChatDetailVo getDetail(Long lastTime, String sessionId, 
			String receiverId, String myEmployeeId) {
		EntityManager em = wxChatContentDao.getEntityManager();
		
		ChatDetailVo chatDetailVo = new ChatDetailVo();
		if(StringUtils.isNotBlank(sessionId)){
			String sql1 = "select * from (select to_char(p.content),p.contentType,p.sendTime,p.voiceTime,e.headImgUrl,e.name,p.senderId from chat_content p left join employee e "
					+ "on p.senderId = e.id where 1 = 1 ";
			if(lastTime != null && lastTime != 0){
				sql1 += "and p.sendTime < ? ";
			}
			sql1 += "and p.sessionId = ? order by p.sendTime desc) where rownum <= 20";
			Query query = em.createNativeQuery(sql1);
			
			int index = 1;
			if(lastTime != null && lastTime != 0){
				query.setParameter(index, lastTime);
				index ++;
			}
			query.setParameter(index, sessionId);
			List<Object[]> chatContentList = query.getResultList();
			
			List<ChatContentVo> contentVoList = new ArrayList<ChatContentVo>();
			for (Object[] obj : chatContentList) {
				String content = String.valueOf(obj[0]);
				Integer contentType = Integer.valueOf(obj[1].toString());
				Long sendTime = Long.valueOf(obj[2].toString());
				Integer voiceTime = Integer.valueOf(obj[3].toString());
				String headImgUrl = null;
				if(obj[4] != null){
					headImgUrl = obj[4].toString();
				}
				String name = String.valueOf(obj[5]);
				String senderId = String.valueOf(obj[6]);
				
				ChatContentVo chatContentVo = new ChatContentVo();
				chatContentVo.setContent(content);
				chatContentVo.setContentType(contentType);
				chatContentVo.setSendTime(sendTime);
				chatContentVo.setVoiceTime(voiceTime);
				chatContentVo.setName(name);
				chatContentVo.setHeadImgUrl(headImgUrl);
				chatContentVo.setSenderId(senderId);
				
				contentVoList.add(chatContentVo);
			}
			chatDetailVo.setContentVoList(contentVoList);
		}
		return chatDetailVo;
	}

	@Transactional
	public void addChatContent(ChatContent chatContent) {
		String chatSessionId = null;
		ChatSession chatSession = chatContent.getChatSession();
		if(chatSession != null){
			chatSessionId = chatSession.getId();
		}
		
		String senderId = chatContent.getSender().getId();
		
		String sessionLc = chatContent.getContent();
		if (chatContent.getContentType() == 2) {
			sessionLc = "[图片]";
		}else if (chatContent.getContentType() == 3) {
			sessionLc = "[语音]";
		}
		if(StringUtils.isBlank(chatSessionId)){
			String receiverId = chatContent.getReceiverId();
			String groupId = chatContent.getGroupId();
			if(StringUtils.isNotBlank(receiverId)){
				/**
				 * 新建会话（单聊）
				 */
				chatSession = new ChatSession();
				chatSession.setLastContent(sessionLc);
				chatSession.setLastSendTime(System.currentTimeMillis());
				chatSession.setType(1);
				chatSession = chatSessionService.save(chatSession);
				
				chatSessionId = chatSession.getId();
				
				ChatSessionMember chatSessionMember = new ChatSessionMember();
				chatSessionMember.setChatSession(chatSession);
				Employee receiveEmployee = employeeDao.findOne(receiverId);
				chatSessionMember.setEmployee(receiveEmployee);;
				chatSessionMember.setUnReadNum(1);
				chatSessionMemberService.save(chatSessionMember);
				
				chatSessionMember = new ChatSessionMember();
				chatSessionMember.setChatSession(chatSession);
				Employee sendEmployee = employeeDao.findOne(senderId);
				chatSessionMember.setEmployee(sendEmployee);
				chatSessionMember.setUnReadNum(0);
				chatSessionMemberService.save(chatSessionMember);
			}else if(StringUtils.isNotBlank(groupId)){
				/**
				 * 新建会话（群聊）
				 */
				chatSession = new ChatSession();
				chatSession.setLastContent(sessionLc);
				chatSession.setLastSendTime(System.currentTimeMillis());
				chatSession.setType(2);
				chatSession = chatSessionService.saveAndFlush(chatSession);
				
				/**
				 * 更新chat_session_member的会话id
				 */
				chatSessionMemberService.updateMemberByGroup(chatSession.getId(), groupId);
			}
			
		}else{
			/**
			 * 更新会话
			 */
			chatSession = chatSessionService.findOne(chatSessionId);
			chatSession.setLastContent(sessionLc);
			chatSession.setLastSendTime(System.currentTimeMillis());
			chatSessionService.save(chatSession);
			
			/**
			 * 更新接收人的未读条数
			 */
			chatSessionMemberService.updateMemberUnReadNum(chatSessionId, senderId);
		}
		/**
		 * 保存聊天消息
		 */
		chatContent.setChatSession(chatSession);
		chatContent.setSendTime(System.currentTimeMillis());
		this.save(chatContent);
		
	}

	@Override
	public BaseDao<ChatContent> getBaseDao() {
		// TODO Auto-generated method stub
		return wxChatContentDao;
	}
}
