package cn.cnyirui.framework.controller.weixin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.eo.WeiXinMenuType;
import cn.cnyirui.framework.model.po.weixin.WeiXinMenu;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.service.weixin.WeiXinMenuService;

@Controller
@RequestMapping("/backend/weiXinMenu")
public class WeiXinMenuController extends BaseCRUDController<WeiXinMenu> {

	@Resource
	private WeiXinMenuService weiXinMenuService;

	@Override
	protected BaseService<WeiXinMenu> getBaseService() {
		return weiXinMenuService;
	}

	@Override
	protected void setCommonModel(ModelAndView m, String viewName) {
		m.addObject("weiXinMenuTypes", WeiXinMenuType.values());
		super.setCommonModel(m, viewName);
	}
}
