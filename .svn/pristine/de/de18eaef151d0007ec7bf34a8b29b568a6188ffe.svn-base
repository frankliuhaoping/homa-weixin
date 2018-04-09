package cn.cnyirui.homaweixin.controller.weixin.workbench.chat;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.model.vo.ChatEmployeeVo;
import cn.cnyirui.homaweixin.model.vo.ChatGroupVo;
import cn.cnyirui.homaweixin.model.vo.ChatOrgPageVo;
import cn.cnyirui.homaweixin.model.vo.RecentContactPageVo;
import cn.cnyirui.homaweixin.service.backend.OrganizationService;
import cn.cnyirui.homaweixin.service.weixin.ChatContactService;
import cn.cnyirui.homaweixin.service.weixin.ChatGroupService;
import cn.cnyirui.homaweixin.service.weixin.ChatSessionMemberService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.PropertiesUtils;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
@RequestMapping("/weixin/workbench/chatcontact")
public class ChatContactController extends BaseCRUDController<Organization> {

	public static String webSocketPushCenterUrl = PropertiesUtils.getValueByKeyFromWebPushConfig("PUSH_CENTER_URL") + "/iker_web_socket_push_center";
	
	@Resource
	private ChatContactService chatContactService;
	@Resource
	private ChatGroupService chatGroupService;
	@Resource
	private ChatSessionMemberService chatSessionMemberService;
	@Resource
	private OrganizationService organizationService;
	
	/**
	 * 聊天联系人首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model, Integer type){
		if(type == null){
			type = 1;
		}
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		Integer unReadNum = chatSessionMemberService.sumUnReadNum(employeeId);
		if(unReadNum == null){
			unReadNum = 0;
		}
		model.addAttribute("employeeId", employeeId);
		model.addAttribute("webSocketPushCenterUrl", webSocketPushCenterUrl);
		model.addAttribute("unReadNum", unReadNum);
		model.addAttribute("type", type);
		return "/weixin/workbench/chat/index";
	}

	@RequestMapping("/getChatOrg")
	@ResponseBody
	public JsonResponse getChatOrg(Searchable searchable){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		String orgId = CurrentUserUtils.getOrganization(true).getId();
		String callId = (String)SessionUtils.getSession().getId();
		
		ChatOrgPageVo chatOrgPageVo = chatContactService.getChatOrg(orgId, employeeId, callId, searchable);
		jsonResponse.setResult(chatOrgPageVo);
		return jsonResponse;
	}
	
	@RequestMapping("/getChatOrgEmployee")
	@ResponseBody
	public JsonResponse getChatOrgEmployee(String orgId, boolean directorOrg){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		String myOrgId = CurrentUserUtils.getOrganization(true).getId();
		List<ChatEmployeeVo> employeeVoList = chatContactService.getOrgEmployee(myOrgId, orgId, directorOrg);
		jsonResponse.setResult(employeeVoList);
		return jsonResponse;
	}
	
	@RequestMapping("/searchEmployee")
	@ResponseBody
	public JsonResponse searchEmployee(String keyword){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		String orgId = CurrentUserUtils.getOrganization(true).getId();
		String callId = (String)SessionUtils.getSession().getId();
		if(StringUtils.isNotBlank(keyword)){
			List<ChatEmployeeVo> employeeVoList = chatContactService.getMyContact(orgId, employeeId, keyword, callId);
			jsonResponse.setResult(employeeVoList);
		}
		return jsonResponse;
	}
	
	@RequestMapping("/groupList")
	@ResponseBody
	public JsonResponse groupList(){
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		List<ChatGroupVo> groupList = chatGroupService.getByEmployeeId(employeeId);
		jsonResponse.setResult(groupList);
		
		return jsonResponse;
	}
	
	/**
	 * 跳转到最近联系人
	 * @return
	 */
	@RequestMapping("/to_recent")
	public String toRecent(){
		return "/weixin/workbench/chat/recent";
	}
	
	/**
	 * 最近联系人
	 * @return
	 */
	@RequestMapping("/recent")
	@ResponseBody
	public JsonResponse recentContact(Searchable searchable){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		RecentContactPageVo recentContactPageVo = chatContactService.getRecentContact(searchable, employeeId);
		jsonResponse.setResult(recentContactPageVo);
		return jsonResponse;
	}
	
	/**
	 * 获取我的总未读条数
	 * @return
	 */
	@RequestMapping("/sumUnReadNum")
	@ResponseBody
	public JsonResponse sumUnReadNum(){
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		String employeeId = CurrentUserUtils.getEmployee(true).getId();
		Integer unReadNum = chatSessionMemberService.sumUnReadNum(employeeId);
		if(unReadNum == null){
			unReadNum = 0;
		}
		jsonResponse.setResult(unReadNum);
		return jsonResponse;
	}
	
	@Override
	protected BaseService<Organization> getBaseService() {
		// TODO Auto-generated method stub
		return chatContactService;
	}
}
