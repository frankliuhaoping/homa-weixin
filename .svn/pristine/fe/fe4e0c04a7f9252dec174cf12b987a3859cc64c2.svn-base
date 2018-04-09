package cn.cnyirui.framework.model.po.weixin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.BaseEntity;


/**
 * 微信用户关注、取消关注流水记录
 * 
 */
@Entity
@Table(name = "wenxin_user_subscribe_record")
public class WeiXinUserSubscribeRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * true为关注，false为取消关注
     */
    @Column
    private Boolean isSubscribe = Boolean.FALSE;


    /**
     * 微信用户
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weiXinUserId")
    private WeiXinUser weiXinUser;


    /**
     * true为关注，false为取消关注
     * 
     * @return isSubscribe true为关注，false为取消关注
     */
    public Boolean getIsSubscribe() {
        return isSubscribe;
    }


    /**
     * true为关注，false为取消关注
     * 
     * @param isSubscribe true为关注，false为取消关注
     */
    public void setIsSubscribe(Boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }


    /**
     * 微信用户
     * 
     * @return weiXinUser 微信用户
     */
    public WeiXinUser getWeiXinUser() {
        return weiXinUser;
    }


    /**
     * 微信用户
     * 
     * @param weiXinUser 微信用户
     */
    public void setWeiXinUser(WeiXinUser weiXinUser) {
        this.weiXinUser = weiXinUser;
    }



}
