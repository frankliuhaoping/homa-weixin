package cn.cnyirui.homaweixin.controller.weixin.workbench.personal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.controller.BaseController;
import cn.cnyirui.framework.model.eo.SysRoleType;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.CurrentUserUtils;
import cn.cnyirui.homaweixin.model.po.Employee;

@Controller
@RequestMapping("/weixin/workbench")
public class PersonalController extends BaseController<Employee> {

	@Override
	protected BaseService<Employee> getBaseService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping("/personal")
	public ModelAndView backendIndex(Model model) {
		Employee employee = CurrentUserUtils.getEmployee(false);
		model.addAttribute("employeeId",employee.getId());
		model.addAttribute("headimg",employee.getHeadImgUrl());
		model.addAttribute("name", employee.getName());
		String strSysRo = employee.getSysRoleType();
		if(strSysRo!=null){
			model.addAttribute("sysRoleType", SysRoleType.valueOf(strSysRo).getText());
		}else {
			model.addAttribute("sysRoleType", "暂无类型");
		}
		
		
		return new ModelAndView("/weixin/workbench/personal/personal");
	}
	
	//修改密码
	
	@RequestMapping("/changePasswordIndex")
	public ModelAndView changePasswordIndex(Model model){
		Employee employee = CurrentUserUtils.getEmployee(false);
		model.addAttribute("employeeId",employee.getId());
		model.addAttribute("headimg",employee.getHeadImgUrl());
		model.addAttribute("name", employee.getName());
		String strSysRo = employee.getSysRoleType();
		if(strSysRo!=null){
			model.addAttribute("sysRoleType", SysRoleType.valueOf(strSysRo).getText());
		}else {
			model.addAttribute("sysRoleType", "暂无类型");
		}
		return new ModelAndView("/weixin/workbench/personal/personalPwd");
	}
	
}