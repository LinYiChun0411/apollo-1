package org.jay.frame.jdbc;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.jay.frame.FrameProperites;
import org.jay.frame.exception.JayFrameException;
import org.jay.frame.jdbc.support.QueryObject;
import org.jay.frame.jdbc.support.QueryParam;
import org.jay.frame.jdbc.support.QuerySqlParser;
import org.jay.frame.jdbc.support.QueryWebParameter;
import org.jay.frame.jdbc.support.QueryWebUtils;

import org.jay.frame.util.SqlUtil;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;




/**
 * 基于spring NamedParameterJdbcTemplate封装的jdbc操作
 * 
 * @desc JdbcDAOImpl
 * 
 */
public class JdbcDAOImpl extends NamedParameterJdbcDaoSupport implements JdbcDAO {

	private static Logger log = Logger.getLogger(JdbcDAOImpl.class);
	
	@Autowired
    public void setDs(DataSource dataSource) {
         setDataSource(dataSource);
    }
	
	public JdbcDAOImpl() {
	}
	
	public JdbcOperations getJdbcOperations() {
		return getNamedParameterJdbcTemplate().getJdbcOperations();
	}

	/**
	 * 直接执行一条sql
	 * 
	 * @param sql
	 */
	public void execute(String sql) {
		Assert.hasText(sql, "sql must be not null");
		getJdbcOperations().execute(sql);
	}

	/**
	 * 可直接运行的sql语句数组
	 * 
	 * @param sql
	 *            update ca_user set a ='newea' ,b=3 where c = 5
	 * @return
	 */
	public int[] batchUpdate(String[] sql) {
		return getJdbcOperations().batchUpdate(sql);
	}

	/**
	 * 注意：这里的sql不支持 命名参数，只能用 ? 代替
	 * 
	 * @param sql
	 *            update ca_user set a =? ,b=? where c = ?
	 * @param dataSet
	 *            List<String[]>
	 * @return
	 */
	public int[] batchUpdate(String sql, final List dataSet) {
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return dataSet.size();
			}

			public void setValues(PreparedStatement psmt, int i) {
				String[] obj = (String[]) dataSet.get(i);
				try {
					for (int j = 0; j < obj.length; j++) {
						psmt.setString(j + 1, obj[j]);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		return getJdbcOperations().batchUpdate(sql, setter);

	}

	/**
	 * 更新sql语句的执行，没有参数的情况
	 * 
	 * @param sql
	 * @return 受影响的行数
	 */
	public int update(String sql) {
		Assert.hasText(sql, "sql must be not null");
		return getJdbcOperations().update(sql);
	}

	/**
	 * 只有一个参数更新语句执行
	 * 
	 * @param sql
	 *            ：更新语句
	 * @param paramMap
	 *            ：命名参数
	 * @return 受影响的行数
	 */
	public int update(String sql, String namedParam, Object value) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return update(sql, paramMap);
	}

	/**
	 * 更新语句执行
	 * 
	 * @param sql
	 *            ：更新语句
	 * @param paramMap
	 *            ：命名参数
	 * @return 受影响的行数
	 */
	public int update(String sql, Map paramMap) {
		// sql = DBSqlParse.parseSql(sql, paramMap);
		getLogInfo(sql, paramMap);
		
		return getNamedParameterJdbcTemplate().update(sql, new MapSqlParameterSource(paramMap));
	}

	/**
	 * 单个参数的情况
	 * 
	 * @param sql
	 *            select count(*) from demo where a =: a and b =: b<br>
	 *            select nid from demo where a =: a and b =: b ;其中nid 为数值型
	 * @param namedParam
	 * @param value
	 * @return 返回一个整数值
	 */
	public int queryForInt(String sql, String namedParam, Object value) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return queryForInt(sql, paramMap);
	}

	/**
	 * 查询单列(类型为整型)，返回一个整数值<br>
	 * 多个参数的情况
	 * 
	 * @param sql
	 *            ：select count(*) from demo where a =: a and b =: b<br>
	 *            select nid from demo where a =: a and b =: b ;其中nid 为数值型
	 * @param paramMap
	 *            命名参数为key；参数值为value的Map
	 * @return 整数值
	 */
	public int queryForInt(String sql, Map paramMap) {
		getLogInfo(sql, paramMap);
		int i = -1;
		try {
			i = getNamedParameterJdbcTemplate().queryForObject(sql, new MapSqlParameterSource(paramMap),int.class);
		} catch (EmptyResultDataAccessException ex) {
			log.error("忽略此类错误[EmptyResultDataAccessException],允许查询为空时,返回-1!");
		}
		return i;
	}

	/**
	 * 没有参数的情况，查询单列(类型为整型)，返回一个整数值<br>
	 * 
	 * @param sql
	 *            ：select count(*) from demo where a =: a and b =: b<br>
	 *            select nid from demo where a =: a and b =: b ;其中nid 为数值型
	 * @return 整数值
	 */
	public int queryForInt(String sql) {
		return queryForInt(sql, null);
	}

