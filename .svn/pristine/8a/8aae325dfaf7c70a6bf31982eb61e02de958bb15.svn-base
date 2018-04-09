package cn.cnyirui.homaweixin.controller.weixin.workbench.chat;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.homaweixin.model.po.ChatGroup;
import cn.cnyirui.homaweixin.model.po.ChatSessionMember;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.weixin.ChatContactService;
import cn.cnyirui.homaweixin.service.weixin.ChatGroupService;
import cn.cnyirui.homaweixin.service.weixin.ChatSessionMemberService;
import cn.cnyirui.homaweixin.utils.Base64Utils;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
@RequestMapping("/weixin/workbench/chatgroup")
public class ChatGroupController extends BaseCRUDController<ChatGroup>{

	@Resource
	private HttpServletRequest request;
	@Resource
	private ChatGroupService chatGroupService;
	@Resource
	private ChatContactService chatContactService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private ChatSessionMemberService chatSessionMemberService;
	
	
	@RequestMapping("/detail")
	public String detail(Model model, String groupId){
		if(StringUtils.isNotBlank(groupId)){
			ChatGroup chatGroup = chatGroupService.findOne(groupId);
			model.addAttribute("chatGroup", chatGroup);
			
			List<ChatSessionMember> groupMembers = chatGroup.getChatSessionMembers();
			model.addAttribute("chatSessionMemberList", groupMembers);
		}
		return "/weixin/workbench/chat/group_detail";
	}
	
	@RequestMapping("/addOrUpdateInit")
	public String addOrUpdateInit(Model model, String groupId){
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		if(StringUtils.isNotBlank(groupId)){
			ChatGroup chatGroup = chatGroupService.findOne(groupId);
			model.addAttribute("chatGroup", chatGroup);
			
			List<ChatSessionMember> groupMembers = chatGroup.getChatSessionMembers();
			
			String checkedEmployees = "";
			int index = 0;
			int cindex = -1;
			for(ChatSessionMember chatSessionMember : groupMembers){
				if(chatSessionMember.getEmployee().getId().equals(employeeId)){
					cindex = index;
					continue;
				}
				checkedEmployees += chatSessionMember.getEmployee().getId() + ",";
				index ++;
			}
			/**
			 * 页面不显示当前登录人
			 */
			if(cindex > -1){
				groupMembers.remove(cindex);
			}
			model.addAttribute("chatSessionMemberList", groupMembers);
			model.addAttribute("checkedEmployees", checkedEmployees);
		}
		model.addAttribute("employeeId", employeeId);
		return "/weixin/workbench/chat/group_edit";
	}
	
	@RequestMapping("/addOrUpdate")
	@ResponseBody
	public JsonResponse addOrUpdate(ChatGroup chatGroup,String groupMemberIds){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		if(StringUtils.isNoneBlank(chatGroup.getHeadImg())){
			String foldpath = "/upload/images/"+DateFormatUtils.format(new Date(), "yyyyMMdd");
			String contextPath = request.getContextPath();
			String realpath = request.getSession().getServletContext().getRealPath(foldpath);
			File file = new File(realpath);
			if(!file.exists()){
				file.mkdirs();
			}
			byte[] imageBytes = Base64Utils.decode(chatGroup.getHeadImg());
			String picture = Base64Utils.saveImage(imageBytes, realpath);
			String picPath = contextPath + foldpath + "/" + picture;
			chatGroup.setHeadImg(picPath);
		}
		ChatGroup newChatGroup = chatGroupService.editGroup(chatGroup, employeeId, groupMemberIds);
		
		jsonResponse.setResult(newChatGroup.getId());
		return jsonResponse;
	}
	
	@RequestMapping("/personal")
	public String personal(Model model, String otherId){
		String currentEmployeeId = CurrentUserUtils.getEmployee(true).getId();
		Employee employee = employeeService.findOne(otherId);
		String remark = "";
		if(StringUtils.isNotBlank(employee.getSysRoleType())){
			remark = SysRoleType.valueOf(employee.getSysRoleType()).getText();
		}
		employee.setRemark(remark);
		model.addAttribute("employee", employee);
		model.addAttribute("currentEmployeeId", currentEmployeeId);
		return "/weixin/workbench/chat/personal";
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public JsonResponse del(String id){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		if(StringUtils.isNotBlank(id)){
			ChatGroup chatGroup = chatGroupService.findOne(id);
			chatGroup.setIsDel(1);
			chatGroupService.save(chatGroup);
			/**
			 * 更新组成员的未读条数为0
			 */
			chatSessionMemberService.updateUnReadNumZeroByGroup(id);
		}
		return jsonResponse;
	}
	
	@Override
	protected BaseService<ChatGroup> getBaseService() {
		// TODO Auto-generated method stub
		return chatGroupService;
	}
}
