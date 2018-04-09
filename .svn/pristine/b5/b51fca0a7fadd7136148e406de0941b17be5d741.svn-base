package cn.cnyirui.homaweixin.service.backend;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.vo.EntityConfig;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.CustomerDao;
import cn.cnyirui.homaweixin.model.po.Customer;

@Service
public class CustomerService extends BaseService<Customer>{

	@Resource
	private CustomerDao customerDao;
	
	@Override
	public BaseDao<Customer> getBaseDao() {
		return customerDao;
	}

	
	public Customer getByWinxinId(String wxid){
		return customerDao.getByWinxinId(wxid);
	}

}
