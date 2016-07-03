package org.jay.frame.jdbc;

import java.util.HashMap;
import java.util.Map;

public class JdbcModel {
	/**
	 * key : 数据库字段
	 */
	private Map<String, JdbcColumn> colMap = new HashMap<String,JdbcColumn>();
	/**
	 * key : java实体类
	 */
	private Map<String, JdbcColumn> propertyMap = new HashMap<String,JdbcColumn>();
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 实体类的class
	 */
	private Class c;
	/**
	 * 主键字段
	 */
	private JdbcColumn primaryCol;
	
	private boolean uncheck;
	

	public JdbcColumn getPrimaryCol() {
		return primaryCol;
	}
	
	/**
	 * 根据java属性获取JdbcColumn
	 * @param property
	 * 				属性名
	 * @return
	 */
	public JdbcColumn getColByProperty(String property){
		return propertyMap.get(property);
	}
	/**
	 * 根据数据库字段名获取JdbcColumn
	 * @param colName
	 * 				列名
	 * @return
	 */
	public JdbcColumn getColByDBColName(String colName){
		return colMap.get(colName);
	}
	
	public void setPrimaryCol(JdbcColumn primaryCol) {
		this.primaryCol = primaryCol;
	}
	
	
	public Map<String, JdbcColumn> getColMap() {
		return colMap;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Class getC() {
		return c;
	}
	public void setC(Class c) {
		this.c = c;
	}
	
	public boolean isUncheck() {
		return uncheck;
	}

	public void setUncheck(boolean uncheck) {
		this.uncheck = uncheck;
	}
	
	public Map<String, JdbcColumn> getPropertyMap() {
		return propertyMap;
	}
}
