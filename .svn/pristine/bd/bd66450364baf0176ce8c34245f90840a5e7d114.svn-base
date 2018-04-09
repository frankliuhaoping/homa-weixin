package cn.cnyirui.framework.extension.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel导入注解
 * 
 * @author pengzhihua
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelImportable {
	/**
	 * excel文件的列序号，从 0开始
	 * 
	 * @return
	 */
	int index() default -1;

	/**
	 * 解析excel文件中的数据 Object methodName(Object data, String propertyName){}
	 * 
	 * @return
	 */
	String parseMethodName() default "";

	/**
	 * 验证数据是否有效的方法名 boolean methodName(Object data, String propertyName){}
	 * false=验证不通过
	 * 
	 * @return
	 */
	String validateMethodName() default "";

	/**
	 * 数据是否能重复 true=不能重复
	 * 
	 * @return
	 */
	boolean isUnique() default false;

	/**
	 * excel文件的列标题(用于生成模板文件)
	 * 
	 * @return
	 */
	String title() default "";

	/**
	 * excel文件的列的宽度(用于生成模板文件)
	 * 
	 * @return
	 */
	int width() default 30;

	/**
	 * excel文件的列注释(用于生成模板文件)
	 * 
	 * @return
	 */
	String comment() default "";

}
