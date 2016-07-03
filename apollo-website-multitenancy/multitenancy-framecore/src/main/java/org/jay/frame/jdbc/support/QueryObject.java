package org.jay.frame.jdbc.support;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jay.frame.jdbc.Constants;
import org.jay.frame.util.StringUtil;


public class QueryObject {
	
	protected final Logger logger = Logger.getLogger(getClass());
	// 参数条件
	private Map<String, Object> paramsMap;

	// 拼装的sql
	private StringBuffer querySql;

	// 排序条件 sql
	private String orderbySql;

	public QueryObject() {
		if (this.paramsMap == null) {
			this.paramsMap = new HashMap<String, Object>();
		}
	}

	public QueryObject(Map<String, Object> map, StringBuffer sql) {
		this.paramsMap = map;
		this.querySql = sql;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap != null ? paramsMap : new HashMap<String, Object>();
	}

	public void setParamsMap(Map<String, Object> map) {
		this.paramsMap = map;
	}

	public StringBuffer getQuerySql() {
		return querySql != null ? querySql : new StringBuffer(0);
	}

	public void setQuerySql(StringBuffer sql) {
		this.querySql = sql;
	}

	public QueryObject setQueryObject(QueryObject queryObject) {
		this.getParamsMap().putAll(queryObject.getParamsMap());
		this.getQuerySql().append(' ').append(queryObject.getQuerySql());
		return this;
	}

	public String getOrderbySql() {
		return orderbySql != null ? orderbySql : "";
	}

	public void setOrderbySql(String orderbySql) {
		this.orderbySql = orderbySql;
	}

	public void setOrderby(String sortfield, String sorttype) {
		if (StringUtil.isEmpty(sortfield)) {
			this.orderbySql = null;
		} else {
			String tmp = StringUtil.trim(sorttype, Constants.SORT_TYPE_DESC);
			//如果排序条件不等于 asc或 desc, 则默认为 desc 
			if(!tmp.toLowerCase().equals(Constants.SORT_TYPE_DESC) && 
					!tmp.toLowerCase().equals(Constants.SORT_TYPE_ASC)){
				tmp = Constants.SORT_TYPE_DESC;
			}
			this.orderbySql = " order by " + sortfield + " " +tmp;
		}
	}
}
