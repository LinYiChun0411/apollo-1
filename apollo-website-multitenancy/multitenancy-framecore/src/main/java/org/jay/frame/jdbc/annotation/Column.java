package org.jay.frame.jdbc.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface Column {
	/**
	 * 数据库字段名
	 * @return
	 */
	String name();
	/**
	 * 主键
	 * @return
	 */
	boolean primarykey()default false;
	/**
	 * 长度0不做校验
	 * @return
	 */
	int length() default 0;
	/**
	 * 是否允许为空
	 * @return
	 */
	boolean nullable() default true; 
	/**
	 * 是否唯一
	 * @return
	 */
	boolean unique() default false; 
	/**
	 * 是否更新此字段
	 * @return
	 */
	boolean updatable() default true; 
	/**
	 * 是否加入此字段
	 * @return
	 */
	boolean insertable() default true; 
	/**
	 * 临时字段
	 * 一旦设置true，updatable和insertable无论设置成什么。都是false
	 * 使用场景：
	 * 		select t1.*,t2.TEMP_COL from t1 left join t2 on(t1.id = t2.cid)
	 * 		此时将查询结果转换成当前类，需要有一个字段存储TEMP_COL.就需要照下列配置
	 * 		因为该字段只是读数据使用，所以更新/插入将不再操作这类型的字段
	 * 		@Column(temp=true,name="TEMP_COL")
	 * 		private String templCol; 
	 * @return
	 */
	boolean temp() default false;
	
	
	/**
	 * jdbc执行update(model)时，字段若是数值类型 (Long Integer BigDecimal 等)
	 * 当值等于 nullvalue时，则会将column赋值为空 
	 * sql:update table set cloumn = null where ... 
	 * @return
	 */
	//int nullvalue() default Integer.MIN_VALUE;
	
	
	/**
	 * 自增加
	 */
	public static final int PK_AUTO = 1;
	/**
	 * 需要手动赋值
	 */
	public static final int PK_BY_HAND = 2;
	
	/**
	 * 主键生产方式
	 * @return
	 */
	int generator() default PK_AUTO;
}

