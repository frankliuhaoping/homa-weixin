package cn.cnyirui.framework.extension.excel;

import org.apache.commons.lang3.ArrayUtils;

public class ExcelExportColumn {
	/**
	 * 列序号，从0开始
	 */
	private int index;
	/**
	 * 列标题
	 */
	private String[] titles;
	/**
	 * 列宽度
	 */
	private int[] widths;

	/**
	 * 对应实体类的属性名
	 */
	private String[] propertyNames;

	/**
	 * 格式化实体类的数据的方法名
	 */
	private String formatMethodName;

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
	 * @return titles 列标题
	 */
	public String[] getTitles() {
		if (ArrayUtils.isEmpty(titles)) {
			return getPropertyNames();
		}
		return titles;
	}

	/**
	 * 列标题
	 * 
	 * @param titles 列标题
	 */
	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	/**
	 * 列宽度
	 * 
	 * @return widths 列宽度
	 */
	public int[] getWidths() {
		if (ArrayUtils.isEmpty(widths)) {
			widths = new int[getPropertyNames().length];
			for (int i = 0; i < widths.length; i++) {
				widths[i] = 30;
			}
		}
		return widths;
	}

	/**
	 * 列宽度
	 * 
	 * @param widths 列宽度
	 */
	public void setWidths(int[] widths) {
		this.widths = widths;
	}

	/**
	 * 对应实体类的属性名
	 * 
	 * @return propertyNames 对应实体类的属性名
	 */
	public String[] getPropertyNames() {
		return propertyNames;
	}

	/**
	 * 对应实体类的属性名
	 * 
	 * @param propertyNames 对应实体类的属性名
	 */
	public void setPropertyNames(String[] propertyNames) {
		this.propertyNames = propertyNames;
	}

	/**
	 * 格式化实体类的数据的方法名
	 * 
	 * @return formatMethodName 格式化实体类的数据的方法名
	 */
	public String getFormatMethodName() {
		return formatMethodName;
	}

	/**
	 * 格式化实体类的数据的方法名
	 * 
	 * @param formatMethodName 格式化实体类的数据的方法名
	 */
	public void setFormatMethodName(String formatMethodName) {
		this.formatMethodName = formatMethodName;
	}

}
