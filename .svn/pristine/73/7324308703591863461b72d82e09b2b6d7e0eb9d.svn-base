package cn.cnyirui.framework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.service.BaseService;
import cn.cnyirui.framework.utils.ReflectUtils;

/**
 * controller基类
 * 
 * @author pengzhihua
 *
 * @param <T>
 */
public abstract class BaseController<T extends BaseEntity> {

	/**
	 * view前缀
	 */
	private String viewPrefix;
	private String requestMappingValue;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected abstract BaseService<T> getBaseService();

	protected BaseController() {
		setViewPrefix(defaultViewPrefix(true));
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
		        .getRequest();
		return request;
	}

	/**
	 * 得到response对象
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
		        .getResponse();
		return response;
	}

	/**
	 * 提示信息页
	 * 
	 * @param alert 是否以alert方式显示错误信息
	 * @param message
	 * @return
	 */
	protected ModelAndView getMessageView(boolean alert, String message) {
		ModelAndView m = new ModelAndView("/error/message");
		m.addObject("alert", alert);
		m.addObject("message", message);
		return m;
	}

	/**
	 * 设置通用数据
	 * 
	 * @param m
	 * @param viewName index or list or view or add or edit
	 */
	protected void setCommonModel(ModelAndView m, String viewName) {
	}

	/**
	 * 当前模块 视图的前缀
	 * 默认
	 * 1、获取当前类头上的@RequestMapping中的value作为前缀
	 * 2、如果没有就使用当前模型小写的简单类名
	 */
	public void setViewPrefix(String viewPrefix) {
		this.viewPrefix = StringUtils.removeStart(viewPrefix, "/");
	}

	public String getViewPrefix() {
		return viewPrefix;
	}

	/**
	 * 获取视图名称：即prefixViewName + "/" + suffixName
	 *
	 * @return
	 */
	public String viewName(String suffixName) {
		suffixName = StringUtils.prependIfMissing(suffixName, "/");
		return getViewPrefix() + suffixName;
	}

	/**
	 * 默认视图名称prefix
	 * 
	 * <pre>
	 * 例：
	 * requestMapping Value = "/rbac/sysRole"
	 * currentViewPrefix = "/rbac/sysRole/sysRole_"
	 * </pre>
	 * 
	 * @return
	 */
	protected String defaultViewPrefix(boolean addSuffixName) {
		String viewPrefix = getRequestMappingValue();
		if (StringUtils.isEmpty(viewPrefix)) {
			viewPrefix = ReflectUtils.findParameterizedType(getClass(), 0).getSimpleName();
		} else {
			if (addSuffixName) {
				viewPrefix = viewPrefix
				        + StringUtils.substringAfterLast(StringUtils.removeEnd(viewPrefix, "/"), "/") + "_";
			}
		}
		return viewPrefix;
	}

	/**
	 * RequestMapping注解的值
	 * 
	 * @param clazz
	 * @return
	 */
	protected String getRequestMappingValue() {
		if (requestMappingValue == null) {
			RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
			if (requestMapping != null && requestMapping.value().length > 0) {
				requestMappingValue = StringUtils.appendIfMissing(requestMapping.value()[0], "/");
			}
		}
		return requestMappingValue;
	}
}
