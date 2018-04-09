package cn.cnyirui.homaweixin.dao.backend;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.Product;

/**
 * 业务表产品
 * 
 * @author zhuming
 *
 */
public interface ProductDao extends BaseDao<Product> {
	/**
	 * 按内销系统的ID查找
	 * 
	 * @param imsId
	 * @return
	 */
	@Query("select p from Product as p where p.IMSId = ?1")
	Product findByIMSId(Long imsId);

	@Query("select p from Product as p where p.name = ?1")
	Product findByName(String name);

	@Query("select distinct p.category.id from Product as p")
	List<Product> findShowNode();

	@Query("select  p  from Product as p where p.category.id=?1 and p.deleted = 0 and p.name is not null")
	Page<Product> getProductByParentId(String parentId, Pageable pageable);

	@Query("select  p  from Product as p where p.name like ?1 and p.deleted = 0 and p.category.deleted=0")
	Page<Product> findProductByKeyWord(String keyword, Pageable pageable);
}
