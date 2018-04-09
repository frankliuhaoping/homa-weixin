package cn.cnyirui.framework.model.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class EntityConfig {
	/**
	 * 需要操作的属性
	 */
	private List<String> includePropertyNames = new ArrayList<String>();
	/**
	 * 不需要操作的属性
	 */
	private List<String> excludePropertyNames = new ArrayList<String>();
	/**
	 * 不需要操作的类型
	 */
	private List<Class<?>> excludeClasses = new ArrayList<Class<?>>();

	/**
	 * key:属性名，value:操作后的属性名
	 */
	private Map<String, String> propertyNameMap = new HashMap<String, String>();

	/**
	 * 需要操作的属性
	 * 
	 * @return includePropertyNames 需要操作的属性
	 */
	public List<String> getIncludePropertyNames() {
		return includePropertyNames;
	}

	/**
	 * 需要操作的属性
	 * 
	 * @param includePropertyNames 需要操作的属性
	 */
	public void setIncludePropertyNames(List<String> includePropertyNames) {
		this.includePropertyNames = includePropertyNames;
	}

	/**
	 * 添加需要操作的属性
	 * 
	 * @param propertyName
	 */
	public void addIncludePropertyName(String propertyName) {
		getIncludePropertyNames().add(propertyName);
	}

	/**
	 * propertyName在includePropertyNames中返回true
	 * 
	 * @param propertyName
	 * @return
	 */
	public boolean includePropertyName(String propertyName) {
		return getIncludePropertyNames().indexOf(propertyName) >= 0;
	}

	/**
	 * 不需要操作的属性
	 * 
	 * @return excludePropertyNames 不需要操作的属性
	 */
	public List<String> getExcludePropertyNames() {
		return excludePropertyNames;
	}

	/**
	 * 不需要操作的属性
	 * 
	 * @param excludePropertyNames 不需要操作的属性
	 */
	public void setExcludePropertyNames(List<String> excludePropertyNames) {
		this.excludePropertyNames = excludePropertyNames;
	}

	/**
	 * 添加不需要操作的属性
	 * 
	 * @param propertyName
	 */
	public void addExcludePropertyName(String propertyName) {
		getExcludePropertyNames().add(propertyName);
	}

	/**
	 * propertyName在excludePropertyNames中返回true
	 * 
	 * @param propertyName
	 * @return
	 */
	public boolean excludePropertyName(String propertyName) {
		return getExcludePropertyNames().indexOf(propertyName) >= 0;
	}

	/**
	 * 不需要操作的类型
	 * 
	 * @return excludeClasses 不需要操作的类型
	 */
	public List<Class<?>> getExcludeClasses() {
		return excludeClasses;
	}

	/**
	 * 不需要操作的类型
	 * 
	 * @param excludeClasses 不需要操作的类型
	 */
	public void setExcludeClasses(List<Class<?>> excludeClasses) {
		this.excludeClasses = excludeClasses;
	}

	/**
	 * 添加不需要操作的类型
	 * 
	 * @param clazz
	 */
	public void addExcludeClass(Class<?> clazz) {
		getExcludeClasses().add(clazz);
	}

	/**
	 * clazz在excludeClasses中返回true
	 * 
	 * @param clazz
	 * @return
	 */
	public boolean excludeClass(Class<?> clazz) {
		for (Class<?> class1 : getExcludeClasses()) {
			if (class1.isAssignableFrom(clazz)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * key:属性名，value:操作后的属性名
	 * 
	 * @return propertyNameMap key:属性名，value:操作后的属性名
	 */
	public Map<String, String> getPropertyNameMap() {
		return propertyNameMap;
	}

	/**
	 * key:属性名，value:操作后的属性名
	 * 
	 * @param propertyNameMap key:属性名，value:操作后的属性名
	 */
	public void setPropertyNameMap(Map<String, String> propertyNameMap) {
		this.propertyNameMap = propertyNameMap;
	}

	/**
	 * 添加操作后的属性名
	 * 
	 * @param oriPropertyName 操作前的属性名
	 * @param convertedPropertyName 操作后的属性名
	 */
	public void addConvertedPropertyName(String oriPropertyName, String convertedPropertyName) {
		getPropertyNameMap().put(oriPropertyName, convertedPropertyName);
	}

	/**
	 * 
	 * @param propertyName 操作前的属性名
	 * @return 操作后的属性名
	 */
	public String getConvertedPropertyName(String propertyName) {
		String result = getPropertyNameMap().get(propertyName);
		if (StringUtils.isEmpty(result)) {
			result = propertyName;
		}
		return result;
	}

}
