package cn.cnyirui.framework.extension.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，拦截dao操作
 * 
 * @author pengzhihua
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
	/**
	 * 描述
	 * 
	 * @return
	 */
	String description() default "";
}
