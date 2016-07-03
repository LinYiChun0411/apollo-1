package org.jay.frame.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.model.BaseModel;
import org.jay.frame.util.StringUtil;

public class SqlMaker {
	
	
	/**
	 * 判断c类是不是继承与BaseModel
	 * @param c
	 * @return
	 */
	public static boolean isBaseModel(Class c){
		while(c != Object.class){
			c = c.getSuperclass();
			if(c == BaseModel.class){
				return true;	
			}
		}
		return false;
	}
	
	/**
	 * 获取insert sql 和参数的顺序
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static ListParameter getAddSql(JdbcModel model) throws IllegalArgumentException,
			IllegalAccessException {
		 
		List<String> names = new ArrayList<String>();
		Map<String, JdbcColumn> colMap = model.getColMap();
		String tbName = model.getTableName();
		StringBuffer header = new StringBuffer(" insert into " + tbName + " (");
		StringBuffer ender = new StringBuffer(" values (");
		boolean first = true;
		for (String key : colMap.keySet()) {
			JdbcColumn col = colMap.get(key);
			String colName = col.getColumnName();

			if(col.getGenerator() == Column.PK_AUTO && col.isPrimary() ){  //主键自动生成
				continue;
			}
			if (!col.isInsertable() || col.isTemp()) {
				continue;
			}
			names.add(colName);
			if (first) {
				header.append(colName);
				ender.append("?");
				first = false;
			} else {
				concat(header, colName, ",");
				concat(ender, "?", ",");
			}
		}
		header.append(")");
		ender.append(")");
		ListParameter parameter = new ListParameter();
		parameter.setSql(header.append(ender).toString());
		parameter.setNameParameters(names);
		parameter.setJdbcModel(model);
		return parameter;
	}

	
	/**
	 * 获取update语句
	 * 
	 * @param t
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static MapParameter getUpdateSql(JdbcModel model) throws IllegalArgumentException,
			IllegalAccessException {
		
		Map<String,String>nameParamMap = new HashMap<String,String>();
		Map<String, JdbcColumn> colMap = model.getColMap();
		JdbcColumn primaryCol = model.getPrimaryCol();
		String tbName = model.getTableName();
		StringBuffer sb = new StringBuffer("update " + tbName + " set ");
		boolean first = true;
		for (String key : colMap.keySet()) {
			JdbcColumn col = colMap.get(key);
			String colName = col.getColumnName();
			if(!col.isUpdatable() || col.isTemp() || col.isPrimary()){
				continue;
			}
			nameParamMap.put(colName, colName);
			if (first) {
				sb.append(colName + " = :" + colName);
				first = false;
			} else {
				concat(sb, colName + " = :" + colName, ",");
			}
		}
		String pcol = primaryCol.getColumnName();
		sb.append(" where " + pcol + " = :"+ pcol + "");
		nameParamMap.put(pcol, pcol);
		
		if(isBaseModel(model.getC())){
			sb.append(" and FLAG_ACTIVE < "+BaseModel.Flag_READONLY +" ");
		}
		MapParameter parameter = new MapParameter();
		parameter.setSql(sb.toString());
		parameter.setJdbcModel(model);
		parameter.setNameParameterMap(nameParamMap);
		return parameter;
	}
	
	private static void concat(StringBuffer sb, String str, String notation) {
		if (!StringUtil.endsWith(sb.toString(), notation)) {
			sb.append(notation);
		}
		sb.append(str);
	}
}
