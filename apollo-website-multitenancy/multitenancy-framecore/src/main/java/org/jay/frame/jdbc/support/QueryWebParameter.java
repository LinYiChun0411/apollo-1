package org.jay.frame.jdbc.support;

import org.jay.frame.jdbc.Constants;



/**
 * 封装查询参数
 * 
 * @desc QueryWebParameter
 *
 */
public class QueryWebParameter {

	private int defaultPageSize = -1;

	/**
	 * 查询参数名称数组
	 */
	private String[] name;

	/**
	 * 查询的操作符号
	 */
	private String[] operator;

	
	/**
	 * 实际的查找值，转换成相应的类型，等等
	 */
	private Object[] actualValues;

	/**
	 * 查询的起始记录
	 */
	private int startIndex;

	/**
	 * 每页显示的数量
	 */
	private int pageSize;

	/**
	 * 当前页号，从1开始
	 */
	private int pageNo;
	
	/**
	 * 排序字段名称
	 */
	private String sortfield;

	private String sorttype = Constants.SORT_TYPE_DESC;

	/**
	 * 排序字段的数组
	 */
	private String[] orderProperty;

	/**
	 * 排序字段的升降序情况
	 */
	private boolean[] descFlag;

	/**
	 * 是否要加入 distinct关键字
	 */
	private boolean distinctFlag;

	/**
	 * 默认查询全部数据
	 * 
	 * @param defaultPageSize
	 */
	public QueryWebParameter() {
	}
	
	public QueryWebParameter(int defaultPageSize) {
		this.defaultPageSize = defaultPageSize;
	}

	public Object[] getActualValues() {
		return actualValues;
	}

	public void setActualValues(Object[] actualValues) {
		this.actualValues = actualValues;
	}

	public boolean[] getDescFlag() {
		return descFlag;
	}

	public void setDescFlag(boolean[] descFlag) {
		this.descFlag = descFlag;
	}

	public boolean isDistinctFlag() {
		return distinctFlag;
	}

	public void setDistinctFlag(boolean distinctFlag) {
		this.distinctFlag = distinctFlag;
	}

	public String[] getOrderProperty() {
		return orderProperty;
	}

	public void setOrderProperty(String[] orderProperty) {
		this.orderProperty = orderProperty;
	}

	public int getPageSize() {
		return defaultPageSize <= 0 ? pageSize : (pageSize = pageSize > defaultPageSize ? defaultPageSize : (pageSize <= 0 ? defaultPageSize : pageSize));
	}

	public void setPageSize(int pageSize) {
		this.pageSize = defaultPageSize <= 0 ? pageSize : (pageSize > defaultPageSize ? defaultPageSize : (pageSize <= 0 ? defaultPageSize : pageSize));
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int page) {
		this.pageNo = page <= 0 ? 1 : page;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getOperator() {
		return operator;
	}

	public void setOperator(String[] operator) {
		this.operator = operator;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getSortfield() {
		return sortfield;
	}

	public void setSortfield(String sortfield) {
		this.sortfield = sortfield;
	}

	public String getSorttype() {
		return sorttype;
	}

	public void setSorttype(String sorttype) {
		this.sorttype = sorttype;
	}
}
