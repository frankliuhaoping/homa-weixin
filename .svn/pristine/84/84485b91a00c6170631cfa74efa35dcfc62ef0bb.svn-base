package cn.cnyirui.homaweixin.controller.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.model.po.Product;
import cn.cnyirui.homaweixin.model.po.ProductCategory;
import cn.cnyirui.homaweixin.service.backend.ProductCategoryService;
import cn.cnyirui.homaweixin.service.backend.ProductService;

@Controller
@RequestMapping("/backend/product")
public class ProductController extends BaseCRUDController<Product> {

	@Resource
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Override
	protected BaseService<Product> getBaseService() {
		return productService;
	}

	@RequestMapping("/findProductCategory")
	@ResponseBody
	public List<ObjectNode> findCategory() {
		Searchable searchable = Searchable.newSearchable();
		searchable.addSearchFilter("deleted", SearchOperator.eq, 0);
		List<ProductCategory> productList = productCategoryService.findAll(searchable).getContent();
		List<ObjectNode> objectNodes = new ArrayList<ObjectNode>();
		for (ProductCategory pc : productList) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNode.put("id", pc.getId());
			objectNode.put("name", pc.getName());
			objectNodes.add(objectNode);
		}
		return objectNodes;
	}

	@Override
	protected void handleSearchFilter(Searchable searchable) {
		searchable.addSearchFilter("deleted", SearchOperator.eq, "0");
		super.handleSearchFilter(searchable);
	}
}
