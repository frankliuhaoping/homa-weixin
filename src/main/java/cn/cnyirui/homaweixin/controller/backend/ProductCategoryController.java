package cn.cnyirui.homaweixin.controller.backend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.vo.JsonResult;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.ProductCategory;
import cn.cnyirui.homaweixin.service.backend.ProductCategoryService;

@Controller
@RequestMapping("/backend/productCategory")
public class ProductCategoryController extends BaseCRUDController<ProductCategory> {

	@Resource
	private ProductCategoryService productCategorySerivce;

	@Override
	protected BaseService<ProductCategory> getBaseService() {
		return productCategorySerivce;
	}

	@RequestMapping("/treeJson")
	@ResponseBody
	public List<ObjectNode> treeJson() {
		return productCategorySerivce.getProductCateTreeJson(null);
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		searchable.addSearchFilter("deleted", SearchOperator.eq, "0");
		super.handleSearchFilter(searchable);
	}

	// 如果是IMS数据，则删除失败
	@Override
	@Transactional
	public JsonResult doDelete(String[] ids) {
		for (String id : ids) {
			ProductCategory p = productCategorySerivce.findOne(id);
			if (p.getIsIMSData() == true) {
				return JsonResult.error(String.format("产品：%s 为IMS数据，删除失败，请重试！", p.getName()));
			}
		}

		return super.doDelete(ids);
	}
}
