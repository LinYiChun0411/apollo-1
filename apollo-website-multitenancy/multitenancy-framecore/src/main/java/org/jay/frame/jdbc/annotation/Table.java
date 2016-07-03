package org.jay.frame.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
	/**
	 * 表名
	 * @return
	 */
	String name();
	/**
	 * 新增或者修改前对字段做验证
	 * @return
	 */
	boolean uncheck() default false;
}
