package org.jay.frame.jdbc;

import java.sql.Array;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.support.QueryObject;
import org.jay.frame.jdbc.support.QueryWebParameter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;


public interface JdbcDAO {

	public int[] batchUpdate(String[] sql);

	public int[] batchUpdate(String sql, final List dataSet);

	public void execute(String sql);
	
	public int update(String sql);

	public int update(String sql, String namedParam, Object value);

	public int update(String sql, Map paramMap);

	public int queryForInt(String sql, String namedParam, Object value);

	public int queryForInt(String sql, Map paramMap);

	public int queryForInt(String sql);

	public long queryForLong(String sql, String namedParam, Object value);

	public long queryForLong(String sql, Map paramMap);

	public long queryForLong(String sql);
	
	public Array  queryForArray(String sql);

	public String queryForString(String sql, Map paramMap);

	public String queryForString(String sql);

	public String queryForString(String sql, String namedParam, Object value);

	public Map selectSingleMap(String sql, Map paramMap);

	public Map selectSingleMap(String sql);

	public Map selectSingleMap(String sql, String namedParam, Object value);

	public Map selectSingleMap(String sql, String namedParam, Object[] value);

	public List selectMapList(String sql, Map paramMap);

	public List selectMapList(String sql);

	public Map selectSingleMapByParam(String sql, Map paramMap, QueryWebParameter webParam);

	public Map selectSingleMapByParam(String sql, QueryWebParameter webParam);
	
	public List selectMapList(String sql, String namedParam, Object value);

	public List selectMapList(String sql, String namedParam, Object[] value);

	public List selectList(String sql, Map paramMap, RowMapper mapper);

	public List selectSingleColList(String sql, Map paramMap, Class elementType);

	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, Map paramMap);

	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, String namedParam, Object value);

	public ResultSetWrappingSqlRowSet queryForRowSet(String sql);

	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, Map paramMap, QueryObject queryObject);

	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, String namedParam, Object value,
	                    QueryObject queryObject);

	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, String namedParam, Object[] values);

	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, String namedParam, Object[] values,
	                    QueryObject queryObject);

	public ResultSetWrappingSqlRowSet queryForRowSet(String sql, QueryObject queryObject);

	public Page pagedQuery(String sql, int pageNo, int pageSize);

	public Page pagedQuery(String sql, int pageNo, int pageSize, String namedParam, Object value);

	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap);

	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap, int mode);

	public Page pagedQuery(String sql, int pageNo, int pageSize, String namedParam, Object value, int mode);

	public Page pagedQuery(String sql, QueryWebParameter webParam);

	public Page pagedQuery(String sql, Map paramMap, QueryWebParameter webParam);

	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap, QueryObject queryObject);

	public Page pagedQuery(String sql, int pageNo, int pageSize, Map paramMap, QueryObject queryObject, int mode);

	public Page pagedQuery(String sql, int pageNo, int pageSize, int mode);

	public Map queryForMapMap(String sql, String namedParam, Object value, String[] keyColumns);

	public Map queryForMapMap(String sql, Map paramMap, String[] keyColumns1, String[] keyColumns2);

	public Map queryForMapMap(String sql, String namedParam, Object value, String[] keyColumns, String[] keyColumns2);

	public Map queryForMapMap(String sql, Map paramMap, String[] keyColumns);

	public Map queryForListMap(String sql, Map paramMap, String[] keyColumns);

	public Map queryForListMap(String sql, String namedParam, Object value, String[] keyColumns);

	public Map queryForMapMap(String sql, String namedParam, Object value, String[] keyColumns, boolean isFirst);

	public Map queryForMapMap(String sql, Map paramMap, String[] keyColumns, boolean isFirst);

	public Map queryForMap(String sql, String namedParam, Object value, String[] keyColumns, String column);

	public Map queryForMap(String sql, Map paramMap, String[] keyColumns, String column);

	public String getNextTreeCode(String tablename, String treecodename, String pkcolname, String parentcolname,
	                    String parentidvalue, String extcondition);

	public void updateTreecode(String tablename, String treecodename, String pkcolname, String parentcolname,
	                    String treelevelcol, String parentidvalue, String treecode, Integer treelevelval);


	/*
	 * 获取sequence值
	 * 
	 * @param sequence
	 * 
	 * @return
	 */
	public long getSequence(String sequence);

}