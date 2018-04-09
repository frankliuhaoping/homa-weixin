package cn.cnyirui.framework.controller.weixin;

import org.apache.commons.lang3.StringUtils;

import cn.cnyirui.framework.controller.BaseCRUDController;
import cn.cnyirui.framework.model.po.base.BaseEntity;

/**
 * 微信端与PC端有相同功能需求，需要共用controller可以继承此controller
 * 
 * @author pengzhihua
 *
 * @param <T>
 */
public abstract class BasePcAndWeiXinCRUDController<T extends BaseEntity> extends BaseCRUDController<T> {

	/**
	 * 默认视图名称prefix
	 * 
	 * <pre>
	 * 例：
	 * requestMapping Value = "pc/backend/sysRole"
	 * currentViewPrefix = "/backend/sysRole/sysRole_pc_"
	 * 
	 * requestMapping Value = "weixin/backend/sysRole"
	 * currentViewPrefix = "/backend/sysRole/sysRole_weixin_"
	 * </pre>
	 */
	@Override
	protected String defaultViewPrefix(boolean addSuffixName) {
		String result = super.defaultViewPrefix(addSuffixName);
		String pcOrWeiXin = StringUtils.substringBefore(result, "/");
		result = StringUtils.remove(result, pcOrWeiXin);
		result = result + StringUtils.removeStart(pcOrWeiXin, "/") + "_";
		return result;
	}
}
