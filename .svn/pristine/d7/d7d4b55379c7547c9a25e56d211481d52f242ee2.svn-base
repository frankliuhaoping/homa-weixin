package cn.cnyirui.homaweixin.controller.weixin.workbench.sign;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseController;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.homaweixin.dao.backend.EmployeeDao;
import cn.cnyirui.homaweixin.model.po.Employee;
import cn.cnyirui.homaweixin.model.po.SignRecord;
import cn.cnyirui.homaweixin.service.weixin.WxSignRecordService;
import cn.cnyirui.homaweixin.utils.PictureUtils;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
/**
 * 签到
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/weixin/workbench/sign")
public class WxSignRecordController extends BaseController<SignRecord>{
 
	@Resource
	private WxSignRecordService wxSignRecordService;
	
	@Resource
	private EmployeeDao employeeDao;
	

	
	@Override
	protected BaseService<SignRecord> getBaseService() {
		// TODO Auto-generated method stub
		return wxSignRecordService;
	}

	/**
	 * 签到首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model){
		
		Employee employee = CurrentUserUtils.getEmployee(false);
		WeiXinUser weixinUser = CurrentUserUtils.getWeiXinUser(false);
		wxSignRecordService.getSign(model,employee.getId());//查询上班记录
		model.addAttribute("employee", employee);
		model.addAttribute("weixinUser", weixinUser);
		
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
		
		return "/weixin/workbench/sign/index";
	}
	
	
	/**
	 * 签到历史页
	 * @param model
	 * @return
	 */
	@RequestMapping("/historyList")
	public String historyList(Model model,String month){
		wxSignRecordService.getEmployeeSignList(model, month);
		return "/weixin/workbench/sign/historyList";
		
	}
	
	
	/**
	 * 签到历史页
	 * @param model
	 * @return
	 */
	@RequestMapping("/salesIndex")
	public String salesIndex(Model model){
		Employee employee = CurrentUserUtils.getEmployee(false);
		WeiXinUser weixinUser = CurrentUserUtils.getWeiXinUser(false);
		wxSignRecordService.getSign(model,employee.getId());
		model.addAttribute("employee", employee);
		model.addAttribute("weixinUser", weixinUser);
		
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
		return "/weixin/workbench/sign/salesIndex";
		
	}
	
	/**
	 * 签到历史页
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public ObjectNode list(String month ,HttpServletRequest request){
		return wxSignRecordService.getEmployeeSignJsonList(month, request);
	}
	
	
	
	//上班签到
	@RequestMapping("signStartWork")
	@ResponseBody
    public ObjectNode signStartWork(String address,String serverid,HttpServletRequest request) throws WxErrorException{			
		ObjectNode objectNode = null;
		try {
			objectNode = wxSignRecordService.signStartWork(address,serverid,request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//写进数据库
		return objectNode;
		
	}
	
	
	//下班签到
	@RequestMapping("signOffWork")
	@ResponseBody
    public ObjectNode signOffWork(String address){
		ObjectNode objectNode = wxSignRecordService.signOffWork(address);
		return objectNode;
		
	}
	
}
