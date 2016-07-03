package org.jay.frame.jdbc.mapper;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import org.jay.frame.exception.JayFrameException;
import org.jay.frame.jdbc.JdbcColumn;
import org.jay.frame.jdbc.JdbcModel;
import org.jay.frame.jdbc.JdbcModelSet;
import org.springframework.jdbc.core.RowMapper;



public class ModelRowMapper implements RowMapper {
	
	private Class modelClass;
	
	public ModelRowMapper(Class c){
		this.modelClass = c;
	}

	public Object mapRow(ResultSet rs, int num) throws SQLException {
		return createObjByRs(rs,this.modelClass);
	}
	
	private static Object getValueByType(Class c,ResultSet rs,String colName) throws SQLException{
		Object obj = null;
		if(c == Long.class || c == long.class){
			obj = rs.getLong(colName);
		}else if (c == Integer.class || c == int.class){
			obj = rs.getInt(colName);
		}else if (c == String.class){
			obj = rs.getString(colName);
		}else if (c == java.util.Date.class || c == Date.class){
			obj = rs.getTimestamp(colName);
		}else if (c == Double.class || c == double.class){
			obj = rs.getDouble(colName);
		}else if (c == Float.class || c==float.class){
			obj = rs.getFloat(colName);
		}else if(c == BigDecimal.class){
			obj = rs.getBigDecimal(colName);
		}else{
			obj = rs.getObject(colName);
		}
		if(rs.wasNull()){
			obj = null;
		}
		return obj;
	}
	
	public static Object createObjByRs(ResultSet rs,Class modelClass) throws SQLException{
		try {
			Object obj = modelClass.newInstance();
			JdbcModel model = JdbcModelSet.get(modelClass);
			Map<String,JdbcColumn> colMap = model.getColMap();
			ResultSetMetaData metaData = rs.getMetaData();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				String colName = metaData.getColumnName(i);
				colName = colName.toLowerCase();
				JdbcColumn col = colMap.get(colName);
				if(null != col){
					Field f = col.getField();
					Object v =  getValueByType(f.getType(), rs,colName);
					f.set(obj,v);
				}
			}
			return obj;
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new JayFrameException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new JayFrameException(e);
		}
	}
	
	public void getModelClass(Class modelClass) {
		this.modelClass = modelClass;
	}
}