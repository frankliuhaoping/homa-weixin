package cn.cnyirui.homaweixin.service.backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.backend.ChatHistoryDao;
import cn.cnyirui.homaweixin.model.po.ChatGroup;
import cn.cnyirui.homaweixin.model.po.ChatSession;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;
import cn.cnyirui.homaweixin.model.po.Employee;


@Service
public class ChatHistroyService extends BaseService<ChatSession> {
    
	@Resource
	private ChatHistoryDao chatHistoryDao;
	
	@Override
	public BaseDao<ChatSession> getBaseDao() {
		// TODO Auto-generated method stub
		return chatHistoryDao;
	}
	

	
	/**
	 * 获取聊天列表数据
	 * @return jiny 2015-11-27
	 */
	public ObjectNode getChatHistoryList() {
		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		List<ChatSession> chatSessionList = null;
		chatSessionList=chatHistoryDao.findAll();
		for (ChatSession chatSession : chatSessionList) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNodeList.add(objectNode);
			
			objectNode.put("id", chatSession.getId());
			int isPersonal=chatSession.getType();
			objectNode.put("type",isPersonal);
			objectNode.put("lastContent", chatSession.getLastContent());
			Date date=new Date(chatSession.getLastSendTime());
		    String lastTime = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
			objectNode.put("lastTime",lastTime);
			
			if (isPersonal==1) {
				//如果是个人的则把名字写上去
				List<ChatSessionMember> chatSessionMemberList = chatHistoryDao.findEnployeeNoByHhId(chatSession.getId());
				if(chatSessionMemberList.size()>0){
				List<Employee> employee = chatHistoryDao.findEnployeeByEmloyeeNo(chatSessionMemberList.get(0).getEmployee().getId(),chatSessionMemberList.get(1).getEmployee().getId());
				objectNode.put("name",employee.get(0).getName()+"与"+employee.get(1).getName());
                   //个人头像信息
				objectNode.put("image",employee.get(0).getHeadImgUrl());
				objectNode.put("image2",employee.get(1).getHeadImgUrl());
				
				}
			} else {

				List<ChatGroup> chatGroup = chatHistoryDao.findGroupByHhId(chatSession.getId());
				if(chatGroup.size()>0){
					objectNode.put("name",chatGroup.get(0).getName());
					if(chatGroup.get(0).getHeadImg()==null||chatGroup.get(0).getHeadImg().equals("")){
						objectNode.put("image","/static/wxworkbench/img/groud.png");
					}else{
						objectNode.put("image",chatGroup.get(0).getHeadImg());
					}
					
					
				}
			}
		}
		ObjectNode Node = JsonUtil.getObjectMapper().createObjectNode();
		Node.putArray("rows").addAll(objectNodeList);
		Node.put("total", objectNodeList.size());
		return Node;
		
	}

}
