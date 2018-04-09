package cn.cnyirui.framework.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.persistence.OneToMany;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.ServletRequestUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.cnyirui.framework.model.po.base.BaseEntity;
import cn.cnyirui.framework.model.vo.EntityConfig;

/**
 * 实体类相关
 * 
 * @author pengzhihua
 *
 */
public class EntityUtils {
	/**
	 * jpa管控的实体类列表
	 */
	private static List<String> jpaManagedEntityClassNames;
	private static ConversionService conversionService;

	private static ConversionService getConversionService() {
		if (conversionService == null) {
			conversionService = SpringUtils.getBean("conversionService");
		}
		return conversionService;
	}

	private static BeanWrapper getBeanWrapper(Object object) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(object);
		beanWrapper.setAutoGrowNestedPaths(true);
		beanWrapper.setConversionService(getConversionService());
		return beanWrapper;
	}

	/**
	 * jpa管控的实体类列表
	 * 
	 * @return
	 */
	public static List<String> getJpaManagedEntityClassNames() {
		if (jpaManagedEntityClassNames == null) {
			try {
				Object o1 = SpringUtils.getBean("entityManagerFactory");
				Field h = o1.getClass().getSuperclass().getDeclaredField("h");
				h.setAccessible(true);
				Object o2 = h.get(o1);
				h = o2.getClass().getDeclaredField("entityManagerFactoryBean");
				h.setAccessible(true);
				LocalContainerEntityManagerFactoryBean entityManagerFactory = (LocalContainerEntityManagerFactoryBean) h
				        .get(o2);

				jpaManagedEntityClassNames = new ArrayList<String>();
				jpaManagedEntityClassNames.addAll(entityManagerFactory.getPersistenceUnitInfo().getManagedClassNames());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jpaManagedEntityClassNames;
	}

	/**
	 * 简单类名转完整类名
	 * 
	 * @param simpleClassName
	 * @return
	 */
	public static String getJpaManagedEntityFullClassName(final String simpleClassName) {
		return CollectionUtils.find(getJpaManagedEntityClassNames(), new Predicate<String>() {
			@Override
			public boolean evaluate(String object) {
				return StringUtils.endsWithIgnoreCase(object, simpleClassName);
			}
		});
	}

	/**
	 * 实体类转换成ObjectNode
	 * 
	 * @param o
	 * @param config
	 * @return
	 */
	public static ObjectNode toObjectNode(Object o, EntityConfig config) {
		ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
		BeanWrapper beanWrapper = getBeanWrapper(o);
		List<String> propertyNames = config.getIncludePropertyNames();
		if (!propertyNames.isEmpty()) {
			for (String propertyName : propertyNames) {
				Object propertyValue = getPropertyValue(beanWrapper, propertyName);
				objectNode.putPOJO(config.getConvertedPropertyName(propertyName),
				        propertyValue == null ? "" : propertyValue);
			}
		} else {
			PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String propertyName = propertyDescriptor.getName();
				Class<?> propertyType = propertyDescriptor.getPropertyType();
				if (!beanWrapper.isReadableProperty(propertyName) || config.excludePropertyName(propertyName)
				        || config.excludeClass(propertyType)) {
					continue;
				}
				Object propertyValue = getPropertyValue(beanWrapper, propertyName);
				objectNode.putPOJO(config.getConvertedPropertyName(propertyName),
				        propertyValue == null ? "" : propertyValue);
			}

		}
		return objectNode;
	}

	private static Object getPropertyValue(BeanWrapper beanWrapper, String propertyName) {
		Object propertyValue = beanWrapper.getPropertyValue(propertyName);
		if (propertyValue != null && Date.class.isAssignableFrom(propertyValue.getClass())) {
			// 日期格式化
			Class<?> clazz = null;
			if (propertyName.indexOf(".") != -1) {
				propertyName = StringUtils.substringBeforeLast(propertyName, ".");
				clazz = beanWrapper.getPropertyValue(propertyName).getClass();
			} else {
				clazz = beanWrapper.getWrappedClass();
			}
			Field field = ReflectionUtils.findField(clazz, propertyName);
			if (field != null) {
				TypeDescriptor descriptor = new TypeDescriptor(field);
				if (descriptor.getAnnotation(DateTimeFormat.class) == null) {
					return DateFormatUtils.format((Date) propertyValue, Constants.DATE_TIME_FORMAT);
				} else {
					return conversionService.convert(propertyValue, descriptor, TypeDescriptor.valueOf(String.class));
				}
			}
		}
		return propertyValue;
	}

	/**
	 * 根据request的参数名，将source的属性copy至target
	 * 
	 * @param source
	 * @param target
	 * @param config
	 * @param request
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void copyEntity(Object source, Object target, EntityConfig config,
	        HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		if (!parameterNames.hasMoreElements()) {
			return;
		}
		BeanWrapper sourceBeanWrapper = getBeanWrapper(source);
		BeanWrapper targetBeanWrapper = getBeanWrapper(target);

		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			if (config.excludePropertyName(parameterName)) {
				continue;
			}
			Class<?> propertyType = sourceBeanWrapper.getPropertyType(parameterName);
			if (propertyType == null || config.excludeClass(propertyType)) {
				continue;
			}
			boolean canCopy = true;
			// 嵌套的属性处理
			if (StringUtils.indexOf(parameterName, ".") != -1) {
				String[] propertyNames = StringUtils.split(parameterName, ".");
				StringBuilder sourcePropertyNameSb = new StringBuilder();
				for (String propertyName : propertyNames) {
					sourcePropertyNameSb.append(sourcePropertyNameSb.length() == 0 ? "" : ".").append(propertyName);
					String sourcePropertyName = sourcePropertyNameSb.toString();
					String targetPropertyName = sourcePropertyName;
					if (config.excludePropertyName(sourcePropertyName)) {
						continue;
					}

					propertyType = sourceBeanWrapper.getPropertyType(sourcePropertyName);
					if (propertyType == null || !BaseEntity.class.isAssignableFrom(propertyType)) {
						continue;
					}

					// 集合属性
					if (StringUtils.indexOf(propertyName, "[") != -1) {
						String listPropertyName = StringUtils.substringBeforeLast(sourcePropertyName, "[");
						Object sourceObject = sourceBeanWrapper.getPropertyValue(listPropertyName);
						if (sourceObject != null && List.class.isAssignableFrom(sourceObject.getClass())) {
							if (config.excludePropertyName(listPropertyName)) {
								continue;
							}
							// 一对多关联
							Field field = ReflectionUtils.findField(source.getClass(), listPropertyName);
							boolean canAdd = field.getAnnotation(OneToMany.class) != null;

							List sourceList = (List) sourceObject;
							List targetList = (List) targetBeanWrapper.getPropertyValue(listPropertyName);

							List newTargetList = new ArrayList();
							for (int i = 0; i < sourceList.size(); i++) {
								if (sourceList.get(i) != null) {
									String id = ((BaseEntity) sourceList.get(i)).getId();
									if (StringUtils.isNotEmpty(id)) {
										newTargetList.add(conversionService.convert(id, sourceList.get(i).getClass()));
										config.addConvertedPropertyName(listPropertyName + "[" + i + "]",
										        listPropertyName + "[" + (newTargetList.size() - 1) + "]");
									} else if (canAdd) {
										newTargetList.add(sourceList.get(i));
										newTargetList.add(conversionService.convert(id, sourceList.get(i).getClass()));
										config.addConvertedPropertyName(listPropertyName + "[" + i + "]",
										        listPropertyName + "[" + (newTargetList.size() - 1) + "]");
									}

								}
							}
							targetList.clear();
							targetList.addAll(newTargetList);
						}
						config.addExcludePropertyName(listPropertyName);
					} else {
						// BaseEntity的子类
						BaseEntity sourceBaseEntity = (BaseEntity) sourceBeanWrapper
						        .getPropertyValue(sourcePropertyName);
						if (sourceBaseEntity != null && StringUtils.isNotEmpty(sourceBaseEntity.getId())) {
							targetBeanWrapper.setPropertyValue(targetPropertyName, sourceBaseEntity.getId());
						} else {
							canCopy = false;
						}
						config.addExcludePropertyName(sourcePropertyName);
					}
				}
			}
			String targetParameterName = parameterName;
			// 集合属性
			if (StringUtils.indexOf(targetParameterName, "[") != -1) {
				Map<String, String> propertyNameMap = config.getPropertyNameMap();
				targetParameterName = StringUtils.replaceEach(targetParameterName,
				        propertyNameMap.keySet().toArray(new String[propertyNameMap.size()]),
				        propertyNameMap.values().toArray(new String[propertyNameMap.size()]));
			}
			if (canCopy) {
				targetBeanWrapper.setPropertyValue(targetParameterName,
				        sourceBeanWrapper.getPropertyValue(parameterName));
			}
			// 排除已经copy的属性
			config.addExcludePropertyName(parameterName);
		}
	}

	public static EntityConfig defaultToObjectNodeEntityConfig(HttpServletRequest request) {
		EntityConfig config = new EntityConfig();
		String dataFields = null;
		if (request != null) {
			dataFields = ServletRequestUtils.getStringParameter(request, "_dataFields", null);
			if (!StringUtils.isEmpty(dataFields)) {
				config.getIncludePropertyNames().addAll(Arrays.asList(StringUtils.split(dataFields, ",")));
			}
		}
		if (request == null || StringUtils.isEmpty(dataFields)) {
			config.addExcludeClass(Collection.class);
			config.addExcludeClass(BaseEntity.class);

			config.addExcludePropertyName("class");
		}

		return config;
	}

	public static EntityConfig defaultCopyEntityConfig() {
		EntityConfig config = new EntityConfig();

		config.addExcludeClass(Collection.class);
		config.addExcludePropertyName("class");
		config.addExcludePropertyName("createdBy");
		config.addExcludePropertyName("createdTime");
		config.addExcludePropertyName("lastModifiedBy");
		config.addExcludePropertyName("lastModifiedTime");
		return config;
	}

}
