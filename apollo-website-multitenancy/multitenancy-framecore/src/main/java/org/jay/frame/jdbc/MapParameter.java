package org.jay.frame.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
/**
 * 例如：
 * sql = update table set a = :aId,b = :bId where id = :id
 * nameParameterMap {"aId":"a","bId":"b","id":"id"}
 * @author admin
 *
 */
public class MapParameter {
	/**
	 * 生成的sql语句
	 */
	private String sql;
	
	/**
	 * key:sql语句参数中的别名
	 * value:数据库字段
	 */
	private Map<String,String> nameParameterMap;
	
	
	private JdbcModel jdbcModel;

	public JdbcModel getJdbcModel() {
		return jdbcModel;
	}

	public void setJdbcModel(JdbcModel jdbcModel) {
		this.jdbcModel = jdbcModel;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Map<String, String> getNameParameterMap() {
		return nameParameterMap;
	}

	public void setNameParameterMap(Map<String, String> nameParameterMap) {
		this.nameParameterMap = nameParameterMap;
	}
	
	public Map toParameters(Object obj) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map param = new HashMap();
		for (String key : nameParameterMap.keySet()) {
			String dbCol = nameParameterMap.get(key);
			JdbcColumn jdbcColumn = jdbcModel.getColByDBColName(dbCol);
			param.put(key, jdbcColumn.getValue(obj));
		}
		return param;
	}
}
