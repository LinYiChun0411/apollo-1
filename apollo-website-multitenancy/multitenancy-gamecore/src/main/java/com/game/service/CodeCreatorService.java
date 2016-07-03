package com.game.service;


import org.jay.frame.jdbc.JdbcModel;

public interface CodeCreatorService {
	/**
	 * java属性类   Boolean.class
	 */
	public final static String TYPE = "type";
	/**
	 * java属性名
	 */
	public final static String JAVA_PROPERTY = "property";
	
	public final static String CLASS_NAME = "className";
	
	public final static String PROPERTY_LIST = "list";
	
	/**
	 * 数据库字段名
	 */
	public final static String COLUMN_NAME = "dbCloumn";
	
	/**
	 * 数据库字段名
	 */
	public final static String COLUMN_COMMENT = "colComment";
	/**
	 * 用于判断是否包含有日期字段
	 */
	public final static String HAS_DATE_COL = "hasDate";
	/**
	 * String最大长度
	 */
	public final static String MAX_LENGTH = "maxLength";
	
	public final static String IS_PRIMARY = "isPrimary";
	/**
	 * 主键
	 */
	public final static String PRIMARY_KEY_COLUMN_NAME = "!@#$%^_PRIMARY_KEY_COLUMN_NAME"; 
	
	public String createModel(String tableName,JdbcModel parent);
}
