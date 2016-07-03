package org.jay.frame.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.jay.frame.exception.JayFrameException;

/**
 *  由于生成的sql语句参数是由？组成。 在执行的时候需要按顺序对应赋值
 *  例如：insert table (a,b) values(?,?)   对应的  nameParameters存储的 ["a","b"]
 *   或   update table set a = ?,b = ? where c = ?  对应的  nameParameters存储的 ["a","b","c"]
 * 
 * @author admin
 *
 */
public class ListParameter {
	
	/**
	 * 生成的sql语句
	 */
	private String sql;
	
	/**
	 * sql参数顺序
	 */
	private List<String> nameParameters;
	
	
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

	public List<String> getNameParameters() {
		return nameParameters;
	}

	public void setNameParameters(List<String> nameParameters) {
		this.nameParameters = nameParameters;
	}
	
	public List toParameters(Object obj) {
		try{
			List param = new ArrayList();
			for (int i = 0; i < nameParameters.size(); i++) {
				JdbcColumn col = jdbcModel.getColByDBColName(nameParameters.get(i));
				param.add(col.getValue(obj));
			}
			return param;
		}catch(Exception e){
			e.printStackTrace();
			throw new JayFrameException(JayFrameException.UNKNOW, e.getMessage());
		}
	}
}
