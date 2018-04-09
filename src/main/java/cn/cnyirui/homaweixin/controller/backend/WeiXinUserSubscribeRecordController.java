package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.po.weixin.WeiXinUserSubscribeRecord;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.weixin.WeiXinUserSubscribeRecordService;
import cn.cnyirui.homaweixin.model.po.Customer;
import cn.cnyirui.homaweixin.service.backend.CustomerService;

@Controller
@RequestMapping("/backend/weiXinUserSubscribeRecord")
public class WeiXinUserSubscribeRecordController extends BaseCRUDController<WeiXinUserSubscribeRecord> {
	
	@Resource
	private WeiXinUserSubscribeRecordService weiXinUserSubscribeRecordSerivce;
	
	@Resource
	private CustomerService customerSerivce;
	
	@Override
	protected BaseService<WeiXinUserSubscribeRecord> getBaseService() {
		return weiXinUserSubscribeRecordSerivce;
	}
	
	@Override
	public ModelAndView doList(HttpServletRequest request) {
		ModelAndView mav = super.doList(request);
		String customerId = ServletRequestUtils.getStringParameter(request, "customerId", null);
		Customer c = customerSerivce.findOne(customerId);
		mav.addObject("weiXinUser", c.getWeiXinUser());

		return mav;
	}
}
