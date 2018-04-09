package cn.cnyirui.homaweixin.controller.backend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.NewsCategory;
import cn.cnyirui.homaweixin.service.backend.NewCategoryService;

@Controller
@RequestMapping("/backend/newsCategory")
public class NewsCategoryController extends BaseCRUDController<NewsCategory> {

	@Resource
	private NewCategoryService newsCategoryService;

	@Override
	protected BaseService<NewsCategory> getBaseService() {
		return newsCategoryService;
	}

	@RequestMapping("/getCategory")
	@ResponseBody
	public List<ObjectNode> getCategory() {
		return newsCategoryService.getCategory();
	}

}
