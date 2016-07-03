package org.jay.frame.jdbc;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.support.QueryWebParameter;
import org.springframework.jdbc.core.RowMapper;

public interface JdbcUtil<T> {
	
	public T get(Serializable id);
	/**
	 * 删除一条数据
	 * @param model
	 * @return
	 */
	public int delete(T model);
	public int delete(Serializable id);
	/**
	 * 逻辑删除，不过当前类必须继承于BaseModel
	 * @param id
	 * @return
	 */
	public int fakeDelete(T t);
	public int fakeDelete(Serializable id);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deletes(long... ids);
	public int deletes(String ids);
	
	/**
	 * 逻辑批量删除
	 * @param ids
	 * @return
	 */
	public int fakeDeletes(long... ids);
	public int fakeDeletes(String ids);
	
	public T save(T model);
	/*
	 * 提供RowMapper回调,根据不同需求修改/封装 返回数据
	 */
	public List<T> query2Model(String sql,Map paramMap);
	public List<T> query2Model(T model);
	public List<T> query2Obj(String sql,Map paramMap,RowMapper mapper);
	
	public T query21Model(String sql,Map paramMap);
	public T query21Model(T model);
	public T query21Obj(String sql,Map paramMap,RowMapper mapper);
	
	
	public List<T> query2Model(String sql);
	public String getTableName();
	public String getTableName(Class modelClass);
	
	/**
	 *  结果集以List<Map>的结构存储
	 *  DB字段转换成java类属性作为key(按驼峰命名规则转换)
	 * 
	 * @param sql
	 * 			执行的sql语句
	 * @param paramMap
	 * 			自定义sql参数
	 * @param webParam
	 * 			分页参数
	 * @return
	 */
	public Page<Map> page2CamelMap(String sql,Map paramMap,QueryWebParameter webParam);
	public Page<Map> page2CamelMap(String sql,QueryWebParameter webParam);
	public Page<Map> page2CamelMap(String sql,Map paramMap);
	public Page<Map> page2CamelMap(String sql);
	
	/**
	 * 将结果集以model对象的形式进行存储
	 * 若只是为了前端展示，建议使用page2CamelMap方法
	 * @param sql
	 * 			执行的sql语句
	 * @param paramMap
	 * 			自定义sql参数
	 * @param webParam
	 * 			分页参数
	 * @return
	 */
	public Page<T> paged2Obj(String sql, Map paramMap,QueryWebParameter webParam);
	public Page<T> paged2Obj(String sql,QueryWebParameter webParam);
	public Page<T> paged2Obj(String sql, Map paramMap);
	public Page<T> paged2Obj(String sql);
	
	/**
	 *  查询只返回一条记录，以map的结构存储
	 *  key按驼峰命名规则转换
	 *  例如：column_name转成columnName
	 * @param sql
	 * @param paramMap
	 *				参数 				
	 * @param prefix
	 * 				key前缀
	 * @return
	 */
	public Map selectSingleCamelMap(String sql, Map paramMap,final String prefix);
	public Map selectSingleCamelMap(String sql, Map paramMap);
	public Map selectSingleCamelMap(String sql);
	/**
	 *  查询只返回多条记录，以map的结构存储
	 *  key按驼峰命名规则转换
	 *  例如：column_name转成columnName
	 * @param sql
	 * @param paramMap
	 * 			参数 
	 * @param prefix
	 * 			key前缀 
	 * @return
	 */
	public List selectCamelListMap(String sql, Map paramMap,final String prefix);
	public List selectCamelListMap(String sql, Map paramMap);
	public List selectCamelListMap(String sql);
	/**
	 * 批量插入
	 */
	public void batchInsert(List<T> data);
}
