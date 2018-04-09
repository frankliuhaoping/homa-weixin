package cn.cnyirui.homaweixin.controller.weixin.workbench.queryCount;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.po.rbac.SysUser;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.framework.utils.SessionUtils;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.OrganizationTreeTemp;
import cn.cnyirui.homaweixin.model.po.SalesOrder;
import cn.cnyirui.homaweixin.model.po.SalesOrderDetail;
import cn.cnyirui.homaweixin.model.po.SignRecord;
import cn.cnyirui.homaweixin.service.backend.EmployeeService;
import cn.cnyirui.homaweixin.service.backend.SalesOrderService;
import cn.cnyirui.homaweixin.service.weixin.OrganizationTreeTempService;
import cn.cnyirui.homaweixin.service.weixin.WxSignRecordService;
import cn.cnyirui.homaweixin.utils.JsonResponse;
import cn.cnyirui.homaweixin.utils.ResponseCode;

@Controller
public class QueryCount {
	
	@Resource
	private OrganizationTreeTempService organizationTreeTempService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private SalesOrderService salesOrderService;
	@Resource
	private WxSignRecordService wxSignRecordService;
	
	@RequestMapping("/weixin/workbench/queryCount/index")
	public String index(Model model){
		/*String s = SessionUtils.getSession().getId().toString();"2950c268-0cd1-477a-b926-80a7f3c088c9"*/
		
		//List< List<Employee> > listEmps = new ArrayList<List<Employee>>();
		/*for(OrganizationTreeTemp o:list){
			List<Employee> t = organizationTreeTempService.findEmployeeByOId(o.getOrganizationId());
			listEmps.add(t);
		}*/
		return "/weixin/workbench/queryCount/index";
	}
	
