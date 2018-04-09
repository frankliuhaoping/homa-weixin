package cn.cnyirui.homaweixin.service.backend;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.homaweixin.dao.backend.IMSLogDao;
import cn.cnyirui.homaweixin.model.po.IMSLog;

@Service
@Lazy(true)
public class IMSLogService extends BaseService<IMSLog> {

	@Resource
	private IMSLogDao imsLogDao;

	@Override
	public BaseDao<IMSLog> getBaseDao() {
		return imsLogDao;
	}
}
