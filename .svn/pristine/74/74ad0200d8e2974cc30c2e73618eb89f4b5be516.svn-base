package cn.cnyirui.framework.service.weixin;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.dao.search.SearchOperator;
import cn.cnyirui.framework.dao.search.Searchable;
import cn.cnyirui.framework.dao.weixin.WeiXinUserDao;
import cn.cnyirui.framework.model.po.weixin.WeiXinUser;
import cn.cnyirui.framework.service.BaseService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
public class WeiXinUserService extends BaseService<WeiXinUser> {

	@Resource
	private WeiXinUserDao weiXinUserDao;
	@Resource
	private WxMpService wxMpService;

	@Override
	public BaseDao<WeiXinUser> getBaseDao() {
		return weiXinUserDao;
	}

	/**
	 * 按微信openId查找，如果找不到则从微信获取用户信息
	 * 
	 * @param wxOpenId
	 * @param reGetUserInfo true重新从微信获取用户信息
	 * @return
	 */
	public WeiXinUser findOrCreateByWxOpenId(String wxOpenId, boolean reGetUserInfo) {
		Assert.hasText(wxOpenId, "OpenId不能为空！");
		Searchable searchable = Searchable.newSearchable().addSearchFilter("openId", SearchOperator.eq, wxOpenId);
		WeiXinUser weiXinUser = weiXinUserDao.findOne(searchable);
		if (weiXinUser == null || reGetUserInfo) {
			try {
				if (weiXinUser == null) {
					weiXinUser = new WeiXinUser();
				}
				WxMpUser wxMpUser = wxMpService.userInfo(wxOpenId, "zh_CN");//获取微信用户信息
				weiXinUser.setCity(wxMpUser.getCity());
				weiXinUser.setCountry(wxMpUser.getCountry());
				weiXinUser.setGroupId(wxMpUser.getGroupId());
				weiXinUser.setHeadImgUrl(wxMpUser.getHeadImgUrl());
				weiXinUser.setNickname(wxMpUser.getNickname());
				weiXinUser.setOpenId(wxMpUser.getOpenId());
				weiXinUser.setProvince(wxMpUser.getProvince());
				weiXinUser.setRemark(wxMpUser.getRemark());
				weiXinUser.setSex(wxMpUser.getSexId());
				if (wxMpUser.getSubscribeTime() != null) {
					//关注时间不为空 设置关注事件
					weiXinUser.setSubscribeTime(new Date(wxMpUser.getSubscribeTime() * 1000));
				}
				weiXinUser.setUnionId(wxMpUser.getUnionId());//绑定后才出现UnionId
				weiXinUserDao.save(weiXinUser);//保存到数据库
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return weiXinUser;
	}

}
