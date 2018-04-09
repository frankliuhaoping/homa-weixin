package cn.cnyirui.homaweixin.service.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.JsonUtil;
import cn.cnyirui.homaweixin.dao.backend.ProductCategoryDao;
import cn.cnyirui.homaweixin.dao.backend.ProductDao;
import cn.cnyirui.homaweixin.model.po.Product;
import cn.cnyirui.homaweixin.model.po.ProductCategory;

@Service
public class ProductCategoryService extends BaseService<ProductCategory> {

	@Resource
	private ProductCategoryDao productCategoryDao;

	@Resource
	private ProductDao productDao;

	@Override
	public BaseDao<ProductCategory> getBaseDao() {
		return productCategoryDao;
	}

	/**
	 * 所有产品分类
	 * 
	 * @param onlyHasProduct
	 *            true=只查询分类下有产品的
	 * @param searchable
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Page<ProductCategory> findAllProductCategory(boolean onlyHasProduct, Searchable searchable) {
		StringBuilder s = new StringBuilder();
		s.append("select p.* from product_category p where 1 = 1");
		if (onlyHasProduct) {
			s.append(" and exists(select r.id from product r where r.categoryId = p.id and r.deleted = 0)");
		}
		s.append(" start with parentId is null connect by prior id = parentId");
		s.append(" order siblings by name");
		return (Page<ProductCategory>) productCategoryDao.findAll(s.toString(), searchable, true,
		        ProductCategory.class);

	}

	@Override
	public ObjectNode entityToObjectNode(ProductCategory entity, EntityConfig config) {
		ObjectNode objectNode = super.entityToObjectNode(entity, config);
		ProductCategory parent = entity.getParent();
		if (parent != null) {
			objectNode.put("_parentId", parent.getId());
		}
		if (entity.getSubCategory() != null) {
			objectNode.put("_hasChildren", !entity.getSubCategory().isEmpty());
		}
		objectNode.put("isIMSData", entity.getIsIMSData());
		return objectNode;
	}

	public List<ObjectNode> getProductCateTreeJson(String parentId) {
		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		List<ProductCategory> productCategoryList = null;
		if (parentId == null) {
			productCategoryList = productCategoryDao.findRootProductCategory();
		} else {
			productCategoryList = productCategoryDao.findSubProductCategory(parentId);
		}
		for (ProductCategory productCategory : productCategoryList) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNodeList.add(objectNode);
			objectNode.put("id", productCategory.getId());
			objectNode.put("text", productCategory.getName());
			objectNode.put("remark", productCategory.getRemark());
			objectNode.putArray("children").addAll(getProductCateTreeJson(productCategory.getId()));
		}
		return objectNodeList;
	}

	public List<ProductCategory> find() {
		return productCategoryDao.findAll();
	}

	public List<ObjectNode> getProductByParentId(String parentId, Integer currentPage) {
		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		List<Product> productList = null;
		Integer cp = currentPage == null ? 0 : currentPage;
		Pageable pageable = new PageRequest(cp, 10);
		Page<Product> page = null;
		page = productDao.getProductByParentId(parentId, pageable);
		productList = page.getContent();

		for (Product product : productList) {
			ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
			objectNodeList.add(objectNode);
			objectNode.put("id", product.getId());
			objectNode.put("text", product.getName());
			if (parentId == null) {
				objectNode.put("more", 0);
			} else {
				objectNode.put("more", page.hasNext() ? 1 : 0);
			}
		}
		return objectNodeList;
	}

	public List<ObjectNode> getProductCateFirstNode(String parentId, Integer currentPage) {
		List<ObjectNode> objectNodeList = new ArrayList<ObjectNode>();
		List<Product> list = productDao.findShowNode();
		List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
		for (int i = 0; i < list.size(); i++) {
			productCategories.add(productCategoryDao.getOne(list.get(i).getCategory().getId()));
		}
		// page =
		// productCategoryDao.findSubProductCategoryPage(parentId,pageable);

		return objectNodeList;

	}
}
