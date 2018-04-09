package cn.cnyirui.homaweixin.controller.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.News;
import cn.cnyirui.homaweixin.service.backend.CompanyNewsService;

@Controller
@RequestMapping("/backend/CompanyNews")
public class CompanyNewsController extends BaseCRUDController<News> {

	@Resource
	private CompanyNewsService companyNewsService;

	@Override
	protected BaseService<News> getBaseService() {
		// TODO Auto-generated method stub
		return companyNewsService;
	}
}
