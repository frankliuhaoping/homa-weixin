package cn.cnyirui.framework.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class SessionUtils {
	/**
	 * 获取session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 将value保存到session中，如果value=null，将从session中删除key
	 * 
	 * @param key
	 * @param value
	 */
	public static void setAttribute(String key, Object value) {
		if (value == null) {
			getSession().removeAttribute(key);
		} else {
			getSession().setAttribute(key, value);
		}
	}

	/**
	 * 取session的值
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(String key) {
		return (T) getSession().getAttribute(key);
	}
}
