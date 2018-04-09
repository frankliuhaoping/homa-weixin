package cn.cnyirui.framework.service.weixin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.weixin.WeiXinMenuDao;
import cn.cnyirui.framework.model.po.weixin.WeiXinMenu;
import cn.cnyirui.framework.service.BaseService;

/**
 * 微信菜单
 * 
 * @author pengzhihua
 *
 */
@Service
public class WeiXinMenuService extends BaseService<WeiXinMenu> {

	@Resource
	private WeiXinMenuDao weiXinMenuDao;

	@Override
	public BaseDao<WeiXinMenu> getBaseDao() {
		return weiXinMenuDao;
	}

}
