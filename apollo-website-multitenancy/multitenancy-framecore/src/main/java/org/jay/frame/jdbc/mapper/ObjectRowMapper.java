package org.jay.frame.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.jay.frame.util.StringUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;


public class ObjectRowMapper implements RowMapper {

	protected Logger log = Logger.getLogger(getClass());

	/**
	 * 行对应的Entity类型.
	 */
	protected Class entityClass;

	/**
	 * 取得entityClass. JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果。
	 */
	protected Class getEntityClass() {
		return entityClass;
	}

	public ObjectRowMapper(Class clazz) {
		entityClass = clazz;
	}

	public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
		ResultSetMetaData rsmd = rset.getMetaData();
		int columnCount = rsmd.getColumnCount();
		String name = null;

		try {
			Object t = getEntityClass().newInstance();
			for (int i = 1; i <= columnCount; i++) {
				name = StringUtil.strnull(rsmd.getColumnName(i));
				Object value = JdbcUtils.getResultSetValue(rset, i);
				PropertyUtils.setProperty(t, name, value);
			}
			return t;
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			throw new SQLException("反射出现错误");
		}
	}

}
