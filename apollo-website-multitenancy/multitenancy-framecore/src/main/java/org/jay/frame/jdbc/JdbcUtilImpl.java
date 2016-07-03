package org.jay.frame.jdbc;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jay.frame.FrameProperites;
import org.jay.frame.exception.GenericException;
import org.jay.frame.exception.Jay;
import org.jay.frame.exception.JayFrameException;
import org.jay.frame.jdbc.mapper.CamelRowMapper;
import org.jay.frame.jdbc.mapper.ModelRowMapper;
import org.jay.frame.jdbc.model.BaseModel;
import org.jay.frame.jdbc.model.User;
import org.jay.frame.jdbc.support.QueryWebParameter;
import org.jay.frame.jdbc.support.QueryWebUtils;
import org.jay.frame.util.ActionUtil;
import org.jay.frame.util.JsonUtil;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.SysUtil;
import org.jay.frame.util.Validator;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

@SuppressWarnings("unchecked")
public class JdbcUtilImpl<T> extends JdbcDAOImpl implements JdbcUtil<T> {
	/**
	 * key: model的全名
	 * value:model对应的RowMapper
	 */
	private static final Map<String,ModelRowMapper> MODEL_MAPPER_MAP = new HashMap<String,ModelRowMapper>(); 
	
	Logger LOG = Logger.getLogger(this.getClass());
	
	/**
	 * 单例模式获取ModelRowMapper
	 * @return
	 */
	public ModelRowMapper getModelRowMapper(){
		Class model = this.getGenericType(0);
		String key = model.getName();
		ModelRowMapper rowMapper = MODEL_MAPPER_MAP.get(key);
		if(rowMapper == null){
			rowMapper = new ModelRowMapper(model);
			MODEL_MAPPER_MAP.put(key, rowMapper);
		}
		return rowMapper;
	}
	

