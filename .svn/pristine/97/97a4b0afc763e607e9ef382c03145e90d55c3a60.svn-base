package cn.cnyirui.homaweixin.controller.weixin.workbench.mark;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.MarketingExperience;
import cn.cnyirui.homaweixin.model.po.MarketingExperienceComment;
import cn.cnyirui.homaweixin.model.po.MarketingExperienceImages;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.weixin.WxMarketingExperienceCommentService;
import cn.cnyirui.homaweixin.service.weixin.WxMarketingExperienceImagesService;
import cn.cnyirui.homaweixin.service.weixin.WxMarketingExperienceService;
import cn.cnyirui.homaweixin.utils.Base64Utils;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
public class WxMarketingExperienceController {
	
	@Resource
	private WxMarketingExperienceService wxMarketingExperienceService;
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private WxMarketingExperienceImagesService wxMarketingExperienceImagesService;
	
	@Resource
	private WxMarketingExperienceCommentService wxMarketingExperienceCommentService;
	
	@RequestMapping("/weixin/workbench/marketing/experience/index")
	public String MarketingExperienceIndex(Model model){
		Employee employee =  CurrentUserUtils.getEmployee(false);
		model.addAttribute("employee", employee);
		return "/weixin/workbench/mark/mark_index";
	}
	
	
	@RequestMapping("/weixin/workbench/marketing/experience/mine")
	public String MarketingExperienceIndex(Model model, String employeeId){
		Employee employee =  employeeService.findOne(employeeId);
		Employee mine =  CurrentUserUtils.getEmployee(false);
		Date date= new Date();
		String date1 = DateFormatUtils.format(date, "yyyy-MM-dd");
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
		w = 0;
		model.addAttribute("date",date1);
		model.addAttribute("weekDay", weekDays[w]);
		model.addAttribute("employee", employee);
		model.addAttribute("mine", mine);
		return "/weixin/workbench/mark/mark_mine";
	}
	
	@RequestMapping("/weixin/workbench/marketing/experience/add")
	public String MarketingExperienceAdd(Model model, String employeeId){
		model.addAttribute("employeeId", employeeId);
		return "/weixin/workbench/mark/mark_add";
	}
	
	
	@RequestMapping("/weixin/workbench/marketing/experience/list")
	@ResponseBody
	public JsonResponse MarketingExperienceList(Searchable searchable, Integer type){
		JsonResponse jsonResponse = null;
		try {
			if(searchable.findSearchFilter("id", SearchOperator.eq).size() > 0){
				searchable.setPageable(0, 1);
			}
			
			List<ObjectNode>  list = wxMarketingExperienceService.findMarketingExperience(searchable);
			jsonResponse = new JsonResponse(ResponseCode.成功);
			jsonResponse.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
		}
		return jsonResponse;
	}
	
	@RequestMapping("/weixin/workbench/marketing/experience/doAdd")
	@ResponseBody
	public JsonResponse doMarketingExperienceAdd(HttpServletRequest request, String imagesData, String content){
		JsonResponse jsonResponse = null;
		try {
			if(imagesData.equals("") && StringUtils.isBlank(content)){
				jsonResponse = new JsonResponse(ResponseCode.不允许为空);
				return jsonResponse;
			}
			String[] imagesUrl = imagesData.split(",");
			List<String> fileUrlList = new ArrayList<String>();
			for(String s : imagesUrl){
				if(StringUtils.isBlank(s)){
					continue;
				}
				byte[] imageBytes = Base64Utils.decode(s);
				String dir = "/upload/wxInvoke";
				String path = request.getSession().getServletContext().getRealPath(dir);

				String file = Base64Utils.saveImage(imageBytes, path);
				fileUrlList.add(dir + "/" + file);
				System.out.println(dir + "/" + file);
			}
			
			MarketingExperience vo = new MarketingExperience();
			vo.setContent(content);
			vo = wxMarketingExperienceService.save(vo);
			
			for(String s : fileUrlList){
				MarketingExperienceImages img = new MarketingExperienceImages();
				img.setImageUrl(s);
				img.setMarketingExperience(vo);
				wxMarketingExperienceImagesService.save(img);
			}
			jsonResponse = new JsonResponse(ResponseCode.成功);
			jsonResponse.setResult(vo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
		}
		return jsonResponse;
	}
	
	
	
	@RequestMapping("/weixin/workbench/marketing/experience/comment/add")
	@ResponseBody
	public JsonResponse marketingExperienceCommentAdd(HttpServletRequest request, String markId, String commentContent){
		JsonResponse jsonResponse = null;
		Employee employee =  CurrentUserUtils.getEmployee(false);
		try {
			if(StringUtils.isBlank(markId) || StringUtils.isBlank(commentContent) || employee == null){
				jsonResponse = new JsonResponse(ResponseCode.不允许为空);
			}else{
				MarketingExperience e = wxMarketingExperienceService.findOne(markId);
				MarketingExperienceComment vo = new MarketingExperienceComment();
				vo.setContent(commentContent);
				vo.setCommentTime(new Date());
				vo.setMarketingExperience(e);
				vo.setLastModifiedTime(new Date());
				vo.setLastModifiedBy(employee.getSysUser());
				vo.setCommentBy(employee.getSysUser());
				vo = wxMarketingExperienceCommentService.save(vo);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("content", vo.getContent());
				map.put("commentBy", employee.getName());
				
				jsonResponse = new JsonResponse(ResponseCode.成功);
				jsonResponse.setResult(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
		}
		return jsonResponse;
	}
}