	/**
	 * 查询单列(类型为整型)，返回一个长整数值<br>
	 * 单个参数的情况
	 * 
	 * @param sql
	 *            ：select count(*) from demo where a =: a <br>
	 *            select nid from demo where a =: a ;其中nid 为数值型
	 * @param namedParam
	 *            命名参数
	 * @param value
	 *            参数值
	 * @return
	 */
	public long queryForLong(String sql, String namedParam, Object value) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return queryForLong(sql, paramMap);
	}

	/**
	 * 查询单列(类型为整型)，返回一个整数值<br>
	 * 
	 * @param sql
	 *            ：select count(*) from demo where a =: a and b =: b <br>
	 *            select nid from demo where a =: a and b =: b ;其中nid 为数值型
	 * @param paramMap
	 *            命名参数为key；参数值为value的Map
	 * @return 长整数值
	 */
	public long queryForLong(String sql, Map paramMap) {
		getLogInfo(sql, paramMap);
		long l = -1L;
		try {
			l = getNamedParameterJdbcTemplate().queryForObject(sql, new MapSqlParameterSource(paramMap),long.class);
		} catch (EmptyResultDataAccessException ex) {
			log.error("忽略此类错误[EmptyResultDataAccessException],允许查询为空时,返回-1!");
		}
		return l;
	}

	/**
	 * 查询单列(类型为整型)，返回一个整数值<br>
	 * 
	 * @param sql
	 *            ：select count(*) from demo where a =: a and b =: b <br>
	 *            select nid from demo where a =: a and b =: b ;其中nid 为数值型
	 * @return 长整数值
	 */
	public long queryForLong(String sql) {
		return queryForLong(sql, null);
	}

	/**
	 * 只取一列的值
	 * 
	 * @param sql
	 *            ：select cname from demo where a =: a and b =: b <br>
	 * @param paramMap
	 *            命名参数为key；参数值为value的Map
	 * @return 返回 字符串，或者""
	 */
	public String queryForString(String sql, Map paramMap) {
		getLogInfo(sql, paramMap);
		String str = null;
		try {
			str = (String) getNamedParameterJdbcTemplate().queryForObject(sql, paramMap, String.class);
		} catch (EmptyResultDataAccessException ex) {
			log.error("忽略此类错误[EmptyResultDataAccessException],允许查询为空时,返回空字符串!");
		}

		return (str != null ? str : "");
	}

	/**
	 * 只取一列值,返回字符串类型
	 * 
	 * @param sql
	 *            ：select cname from demo where nid = 1 <br>
	 * @return 返回 字符串，或者""
	 */
	public String queryForString(String sql) {
		return queryForString(sql, null);
	}

	/**
	 * 单个参数的情况，只取一列的值
	 * 
	 * @param sql
	 *            ：select cname from demo where nid = 1 <br>
	 * @param namedParam
	 *            ：命名参数
	 * @param value
	 *            ：参数值
	 * @return 返回 字符串，或者""
	 */
	public String queryForString(String sql, String namedParam, Object value) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return queryForString(sql, paramMap);
	}

	/**
	 * 解析结果集 成 Map对象
	 *  For Oracle
	 * @param rset
	 * @return 结果集的hashMap
	 * @throws SQLException
	 */
	
	/*
	private static Map row2Map(ResultSet rset) {
		try {
			ResultSetMetaData mdRset = rset.getMetaData();
			String colTypeName = null;
			int columnCount = mdRset.getColumnCount();
			Map paramMap = new HashMap(columnCount);

			for (int i = 1; i <= columnCount; i++) {
				// 增加对BLOB\CLOB的处理
				colTypeName = mdRset.getColumnTypeName(i);
				String colName = StringUtil.strnull(mdRset.getColumnName(i));

				if ("BLOB".equalsIgnoreCase(colTypeName) || "MEDIUMBLOB".equalsIgnoreCase(colTypeName)
				    || "LONGBLOB".equalsIgnoreCase(colTypeName)) {
					Blob tmp = rset.getBlob(i);
					if (null != tmp) {
						byte[] bytes = tmp.getBytes(1, (int) (tmp.length()));
						paramMap.put("hasPicture", "1");
						paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), bytes);
					} else {
						paramMap.put("hasPicture", "0");
						paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), new byte[0]);
					}
				} else if ("LONG RAW".equalsIgnoreCase(colTypeName)) {
					byte[] data = rset.getBytes(i);
					if (null != data) {
						paramMap.put("hasPicture", "1");
						paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), data);
					} else {
						paramMap.put("hasPicture", "0");
						paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), new byte[0]);
					}
				} else if ("CLOB".equalsIgnoreCase(colTypeName) || "LONGTEXT".equalsIgnoreCase(colTypeName)) {
					java.sql.Clob clob = rset.getClob(i);
					if (null != clob) {
						String tmp = clob.getSubString(1, (int) clob.length());
						paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), tmp);
					} else {
						paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), "");
					}
				} else if ("INTEGER".equalsIgnoreCase(colTypeName)) {
					int val = rset.getInt(i);
					paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), val);
				} else if ("NUMBER".equalsIgnoreCase(colTypeName)) {
					paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), rset.getBigDecimal(i));
				} else if ("LONG".equalsIgnoreCase(colTypeName)) {
					paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), rset.getLong(i));
				} else {
					//
					String tmp = (rset.getString(i) == null) ? "" : rset.getString(i);
					paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), tmp);
				}
			}
			return paramMap;
		} catch (SQLException e) {
			log.error("滚动查询分页数据出现错误：" + e.getMessage(), e);
			return null;
		}
	}*/
	/**
	 * 解析结果集 成 Map对象  For Mysql
	 * @param rset
	 * @return 结果集的hashMap
	 * @throws SQLException
	 */
	private static Map row2Map(ResultSet rset) {
		try {
			ResultSetMetaData mdRset = rset.getMetaData();
			String colTypeName = null;
			int columnCount = mdRset.getColumnCount();
			Map paramMap = new HashMap(columnCount);

			for (int i = 1; i <= columnCount; i++) {
				// 增加对BLOB\CLOB的处理
				colTypeName = mdRset.getColumnTypeName(i);
				String colName = StringUtil.strnull(mdRset.getColumnName(i));
				
				if("TIMESTAMP".equalsIgnoreCase(colTypeName) || "DATE".equalsIgnoreCase(colTypeName)
						|| "DATETIME".equalsIgnoreCase(colTypeName)){
					paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), rset.getTimestamp(i));
				}else if ("DOUBLE".equalsIgnoreCase(colTypeName)) {
					paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), rset.getDouble(i));
				} else if ("INT".equalsIgnoreCase(colTypeName)) {
					paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), rset.getLong(i));
				} else {
					String tmp = (rset.getString(i) == null) ? "" : rset.getString(i);
					paramMap.put(StringUtil.strnull(mdRset.getColumnName(i)), tmp);
				}
			}
			return paramMap;
		} catch (SQLException e) {
			log.error("滚动查询分页数据出现错误：" + e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 取得HashMap封装的数据结果集<br>
	 * 有blob列情况，追加一个key="hasPicture", value="1"、"0"<br>
	 * 
	 * @param sql
	 * @param paramMap
	 *            ：命名参数Map
	 * @return HashMap:[key=colName,value=colValues]
	 */
	public Map selectSingleMap(String sql, Map paramMap) {
		getLogInfo(sql, paramMap);
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
				return row2Map(rset);
			}
		};
		try {
			return (Map) getNamedParameterJdbcTemplate().queryForObject(sql, paramMap, mapper);
		} catch (EmptyResultDataAccessException ex) {
			log.error("忽略此类错误[EmptyResultDataAccessException],允许查询为空时,返回空字符串!");
			return null;
		}
	}

	/**
	 * 取得HashMap封装的数据结果集<br>
	 * 有blob列情况，追加一个key="hasPicture", value="1"、"0"<br>
	 * 
	 * @param sql
	 * @return HashMap:[key=colName,value=colValues]
	 */
	public Map selectSingleMap(String sql) {
		return selectSingleMap(sql, null);
	}

	/**
	 * 没有参数的情况<br>
	 * 取得HashMap封装的数据结果集<br>
	 * 有blob列情况，追加一个key="hasPicture", value="1"、"0"<br>
	 * 
	 * @param sql
	 * @return HashMap:[key=colName,value=colValues]
	 */
	public Map selectSingleMap(String sql, String namedParam, Object value) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return selectSingleMap(sql, paramMap);
	}

	/**
	 * 只有一个参数的情况 取得HashMap封装的数据结果集<br>
	 * 有blob列情况，追加一个key="hasPicture", value="1"、"0"<br>
	 * 
	 * @param sql
	 *            "SELECT AGE FROM CUSTMR WHERE ID = :id"
	 * @param namedParam
	 *            "id"
	 * @param value
	 *            133
	 * @return HashMap:[key=colName,value=colValues]
	 */
	public Map selectSingleMap(String sql, String namedParam, Object[] value) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), Arrays.asList(value));
		return selectSingleMap(sql, paramMap);
	}

	/**
	 * 只有一个数组参数的情况
	 * 
	 * 取得HashMap封装的数据结果集<br>
	 * 有blob列情况，追加一个key="hasPicture", value="1"、"0"<br>
	 * 
	 * @param sql
	 *            "SELECT AGE FROM CUSTMR WHERE ID NOT IN (:ids)"
	 * @param namedParam
	 *            "ids"
	 * @param value
	 *            133
	 * @return HashMap:[key=colName,value=colValues]
	 */
	public List selectMapList(String sql, Map paramMap) {
		getLogInfo(sql, paramMap);
		RowMapper mapper = new RowMapper() {
			public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
				return row2Map(rset);
			}
		};
		log.info(sql);
		return getNamedParameterJdbcTemplate().query(sql, paramMap, mapper);
	}
	
	/**
	 * 没有参数的情况，获取封装了HashMap的list列表 <br>
	 * 有blob列情况，追加一个key="hasPicture", value="1"、"0"<br>
	 * 
	 * @param sql
	 *            "SELECT AGE FROM CUSTMR"
	 * @return list 封装了HashMap的list列表
	 */
	public List selectMapList(String sql) {
		return selectMapList(sql, null);
	}

	/**
	 * 获取封装了HashMap的list列表 <br>
	 * 有blob列情况，追加一个key="hasPicture", value="1"、"0"<br>
	 * 
	 * @param sql
	 *            "SELECT AGE FROM CUSTMR WHERE ID = :id"
	 * @param paramMap
	 *            HashMap paramMap = new HashMap(1); paramMap.put("id", new Integer(3));
	 * @return list 封装了HashMap的list列表
	 */
	public List selectMapList(String sql, String namedParam, Object value) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return selectMapList(sql, paramMap);
	}

	/**
	 * 只有一个参数的情况 有blob列情况 <br>
	 * 追加一个key="hasPicture", value="1"、"0"<br>
	 * 
	 * @param sql
	 *            "SELECT AGE FROM CUSTMR WHERE ID in (:id)"
	 * @param namedParam
	 *            "id"
	 * @param value
	 *            133
	 * @return list 封装了HashMap的list列表
	 */
	public List selectMapList(String sql, String namedParam, Object[] value) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), Arrays.asList(value));
		return selectMapList(sql, paramMap);
	}

	/**
	 * 允许用户自己定义返回的行对象类型
	 * 
	 * @param sql
	 * @param paramMap
	 * @param mapper
	 * @return
	 */
	public List selectList(String sql, Map paramMap, RowMapper mapper) {
		getLogInfo(sql, paramMap);
		if (mapper == null) {
			mapper = new RowMapper() {
				public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
					return row2Map(rset);
				}
			};
		}
		log.info(sql);
		return getNamedParameterJdbcTemplate().query(sql, paramMap, mapper);
	}

	/**
	 * 查询单列的情况
	 * 
	 * @param sql
	 * @param paramMap
	 * @param elementType
	 * @return
	 */
	public List selectSingleColList(String sql, Map paramMap, Class elementType) {
		getLogInfo(sql, paramMap);
		return getNamedParameterJdbcTemplate().queryForList(sql, paramMap, elementType);
	}

	/**
	 * 根据动态条件查询返回一个断开连接的结果集,不占用数据库连接资源
	 * 
	 * @param sql
	 *            -原来的sql
	 * @param paramMap
	 *            -原有的条件
	 * @param queryObject
	 *            -动态拼装的查询条件对象
	 * @return ResultSetWrappingSqlRowSet
	 */
	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, Map paramMap, QueryObject queryObject) {
		Assert.hasText(sql);
		Assert.notNull(queryObject, "queryObject must not be null");
		Map queryMap = queryObject.getParamsMap();
		String querySql = queryObject.getQuerySql().toString();

		// 构造新的sql语句
		String newSql = QuerySqlParser.getQuerySql(sql, querySql);

		MapSqlParameterSource sqlParam = new MapSqlParameterSource(paramMap);
		// 增加动态命名条件
		sqlParam.addValues(queryMap);
		// 日志打印
		getLogInfo(newSql, sqlParam.getValues());
		return (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate().queryForRowSet(sql, sqlParam);
	}

	/**
	 * 查询返回一个断开连接的结果集,不占用数据库连接资源
	 * 
	 * @param sql
	 * @param paramMap
	 * @return ResultSetWrappingSqlRowSet
	 * 
	 *         <pre>
	 *         处理方式参看以下方法：
	 *         getXmlPage(ResultSetWrappingSqlRowSet sqlRowSet, int startIndex, int pageSize){}
	 * &lt;pre&gt;
	 * 
	 */
	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, Map paramMap) {
		Assert.hasText(sql);
		// 日志打印
		getLogInfo(sql, paramMap);
		MapSqlParameterSource sqlParam = new MapSqlParameterSource(paramMap);
		return (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate().queryForRowSet(sql, sqlParam);
	}

	/**
	 * 只有一个参数的情况
	 * 
	 * @param sql
	 *            ：查询的sql语句
	 * @param namedParam
	 *            ：命名参数
	 * @param value
	 *            ：参数值
	 * @return 返回一个断开连接的结果集,不占用数据库连接资源
	 * @see ResultSetWrappingSqlRowSet.java
	 */
	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, String namedParam, Object value) {
		Assert.hasText(sql);
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return queryForRowSet(sql, paramMap);
	}

	/**
	 * 根据动态条件查询返回一个断开连接的结果集,不占用数据库连接资源
	 * 
	 * @param sql
	 *            -原来的sql
	 * @param namedParam
	 *            ：命名参数
	 * @param value
	 *            ：参数值
	 * @param queryObject
	 *            -动态拼装的查询条件对象
	 * @return ResultSetWrappingSqlRowSet
	 */
	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, String namedParam, Object value,
	                    QueryObject queryObject) {
		Assert.hasText(sql);
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return queryForRowSet(sql, paramMap, queryObject);
	}

	/**
	 * 只有一个参数的情况
	 * 
	 * @param sql
	 *            ：查询的sql语句
	 * @param namedParam
	 *            ：命名参数
	 * @param values
	 *            ：参数数组，用在in的情况
	 * @return 返回一个断开连接的结果集,不占用数据库连接资源
	 * @see ResultSetWrappingSqlRowSet.java
	 */
	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, String namedParam, Object[] values) {
		Assert.hasText(sql);
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), Arrays.asList(values));
		return queryForRowSet(sql, paramMap);
	}

	/**
	 * 根据动态条件查询返回一个断开连接的结果集,不占用数据库连接资源
	 * 
	 * @param sql
	 *            -原来的sql
	 * @param namedParam
	 *            ：命名参数
	 * @param value
	 *            ：参数数组 用在in的情况
	 * @param queryObject
	 *            -动态拼装的查询条件对象
	 * @return ResultSetWrappingSqlRowSet
	 */
	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, String namedParam, Object[] values,
	                    QueryObject queryObject) {
		Assert.hasText(sql);
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), Arrays.asList(values));
		return queryForRowSet(sql, paramMap, queryObject);
	}

	/**
	 * 没有参数的情况
	 * 
	 * @param sql
	 *            ：查询的sql语句
	 * @param queryObject
	 *            -动态拼装的查询条件对象
	 * 
	 * @return 返回一个断开连接的结果集,不占用数据库连接资源
	 * @see ResultSetWrappingSqlRowSet.java
	 */
	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, QueryObject queryObject) {
		Assert.hasText(sql);
		Assert.notNull(queryObject, "queryObject must not be null");
		Map queryMap = queryObject.getParamsMap();
		String querySql = queryObject.getQuerySql().toString();

		// 构造新的sql语句
		String newSql = QuerySqlParser.getQuerySql(sql, querySql);
		Assert.hasText(newSql);
		MapSqlParameterSource sqlParam = new MapSqlParameterSource(queryMap);
		// 日志打印
		getLogInfo(newSql, queryMap);
		return (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate().queryForRowSet(sql, sqlParam);
	}

	/**
	 * 没有参数的情况
	 * 
	 * @param sql
	 *            ：查询的sql语句
	 * @return 返回一个断开连接的结果集,不占用数据库连接资源 see ResultSetWrappingSqlRowSet.java
	 */
	public ResultSetWrappingSqlRowSet queryForRowSet(String sql) {
		Assert.hasText(sql);
		return (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate().queryForRowSet(sql,
		                                                                                   new MapSqlParameterSource());
	}

	/**
	 * 分页查询<BR>
	 * 1、sql, pageNO, pageSize, mode, Map <BR>
	 * 2、总记录数totalCount的计算：()<BR>
	 * A)count(*)-- queryForInt <BR>
	 * B)滚动指针 -- <BR>
	 * 3、返回结果集SqlRowSet <BR>
	 * A)分不同数据库的情况：转换sql分页语句 <BR>
	 * B)<BR>
	 * 4、取结果集，封装成List(HashMap)<BR>
	 * 5、返回page
	 * 
	 */
	public Page pagedQuery(String sql, int pageNo, int pageSize) {
		return pagedQuery(sql, pageNo, pageSize, null, Constants.COUNT_MODE);
	}

	public Page pagedQuery(String sql, int pageNo, int pageSize, String namedParam, Object value) {
		HashMap paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return pagedQuery(sql, pageNo, pageSize, paramMap, Constants.COUNT_MODE);
	}

	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap) {
		return pagedQuery(sql, pageNo, pageSize, paramMap, Constants.COUNT_MODE);
	}
	/**
	 * 根据索引获取泛型列表T 即getGenericType(0);
	 * 
	 * @param index
	 * @return
	 */
	public Class getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index out of bounds(" + index + ")");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	/**
	 * 分页查询
	 */
	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap, int mode,RowMapper rowMapper) {
		Assert.hasText(sql);
		// 日志打印
		getLogInfo(sql, paramMap);

		return getPage(sql, new MapSqlParameterSource(paramMap), pageNo, pageSize, mode,rowMapper);
	}

	/**
	 * 动态拼装条件的分页查询
	 * 
	 * @param sql
	 * @param paramMap
	 * @param webParam
	 *            ：查询条件，从HTTP Request中生成，请参照<br>
	 * @see com.westsnow.base.jdbc.support.QueryWebUtils#generateQueryWebParameter
	 * @return 分页对象
	 */
	public Page pagedQuery(String sql, QueryWebParameter webParam) {
		return pagedQuery(sql, null, webParam,null);
	}
	
	public Map selectSingleMapByParam(String sql, QueryWebParameter webParam) {
		return this.selectSingleMapByParam(sql, null, webParam);
	}

	public Map selectSingleMapByParam(String sql, Map paramMap, QueryWebParameter webParam) {
		Assert.notNull(webParam, "QueryWebParameter must not be null");

		QueryParam queryParam = QueryWebUtils.generateQueryParam(webParam.getName(), webParam.getOperator(),
		                                                         webParam.getActualValues());

		QueryObject queryObject = queryParam.toQueryObject();
		// 增加排序条件
		queryObject.setOrderby(webParam.getSortfield(), webParam.getSorttype());
		Map queryMap = queryObject.getParamsMap();

		String orderSql = queryObject.getOrderbySql();

		String querySql = queryObject.getQuerySql().append(" ").append(orderSql).toString();

		// 构造新的sql语句
		String newSql = QuerySqlParser.getQuerySql(sql, querySql);

		// 日志打印
		// getLogInfo(newSql, sqlParam.getValues());
		if (Validator.isNotNull(paramMap)) {
			paramMap.putAll(queryMap);
			return this.selectSingleMap(newSql, paramMap);
		} else {
			return this.selectSingleMap(newSql, queryMap);
		}

	}

	/**
	 * 动态拼装条件的分页查询
	 * 
	 * @param sql
	 * @param paramMap
	 * @param webParam
	 *            ：查询条件，从HTTP Request中生成，请参照<br>
	 * @see com.westsnow.base.jdbc.support.QueryWebUtils#generateQueryWebParameter
	 * @return 分页对象
	 */
	public Page pagedQuery(String sql, Map paramMap, QueryWebParameter webParam,RowMapper mapper) {
		Assert.notNull(webParam, "QueryWebParameter must not be null");

		QueryParam queryParam = QueryWebUtils.generateQueryParam(webParam.getName(), webParam.getOperator(),
		                                                         webParam.getActualValues());

		QueryObject queryObject = queryParam.toQueryObject();
		// 增加排序条件
		queryObject.setOrderby(webParam.getSortfield(), webParam.getSorttype());

		return pagedQuery(sql, webParam.getPageNo(), webParam.getPageSize(), paramMap, queryObject,
		                  Constants.COUNT_MODE,mapper);
	}

	/**
	 * 动态拼装条件的分页查询，默认采用先查询总数，再获取数据的方式
	 * 
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param paramMap
	 * @param queryObject
	 * @return
	 */
	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap, QueryObject queryObject) {
		Assert.notNull(queryObject, "queryObject must not be null");
		return pagedQuery(sql, pageNo, pageSize, paramMap, queryObject, Constants.COUNT_MODE);
	}

	/**
	 * 
	 * 分页查询<BR>
	 * 1、sql, pageNO, pageSize, mode, Map <BR>
	 * 2、总记录数totalCount的计算：()<BR>
	 * A)count(*)-- queryForInt <BR>
	 * B)滚动指针 -- <BR>
	 * 3、返回结果集SqlRowSet <BR>
	 * A)分不同数据库的情况：转换sql分页语句 <BR>
	 * B)<BR>
	 * 4、取结果集，封装成List(HashMap)<BR>
	 * 5、返回page
	 * 
	 * @param sql
	 *            ：select a.* ,b.name,c.nid from a,b,c where a.bid = b.nid and b.cid = c.nid
	 * @param pageNo
	 * @param pageSize
	 * @param paramMap
	 * @param queryObject
	 *            动态条件
	 * @param gridkey
	 *            列表id
	 * @param mode
	 * <br/>
	 *            1) Contants.COUNT_MODE:采用count(*)查询出总记录数，再分不同数据库查询分页数据<br/>
	 *            2) Contants.SCROLL_MODE：采用滚动指针，断开连接后处理结果集<br/>
	 * @return Page
	 */
	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap, QueryObject queryObject, int mode,RowMapper mapper
	                    ) {
		Assert.hasText(sql);
		Assert.notNull(queryObject, "queryObject must not be null");
		Map queryMap = queryObject.getParamsMap();

		String orderSql = queryObject.getOrderbySql();

		String querySql = queryObject.getQuerySql().append(" ").append(orderSql).toString();

		// 构造新的sql语句
		String newSql = QuerySqlParser.getQuerySql(sql, querySql);

		MapSqlParameterSource sqlParam = new MapSqlParameterSource(paramMap);
		// 增加动态命名条件
		if (null != queryMap && queryMap.size() > 0) {
			sqlParam.addValues(queryMap);
		}
		// 日志打印
		getLogInfo(newSql, sqlParam.getValues());
		return getPage(newSql, sqlParam, pageNo, pageSize, mode , mapper);
	}

	private Page getPage(String newSql, MapSqlParameterSource sqlParam, int pageNo, int pageSize, int mode,RowMapper mapper) {
		NamedParameterJdbcTemplate jdbcTemplate = getNamedParameterJdbcTemplate();
		if (mode == Constants.COUNT_MODE) {
			// 这个计算支持 group
			String countQueryString = QuerySqlParser.getCountSql(newSql);
			getLogInfo(countQueryString, sqlParam.getValues());

			int totalCount = 0;
			// 判断是否有group by
			String tmpsql = countQueryString.toLowerCase().replaceAll("\\s+", " ");
			if (QuerySqlParser.isFilterGroupBy(tmpsql)) {
				List ls = jdbcTemplate.queryForList(countQueryString, sqlParam);
				if (ls != null) {
					totalCount = ls.size();
				}
			} else {
				totalCount = jdbcTemplate.queryForObject(countQueryString, sqlParam,int.class);
			}

			return getPageInstanceByCount(newSql, totalCount, pageNo, pageSize, sqlParam, jdbcTemplate,mapper);
		} else {
			return getPageInstanceByScroll(newSql, pageNo, pageSize, sqlParam, jdbcTemplate,mapper);
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param sql
	 *            ：查询的sql语句 <br/>
	 * @param pageNo
	 *            ：页号 <br/>
	 * @param pageSize
	 *            ：每页显示的数目 <br/>
	 * @param namedParam
	 *            ：命名参数 <br/>
	 * @param value
	 *            ：参数值 <br/>
	 * @param mode
	 * <br/>
	 *            1) Contants.COUNT_MODE:采用count(*)查询出总记录数，再分不同数据库查询分页数据<br/>
	 *            2) Contants.SCROLL_MODE：采用滚动指针，断开连接后处理结果集<br/>
	 * 
	 * @return 分页对象Page
	 */
	public Page pagedQuery(String sql, int pageNo, int pageSize, String namedParam, Object value, int mode) {
		Assert.hasText(sql);
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		getLogInfo(sql, paramMap);
		return pagedQuery(sql, pageNo, pageSize, paramMap, mode);
	}

	/**
	 * 分页查询--滚动分页
	 * 
	 * @param sql
	 *            ：查询的sql语句 <br/>
	 * @param pageNo
	 *            ：页号 <br/>
	 * @param pageSize
	 *            ：每页显示的数目 <br/>
	 * @param namedParam
	 *            ：命名参数 <br/>
	 * @param value
	 *            ：参数值 <br/>
	 * @param mode
	 * <br/>
	 *            1) Contants.COUNT_MODE:采用count(*)查询出总记录数，再分不同数据库查询分页数据<br/>
	 *            2) Contants.SCROLL_MODE：采用滚动指针，断开连接后处理结果集<br/>
	 * @return 分页对象Page
	 */
	public Page pagedQuery(String sql, int pageNo, int pageSize, int mode) {
		Assert.hasText(sql);
		getLogInfo(sql, null);
		return pagedQuery(sql, pageNo, pageSize, null, mode);
	}

	/**
	 * 内部方法，不建议外部调用；返回分页数据
	 * 
	 * @param sql
	 *            查询sql语句
	 * @param totalSize
	 *            总记录数
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页显示记录数
	 * @param MapSqlParameterSource
	 *            参数Map
	 * @param jdbcTemplate
	 *            命名参数模版
	 * @return 返回分页数据
	 */
	protected static Page getPageInstanceByCount(String sql, int totalSize, int pageNo, int pageSize,
	                    MapSqlParameterSource sqlParam, NamedParameterJdbcTemplate jdbcTemplate,RowMapper mapper) {
		if (totalSize < 1)
			return new Page();
		// 如果<0的话，则取出全部
		pageSize = pageSize <= 0 ? FrameProperites.PAGING_DEFAULT_PAGE_SIZE : pageSize;// 20
		if(FrameProperites.PAGING_PAGE_NUMBER_START_FROM_ZERO){
			pageNo = pageNo + 1;
		}
		pageNo = pageNo <= 0 ? 1 : pageNo;// 1
		int startIndex = (pageNo - 1) * pageSize;
		int endIndex = startIndex + pageSize;
		String pagingSql = SqlUtil.getLimitString(sql, startIndex, endIndex);

		
		List results  = null;
		if(mapper != null){//List<Model>
			results  = jdbcTemplate.query(pagingSql, sqlParam,mapper);
		}else{// List<Map>
			results = jdbcTemplate.queryForList(pagingSql, sqlParam);
		}
		return new Page(startIndex, totalSize, pageSize, results);
	}

	/**
	 * 内部方法，不建议外部调用；返回分页数据 采用滚动指针方式查询
	 * 
	 * @param sql
	 *            查询sql语句
	 * @param totalSize
	 *            总记录数
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页显示记录数
	 * @param MapSqlParameterSource
	 *            参数Map
	 * @param jdbcTemplate
	 *            命名参数模版
	 * @return 返回分页数据
	 */
	protected static Page getPageInstanceByScroll(String sql, int pageNo, int pageSize, MapSqlParameterSource sqlParam,
	                    NamedParameterJdbcTemplate jdbcTemplate,RowMapper mapper) {
		pageSize = pageSize <= 0 ? FrameProperites.PAGING_DEFAULT_PAGE_SIZE : pageSize;// 20
		if(FrameProperites.PAGING_PAGE_NUMBER_START_FROM_ZERO){
			pageNo = pageNo + 1;
		}
		pageNo = pageNo <= 0 ? 1 : pageNo;// 1
		int startIndex = (pageNo - 1) * pageSize;

		ResultSetWrappingSqlRowSet sqlRowSet = (ResultSetWrappingSqlRowSet) jdbcTemplate.queryForRowSet(sql, sqlParam);
		return getPageInstance(sqlRowSet, startIndex, pageSize,mapper);

	}

	/**
	 * 内部方法，不建议外部调用；返回分页数据 采用滚动指针方式查询
	 * 
	 * @param sqlRowSet
	 *            结果集
	 * @param startIndex
	 *            起始行
	 * @param pageSize
	 *            每页显示记录数
	 * @return 返回分页数据
	 */
	private static Page getPageInstance(ResultSetWrappingSqlRowSet sqlRowSet, int startIndex, int pageSize,RowMapper mapper) {
		int count = 0;
		sqlRowSet.last();
		int totalSize = sqlRowSet.getRow();
		if (totalSize < 1) {
			return new Page();
		}
		if (startIndex > totalSize) {
			return new Page(startIndex, totalSize, pageSize, new ArrayList());
		}
		sqlRowSet.first();
		sqlRowSet.relative(startIndex - 1);
		List list = new ArrayList();
		while (sqlRowSet.next()) {
			count++;
			if(mapper == null){
				list.add(row2Map(sqlRowSet.getResultSet()));
			}else{
				try{
					list.add(mapper.mapRow(sqlRowSet.getResultSet(), count));
				}catch(SQLException e){
					throw new JayFrameException(e);
				}
			}
			if (count == pageSize) {
				break;
			}
		}
		return new Page(startIndex, totalSize, pageSize, list);
	}

	/**
	 * 打印相应的日志信息
	 * 
	 * @param sql
	 * @param paramMap
	 *            ：命名参数值对
	 */
	protected void getLogInfo(String sql, Object param) {
		if(log.isDebugEnabled() == false && FrameProperites.JDBC_SHOW_SQL == false){
			return;
		}
		try {
			StringBuffer sbuf = new StringBuffer(" JDBC查询的sql语句是：").append(sql);
			if (null == param) {
				sbuf.append(" ; 参数为空!");
			} else if(param instanceof Map){
				Map paramMap = (Map)param;
				sbuf.append("\n 命名参数以及对应的数值:\n");
				Iterator keys = paramMap.keySet().iterator();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					sbuf.append("  {").append(key).append("} = ").append(paramMap.get(key)).append("\n");
				}
			}else if(param instanceof List){
				sbuf.append(param.toString());
			}else if(param.getClass() == String.class){
				sbuf.append(param);
			}else{
				sbuf.append("[未知数据结构]");
			}
			
			if(log.isDebugEnabled()){
				log.debug(sbuf);
			}else{
				System.out.println(sbuf);
			}
		} catch (Exception ex) {
			logger.error("日志打印出现错误：" + ex.getMessage());
		}
	}

	/**
	 * 
	 */
	public Map queryForMap(String sql, String namedParam, Object value, String[] keyColumns, String column) {
		HashMap paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return this.queryForMap(sql, paramMap, keyColumns, column);
	}

	/**
	 * 
	 */
	public Map queryForMap(String sql, Map paramMap, String[] keyColumns, String column) {
		Assert.hasText(sql);
		Assert.notEmpty(keyColumns, "Must set the keyColumn");
		// 日志打印
		getLogInfo(sql, paramMap);
		MapSqlParameterSource sqlParam = new MapSqlParameterSource(paramMap);
		ResultSetWrappingSqlRowSet sqlRowSet = (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate()
		                                                                                                   .queryForRowSet(
		                                                                                                                   sql,
		                                                                                                                   sqlParam);
		return getMapRow(sqlRowSet, keyColumns, column);
	}

	public Map queryForMapMap(String sql, Map paramMap, String[] keyColumns) {
		Assert.hasText(sql);
		Assert.notEmpty(keyColumns, "Must set the keyColumn");
		// 日志打印
		getLogInfo(sql, paramMap);
		MapSqlParameterSource sqlParam = new MapSqlParameterSource(paramMap);
		ResultSetWrappingSqlRowSet sqlRowSet = (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate()
		                                                                                                   .queryForRowSet(
		                                                                                                                   sql,
		                                                                                                                   sqlParam);
		return getMapRow(sqlRowSet, keyColumns);
	}

	public Map queryForMapMap(String sql, String namedParam, Object value, String[] keyColumns) {
		HashMap paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return this.queryForMapMap(sql, paramMap, keyColumns);
	}

	private static Map getMapRow(ResultSetWrappingSqlRowSet sqlRowSet, String[] keyColumns) {
		HashMap resultMap = new HashMap();
		try {
			sqlRowSet.last();
			int totalSize = sqlRowSet.getRow();
			if (totalSize < 1) {
				return resultMap;
			}
			sqlRowSet.beforeFirst();
			while (sqlRowSet.next()) {
				java.sql.ResultSet rset = sqlRowSet.getResultSet();
				String key = "";

				for (int i = 0; i < keyColumns.length; i++) {
					key += rset.getString(keyColumns[i]);
				}
				resultMap.put(key, row2Map(rset));
			}
			return resultMap;
		} catch (SQLException e) {
			log.error("滚动查询分页数据出现错误：" + e.getMessage(), e);
			return null;
		}
	}

	private static Map getMapRow(ResultSetWrappingSqlRowSet sqlRowSet, String[] keyColumns, String column) {
		Map resultMap = new HashMap();
		try {
			sqlRowSet.last();
			int totalSize = sqlRowSet.getRow();
			if (totalSize < 1) {
				return resultMap;
			}
			sqlRowSet.beforeFirst();
			while (sqlRowSet.next()) {
				java.sql.ResultSet rset = sqlRowSet.getResultSet();
				String key = "";
				for (int i = 0; i < keyColumns.length; i++) {
					key += rset.getString(keyColumns[i]);
				}

				resultMap.put(key, rset.getString(column));
			}
			return resultMap;
		} catch (SQLException e) {
			log.error("滚动查询分页数据出现错误：" + e.getMessage(), e);
			return null;
		}
	}

	public Map queryForMapMap(String sql, Map paramMap, String[] keyColumns, boolean isFirst) {
		Assert.hasText(sql);
		Assert.notEmpty(keyColumns, "Must set the keyColumn");
		// 日志打印
		getLogInfo(sql, paramMap);
		MapSqlParameterSource sqlParam = new MapSqlParameterSource(paramMap);
		ResultSetWrappingSqlRowSet sqlRowSet = (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate()
		                                                                                                   .queryForRowSet(
		                                                                                                                   sql,
		                                                                                                                   sqlParam);

		return getMapRow(sqlRowSet, keyColumns, isFirst);
	}

	public Map queryForMapMap(String sql, String namedParam, Object value, String[] keyColumns, boolean isFirst) {
		HashMap paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		getLogInfo(sql, paramMap);
		return this.queryForMapMap(sql, paramMap, keyColumns);
	}

	private static Map getMapRow(ResultSetWrappingSqlRowSet sqlRowSet, String[] keyColumns, boolean isFirst) {
		Map resultMap = new HashMap();
		try {
			sqlRowSet.last();
			int totalSize = sqlRowSet.getRow();
			if (totalSize < 1) {
				return resultMap;
			}
			sqlRowSet.beforeFirst();
			while (sqlRowSet.next()) {
				java.sql.ResultSet rset = sqlRowSet.getResultSet();
				String key = "";

				for (int i = 0; i < keyColumns.length; i++) {
					key += rset.getString(keyColumns[i]);
				}

				if (isFirst) {
					if (resultMap.containsKey(key)) {
						continue;
					} else {
						resultMap.put(key, row2Map(rset));
					}
				} else {
					resultMap.put(key, row2Map(rset));
				}
			}
			return resultMap;
		} catch (SQLException e) {
			log.error("滚动查询分页数据出现错误：" + e.getMessage(), e);
			return null;
		}
	}

	public Map queryForListMap(String sql, Map paramMap, String[] keyColumns) {
		Assert.hasText(sql);
		Assert.notEmpty(keyColumns, "Must set the keyColumn");
		// 日志打印
		getLogInfo(sql, paramMap);
		MapSqlParameterSource sqlParam = new MapSqlParameterSource(paramMap);
		ResultSetWrappingSqlRowSet sqlRowSet = (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate()
		                                                                                                   .queryForRowSet(
		                                                                                                                   sql,
		                                                                                                                   sqlParam);
		return getListMapRow(sqlRowSet, keyColumns);
	}

	public Map queryForListMap(String sql, String namedParam, Object value, String[] keyColumns) {
		HashMap paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return queryForListMap(sql, paramMap, keyColumns);
	}

	private static Map getListMapRow(ResultSetWrappingSqlRowSet sqlRowSet, String[] keyColumns) {
		// <String, List<Map>>
		Map resultMap = new HashMap();
		try {
			sqlRowSet.last();
			int totalSize = sqlRowSet.getRow();
			if (totalSize < 1) {
				return resultMap;
			}
			sqlRowSet.beforeFirst();
			while (sqlRowSet.next()) {
				java.sql.ResultSet rset = sqlRowSet.getResultSet();
				String key = "";

				for (int i = 0; i < keyColumns.length; i++) {
					key += rset.getString(keyColumns[i]);
				}

				if (resultMap.containsKey(key)) {
					List list = (List) resultMap.get(key);
					list.add(row2Map(rset));
				} else {
					List list = new LinkedList();
					list.add(row2Map(rset));
					resultMap.put(key, list);
				}
			}
			return resultMap;
		} catch (SQLException e) {
			log.error("滚动查询分页数据出现错误：" + e.getMessage(), e);
			return null;
		}
	}

	public Map queryForMapMap(String sql, Map paramMap, String[] keyColumns1, String[] keyColumns2) {
		Assert.hasText(sql);
		Assert.notEmpty(keyColumns1, "Must set the keyColumn1");
		Assert.notEmpty(keyColumns2, "Must set the keyColumn2");
		// 日志打印
		getLogInfo(sql, paramMap);
		MapSqlParameterSource sqlParam = new MapSqlParameterSource(paramMap);
		ResultSetWrappingSqlRowSet sqlRowSet = (ResultSetWrappingSqlRowSet) getNamedParameterJdbcTemplate()
		                                                                                                   .queryForRowSet(
		                                                                                                                   sql,
		                                                                                                                   sqlParam);
		return getMapMapRow(sqlRowSet, keyColumns1, keyColumns2);
	}

	public Map queryForMapMap(String sql, String namedParam, Object value, String[] keyColumns1, String[] keyColumns2) {
		Map paramMap = new HashMap(1);
		paramMap.put(StringUtil.strnull(namedParam), value);
		return queryForMapMap(sql, paramMap, keyColumns1, keyColumns2);
	}

	private static Map getMapMapRow(ResultSetWrappingSqlRowSet sqlRowSet, String[] keyColumns1, String[] keyColumns2) {
		// <String, Map<String, Map>>
		Map resultMap = new HashMap();
		try {
			sqlRowSet.last();
			int totalSize = sqlRowSet.getRow();
			if (totalSize < 1) {
				return resultMap;
			}
			sqlRowSet.beforeFirst();
			while (sqlRowSet.next()) {
				java.sql.ResultSet set = sqlRowSet.getResultSet();
				String key1 = "";
				for (int i = 0; i < keyColumns1.length; i++) {
					key1 += set.getString(keyColumns1[i]);
				}
				String key2 = "";

				for (int i = 0; i < keyColumns2.length; i++) {
					key2 += set.getString(keyColumns2[i]);
				}
				if (resultMap.containsKey(key1)) {
					Map map = (Map) resultMap.get(key1);
					map.put(key2, row2Map(set));
				} else {
					Map map = new HashMap();
					map.put(key2, row2Map(set));
					resultMap.put(key1, map);
				}
			}
			return resultMap;
		} catch (SQLException e) {
			log.error("滚动查询分页数据出现错误：" + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 获取树的下一个编码
	 * 
	 * @param tablename
	 *            ：树形表的名称,如Organization_
	 * @param treecodename
	 *            : 树形表的树形编码字段名称,如treecode
	 * @param pkcolname
	 *            ：树形表的主键字段名称,如organizationid
	 * @param parentcolname
	 *            ：上级关联字段ID，如parentOrganizationid
	 * @param parentidvalue
	 *            :上级节点id的值
	 * @return
	 * @throws Exception
	 */
	public String getNextTreeCode(String tablename, String treecodename, String pkcolname, String parentcolname,
	                    String parentidvalue, String extcondition) {
		String returnStr = "";
		String maxccode = null;

		String sql = StringUtil.formate("select max({0}) from {1} where {2} ", new String[] { treecodename, tablename,
		                                                                                     parentcolname });
		if (StringUtil.isNotEmpty(parentidvalue)) {
			maxccode = this.queryForString(sql + " =:" + parentcolname + " " + extcondition, parentcolname,
			                               parentidvalue);
		} else {
			maxccode = this.queryForString(sql + " is null " + extcondition);
		}
		
		if(maxccode.length()<2){
			sql = StringUtil.formate("select max(s.{0}) from (select t.* from {1} t where length(t.{2})>2 ) s where s.{3}",  new String[] { treecodename, tablename,
		                                                                                                                                      treecodename,parentcolname });
			maxccode = this.queryForString(sql + " =:" + parentcolname + " " + extcondition, parentcolname,
		                               parentidvalue);
			
		}
		

		if (StringUtil.isNotEmpty(maxccode) && maxccode.length()>=2) {
			String str = maxccode.substring(maxccode.length() - 2, maxccode.length());// 取编号的最后两位
			returnStr = maxccode.substring(0, maxccode.length() - 2)
			    + StringUtil.intToStr(StringUtil.toInt(str) + 1, 2);
		}else if (StringUtil.isNotEmpty(parentidvalue)) {
			String selparent = StringUtil.formate("select {0} from {1} where {2} =:{2}  " + extcondition,
			                                      new String[] { treecodename, tablename, pkcolname });
			returnStr = StringUtil.strnull(this.queryForString(selparent, pkcolname, parentidvalue)) + "01";
		} else {
			returnStr = "01";
		}
		return returnStr;
	}

	/**
	 * 删除节点后调用该函数
	 * 
	 * @param tablename
	 *            ：树形表的名称,如Organization_
	 * @param treecodename
	 *            : 树形表的树形编码字段名称,如treecode
	 * @param pkcolname
	 *            ：树形表的主键字段名称,如organizationid
	 * @param parentcolname
	 *            ：上级关联字段ID，如parentOrganizationid
	 * @param treelevelcol
	 *            ：树形级别字段名，如treelevel
	 * @param parentidvalue
	 *            :上级节点id的值
	 * @param treecode
	 *            : 树形编码值
	 * @param treelevelval
	 *            ： 树形级别值
	 */
	public void updateTreecode(String tablename, String treecodename, String pkcolname, String parentcolname,
	                    String treelevelcol, String parentidvalue, String treecode, Integer treelevelval) {
		String parentcode = "";
		int seq = 0;
		if (StringUtil.isNotEmpty(parentidvalue)) {
			String sql = StringUtil.formate("select {0} from {1} where {2} =:{2} ",
			                                new String[] { treecodename, tablename, pkcolname });
			parentcode = StringUtil.strnull(this.queryForString(sql, pkcolname, parentidvalue));
			// {0}tablename,{1}level,{2},parent,{3}treecode
			String sqlformate = "select count(*) from {0} where {1} =:{1} and {2} = :{2} and {3} < :{3}";

			String sql2 = StringUtil.formate(sqlformate, new String[] { tablename, treelevelcol, parentcolname,
			                                                           treecodename });
			Map paramMap = new HashMap();
			paramMap.put(treelevelcol, treelevelval);
			paramMap.put(parentcolname, parentidvalue);
			paramMap.put(treecodename, treecode);
			seq = this.queryForInt(sql2, paramMap);
		} else {
			String sqlformate = "select count(*) from {0} where {1} =:{1} and {2} is null and {3} < :{3}";

			String sql2 = StringUtil.formate(sqlformate, new String[] { tablename, treelevelcol, parentcolname,
			                                                           treecodename });
			Map paramMap = new HashMap();
			paramMap.put(treelevelcol, treelevelval);
			paramMap.put(treecodename, treecode);
			seq = this.queryForInt(sql2, paramMap);
		}
		// select nid,csyscode,nlevelno from ca_company where nlevelno
		// =:nLevelno and csyscode > :csyscode and csyscode like :likecsyscode
		// order by csyscode
		String sql = "select {0} as pkcol,{1} as treecode,{2} as treelevel from {3} where {2} =:{2} and {1} > :{1} and {1} like :liketreecode order by {1}";

		String sql2 = StringUtil.formate(sql, new String[] { pkcolname, treecodename, treelevelcol, tablename });

		Map paramMap = new HashMap();
		paramMap.put(treelevelcol, treelevelval);
		paramMap.put(treecodename, treecode);
		paramMap.put("liketreecode", parentcode + "%");

		List ls = this.selectMapList(sql2, paramMap);
		if (ls != null && ls.size() > 0) {
			// "update ca_company set csyscode = :csyscode where nid = :nid";
			String updatecode = StringUtil.formate("update {0} set {1} = :{1} where {2} = :{2}",
			                                       new String[] { tablename, treecodename, pkcolname });

			for (int i = 0, len = ls.size(); i < len; i++) {
				Map tmp = (Map) ls.get(i);
				seq = seq + 1;
				String csyscodenew = parentcode.concat(StringUtil.appendZeroBefore(2, "" + seq));
				String csyscodeold = StringUtil.strnull(tmp.get("treecode"));
				String nlevelno = StringUtil.strnull(tmp.get("treelevel"));
				Map param = new HashMap();
				param.put(treecodename, csyscodenew);
				param.put(pkcolname, StringUtil.strnull(tmp.get("pkcol")));
				this.update(updatecode, param);
				this.updateCode(tablename, treecodename, treelevelcol, csyscodenew, csyscodeold,
				                StringUtil.toInt(nlevelno), 0);
			}
		}
	}

	/**
	 * 更新下级子节点 所有编号，适用于拖放到不同级别
	 * 
	 * @param tablename
	 *            ：树形表的名称,如Organization_
	 * @param treecodename
	 *            : 树形表的树形编码字段名称,如treecode
	 * @param treelevelcol
	 *            ：树形级别字段名，如treelevel
	 * @param asClassCodeNew
	 *            ---修改后节点classcode
	 * @param asClassCodeOld
	 *            ---原有的classcode
	 * @param anClasslevel
	 *            ---节点的当前级别
	 * @param anClasslevelDiff
	 *            ---节点前后的级别差
	 */
	public void updateCode(String tablename, String treecodename, String treelevelcol, String asClassCodeNew,
	                    String csyscodeold, int anClasslevel, int anClasslevelDiff) {
		int li_pos = (anClasslevel * 2) + 1;

		StringBuffer sqlformate = new StringBuffer(64);
		sqlformate.append(" update {0} set {1} = ");
		// 格式化字符串，需要对已有的字符特殊处理时，需要用俩单引号括起来 ''特殊字符''
		sqlformate.append(SqlUtil.linkColumn("''" + asClassCodeNew + "''", SqlUtil.substr("{1}", li_pos)));
		sqlformate.append(", {2} = {2} + ").append(anClasslevelDiff);
		sqlformate.append("  where {1} like :likecsyscode and {1} <> :csyscodeold");

		String updatesql = StringUtil.formate(sqlformate.toString(), new String[] { tablename, treecodename,
		                                                                           treelevelcol });
		// treecodename
		Map paramMap = new HashMap();
		paramMap.put("likecsyscode", csyscodeold + "%");
		paramMap.put("csyscodeold", csyscodeold);

		this.update(updatesql, paramMap);
	}



	/**
	 * 更新下级子节点 所有编号，适用于拖放到不同级别
	 * 
	 * @param tablename
	 *            ：树形表的名称,如Organization_
	 * @param treecodename
	 *            : 树形表的树形编码字段名称,如treecode
	 * @param treelevelcol
	 *            ：树形级别字段名，如treelevel
	 * @param asClassCodeNew
	 *            ---修改后节点classcode
	 * @param asClassCodeOld
	 *            ---原有的classcode
	 * @param anClasslevel
	 *            ---节点的当前级别
	 * @param anClasslevelDiff
	 *            ---节点前后的级别差
	 * @param excondtion
	 *            ---额外条件，如果没有外键，可以为""
	 */
	public void updateCode(String tablename, String treecodename, String treelevelcol, String asClassCodeNew,
	                    String csyscodeold, int anClasslevel, int anClasslevelDiff, String excondtion) {
		int li_pos = (anClasslevel * 2) + 1;

		StringBuffer sqlformate = new StringBuffer(64);
		sqlformate.append(" update {0} set {1} = ");
		// 格式化字符串，需要对已有的字符特殊处理时，需要用俩单引号括起来 ''特殊字符''
		sqlformate.append(SqlUtil.linkColumn("''" + asClassCodeNew + "''", SqlUtil.substr("{1}", li_pos)));
		sqlformate.append(", {2} = {2} + ").append(anClasslevelDiff);
		sqlformate.append("  where {1} like :likecsyscode and {1} <> :csyscodeold ");
		sqlformate.append(excondtion);

		String updatesql = StringUtil.formate(sqlformate.toString(), new String[] { tablename, treecodename,
		                                                                           treelevelcol });
		// treecodename
		Map paramMap = new HashMap();
		paramMap.put("likecsyscode", csyscodeold + "%");
		paramMap.put("csyscodeold", csyscodeold);

		this.update(updatesql, paramMap);
	}

	public long getSequence(String sequence) {
		String sqlString = "SELECT " + sequence + ".NEXTVAL   from   dual";
		return this.queryForInt(sqlString);
	}

	
	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap,
			int mode) {
		return pagedQuery(sql,pageNo,pageSize,paramMap,mode,null);
	}


	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap,
			QueryObject queryObject, int mode) {
		return pagedQuery(sql,pageNo,pageSize,paramMap,queryObject,mode,null);
	}

	public Array  queryForArray(String sql) {
		return getNamedParameterJdbcTemplate().queryForObject(sql, new MapSqlParameterSource(null),Array.class);
	}

	public Page pagedQuery(String sql, Map paramMap, QueryWebParameter webParam) {
		return pagedQuery(sql, paramMap, webParam,null);
	}

}
