package cn.cnyirui.framework.dao.weixin;

import java.util.List;

import org.springframework.data.domain.Pageable;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;

public interface WeiXinUserDao extends BaseDao<WeiXinUser> {
	/**
	 * 按SysUser的id查找
	 * 
	 * @param sysUserId
	 * @return
	 */
	List<WeiXinUser> findBySysUserId(String sysUserId, Pageable pageable);
}
