package cn.cnyirui.framework.extension.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel导出注解
 * 
 * @author pengzhihua
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelExportable {
	/**
	 * excel文件的列序号，从 0开始
	 * 
	 * @return
	 */
	int index() default -1;

	/**
	 * 格式化实体类的数据 Object methodName(Object data, String propertyName){}
	 * 
	 * @return
	 */
	String formatMethodName() default "";

	/**
	 * 导出的属性名(当导出对象是复杂对象时，可以指定具体导出哪个属性)
	 * 
	 * @return
	 */
	String[]propertyNames() default {};

	/**
	 * excel文件的列标题(用于生成模板文件)
	 * 
	 * @return
	 */
	String[]titles() default {};

	/**
	 * excel文件的列的宽度(用于生成模板文件)
	 * 
	 * @return
	 */
	int[]widths() default {};

}
