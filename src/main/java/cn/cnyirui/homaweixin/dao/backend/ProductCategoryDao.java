package cn.cnyirui.homaweixin.dao.backend;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.ProductCategory;

/**
 * 产品表—分类
 * 
 * @author zhuming
 *
 */
public interface ProductCategoryDao extends BaseDao<ProductCategory> {

	/**
	 * 上级目录
	 * 
	 * @return
	 */
	@Query("select p from ProductCategory as p where p.parent.id is null and p.deleted = 0")
	List<ProductCategory> findRootProductCategory();

	/**
	 * 下级目录
	 * 
	 * @return
	 */
	@Query("select p from ProductCategory as p where p.parent.id = ?1 and p.deleted = 0")
	List<ProductCategory> findSubProductCategory(String parentId);

	/**
	 * 按内销系统的ID查找
	 * 
	 * @param imsId
	 * @return
	 */
	@Query("select p from ProductCategory as p where p.IMSId = ?1")
	ProductCategory findByIMSId(Long imsId);

	/**
	 * 按分类名查找
	 * 
	 * @param imsId
	 * @return
	 */
	@Query("select p from ProductCategory as p where p.name = ?1")
	ProductCategory findByName(String name);

	@Query("select p from ProductCategory as p where p.parent.id = ?1 ")
	Page<ProductCategory> findSubProductCategoryPage(String parentId, Pageable pageable);

	@Query("select  p from ProductCategory as p where p.parent.id = ?1 ")
	List<ProductCategory> findShowNode();

}
