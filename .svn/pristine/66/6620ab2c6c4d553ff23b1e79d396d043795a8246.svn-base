package cn.cnyirui.framework.extension.excel;

import org.apache.commons.lang3.StringUtils;

public class ExcelImportColumn {
	/**
	 * 列序号，从0开始
	 */
	private int index;
	/**
	 * 列标题
	 */
	private String title;
	/**
	 * 列注释
	 */
	private String comment;
	/**
	 * 列宽度
	 */
	private int width;

	/**
	 * 对应实体类的属性名
	 */
	private String propertyName;
	/**
	 * 解析excel中数据的方法名(用于导入)
	 */
	private String parseMethodName;
	/**
	 * 验证数据有效性的方法名(用于导入)
	 */
	private String validateMethodName;
	/**
	 * 数据是否能重复 true=不能重复
	 */
	private Boolean isUnique;

	/**
	 * 列序号，从0开始
	 * 
	 * @return index 列序号，从0开始
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 列序号，从0开始
	 * 
	 * @param index 列序号，从0开始
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 列标题
	 * 
	 * @return title 列标题
	 */
	public String getTitle() {
		if (StringUtils.isEmpty(title)) {
			return getPropertyName();
		}
		return title;
	}

	/**
	 * 列标题
	 * 
	 * @param title 列标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 列注释
	 * 
	 * @return comment 列注释
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 列注释
	 * 
	 * @param comment 列注释
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 列宽度
	 * 
	 * @return width 列宽度
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 列宽度
	 * 
	 * @param width 列宽度
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 对应实体类的属性名
	 * 
	 * @return propertyName 对应实体类的属性名
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 对应实体类的属性名
	 * 
	 * @param propertyName 对应实体类的属性名
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 解析excel中数据的方法名(用于导入)
	 * 
	 * @return parseMethodName 解析excel中数据的方法名(用于导入)
	 */
	public String getParseMethodName() {
		return parseMethodName;
	}

	/**
	 * 解析excel中数据的方法名(用于导入)
	 * 
	 * @param parseMethodName 解析excel中数据的方法名(用于导入)
	 */
	public void setParseMethodName(String parseMethodName) {
		this.parseMethodName = parseMethodName;
	}

	/**
	 * 验证数据有效性的方法名(用于导入)
	 * 
	 * @return validateMethodName 验证数据有效性的方法名(用于导入)
	 */
	public String getValidateMethodName() {
		return validateMethodName;
	}

	/**
	 * 验证数据有效性的方法名(用于导入)
	 * 
	 * @param validateMethodName 验证数据有效性的方法名(用于导入)
	 */
	public void setValidateMethodName(String validateMethodName) {
		this.validateMethodName = validateMethodName;
	}

	/**
	 * 数据是否能重复true=不能重复
	 * 
	 * @return isUnique 数据是否能重复true=不能重复
	 */
	public Boolean getIsUnique() {
		return isUnique;
	}

	/**
	 * 数据是否能重复true=不能重复
	 * 
	 * @param isUnique 数据是否能重复true=不能重复
	 */
	public void setIsUnique(Boolean isUnique) {
		this.isUnique = isUnique;
	}

}
