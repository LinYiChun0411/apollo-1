package org.jay.frame.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jay.frame.jdbc.annotation.Column;

public class JdbcColumn {

	//java实体类中的命名
	private String name;
	
	//数据库中的命名
	private String columnName;
	//字段Java类型
	private Class type;
	
	/**
	 * 主键
	 */
	private boolean primary = false;
 	
	/**
	 * 长度校验
	 * 为0不做校验
	 */
	private int length = 0;
	/**
	 * 是否允许为空
	 */
	private boolean nullable = true;
	/**
	 * 是否唯一
	 */
	private boolean unique = false;
	/**
	 * 是否更新此字段
	 */
	private boolean updatable = true; 
	/**
	 * 是否加入此字段
	 */
	private boolean insertable = true; 
	/**
	 * 是否读取此字段
	 */
	private boolean temp = true;
	/**
	 * 空值标识
	 */
	//private int nullvalue = Integer.MIN_VALUE;
	
	/**
	 * 属性对应的set方法
	 */
	private Method setMethod = null;
	/**
	 * 属性对应的get方法
	 */
	private Method getMethod = null;
	
	/**
	 * 主键生产方式
	 */
	private int generator = Column.PK_AUTO;

	private Field field;
	
	/**
	 * 赋值
	 * @param obj
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException 
	 */
	public void setValue(Object obj,Object value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(setMethod != null){
			setMethod.invoke(obj,value);
			return;
		}
		this.field.set(obj, value);
	}
	/**
	 * 读取
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public Object getValue(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(getMethod != null){
			return getMethod.invoke(obj);
		}
		return this.field.get(obj);
	}
	
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		field.setAccessible(true);
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isUpdatable() {
		return updatable;
	}

	public void setUpdatable(boolean updatable) {
		this.updatable = updatable;
	}

	public boolean isInsertable() {
		return insertable;
	}

	public void setInsertable(boolean insertable) {
		this.insertable = insertable;
	}

	public boolean isTemp() {
		return temp;
	}

	public void setTemp(boolean temp) {
		this.temp = temp;
	}
	public Method getSetMethod() {
		return setMethod;
	}
	public void setSetMethod(Method setMethod) {
		this.setMethod = setMethod;
	}
	public Method getGetMethod() {
		return getMethod;
	}
	public void setGetMethod(Method getMethod) {
		this.getMethod = getMethod;
	}

	public int getGenerator() {
		return generator;
	}
	public void setGenerator(int generator) {
		this.generator = generator;
	}
}
