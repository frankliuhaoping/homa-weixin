package cn.cnyirui.framework.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import cn.cnyirui.framework.dao.BaseDao;
import cn.cnyirui.framework.service.BaseService;

public final class SpringUtils implements BeanFactoryPostProcessor {
	private static final Logger log = LoggerFactory.getLogger(SpringUtils.class);

	private static ConfigurableListableBeanFactory beanFactory; // Spring应用上下文环境

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		SpringUtils.beanFactory = beanFactory;
	}

	/**
	 * 获取对象
	 *
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws org.springframework.beans.BeansException
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) beanFactory.getBean(name);
	}

	/**
	 * 实体类名获取对应的DAO
	 * 
	 * @param entityClassName
	 * @return
	 */
	public static <T extends BaseDao<?>> T getDaoBean(String entityClassName) {
		entityClassName = StringUtils.uncapitalize(entityClassName);
		try {
			return getBean(entityClassName + "Dao");
		} catch (NoSuchBeanDefinitionException e) {
			log.warn("未找到Bean：" + entityClassName + "Dao");
			return null;
		}

	}

	/**
	 * 实体类名获取胜对应的Service
	 * 
	 * @param entityClassName
	 * @return
	 */
	public static <T extends BaseService<?>> T getServiceBean(String entityClassName) {
		entityClassName = StringUtils.uncapitalize(entityClassName);
		try {
			return getBean(entityClassName + "Service");
		} catch (NoSuchBeanDefinitionException e) {
			log.warn("未找到Bean：" + entityClassName + "Service");
			return null;
		}

	}

	/**
	 * 获取类型为requiredType的对象
	 *
	 * @param clz
	 * @return
	 * @throws org.springframework.beans.BeansException
	 *
	 */
	public static <T> T getBean(Class<T> clz) throws BeansException {
		T result = beanFactory.getBean(clz);
		return result;
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 *
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return beanFactory.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 *
	 * @param name
	 * @return boolean
	 * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
	 *
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
	 *
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 *
	 * @param name
	 * @return
	 * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
	 *
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.getAliases(name);

	}

	@SuppressWarnings("unchecked")
	public static <T> T createBean(Class<T> beanClass, int autowireMode, boolean dependencyCheck)
	        throws BeansException {
		return (T) beanFactory.createBean(beanClass, autowireMode, dependencyCheck);
	}

	public static <T> T createBean(Class<T> beanClass) throws BeansException {
		return beanFactory.createBean(beanClass);
	}

}
