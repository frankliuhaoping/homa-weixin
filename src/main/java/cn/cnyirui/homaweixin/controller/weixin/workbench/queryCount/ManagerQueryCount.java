package cn.cnyirui.homaweixin.controller.weixin.workbench.queryCount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.backend.OrganizationService;
import cn.cnyirui.homaweixin.service.backend.SalesOrderService;
import cn.cnyirui.homaweixin.service.weixin.OrganizationTreeTempService;
import cn.cnyirui.homaweixin.service.weixin.WxSignRecordService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
public class ManagerQueryCount {
	
	@Resource
	private OrganizationTreeTempService organizationTreeTempService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private SalesOrderService salesOrderService;
	@Resource
	private WxSignRecordService wxSignRecordService;
	@Resource
	private OrganizationService organizationService;
	
	@RequestMapping("/weixin/workbench/queryCount/Mindex")
	public String index(Model model,String orderId){
		/*String s = SessionUtils.getSession().getId().toString();"2950c268-0cd1-477a-b926-80a7f3c088c9"*/
		/*Page<OrganizationTreeTemp> page = organizationTreeTempService.getOrganizationTreeTemp(s);
		List<OrganizationTreeTemp> organizationTreeTemps = page.getContent();*/
		//List< List<Employee> > listEmps = new ArrayList<List<Employee>>();
		/*for(OrganizationTreeTemp o:list){
			List<Employee> t = organizationTreeTempService.findEmployeeByOId(o.getOrganizationId());
			listEmps.add(t);
		}*/
		/*model.addAttribute("organizationTreeTemps", organizationTreeTemps);*/
		
		return "/weixin/workbench/queryCount/Mindex";
	}
	
	
	
	
	@RequestMapping("/weixin/workbench/queryCount/departmentMsg")
	public String employeeMsg(Model model,String eid){
		Calendar date=Calendar.getInstance();
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH)+1;
		String s = SessionUtils.getSession().getId().toString();
		Page<HashMap<String, Object>> page = organizationTreeTempService.getCountOfDepartment(s, eid, year+"", month+"");
		List<HashMap<String, Object>> list = page.getContent();
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("list", list);
		model.addAttribute("eid", eid);
		model.addAttribute("name",organizationService.findOne(eid).getName());
		return "/weixin/workbench/queryCount/departmentMsg";
	}
	
	
	@RequestMapping("/weixin/workbench/queryCount/departListJson")
	@ResponseBody
	public JsonResponse departListJson(String year,String month,String eid) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			String s = SessionUtils.getSession().getId().toString();
			Page<HashMap<String, Object>> page = organizationTreeTempService.getCountOfDepartment(s, eid, year+"", month+"");
			List<HashMap<String, Object>> list = page.getContent();
			Long sumMoneys = 0l;
			Long sumNums =0l;
			for(int i=0;i<list.size();i++){
				
				sumMoneys+=((BigDecimal)list.get(i).get("SALESMONEY")).longValue();
				sumNums+=((BigDecimal)list.get(i).get("NUMS")).longValue();
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sumMoneys", sumMoneys);
			map.put("sumNums", sumNums);
			map.put("listData", list);
			//统计
			//list.add(map);
			List<HashMap<String, Object>> ret = new ArrayList<HashMap<String,Object>>();
			ret.add(map);
			jsonResponse.setResult(ret);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;
		
	}
	
}
