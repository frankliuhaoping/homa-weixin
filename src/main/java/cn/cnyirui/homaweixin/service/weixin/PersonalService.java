package cn.cnyirui.homaweixin.service.weixin;

import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.model.po.Employee;

@Service
public class PersonalService extends BaseService<Employee> {

	@Override
	public BaseDao<Employee> getBaseDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
