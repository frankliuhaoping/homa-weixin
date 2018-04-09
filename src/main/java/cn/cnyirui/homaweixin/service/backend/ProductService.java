package cn.cnyirui.homaweixin.service.backend;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.ProductDao;
import cn.cnyirui.homaweixin.model.po.Product;

@Service
public class ProductService extends BaseService<Product>{

	@Resource
	private ProductDao productDao;
	
	@Override
	public BaseDao<Product> getBaseDao() {
		return productDao;
	}
	
	public Page<Product> findProductByKeyWord(String keyword, Pageable pageable){
		keyword = keyword==null?"":keyword;
		keyword = "%"+keyword+"%";
		return  productDao.findProductByKeyWord(keyword,pageable);
	}

}
