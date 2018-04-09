package cn.cnyirui.homaweixin.controller.weixin.workbench.chat;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.homaweixin.model.po.ChatContent;
import cn.cnyirui.homaweixin.model.po.ChatGroup;
import cn.cnyirui.homaweixin.model.po.ChatSession;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.vo.ChatDetailVo;
import cn.cnyirui.homaweixin.model.vo.ChatResultVo;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.webPush.IContentPushService;
import cn.cnyirui.homaweixin.service.weixin.ChatGroupService;
import cn.cnyirui.homaweixin.service.weixin.ChatSessionMemberService;
import cn.cnyirui.homaweixin.service.weixin.ChatSessionService;
import cn.cnyirui.homaweixin.service.weixin.WxChatContentService;
import cn.cnyirui.homaweixin.utils.Base64Utils;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.PropertiesUtils;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
@RequestMapping("/weixin/workbench/chatcontent")
public class WxChatContentController extends BaseCRUDController<ChatContent>{

	public static String webSocketPushCenterUrl = PropertiesUtils.getValueByKeyFromWebPushConfig("PUSH_CENTER_URL") + "/iker_web_socket_push_center";
	
	@Resource
	private HttpServletRequest request;
	@Resource
	private WxChatContentService wxChatContentService;
	@Resource(name = "contentPushServiceImpl")
	private IContentPushService iContentPushService; // 推送给APP
	@Resource
	private ChatSessionMemberService chatSessionMemberService;
	@Resource
	private ChatSessionService chatSessionService;
	@Resource
	private ChatGroupService chatGroupService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private WxMpService wxMpService;
	
