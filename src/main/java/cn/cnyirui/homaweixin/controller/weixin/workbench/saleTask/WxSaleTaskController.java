package cn.cnyirui.homaweixin.controller.weixin.workbench.saleTask;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseController;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.SalesTask;
import cn.cnyirui.homaweixin.service.weixin.WxSalesTaskService;


/**
 * 销售任务
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/weixin/workbench/saleTask")
public class WxSaleTaskController extends BaseController<SalesTask>{

    @Resource
    WxSalesTaskService wxSalesTaskService;
	
	@Override
	protected BaseService<SalesTask> getBaseService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@RequestMapping("/index")
	public String index(Model model){
		
		return "/weixin/workbench/saleTask/saleTask";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model){
		return "/weixin/workbench/saleTask/taskDetail";
	}
	
	@RequestMapping("/saleTaskaChart")
	public String taskaChart(Model model,int year){
		model.addAttribute("year", year);
		return "/weixin/workbench/saleTask/saleTaskaChart";
	}
	
	@RequestMapping("/jsonList")
	@ResponseBody
	public ObjectNode josnList(Model model,String year){
		
		ObjectNode objNode=null;
		if (year==null||year.equals("")) {
			Date date = new Date();
			year = DateFormatUtils.format(date, "yyyy");
			objNode = wxSalesTaskService.getSalesTaskByEmployeeId(Integer.parseInt(year));
		}else{
			objNode = wxSalesTaskService.getSalesTaskByEmployeeId(Integer.parseInt(year));
		}
	
		model.addAttribute("objNode", objNode);
		return objNode;
	}
	
	
	
}
