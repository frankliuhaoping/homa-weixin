package cn.cnyirui.homaweixin.controller.weixin.workbench.wage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.EmployeeWage;
import cn.cnyirui.homaweixin.model.po.Organization;
import cn.cnyirui.homaweixin.service.backend.EmployeeWageService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
/**
 * 任务
 * @author Administrator
 *
 */
@Controller
public class WxEmployeeWageController {
	
	@Resource
	private EmployeeWageService employeeWageService;
	
	@Resource
	private WxMpService wxMpService;
	
	@RequestMapping("/weixin/workbench/employee/wage/index")
	public String index(Model model){
		Employee employee =  CurrentUserUtils.getEmployee(false);//获取员工信息
		Calendar a = Calendar.getInstance();
		model.addAttribute("year", a.get(Calendar.YEAR)); //得到年
		return "/weixin/workbench/wage/wage_index";
	} 
	
	@RequestMapping("/weixin/workbench/employee/wage/list")
	@ResponseBody
	public JsonResponse employeeWageList(Integer year) {
		JsonResponse jsonResponse = null;
		try {
			Employee employee =  CurrentUserUtils.getEmployee(false);
			List<ObjectNode>  list = employeeWageService.findEmployeeWageByYear(year, employee.getId());
			jsonResponse = new JsonResponse(ResponseCode.成功);
			jsonResponse.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
		}
		return jsonResponse;
		
	}
	
	@Transactional
	@RequestMapping("/weixin/workbench/employee/wage/detail/{id}")
	public String employeeWageDetail(Model model, @PathVariable("id") String id){
		try {
			EmployeeWage po = employeeWageService.findOne(id);
			model.addAttribute("id", po.getId());
			model.addAttribute("basicSalary", po.getBasicSalary());
			model.addAttribute("royaltyWage", po.getRoyaltyWage());
			model.addAttribute("overtimeWage", po.getOvertimeWage());
			model.addAttribute("socialInsurance", po.getSocialInsurance());
			model.addAttribute("ageWage", po.getAgeWage());
			model.addAttribute("awardWage", po.getAwardWage());
			model.addAttribute("holidayFee", po.getHolidayFee());
			model.addAttribute("taxDeduction", po.getTaxDeduction());
			model.addAttribute("virtualWage", po.getVirtualWage());
			model.addAttribute("wageMonth", po.getWageMonth());
			model.addAttribute("wageVersion", po.getWageVersion());
			model.addAttribute("isAbnormal", po.getIsAbnormal());
			model.addAttribute("wageYear", po.getWageYear());
			model.addAttribute("actualWage", po.getActualWage());
			SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");
			model.addAttribute("createdTime", ss.format(po.getCreatedTime()));
			
			if(po.getIsRead() == null || !po.getIsRead()){
				po.setIsRead(Boolean.TRUE);
				employeeWageService.save(po);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/weixin/workbench/wage/wage_detail";
	} 
	
	
	@RequestMapping("/weixin/workbench/employee/wage/abnormal")
	@ResponseBody
	public JsonResponse employeeWageAbnormal(String id){
		JsonResponse jsonResponse = null;
		try {
			EmployeeWage po = employeeWageService.findOne(id);
			Employee employee =  CurrentUserUtils.getEmployee(false);
			if(!po.getEmployee().getId().equals(employee.getId()) || po.getWageVersion().intValue() == 2){
				jsonResponse = new JsonResponse(ResponseCode.不允许此方法);
			}
			if(po.getIsAbnormal() != null && po.getIsAbnormal()){
				jsonResponse = new JsonResponse(ResponseCode.不允许重复);
			}
			if(po.getIsAbnormal() == null || !po.getIsAbnormal()){
				po.setIsAbnormal(Boolean.TRUE);
				employeeWageService.save(po);
				jsonResponse = new JsonResponse(ResponseCode.成功);
				
				Organization organization = employee.getOrganization();
				Employee director = organization.getDirector();
				SysUser sysUser = director.getSysUser();
				WeiXinUser weiXinUser = sysUser.getWeiXinUser();
				
				if(organization != null && director != null && sysUser != null && weiXinUser != null){
					WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
					templateMessage.setToUser(weiXinUser.getOpenId());
					templateMessage.setTemplateId("etNWdGeGTR2tTa_W9Z09c7B-qYraTRO_FvHD1iHeuQA");
					templateMessage.setUrl("");
					templateMessage.setTopColor("");
					templateMessage.getDatas().add(new WxMpTemplateData("first", "申请工资异常", "#173177"));
					templateMessage.getDatas().add(new WxMpTemplateData("keyword1", employee.getName(), "#173177"));
					templateMessage.getDatas().add(new WxMpTemplateData("keyword2", "实发工资：" + po.getActualWage(), "#173177"));
					templateMessage.getDatas().add(new WxMpTemplateData("remark", "请您及时处理，谢谢！", "#173177"));
					wxMpService.templateSend(templateMessage);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
		}
		return jsonResponse;
	} 
}
