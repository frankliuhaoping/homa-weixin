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
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;
import cn.cnyirui.homaweixin.dao.weixin.ChatGroupDao;
import cn.cnyirui.homaweixin.model.po.ChatGroup;
import cn.cnyirui.homaweixin.model.po.ChatSession;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.vo.ChatGroupVo;
import cn.cnyirui.homaweixin.model.vo.RecentContactVo;

/**
 * 群组接口
 * @author zhoujuhui
 *
 */
@Service
public class ChatGroupService extends BaseService<ChatGroup> {
    
	@Resource
	private ChatGroupDao chatGroupDao;
	@Resource
	private EmployeeDao employeeDao;
	@Resource
	private ChatSessionService chatSessionService;
	@Resource
	private ChatSessionMemberService chatSessionMemberService;
	/**
	 * 获取用户的群组列表
	 * @param employeeId
	 * @return
	 */
	public List<ChatGroupVo> getByEmployeeId(String employeeId){
		String csyser = CurrentUserUtils.getSysUser().getId();
		List<ChatGroupVo> groupVoList = new ArrayList<ChatGroupVo>();
		List<ChatGroup> chatGroupList = chatGroupDao.getByEmployeeId(employeeId);
		for(ChatGroup chatGroup : chatGroupList){
			ChatGroupVo chatGroupVo = new ChatGroupVo();
			chatGroupVo.setId(chatGroup.getId());
			chatGroupVo.setName(chatGroup.getName());
			chatGroupVo.setHeadImg(chatGroup.getHeadImg());
			chatGroupVo.setRemark(chatGroup.getRemark());
			if(chatGroup.getCreatedBy() != null){
				chatGroupVo.setCreater(chatGroup.getCreatedBy().getId());
			}
			chatGroupVo.setCsyser(csyser);
			groupVoList.add(chatGroupVo);
		}
		return groupVoList;
	}

	/**
	 * 新增修改群组
	 */
	@Transactional
	public ChatGroup editGroup(ChatGroup chatGroup, String currentEmployeeId, String groupMemberIds){
		if(StringUtils.isNoneBlank(groupMemberIds)){
			ChatSession chatSession = null;
			if(StringUtils.isBlank(chatGroup.getId())){
				/**
				 * 新建群组（群聊）
				 */
				chatGroup = chatGroupDao.save(chatGroup);
				
			}else{
				ChatGroup oldChatGroup = chatGroupDao.findOne(chatGroup.getId());
				oldChatGroup.setName(chatGroup.getName());
				oldChatGroup.setRemark(chatGroup.getRemark());
				if(StringUtils.isNotBlank(chatGroup.getHeadImg())){
					oldChatGroup.setHeadImg(chatGroup.getHeadImg());
				}
				chatGroup = chatGroupDao.save(oldChatGroup);
				
				String sessionId = chatSessionMemberService.getSessionIdByGroup(chatGroup.getId());
				if(StringUtils.isNotBlank(sessionId)){
					chatSession = chatSessionService.findOne(sessionId);
				}
				/**
				 * 删除之前的会话成员
				 */
				chatSessionMemberService.deleteMemberByGroup(chatGroup.getId());
			}
			
			ChatSessionMember chatSessionMember = new ChatSessionMember();
			Employee currentEmployee = employeeDao.findOne(currentEmployeeId);
			chatSessionMember.setEmployee(currentEmployee);;
			chatSessionMember.setUnReadNum(0);
			chatSessionMember.setChatGroup(chatGroup);
			if(chatSession != null){
				chatSessionMember.setChatSession(chatSession);
			}
			chatSessionMemberService.save(chatSessionMember);
			
			String[] memberIds = groupMemberIds.split(",");
			for(String memberId : memberIds){
				chatSessionMember = new ChatSessionMember();
				Employee receiveEmployee = employeeDao.findOne(memberId);
				chatSessionMember.setEmployee(receiveEmployee);;
				chatSessionMember.setUnReadNum(0);
				chatSessionMember.setChatGroup(chatGroup);
				if(chatSession != null){
					chatSessionMember.setChatSession(chatSession);
				}
				chatSessionMemberService.save(chatSessionMember);
			}
		}
		return chatGroup;
	}
	
	@Override
	public BaseDao<ChatGroup> getBaseDao() {
		// TODO Auto-generated method stub
		return chatGroupDao;
	}
}
