package cn.cnyirui.framework.controller.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认的分页数据，先从参数找，参数找不到从方法上找
 */
@Target({ ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageableDefaults {

	int pageSize() default 20;

	int pageNumber() default 0;

	/**
	 * 默认的排序 格式为{"a=desc", "a.b=desc"}
	 */
	String[]sort() default {};

}