	/**
	 * 跳转到聊天详情页面(获取是否存在会话id)
	 * @param model
	 * @param sessionId
	 * @param groupId
	 * @param otherId
	 * @return
	 */
	@RequestMapping("/to_detail")
	public String toDetail(Model model, String sessionId, String groupId, String otherId){
		String newSessionId = "";
		Employee employee = CurrentUserUtils.getEmployee(true);
		Integer type = 1;
		if(StringUtils.isNotBlank(sessionId)){
			newSessionId = sessionId;
		}else if(StringUtils.isNotBlank(groupId)){
			newSessionId = chatSessionMemberService.getSessionIdByGroup(groupId);
			type = 2;
		}else if(StringUtils.isNotBlank(otherId)){
			newSessionId = chatSessionMemberService.getSessionIdByUser(employee.getId(), otherId);
		}
		
		String newGroupId = groupId;
		String newOtherId = otherId;
		String title = "";
		if(StringUtils.isNotBlank(newSessionId)){
			ChatSession chatSession = chatSessionService.findOne(newSessionId);
			type = chatSession.getType();
			if(type == 2 && StringUtils.isBlank(newGroupId)){
				newGroupId = chatSessionMemberService.getGroupIdBySession(newSessionId);
			}else if(type == 1 && StringUtils.isBlank(newOtherId)){
				List<ChatSessionMember> members = chatSession.getChatSessionMembers();
				for(ChatSessionMember chatSessionMember : members){
					if(!employee.getId().equals(chatSessionMember.getEmployee().getId())){
						newOtherId = chatSessionMember.getEmployee().getId();
						title = "与"+chatSessionMember.getEmployee().getName()+"聊天";
						break;
					}
				}
			}
		}
		
		if(StringUtils.isBlank(title)){
			if(type == 1){
				if(StringUtils.isNotBlank(newOtherId)){
					Employee nEmployee = employeeService.findOne(newOtherId);
					title = "与"+nEmployee.getName()+"聊天";
				}
			}else if(type == 2){
				if(StringUtils.isNotBlank(newGroupId)){
					ChatGroup chatGroup = chatGroupService.findOne(newGroupId);
					title = chatGroup.getName();
				}
			}
		}
		
		model.addAttribute("type", type);
		model.addAttribute("title", title);
		model.addAttribute("sessionId", newSessionId);
		model.addAttribute("groupId", newGroupId);
		model.addAttribute("otherId", newOtherId);
		
		model.addAttribute("employeeId", employee.getId());
		model.addAttribute("webSocketPushCenterUrl", webSocketPushCenterUrl);
		return "/weixin/workbench/chat/detail";
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public JsonResponse detail(Long lastTime, 
			String sessionId, String receiverId){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		Employee employee = CurrentUserUtils.getEmployee(true);
		String headImg = CurrentUserUtils.getWeiXinUser(true).getHeadImgUrl();
		String myEmployeeId = employee.getId();
		ChatDetailVo chatDetailVo = wxChatContentService.getDetail(lastTime, sessionId, receiverId, myEmployeeId);
		chatDetailVo.setSenderId(employee.getId());
		chatDetailVo.setSenderName(employee.getName());
		chatDetailVo.setSenderHeadImg(headImg);
		chatDetailVo.setCurrentTime(System.currentTimeMillis());
		jsonResponse.setResult(chatDetailVo);
		return jsonResponse;
	}

	@RequestMapping("/chatadd")
	@ResponseBody
	public JsonResponse add(HttpServletRequest request, String uuid, ChatContent chatContent) {
		Employee employee = CurrentUserUtils.getEmployee(true);
		chatContent.setSender(employee);
		
		if(chatContent.getContentType() == 2){
			/**
			 * 图片
			 */
			String picPath = saveImg(chatContent.getContent());
			chatContent.setContent(picPath);
		}else if(chatContent.getContentType() == 3){
			/**
			 * 语音
			 */
			String voicePath = saveVoice(chatContent.getContent());
			chatContent.setContent(voicePath);
		}
		
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		wxChatContentService.addChatContent(chatContent);
		
		/**
		 * 构造返回的json数据
		 */
		JSONObject message = new JSONObject();
		
		message.put("id", chatContent.getId());
		message.put("content", chatContent.getContent());
		message.put("contentType", chatContent.getContentType());
		message.put("voiceTime", chatContent.getVoiceTime());
		message.put("sendTime", chatContent.getSendTime());
		message.put("chatSessionId", chatContent.getChatSession().getId());
		message.put("name", employee.getName());	
		message.put("headImgUrl", CurrentUserUtils.getWeiXinUser(true).getHeadImgUrl());	
	    
		/**
		 * websocket推送消息
		 */
		List<ChatSessionMember> chatSessionMemberList = chatSessionMemberService.getMemberBySessionId(chatContent.getChatSession().getId());
		for(ChatSessionMember chatSessionMember : chatSessionMemberList){
			String receiverId = chatSessionMember.getEmployee().getId();
			if(!receiverId.equals(employee.getId())){
				iContentPushService.pushMessage(
						"/message/receive_chat_message/employee/" + receiverId,
						message.toString());
			}
		}
		
		ChatResultVo resultVo = new ChatResultVo();
		resultVo.setUuid(uuid);
		resultVo.setSessionId(chatContent.getChatSession().getId());
		jsonResponse.setResult(resultVo);
		return jsonResponse;
	}
	
	/**
	 * 设置我的未读条数为0
	 * @param sessionId
	 * @param employeeId
	 * @return
	 */
	@RequestMapping("/updateUnReadNumZero")
	@ResponseBody
	public JsonResponse updateUnReadNumZero(String sessionId){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		chatSessionMemberService.updateUnReadNumZero(sessionId, employeeId);
		return jsonResponse;
	}
	
	private String saveImg(String content){
		String foldpath = "/upload/images/"+DateFormatUtils.format(new Date(), "yyyyMMdd");
		String contextPath = request.getContextPath();
		String realpath = request.getSession().getServletContext().getRealPath(foldpath);
		File file = new File(realpath);
		if(!file.exists()){
			file.mkdirs();
		}
		
		content = content.substring(content.indexOf("base64,")+7);
		System.out.println(content);
		byte[] imageBytes = Base64Utils.decode(content);
		String picture = Base64Utils.saveImage(imageBytes, realpath);
		String picPath = contextPath + foldpath + "/" + picture;
		return picPath;
	}
	
	private String saveVoice(String mediaId){
		String foldpath = "/upload/voice/"+DateFormatUtils.format(new Date(), "yyyyMMdd");
		String contextPath = request.getContextPath();
		String realpath = request.getSession().getServletContext().getRealPath(foldpath);
		File foldFile = new File(realpath);
		if(!foldFile.exists()){
			foldFile.mkdirs();
		}
		
		String lastFileName = UUID.randomUUID().toString().replaceAll("-", "") + ".amr";
		String fullPath = realpath + "/" + lastFileName;
		File destFile = new File(fullPath);
		
		try {
			File srcFile = wxMpService.mediaDownload(mediaId);
			FileUtils.copyFile(srcFile, destFile);
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String voicePath = contextPath + foldpath + "/" + lastFileName;
		return voicePath;
	}
	
	@Override
	protected BaseService<ChatContent> getBaseService() {
		// TODO Auto-generated method stub
		return wxChatContentService;
	}
	
	
	
	
}
