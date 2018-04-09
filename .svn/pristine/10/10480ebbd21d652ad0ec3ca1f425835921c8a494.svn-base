package cn.cnyirui.framework.model.po.weixin;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import cn.cnyirui.framework.model.po.base.SequenceableEntity;

@Entity
@Table(name = "weixin_menu")
@DynamicInsert
@DynamicUpdate
public class WeiXinMenu extends SequenceableEntity {
	private static final long serialVersionUID = 4213191204390882283L;

	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 */
	@Column(length = 100)
	private String name;

	/**
	 * 菜单的响应动作类型
	 */
	@Column
	private Integer menuType;

	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节( click等点击类型必须 )
	 */
	@Column(name = "`key`", length = 500)
	private String key;

	/**
	 * 网页链接，用户点击菜单可打开链接，不超过256字节( view类型必须 )
	 */
	@Column(length = 500)
	private String url;

	/**
	 * media_id类型和view_limited类型必须(调用新增永久素材接口返回的合法media_id)
	 */
	@Column(length = 100)
	private String mediaId;

	/**
	 * 是否屏蔽 true=屏蔽
	 */
	private Boolean isDisabled;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 所属菜单id(父id)
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private WeiXinMenu parent;

	/**
	 * 子菜单
	 */
	@OneToMany(mappedBy = "parent")
	@OrderBy("seq")
	private List<WeiXinMenu> children = new ArrayList<WeiXinMenu>(0);

	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * 
	 * @return name 菜单标题，不超过16个字节，子菜单不超过40个字节
	 */
	public String getName() {
		return name;
	}

	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 * 
	 * @param name 菜单标题，不超过16个字节，子菜单不超过40个字节
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 菜单的响应动作类型
	 * 
	 * @return menuType 菜单的响应动作类型
	 */
	public Integer getMenuType() {
		return menuType;
	}

	/**
	 * 菜单的响应动作类型
	 * 
	 * @param menuType 菜单的响应动作类型
	 */
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节(click等点击类型必须)
	 * 
	 * @return key 菜单KEY值，用于消息接口推送，不超过128字节(click等点击类型必须)
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节(click等点击类型必须)
	 * 
	 * @param key 菜单KEY值，用于消息接口推送，不超过128字节(click等点击类型必须)
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 网页链接，用户点击菜单可打开链接，不超过256字节(view类型必须)
	 * 
	 * @return url 网页链接，用户点击菜单可打开链接，不超过256字节(view类型必须)
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 网页链接，用户点击菜单可打开链接，不超过256字节(view类型必须)
	 * 
	 * @param url 网页链接，用户点击菜单可打开链接，不超过256字节(view类型必须)
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * media_id类型和view_limited类型必须(调用新增永久素材接口返回的合法media_id)
	 * 
	 * @return mediaId media_id类型和view_limited类型必须(调用新增永久素材接口返回的合法media_id)
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * media_id类型和view_limited类型必须(调用新增永久素材接口返回的合法media_id)
	 * 
	 * @param mediaId media_id类型和view_limited类型必须(调用新增永久素材接口返回的合法media_id)
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * 是否屏蔽true=屏蔽
	 * 
	 * @return isDisabled 是否屏蔽true=屏蔽
	 */
	public Boolean getIsDisabled() {
		return isDisabled;
	}

	/**
	 * 是否屏蔽true=屏蔽
	 * 
	 * @param isDisabled 是否屏蔽true=屏蔽
	 */
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	/**
	 * 备注
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * 
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 所属菜单id(父id)
	 * 
	 * @return parent 所属菜单id(父id)
	 */
	public WeiXinMenu getParent() {
		return parent;
	}

	/**
	 * 所属菜单id(父id)
	 * 
	 * @param parent 所属菜单id(父id)
	 */
	public void setParent(WeiXinMenu parent) {
		this.parent = parent;
	}

	/**
	 * 子菜单
	 * 
	 * @return children 子菜单
	 */
	public List<WeiXinMenu> getChildren() {
		return children;
	}

	/**
	 * 子菜单
	 * 
	 * @param children 子菜单
	 */
	public void setChildren(List<WeiXinMenu> children) {
		this.children = children;
	}

}
