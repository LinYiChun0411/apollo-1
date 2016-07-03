package org.jay.frame.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.jay.frame.util.StringUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class CamelRowMapper implements RowMapper {
	/**
	 * struts2 action类可以根据model.property自动给model属性赋值
	 */
	private String prefix;
	
	public CamelRowMapper(){
		
	}
	
	public CamelRowMapper(String prefix){
		this.prefix = prefix;
	}
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map data = new HashMap();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			Object value = JdbcUtils.getResultSetValue(rs, i);
			String name = StringUtil.strnull(rsmd.getColumnName(i));
			String key = underlineToCamel(name);
			if(StringUtil.isNotEmpty(prefix)){
				key = prefix + "." + name;
			}
			data.put(key, value);
		}
		return data;
	}

	private String underlineToCamel(String param) {
		if (param == null) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
