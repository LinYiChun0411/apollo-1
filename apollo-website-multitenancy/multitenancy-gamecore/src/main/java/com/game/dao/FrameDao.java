package com.game.dao;

import org.jay.frame.exception.JayFrameException;
import org.jay.frame.jdbc.JdbcDAOImpl;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.game.service.CodeCreatorService;

import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

@SuppressWarnings("unchecked")
@Repository
public class FrameDao extends JdbcDAOImpl{
	 
	public Map getTableFrame(String tableName){
		String sql = "select * from " + tableName + " where 1 <> 1 ";
		Map frame = MixUtil.newHashMap(CodeCreatorService.HAS_DATE_COL, false);
		PreparedStatement pstmt = null;
		ResultSet ids = null;
		try{
			Connection conn = super.getConnection();
			DatabaseMetaData meta = conn.getMetaData(); 
			ids = meta.getPrimaryKeys("", "",tableName);
			String col = null;
			if(ids != null && ids.next()){
				 col = ids.getString("COLUMN_NAME");  
				 frame.put(CodeCreatorService.PRIMARY_KEY_COLUMN_NAME, col);
			}
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();  //这点特别要注意:如果是Oracle而对于mysql可以不用加.
			ResultSetMetaData data = pstmt.getMetaData();
			
			List list = new ArrayList();
			frame.put(CodeCreatorService.PROPERTY_LIST, list);
			for (int i = 1; i <= data.getColumnCount(); i++) {
				String colName = data.getColumnName(i);
				Map map = MixUtil.newHashMap(CodeCreatorService.IS_PRIMARY, false);
				if(colName.equals(col)){
					map.put(CodeCreatorService.IS_PRIMARY, true);
				}
				map.put(CodeCreatorService.COLUMN_NAME, colName);
				String property = toPropertyName(colName);
				map.put(CodeCreatorService.JAVA_PROPERTY,property);
				
				list.add(map);
				int colType = data.getColumnType(i);
				boolean isBool = false;
				if(colType == Types.VARCHAR || colType == Types.CHAR || colType == Types.LONGNVARCHAR
						||colType == Types.LONGVARCHAR|| colType == Types.NCHAR || colType == Types.NVARCHAR){
					map.put(CodeCreatorService.TYPE, String.class.getSimpleName());
					map.put(CodeCreatorService.MAX_LENGTH, data.getColumnDisplaySize(i));
				}else if(colType == Types.INTEGER || colType == Types.BIGINT || colType == Types.BIT){
					map.put(CodeCreatorService.TYPE,Long.class.getSimpleName());
				}else if(colType == Types.DOUBLE || colType ==  Types.DECIMAL || 
						colType == Types.FLOAT || colType == Types.NUMERIC){
					map.put(CodeCreatorService.TYPE,Double.class.getSimpleName());
				}else if( colType == Types.CLOB || colType == Types.NCLOB ){
					map.put(CodeCreatorService.TYPE, String.class.getSimpleName());
				}else if(colType == Types.DATE || colType == Types.TIMESTAMP || colType == Types.TIME){
					map.put(CodeCreatorService.TYPE,Date.class.getSimpleName());
					frame.put(CodeCreatorService.HAS_DATE_COL, true);
				}else if(colType == Types.BOOLEAN){
					isBool = true;
					map.put(CodeCreatorService.TYPE,Boolean.class.getSimpleName());
				}else{
					map.put(CodeCreatorService.TYPE,Object.class.getSimpleName());
				}
				map.put("getMethodName", StringUtil.getGetMethodName(property,isBool));
				map.put("setMethodName", StringUtil.getSetMethodName(property));
			}
		}catch(Exception e){
			throw new JayFrameException(e);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(ids != null){
					ids.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return frame;
	}
	/**
	 * 获取postgresql 表结构
	 * @param tableName
	 * @return
	 */
	public Map getPostgresqlComment(String tableName){
		String sql = "SELECT " +
		   " col.column_name ," +
		   "  des.description " +
		   " FROM " +
		   "  information_schema.columns col LEFT JOIN pg_description des " +
		   "      ON col.table_name::regclass = des.objoid " +
		   "   AND col.ordinal_position = des.objsubid " +
		   " WHERE " +
		   "   table_schema = 'public' " +   //所属架构
		   "  AND table_name = '"+tableName+"' " +  //表名
		   "   AND table_catalog = 'GAME' "+  //数据库名
		   " ORDER BY " +
		   "   ordinal_position";
		Map commentMap = MixUtil.newHashMap();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Connection conn = super.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				commentMap.put(rs.getString("column_name"), rs.getString("description"));
			}
			return commentMap;
		}catch(Exception e){
			throw new JayFrameException(e);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(rs != null){
					rs.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private String toPropertyName(String colName){
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < colName.length(); i++) {
			char c = colName.charAt(i);
			if(flag){
				sb.append(Character.toUpperCase(c));
				flag = false;
				continue;
			}else if(c == '_'){
				flag = true;
				continue;
			}
			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}
	
}
