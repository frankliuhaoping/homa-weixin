package cn.cnyirui.framework.service.rbac;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.rbac.SysLogDao;
import cn.cnyirui.framework.model.po.rbac.SysLogEntity;
import cn.cnyirui.framework.service.BaseService;

/**
 * 操作日志
 * 
 * @author pengzhihua
 *
 */
@Service
public class SysLogService extends BaseService<SysLogEntity> {

	@Resource
	private SysLogDao sysLogDao;

	@Override
	public BaseDao<SysLogEntity> getBaseDao() {
		return sysLogDao;
	}

	public void saveSysLog(String method, String params, String description, String ip) {
		SysLogEntity sysLog = new SysLogEntity();
		sysLog.setMethod(method);
		sysLog.setParams(params);
		sysLog.setIp(ip);
		sysLog.setDescription(description);
		save(sysLog);
	}
}
