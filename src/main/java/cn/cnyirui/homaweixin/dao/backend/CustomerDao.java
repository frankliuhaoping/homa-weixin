package cn.cnyirui.homaweixin.dao.backend;

import org.springframework.data.jpa.repository.Query;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.homaweixin.model.po.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	@Query("from Customer c where c.weiXinUser.id = ?1")
	Customer getByWinxinId(String wxid);

}