	public int update(T t) {
		Jay.isNotNull(t);
		String sql = null;
		JdbcModel jdbcModel = this.get();
		Map paramMap = null;
		try {
			//this.checkModel(t, false);
			if(t instanceof BaseModel){
				BaseModel b =  (BaseModel)t;
				b.setModifyDatetime(new Date());
				b.setModifyUserId(this.getSysUserId());
			}
			MapParameter sqlObj = SqlMaker.getUpdateSql(jdbcModel);
			sql = sqlObj.getSql();
			paramMap = sqlObj.toParameters(t);
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof GenericException){
				throw (GenericException)e;
			}else{
				throw new JayFrameException(JayFrameException.UNKNOW,e.getMessage());
			}
		}
		super.getLogInfo(sql,paramMap);
		return super.getNamedParameterJdbcTemplate().update(sql, paramMap);
	}
	
	/**
	 * 将执行的sql语句刷出到控制台
	 * @param sql
	 * @param param
	 */
	private void printSql(String sql,Object param){
		if(FrameProperites.JDBC_SHOW_SQL == false){
			return;
		}
		String paramDesc = null;
		if(param == null){
			paramDesc = "null";
		}else if(param instanceof List){
			paramDesc = param.toString();
		}else if(param instanceof Map){
			paramDesc = param.toString();
		}else{
			paramDesc = "[未知数据结构]";
		}
		System.out.println("执行sql语句：" + sql);
		System.out.println("参数列表：" + paramDesc);
	}
	
	public T insert(T t){
		Jay.isNotNull(t);
		final String sql;
		try {
			if(t instanceof BaseModel){
				BaseModel b =  (BaseModel)t;
				b.setCreateDatetime(new Date());
				b.setFlagActive(BaseModel.Flag_True);
				b.setCreateUserId(this.getSysUserId());
			}
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			final JdbcModel model = get();
			ListParameter sqlObj = SqlMaker.getAddSql(model);
			sql = sqlObj.getSql();
			final List paramList = sqlObj.toParameters(t);
			super.getLogInfo(sql,paramList);
			
			super.getJdbcOperations().update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = null;
					if(FrameProperites.DB_TYPE == DBType.POSTGRESQL){
						final String primaryColumn = model.getPrimaryCol().getColumnName();
						ps = conn.prepareStatement(sql,new String[]{primaryColumn});
					}else{
						ps = conn.prepareStatement(sql);
					}
					
					for (int i = 0; i < paramList.size(); i++) {
						Object val = paramList.get(i);
						setSqlParameter(ps ,(i+1),val);
					}
					return ps;
				}
			}, keyHolder);
			this.getPrimaryCol().setValue(t, keyHolder.getKey().longValue());
		}catch (Exception e) {
			e.printStackTrace();
			if(e instanceof GenericException){
				throw (GenericException)e;
			}else{
				throw new JayFrameException(JayFrameException.UNKNOW,e.getMessage());	
			}
		}
		return t;
	}
	
	
	public int fakeDelete(T t){
		Assert.notNull(t,  "["+t.getClass()+ "] model is null,pls check before delete.");
		JdbcColumn col = this.getPrimaryCol();
		try {
			Object v = col.getField().get(t);
			return fakeDelete(StringUtil.trim2Null(v));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return 0;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int fakeDelete(Serializable id){
		if(this.isBaseModel()){
			throw new JayFrameException(JayFrameException.NOT_BASE_MODEL);
		}
		Assert.notNull(id,  "id is null,pls check before delete.");
		JdbcModel model = this.get();
		String tbName = model.getTableName();
		JdbcColumn col = this.getPrimaryCol();
		StringBuffer sql  = new StringBuffer("update " + tbName + " set flag_active = " +BaseModel.Flag_False+ " where " + col.getColumnName()
				+ " = :id ");

		Map paramMap = MixUtil.newHashMap("id", id);
		String executeSql = sql.toString();
		super.getLogInfo(executeSql, paramMap);
		return super.update(executeSql,paramMap);
	}
	
	/**
	 * 删除单条记录
	 */
	public int delete(T t) {
		Assert.notNull(t,  "["+t.getClass()+ "] model is null,pls check before delete.");
		JdbcColumn col = this.getPrimaryCol();
		try {
			Object v = col.getField().get(t);
			return delete(StringUtil.trim2Null(v));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return 0;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 根据ID删除单条记录
	 */
	public int delete(Serializable id) {
		Assert.notNull(id,  "id is null,pls check before delete.");
		JdbcModel model = this.get();
		String tbName = model.getTableName();
		JdbcColumn col = this.getPrimaryCol();
		StringBuffer sql  = new StringBuffer("delete from " + tbName + " where " + col.getColumnName()
				+ " = :id ");
		if(this.isBaseModel()){
			sql.append(" and FLAG_ACTIVE < "+BaseModel.Flag_SYS) ;
		}
		Map paramMap = MixUtil.newHashMap("id", id);
		String executeSql = sql.toString();
		super.getLogInfo(executeSql, paramMap);
		return super.update(executeSql,paramMap);
	}
	
	/**
	 * 物理删除数据
	 * @param ids
	 * @return
	 */
	public int deletes(long... ids){
		if(ids.length == 0){
			return 0;
		}
		JdbcModel model = this.get();
		String tbName = model.getTableName();
		JdbcColumn col = this.getPrimaryCol();
		String primaryColName = col.getColumnName();
		StringBuffer sql  = new StringBuffer("delete from " + tbName + " WHERE ");
		//系统数据不能删除
		if(this.isBaseModel()){
			sql.append(" FLAG_ACTIVE < "+BaseModel.Flag_SYS + " " );
		}
		sql.append(" ( " );
		for (int i = 0; i < ids.length; i++) {
			if(i != 0){
				sql.append(" OR ");
			}
			sql.append(" " + primaryColName + " = " + ids[i] + " ");
		}
		sql.append(" ) " );
		String executeSql = sql.toString();
		super.getLogInfo(executeSql, null);
		return super.update(executeSql);
	}
	
	/**
	 * 根据一串id删除一组数据   
	 * @param ids
	 * 			格式：1，2，3，4
	 * @return
	 */
	public int deletes(String ids){
		long [] arr = StringUtil.toLongArray(ids, ",");
		return deletes(arr);
	}
	
	/**
	 * 逻辑删除数据
	 * @param ids
	 * @return
	 */
	public int fakeDeletes(long... ids) {
		JdbcModel model = this.get();
		String tbName = model.getTableName();
		JdbcColumn col = this.getPrimaryCol();
		String primaryColName = col.getColumnName();
		StringBuffer sql = new StringBuffer("update " + tbName + " set FLAG_ACTIVE="+BaseModel.Flag_False
				+" where " );
		if(this.isBaseModel()){
			sql.append(" and FLAG_ACTIVE < "+BaseModel.Flag_SYS);
		}

		sql.append(" ( " );
		for (int i = 0; i < ids.length; i++) {
			if(i != 0){
				sql.append(" OR ");
			}
			sql.append(" " + primaryColName + " = " + ids[i] + " ");
		}
		sql.append(" ) " );
		
		String executeSql = sql.toString();
		super.getLogInfo(executeSql, null);
		return super.update(executeSql);
	}
	
	/**
	 * 逻辑删除数据
	 * @param ids
	 * @return
	 */
	public int fakeDeletes(String ids) {
		long [] arr = StringUtil.toLongArray(ids, ",");
		return fakeDeletes(arr);
	}
	/**
	 * 通过主键获取实体对象
	 */
	public T get(Serializable id) {
		String sql = this.getSelSql();
		Map paramMap = MixUtil.newHashMap("id", id);
		super.getLogInfo(sql, paramMap);
		Object obj =  null;
		try{
			 obj =  super.getNamedParameterJdbcTemplate().queryForObject(sql,
					 paramMap,
				new ModelRowMapper(getGenericType(0)));
		}catch(EmptyResultDataAccessException e){
			return null;
		}
		return obj == null ? null : (T) obj;
	}
	
	/**
	 * 通过sql查询返回当前对象集合
	 */
	public List<T> query2Model(String sql) {
		super.getLogInfo(sql,null);
		List<T> list = (List<T>) super.getNamedParameterJdbcTemplate().query(
				sql,new HashMap(), new ModelRowMapper(getGenericType(0)));
		return list;
	}
	
	/**
	 * 通过sql查询返回当前对象集合
	 */
	public List<T> query2Model(String sql, Map paramMap) {
		super.getLogInfo(sql, paramMap);
		List<T> list = (List<T>) super.getNamedParameterJdbcTemplate().query(
				sql, paramMap, new ModelRowMapper(getGenericType(0)));
		return list;
	}
	
	/**
	 * 持久化一个对象
	 */
	public T save(T model) {
		Jay.isNotNull(model);
		JdbcModel jdbcModel = this.get();
		JdbcColumn primaryCol = jdbcModel.getPrimaryCol();
		try {
			Object id = primaryCol.getField().get(model);
			if (Validator.isNotNull(id)) {
				this.update(model);
			} else {
				this.insert(model);
				
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		return model;
	}
	/**
	 * 查询返回List<Object>对象，
	 * 由RowMapper生成自定义格式对象
	 */
	public List query2Obj(String sql, Map paramMap, RowMapper mapper) {
		super.getLogInfo(sql, paramMap);
		List<T> list = (List<T>) super.getNamedParameterJdbcTemplate().query(
				sql, paramMap, mapper);
		return list;
	}
	
	private JdbcColumn getPrimaryCol() {
		JdbcModel model = this.get();
		return model.getPrimaryCol();
	}

	/**
	 * 获取根据ID查询的Sql语句
	 * 
	 * @return
	 */
	private String getSelSql() {
		StringBuffer sb = new StringBuffer(" select ");
		String primaryKey = null;
		JdbcModel model = this.get();
		String tbName = model.getTableName();
		Map<String, JdbcColumn> colMap = model.getColMap();
		boolean first = true;
		for (String key : colMap.keySet()) {
			JdbcColumn col = colMap.get(key);
			String colName = col.getColumnName();
			if(col.isTemp()){
				continue;
			}
			if (col.isPrimary()) {
				primaryKey = colName;
			}
			if (first) {
				sb.append(colName);
				first = false;
			} else {
				concat(sb, colName, ",");
			}
		}
		sb.append(" from " + tbName + " where " + primaryKey + " = :id ");
		if(isBaseModel()){			
			sb.append(" and FLAG_ACTIVE >=  " + BaseModel.Flag_True );
		}
		return sb.toString();
	}

	private boolean isBaseModel(){
		Class c = this.getGenericType(0);
		while(c != Object.class){
			c = c.getSuperclass();
			if(c == BaseModel.class){
				return true;	
			}
		}
		return false;
	}
	
	private void concat(StringBuffer sb, String str, String notation) {
		if (!StringUtil.endsWith(sb.toString(), notation)) {
			sb.append(notation);
		}
		sb.append(str);
	}

	/**
	 * 获取T对应的模型对象
	 * 
	 * @return
	 */
	private JdbcModel get() {
		Class c = this.getGenericType(0);
		JdbcModel jdbcModel = JdbcModelSet.get(c);
		if(jdbcModel == null){
			throw new JayFrameException(JayFrameException.NOT_JDBC_MODEL,c.getName());
		}
		return jdbcModel;
	}
	
	/**
	 * 获取update语句
	 * 
	 * @param t
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private String getUpdateSql(T t ,Map paramMap) throws IllegalArgumentException,
			IllegalAccessException {
		JdbcModel model = this.get();
		Map<String, JdbcColumn> colMap = model.getColMap();
		JdbcColumn primaryCol = model.getPrimaryCol();
		String tbName = model.getTableName();
		StringBuffer sb = new StringBuffer("update " + tbName + " set ");
		boolean first = true;
		for (String key : colMap.keySet()) {
			JdbcColumn col = colMap.get(key);
			String colName = col.getColumnName();
			String name = col.getName();
			Field f = col.getField();
			Object v = f.get(t);
			
			if(!col.isUpdatable() || col.isTemp() || col.isPrimary()){
				continue;
			}
			paramMap.put(colName, v);
			if (first) {
				sb.append(colName + " = :" + colName);
				first = false;
			} else {
				this.concat(sb, colName + " = :" + colName, ",");
			}
		}
		String primaryColName = primaryCol.getColumnName();
		sb.append(" where " + primaryColName + " = :"+ primaryColName + "");
		if(t instanceof BaseModel){
			sb.append(" and FLAG_ACTIVE < "+BaseModel.Flag_READONLY +" ");
		}
		paramMap.put(primaryCol.getColumnName(), primaryCol.getField().get(t));
		return sb.toString();
	}
	
	public String getTableName(){
		JdbcModel jdbcModel = this.get();
		return jdbcModel.getTableName();
	}
	
	public String getTableName(Class modelClass){
		JdbcModel jdbcModel = JdbcModelSet.get(modelClass);
		return jdbcModel.getTableName();
	}
	
	public List<T> query2Model(T model) {
		Jay.isNotNull(model);
		Map paramMap = MixUtil.newHashMap();
		if( model instanceof BaseModel){
			paramMap.put("FLAG_ACTIVE", BaseModel.Flag_True);
		}
		
		String sql = this.getQuerySql(model, paramMap);
		super.getLogInfo(sql, paramMap);
		List<T> list = (List<T>) super.getNamedParameterJdbcTemplate().query(
				sql, paramMap, new ModelRowMapper(getGenericType(0)));
		return list;
	}
	
	private String getQuerySql(T model,Map paramMap){
		int size = paramMap.size();
		JdbcModel jdbcModel = this.get();
		Map<String, JdbcColumn> colMap = jdbcModel.getColMap();
		//JdbcColumn primaryCol = jdbcModel.getPrimaryCol();
		String tbName = jdbcModel.getTableName();
		StringBuffer header = new StringBuffer("select ");
		StringBuffer condition = new StringBuffer();
		boolean first = true;
		for (String key : colMap.keySet()) {
			JdbcColumn primaryCol = colMap.get(key);
			if(primaryCol.isTemp()){
				continue;
			}
			if(first){
				header.append(key);
				first = false;
			}else{
				header.append(","+key);
			}
			Field field = primaryCol.getField();
			Object v = null;
			try {
				v = field.get(model);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(Validator.isNotNull(v)){
				paramMap.put(key, v);
				condition.append(" and "+key+" =:"+key );
			}
		}
		if(paramMap.size() == size){//查询参数所有属性都是空值
			throw new JayFrameException(JayFrameException.EMPTY_PROPERTY_OBJECT,model.getClass().getName());
		}
		if( model instanceof BaseModel && Validator.isNull(((BaseModel)model).getFlagActive())){
			condition.append(" and FLAG_ACTIVE>="+BaseModel.Flag_True);
		}
		header.append(" from " + tbName + " where 1=1 ");
		header.append(condition);
		return header.toString();
	}
	
	
	/**
	 * 获取单条记录，存在多条则抛出异常
	 * @param list
	 * @return
	 */
	private T tran(List<T> list){
		int size = list.size();
		if(size > 1){
			throw new RuntimeException("Get duplicate data.");
		}
		if(size == 0){
			return null;
		}
		return list.get(0);
	}
	/**
	 * 返回一个model对象
	 */
	public T query21Model(String sql, Map paramMap) {
		List<T> list = this.query2Model(sql, paramMap);
		return tran(list);
	}
	
	public T query21Model(String sql) {
		List<T> list = this.query2Model(sql, null);
		return tran(list);
	}

	public T query21Model(T model) {
		List<T> list = this.query2Model(model);
		return tran(list);
	}

	public T query21Obj(String sql, Map paramMap, RowMapper mapper) {
		List<T> list = this.query2Obj(sql, paramMap, mapper);
		return tran(list);
	}
	
	/**
	 * 判断对象某些属性的值在数据库中不存在重复
	 * 
	 * @param names
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,code" 返回true表示，存在一条数据name值和code的值
	 *            都跟一样
	 * @param ignoreCase
	 * 				true: 忽略大小写
	 */
	public boolean isNotUnique(T entity, String names, boolean ignoreCase){
		Jay.hasText(names);
		String[]ns = names.split(",");
		Map paramMap = new HashMap();
		JdbcModel model = this.get();
		JdbcColumn primaryCol = model.getPrimaryCol();
		String primaryColName = primaryCol.getColumnName();
		Map<String, JdbcColumn> colMap = model.getColMap();
		StringBuffer sb = new StringBuffer(" select count(1) from "+model.getTableName()+" where 1 = 1 ");
		for (int i = 0; i < ns.length; i++) {
			String name = ns[i];
			for (JdbcColumn column : colMap.values()) {
				
				if(name.equals(column.getName())){
					Field f = column.getField();
					try{
						Object v = f.get(entity);
						paramMap.put(name,v);
						if(column.getType() == String.class && ignoreCase){
							sb.append(" and UPPER(" + column.getColumnName() + ") = UPPER(:" +name+") ");
						}else{
							sb.append(" and " + column.getColumnName() + " = :" +name);
						}
					}catch(Exception e){
						e.printStackTrace();
						throw new JayFrameException(JayFrameException.UNKNOW);
					}
				}
			}
			if(paramMap.size() != (i+1)){//未找到参数对应的列
				throw new JayFrameException(JayFrameException.UNKNOW_COLUMN,name,model.getClass().getName());
			}
		}
		try {
			Long id = StringUtil.toLong(primaryCol.getField().get(entity));
			if(Validator.isNotNull(id)){
				sb.append(" and " + primaryColName + " <> :model_primary_id ");
				paramMap.put("model_primary_id",id);
			}
		} catch (Exception e) {
			throw new JayFrameException(JayFrameException.UNKNOW);
		}
		if(entity instanceof BaseModel){
			sb.append(" and FLAG_ACTIVE >=  "+BaseModel.Flag_True);
		}
		return super.queryForInt(sb.toString(),paramMap) > 0;
	}
	/**
	 * 判断对象某些属性的值在数据库中不存在重复
	 * 
	 * @param names
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,code" 返回true表示，存在一条数据name值和code的值
	 *            都跟一样
	 */
	public boolean isNotUnique(T entity, String names){
		return isNotUnique(entity,names,true);
	}

	private User getCurrentUser(){
		return SysUtil.getCurrentUser();
	}
	
	private Long getSysUserId(){
		return SysUtil.getUserId();
	}
	

	public Page<T> paged2Obj(String sql, Map paramMap,QueryWebParameter webParam) {
		return super.pagedQuery(sql, paramMap, webParam,this.getModelRowMapper());
	}

	public Page<T> paged2Obj(String sql,QueryWebParameter webParam) {
		return super.pagedQuery(sql, null, webParam,this.getModelRowMapper());
	}
	
	
	public Page<T> paged2Obj(String sql, Map paramMap) {
		QueryWebParameter webParam = QueryWebUtils.generateQueryWebParameter(ActionUtil.getRequest());
		return super.pagedQuery(sql, paramMap, webParam,this.getModelRowMapper());
	}

	public Page<T> paged2Obj(String sql) {
		QueryWebParameter webParam = QueryWebUtils.generateQueryWebParameter(ActionUtil.getRequest());
		return super.pagedQuery(sql, null, webParam,this.getModelRowMapper());
	}
	

	@Override
	public Map selectSingleCamelMap(String sql, Map paramMap,final String prefix){
		RowMapper mapper = new CamelRowMapper(prefix);
		try {
			return (Map) getNamedParameterJdbcTemplate().queryForObject(sql, paramMap, mapper);
		} catch (EmptyResultDataAccessException ex) {
			logger.error("忽略此类错误[EmptyResultDataAccessException],允许查询为空时,返回空字符串!");
			return null;
		}
	}
	@Override
	public Map selectSingleCamelMap(String sql, Map paramMap){
		return selectSingleCamelMap(sql,paramMap,null);
	}
	

	@Override
	public Map selectSingleCamelMap(String sql) {
		return selectSingleCamelMap(sql,null,null);
	}

	@Override
	public List selectCamelListMap(String sql, Map paramMap,final String prefix){
		RowMapper mapper = new CamelRowMapper(prefix);
		try {
			return getNamedParameterJdbcTemplate().query(sql, paramMap, mapper);
		} catch (EmptyResultDataAccessException ex) {
			logger.error("忽略此类错误[EmptyResultDataAccessException],允许查询为空时,返回空字符串!");
			return null;
		}
	}
	
	@Override
	public List selectCamelListMap(String sql, Map paramMap){
		return selectCamelListMap(sql,paramMap,null);
	}
	
	@Override
	public List selectCamelListMap(String sql) {
		return selectCamelListMap(sql,null,null);
	}
	
	@Override
	public void batchInsert(final List<T> data) {
		final JdbcModel model = get();
		try {
			final ListParameter parameter = SqlMaker.getAddSql(model);
			final String sql = parameter.getSql();
			super.getLogInfo("批量插入:"+sql,null);
			//final List<String> nameParams = parameter.getNameParameters();
			super.getJdbcOperations().batchUpdate(sql, new BatchPreparedStatementSetter(){
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					T t = data.get(i);
					List paramList = parameter.toParameters(t);
					for (int j = 0; j < paramList.size(); j++) {
						setSqlParameter(ps ,(j+1),paramList.get(j));
					}
				}
				@Override
				public int getBatchSize() {
					return data.size();
				}});
			
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof GenericException) {
				throw (GenericException) e;
			} else {
				throw new JayFrameException(JayFrameException.UNKNOW, e.getMessage());
			}
		}
	}
	
	/**
	 * 给ps设置参数
	 * @param ps
	 * @param i
	 * @param val
	 * @throws SQLException 
	 */
	private void setSqlParameter(PreparedStatement ps ,int i,Object val) throws SQLException{
		if(val != null && FrameProperites.DB_TYPE == DBType.POSTGRESQL && val.getClass() == Date.class){
			ps.setTimestamp(i, new java.sql.Timestamp(((Date)val).getTime()));
		}else{
			ps.setObject(i, val);
		}
	}

	@Override
	public Page<Map> page2CamelMap(String sql, Map paramMap, QueryWebParameter webParam) {
		return super.pagedQuery(sql, paramMap, webParam,new CamelRowMapper());
	}

	@Override
	public Page<Map> page2CamelMap(String sql, QueryWebParameter webParam) {
		return page2CamelMap(sql,null,webParam);
	}

	@Override
	public Page<Map> page2CamelMap(String sql, Map paramMap) {
		QueryWebParameter webParam = QueryWebUtils.generateQueryWebParameter(ActionUtil.getRequest());
		return page2CamelMap(sql,paramMap,webParam);
	}

	@Override
	public Page<Map> page2CamelMap(String sql) {
		QueryWebParameter webParam = QueryWebUtils.generateQueryWebParameter(ActionUtil.getRequest());
		return page2CamelMap(sql,null,webParam);
	}

}