	@RequestMapping("/weixin/workbench/queryCount/organizationListJson")
	@ResponseBody
	public JsonResponse organizationListJson(Searchable searchable) {
		System.err.println("pagenum : "+searchable.getPageable().getPageNumber());
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			String s = SessionUtils.getSession().getId().toString();
			Page<OrganizationTreeTemp> page = organizationTreeTempService.getOrganizationTreeTemp(s,searchable);
			List<OrganizationTreeTemp> organizationTreeTemps = page.getContent();
			List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
			for(int i=0;i<organizationTreeTemps.size();i++){
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodeList.add(objectNode);
				objectNode.put("id", organizationTreeTemps.get(i).getOrganizationId());
				objectNode.put("name", organizationTreeTemps.get(i).getOrganizationName());
				objectNode.put("more",page.hasNext());
			}
			jsonResponse.setResult(objectNodeList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;
		
	}
	
	@RequestMapping("/weixin/workbench/queryCount/employeeMsg")
	public String employeeMsg(Model model,String eid){
		Calendar date=Calendar.getInstance();
		Employee employee = employeeService.findOne(eid);
		
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH)+1;
		int max = getMaxDay(year,month);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fdate = null;
		try {
			fdate = df.parse(year+"-"+month+"-"+"1 00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date ldate = null;
		try {
			ldate = df.parse(year+"-"+month+"-"+ max+" 23:59:59");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<SalesOrder> salesOrders = salesOrderService.findOrderByMonth(eid,fdate,ldate);
		List<String> days = new ArrayList<String>();
		List<Long> nums = new ArrayList<Long>();
		List<Long> moneys = new ArrayList<Long>();
		count(salesOrders,days,nums,moneys);
		System.err.println("year "+year +" month "+month);
		List<SignRecord> signs =  wxSignRecordService.getEmployeeSignList(year,month,eid);
		model.addAttribute("signs", signs);
		model.addAttribute("days", days);
		model.addAttribute("nums", nums);
		model.addAttribute("moneys", moneys);
		model.addAttribute("employee", employee);
		model.addAttribute("year", date.get(Calendar.YEAR));
		model.addAttribute("month", date.get(Calendar.MONTH)+1);
		
		SysUser sysUser = employee.getSysUser();
		String headurl = "/static/wxworkbench/img/head.jpg";
		if(sysUser!=null){
			WeiXinUser weiXinUser = employee.getSysUser().getWeiXinUser();
			if(weiXinUser!=null){
				headurl = weiXinUser.getHeadImgUrl();
			}
		}
		model.addAttribute("headurl", headurl);
		return "/weixin/workbench/queryCount/employeeMsg";
	}
	
	
	
	@RequestMapping("/weixin/workbench/queryCount/employeeSignMsg")
	public String employeeSignMsg(Model model,String eid){
		return "/weixin/workbench/queryCount/employeeMsg";
	}
	
	
	/**
	 * 
	 * 如果oid 为空，按searchable 查询
	 * @param oid
	 * @param searchable
	 * @return
	 */
	
	@RequestMapping("/weixin/workbench/queryCount/employeesListJson")
	@ResponseBody
	public JsonResponse employeesListJson(String oid,Searchable searchable) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			List<Employee> t = null;
			if(oid!=null){
			 t = organizationTreeTempService.findEmployeeByOId(oid);
			 }else{
				 Page<Employee> page = employeeService.findAll(searchable);
				 t = page.getContent();
			 }
			
			List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
			for (Employee o : t) {
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodeList.add(objectNode);
				objectNode.put("id", o.getId());
				objectNode.put("name", o.getName());
				SysUser sysUser = o.getSysUser();
				String headurl = "";
				if(sysUser!=null){
					WeiXinUser weiXinUser = o.getSysUser().getWeiXinUser();
					if(weiXinUser!=null){
						headurl = weiXinUser.getHeadImgUrl();
					}
				}
				
				objectNode.put("head", headurl);
			}
			jsonResponse.setResult(objectNodeList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;
		
	}
	
	
	@RequestMapping("/weixin/workbench/queryCount/countSignJson")
	@ResponseBody
	public JsonResponse countSignJson(String year,String month,String employeeId) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			List<SignRecord> signs =  wxSignRecordService.getEmployeeSignList(Integer.parseInt(year),Integer.parseInt(month),employeeId);
			SimpleDateFormat sFormat = new SimpleDateFormat("d");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
			List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
			
			for(int i=0;i<signs.size();i++){
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodeList.add(objectNode);
				objectNode.put("days", sFormat.format(signs.get(i).getSignTime()));
				objectNode.put("nums", time.format(signs.get(i).getSignTime()));
				objectNode.put("moneys", signs.get(i).getSignType()==0?"上班":"下班");
			}
			
			
			
			jsonResponse.setResult(objectNodeList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;
		
	}
	
	
	//查询统计 订单
	@RequestMapping("/weixin/workbench/queryCount/countOrderJson")
	@ResponseBody
	public JsonResponse countOrderJson(String year,String month,String employeeId) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			int max = getMaxDay(Integer.parseInt(year),Integer.parseInt(month));
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.err.println(df.parse(year+"-"+month+"-"+"1 00:00:00"));
			Date fdate=df.parse(year+"-"+month+"-"+"1 00:00:00");
			Date ldate=df.parse(year+"-"+month+"-"+ max+" 23:59:59");
			
			List<SalesOrder> salesOrders = salesOrderService.findOrderByMonth(employeeId,fdate,ldate);
			List<String> days = new ArrayList<String>();
			List<Long> nums = new ArrayList<Long>();
			List<Long> moneys = new ArrayList<Long>();
			List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
			count(salesOrders,days,nums,moneys);
			Long sumNums = 0L;
			Long sumMoneys = 0L;
			
			
			//统计
			for(int i=0;i<days.size();i++){
				sumNums+=nums.get(i);
				sumMoneys+=moneys.get(i);
			}
			for(int i=0;i<days.size();i++){
				ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
				objectNodeList.add(objectNode);
				objectNode.put("days", days.get(i));
				objectNode.put("nums", nums.get(i));
				objectNode.put("moneys", moneys.get(i));
				objectNode.put("sumNums", sumNums);
				objectNode.put("sumMoneys", sumMoneys);
			}
			
			
			
			
			
			jsonResponse.setResult(objectNodeList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;
		
	}
	
	
	
	private int checkDay(List<String> list ,String day){
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(day)){
				return i;
			}
		}
		return -1;
		
	}
	
	
	private int getMaxDay(int year,int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month,1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	
	private void count(List<SalesOrder> salesOrders,List<String> days,List<Long> nums,List<Long> moneys){
		SimpleDateFormat sFormat = new SimpleDateFormat("d");
		for (SalesOrder o : salesOrders) {
			String d = sFormat.format(o.getSalesTime());
			int index = checkDay(days,d);
			if(index ==-1){
				days.add(d);
				int sales = 0;
				List<SalesOrderDetail> salesOrderDetails = o.getSalesOrderDetailList();
				for(int i=0;i<salesOrderDetails.size();i++){
					sales+= salesOrderDetails.get(i).getQty();
				}
				nums.add((long) sales);
				Double t = o.getSalesMoney();
				if(t==null){
					t = 0.0;
				}
				moneys.add(t.longValue());
			}else{
				int sales = 0;
				List<SalesOrderDetail> salesOrderDetails = o.getSalesOrderDetailList();
				for(int i=0;i<salesOrderDetails.size();i++){
					Integer temp = salesOrderDetails.get(i).getQty();
					
					if(temp==null){
						temp = 1;
					}
					if(temp == 500){
						System.err.println("500");
					}
					sales+= temp;
				}
				nums.set(index,nums.get(index)+(long) sales);
				Double t = o.getSalesMoney();
				if(t!=null)
					moneys.set(index,moneys.get(index)+o.getSalesMoney().longValue());
			}
		}
	}
	
	@RequestMapping("/weixin/workbench/queryCount/index-new")
	public String indexNew(Model model){
		return "/weixin/workbench/queryCount/queryCount";
	}
	
	@RequestMapping("/weixin/workbench/queryCount/organizationListNew")
	@ResponseBody
	public JsonResponse organizationListNew(String parentId) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			List<OrganizationTreeTemp> treeTemp = organizationTreeTempService.getCanViewOrganizationList(parentId);
			jsonResponse.setResult(treeTemp);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;
		
	}
	
	@RequestMapping("/weixin/workbench/queryCount/searchOrgListNew")
	@ResponseBody
	public JsonResponse searchOrgListNew(String keyword) {
		JsonResponse jsonResponse = new JsonResponse(ResponseCode.成功);
		try {
			List<OrganizationTreeTemp> treeTemp = organizationTreeTempService.searchCanViewOrganizationList(keyword);
			jsonResponse.setResult(treeTemp);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse = new JsonResponse(ResponseCode.服务器异常);
			jsonResponse.setMsg("服务器异常");
			return jsonResponse;
		}
		return jsonResponse;
		
	}

}
