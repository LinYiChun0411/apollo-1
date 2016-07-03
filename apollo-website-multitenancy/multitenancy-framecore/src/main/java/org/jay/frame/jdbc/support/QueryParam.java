package org.jay.frame.jdbc.support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jay.frame.jdbc.Constants;
import org.jay.frame.util.StringUtil;


/**
 * Advanced Query Parameters, can be nested unlimited. Example:
 * 
 * <pre>
 * QueryParam queryParam1 = new QueryParam(&quot;name&quot;, &quot;like&quot;, &quot;admin%&quot;);
 * QueryParam queryParam2 = new QueryParam(&quot;id&quot;, &quot;&gt;&quot;, new Integer(100));
 * QueryParam queryParam3 = new QueryParam(&quot;age&quot;, &quot;&lt;&quot;, new Integer(30));
 * QueryParam queryParam4 = new QueryParam(&quot;creationDate &gt; '2004-10-24'&quot;);
 * QueryParam queryParam = new QueryParam();
 * queryParam.andParameter(queryParam1);
 * queryParam.andParameter(queryParam2);
 * queryParam.notParameter(queryParam3);
 * queryParam.notParameter(queryParam4);
 * </pre>
 * 
 * the above code will generate the sql: where (name like admin% and id > 100) and not age < 30 and not creationDate >
 * '2004-10-24')
 * 
 * Note: A QueryParam can not be nested when his model is BASIC. <br/>
 * 系统的查询参数封装类
 * 
 * 
 */

public final class QueryParam {
	private final Logger logger = Logger.getLogger(QueryParam.class);

	// for special usage, such as only
	public static String EMPTY = "empty";

	// generate a alias

	public static String AND = "and";

	public static String OR = "or";

	public static String NOT = "not";
	
	//日期类型的等  Constants.SYS_DATE_FORMAT
	public static String OPERATOR_DEQ = "D=";
	
	public static String OPERATOR_DGE = "D>=";
	
	public static String OPERATOR_DLE = "D<=";
	//combo下拉框,空置为0
	public static String OPERATOR_CEQ = "C=";
	
	// Equal-->EQ
	public static String OPERATOR_EQ = "=";
	
	// Not Equal-->NE
	public static String OPERATOR_NE = "<>";

	// greater than or equal
	public static String OPERATOR_GE = ">=";

	// greater than
	public static String OPERATOR_GT = ">";

	// "less than or equal"
	public static String OPERATOR_LE = "<=";

	// "less than"
	public static String OPERATOR_LT = "<";

	// 非模糊
	public static String OPERATOR_LIKE = "like";
	
	// 以 value% 开头的数据
	public static String OPERATOR_ILIKE = "like";

	// 模糊，系统自动在查询值两边加上'%'
	public static String OPERATOR_INCLUDE = "like";

	// is null
	public static String OPERATOR_IS = "is";

	// is not null
	public static String OPERATOR_NIS = "is not";

	public static String OPERATOR_IN = "in";
	
	public static String OPERATOR_NIN = "not in";
	
	public static final Map<String, String> OPERATOR_NAME_MAP = new HashMap<String, String>();
	static{
		OPERATOR_NAME_MAP.put(OPERATOR_DEQ, "DEQ");
		OPERATOR_NAME_MAP.put(OPERATOR_DGE, "DGE");
		OPERATOR_NAME_MAP.put(OPERATOR_DLE, "DLE");
		
		OPERATOR_NAME_MAP.put(OPERATOR_CEQ, "CEQ");
		OPERATOR_NAME_MAP.put(OPERATOR_EQ, "EQ");
		OPERATOR_NAME_MAP.put(OPERATOR_GE, "GE");
		OPERATOR_NAME_MAP.put(OPERATOR_GT, "GT");
		OPERATOR_NAME_MAP.put(OPERATOR_LE, "LE");
		OPERATOR_NAME_MAP.put(OPERATOR_LT, "LT");
	}

	public final static int BASIC = 1;

	public final static int ADVANCED = 2;

	private String name;
	
	private Object value;

	private int type;

	private String operator = OPERATOR_EQ;

	private String sql;

	private int queryMode;

	private List<QueryParam> andParams;

	private List<QueryParam> orParams;

	private List<QueryParam> notParams;

	/**
	 * sql方式,eg:
	 * 
	 * @param sql
	 *            eg: usrName = 'chenzp'
	 */
	public QueryParam(String sql) {
		this.queryMode = BASIC;
		this.sql = sql;
	}

	public QueryParam(String name, String operator, Object value, int type) {
		this.queryMode = BASIC;
		setName(name);
		setOperator(operator);
		setValue(value);
		this.type = type;
		checkOperatorForNullValue();
	}

	public QueryParam(String name, String operator, Object value) {
		this.queryMode = BASIC;
		setName(name);
		setOperator(operator);
		setValue(value);
		this.type = -1;
		checkOperatorForNullValue();
	}

	public QueryParam(String name, Object value, int type) {
		this.queryMode = BASIC;
		setName(name);
		setValue(value);
		this.type = type;
		checkOperatorForNullValue();
	}

	public QueryParam(String name, Object value) {
		this.queryMode = BASIC;
		setName(name);
		setValue(value);
		this.type = -1;
		checkOperatorForNullValue();
	}

	public QueryParam() {
	}

	/**
	 * 添加 and关系的查询条件， 调用的QueryParam必须是this.queryMode = ADVANCED(2);
	 * 
	 * @param queryParam
	 */
	public void andParameter(QueryParam queryParam) {
		if (this.queryMode == BASIC) {
			logger.error("Current QueryParam was set as BASIC mode, can not be added a QueryParam!");
		}
		this.queryMode = ADVANCED;
		if (andParams == null) {
			andParams = new LinkedList<QueryParam>();
		}
		andParams.add(queryParam);
	}

	/**
	 * 添加 orParameter关系的查询条件， 调用的QueryParam必须是this.queryMode = ADVANCED(2);
	 * 
	 * @param queryParam
	 */
	public void orParameter(QueryParam queryParam) {
		if (this.queryMode == BASIC) {
			logger.error("Current QueryParam was set as BASIC mode, can not be added a QueryParam!");
		}
		this.queryMode = ADVANCED;
		if (orParams == null) {
			orParams = new LinkedList<QueryParam>();
		}
		orParams.add(queryParam);
	}

	/**
	 * 添加 notParameter关系的查询条件， 调用的QueryParam必须是this.queryMode = ADVANCED(2);
	 * 
	 * @param queryParam
	 */
	public void notParameter(QueryParam queryParam) {
		if (this.queryMode == BASIC) {
			logger.error("Current QueryParam was set as BASIC mode, can not be added a QueryParam!");
		}
		this.queryMode = ADVANCED;
		if (notParams == null) {
			notParams = new LinkedList<QueryParam>();
		}
		notParams.add(queryParam);
	}

	public String getName() {
		return name;
	}

	public String getOperator() {
		return operator;
	}

	public int getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public String getSql() {
		return sql;
	}

	private void setName(String name) {
		if (name == null) {
			logger.error("Parameter name can not be NULL!");
			return;
		}
		this.name = name.trim();
	}

	private void setOperator(String operator) {
		if (operator == null) {
			logger.error("Operator can not be NULL!");
			return;
		}
		this.operator = operator.trim();
		validateOperator();
		transferOperator();
	}

	private void setValue(Object value) {
		this.value = value;
		transferOperator();
	}

	/**
	 * 值为null时，转换关系符 = --> is <> --> is 值不是null时 is --> = is not--> <>
	 */
	private void transferOperator() {
		if (value == null) {
			if (OPERATOR_EQ.equals(this.operator)) {
				this.operator = OPERATOR_IS;
			} else if (OPERATOR_NE.equals(this.operator)) {
				this.operator = OPERATOR_NIS;
			}
		} else {
			if (OPERATOR_IS.equals(this.operator)) {
				this.operator = OPERATOR_EQ;
			} else if (OPERATOR_NIS.equals(this.operator)) {
				this.operator = OPERATOR_NE;
			}
		}
	}

	/**
	 * 检查值为null时，传入的关系符是否正确 'is' or '=' or 'is not' or '<>' when value is NULL 关系符为'is' or 'is not'时，值必须是null才有效
	 */
	private void checkOperatorForNullValue() {
		if (this.value == null) {
			if (!(OPERATOR_IS.equals(this.operator) || OPERATOR_NIS.equals(this.operator))) {
				logger.error("The operator can only be set 'is' or '=' or 'is not' or '<>' when value is NULL!");
			}
		} else if (OPERATOR_IS.equals(this.operator) || OPERATOR_NIS.equals(this.operator)) {
			logger.error("The operator 'is' or 'is not' is available only when value is NULL!");
		}
	}

	/**
	 * 校验传入的关系符是否正确
	 * 
	 */
	private void validateOperator() {
		if (OPERATOR_NE.equals(this.operator))
			return;
		if (OPERATOR_NIS.equals(this.operator))
			return;
		if (OPERATOR_NIN.equals(this.operator))
			return;
		if (OPERATOR_EQ.equals(this.operator))
			return;
		if (OPERATOR_GE.equals(this.operator))
			return;
		if (OPERATOR_GT.equals(this.operator))
			return;
		if (OPERATOR_LE.equals(this.operator))
			return;
		if (OPERATOR_LT.equals(this.operator))
			return;
		if (OPERATOR_LIKE.equals(this.operator))
			return;
		if (OPERATOR_INCLUDE.equals(this.operator))
			return;
		if (OPERATOR_IS.equals(this.operator))
			return;
		if (OPERATOR_IN.equals(this.operator))
			return;
		logger.error("The operator " + this.operator + " could be incorrect!");
	}

	public int getMode() {
		return queryMode;
	}

	public List<QueryParam> getAndParams() {
		return andParams;
	}

	public List<QueryParam> getNotParams() {
		return notParams;
	}

	public List<QueryParam> getOrParams() {
		return orParams;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (queryMode == BASIC) {
			if (sql != null) {
				sb.append(sql);
			} else {
				sb.append(name).append(' ').append(operator).append(' ').append(value);
			}
		} else {
			if (andParams != null && andParams.size() > 0) {
				boolean firstFlag = true;
				for (QueryParam q : andParams) {
					if (firstFlag) {
						firstFlag = false;
					} else {
						sb.append(" and ");
					}
					sb.append(q.toString());
				}
			}
			if (orParams != null && orParams.size() > 0) {
				if (sb.length() > 1) {
					sb.append(" or ");
				}
				boolean firstFlag = true;
				for (QueryParam q :  orParams) {
					if (firstFlag) {
						firstFlag = false;
						sb.append(" ( ");
					} else {
						sb.append(" or ");
					}
					sb.append(q.toString());
				}

				sb.append(" ) ");
			}
			if (notParams != null && notParams.size() > 0) {
				if (sb.length() > 0) {
					sb.append(" and ");
				}
				boolean firstFlag = true;
				for (QueryParam q :  notParams) {
					if (firstFlag) {
						firstFlag = false;
						sb.append(" ( ");
					} else {
						sb.append(" and ");
					}
					sb.append("not ");
					sb.append(q.toString());
				}
				sb.append(" ) ");
			}
		}

		return sb.toString();
	}

	public QueryObject toQueryObject() {
		QueryObject queryObj = new QueryObject();
		StringBuffer sb = new StringBuffer();
		Map<String, Object> map = queryObj.getParamsMap();
		if (queryMode == BASIC) {
			if (sql != null) {
				sb.append(sql);
			} else {
				//在英文环境下，需要大小写不敏感,Upper(name) like (value)
				String namedParam = name;
				String tempname = "";
				// <input name="f_f.gzsj^EQ?to_date(:gzsj,'yyyy-MM-dd') />
				int pos = namedParam.lastIndexOf('?');

				if (pos != -1) {
					name = namedParam.substring(0, pos);
					tempname = name;
					if (namedParam.indexOf(":") != -1 && namedParam.indexOf(",") != -1) {
						String temp = namedParam;
						int index = temp.indexOf(":");
						name = temp.substring(index + 1, temp.indexOf(",",index)).trim();
					}
					namedParam = namedParam.substring(pos + 1);

					// 这里可能是 to_date(:name,'yyyy-mm-dd')，所以命名参数是:和,之间，否则就出错
					if (namedParam.indexOf(":") != -1 && namedParam.indexOf(",") != -1) {
						String tempnamedParam = namedParam.substring(namedParam.indexOf(":") + 1, namedParam.indexOf(",")).trim();
						String tempnamedParam2 = StringUtil.replace(namedParam, tempnamedParam, tempnamedParam+StringUtil.strnull(OPERATOR_NAME_MAP.get(operator)));
						//String tempnamedParam2 = namedParam.replace(tempnamedParam, tempnamedParam+StringUtil.strnull(OPERATOR_NAME_MAP.get(operator))) ;
						sb.append(tempname).append(' ').append(operator).append(' ').append(tempnamedParam2);
						namedParam = namedParam.substring(namedParam.indexOf(":") + 1, namedParam.indexOf(",")).trim();
					} else {
						
						//模糊查询，忽略大小写
						namedParam = namedParam + StringUtil.strnull(OPERATOR_NAME_MAP.get(operator));
						
						if(OPERATOR_LIKE.equalsIgnoreCase(operator)){
							sb.append("Upper(").append(name).append(")");
						}else{
							sb.append(name);
						}
						sb.append(' ').append(operator).append(" :").append(namedParam);
					}

				} else {
					while (namedParam.indexOf(".") >= 0) {
						namedParam = namedParam.replace('.', '_');
					}
					// 如果是 in 或者是not in的情况，需要在命名参数两边加上'(',')'
					if (QueryParam.OPERATOR_NIN.equalsIgnoreCase(operator)
					    || QueryParam.OPERATOR_IN.equalsIgnoreCase(operator)) {
						sb.append(name).append(' ').append(operator).append(" (:").append(namedParam).append(')');
					} else {
						String tmp = operator;
						//模糊查询，忽略大小写
						namedParam = namedParam + StringUtil.strnull(OPERATOR_NAME_MAP.get(operator));

						if(OPERATOR_LIKE.equalsIgnoreCase(operator)){
							sb.append("Upper(").append(name).append(")");
						}else if(OPERATOR_DEQ.equalsIgnoreCase(operator)){
							sb.append("DATE_FORMAT(" + name + ",'"+Constants.MYSQL_DATE_TO_STR+"')");
							tmp = this.OPERATOR_EQ;
						}else if(OPERATOR_DLE.equalsIgnoreCase(operator)){
							sb.append("DATE_FORMAT(" + name + ",'"+Constants.MYSQL_DATE_TO_STR+"')");
							tmp = this.OPERATOR_LE;
						}else if(OPERATOR_DGE.equalsIgnoreCase(operator)){
							sb.append("DATE_FORMAT(" + name + ",'"+Constants.MYSQL_DATE_TO_STR+"')");
							tmp = this.OPERATOR_GE;
						}else if(OPERATOR_CEQ.equalsIgnoreCase(operator)){
							sb.append(name);
							tmp = this.OPERATOR_EQ;
						}else{
							sb.append(name);
						}
						
						sb.append(' ').append(tmp).append(" :").append(namedParam);
					}
				}
				map.put(namedParam, value);
			}
		} else {
			if (andParams != null && andParams.size() > 0) {
				boolean firstFlag = true;

				for (QueryParam q : andParams) {
					if (firstFlag) {
						firstFlag = false;
					} else {
						sb.append(" and ");
					}
					sb.append(q.toQueryObject().getQuerySql().toString());

					queryObj.setQueryObject(q.toQueryObject());
				}
			}
			if (orParams != null && orParams.size() > 0) {
				if (sb.length() > 1) {
					sb.append(" or ");
				}
				boolean firstFlag = true;

				for (QueryParam q : orParams) {
					if (firstFlag) {
						firstFlag = false;
						sb.append(" ( ");
					} else {
						sb.append(" or ");
					}
					sb.append(q.toQueryObject().getQuerySql().toString());

					queryObj.setQueryObject(q.toQueryObject());
				}

				sb.append(" ) ");
			}
			if (notParams != null && notParams.size() > 0) {
				if (sb.length() > 0) {
					sb.append(" and ");
				}
				boolean firstFlag = true;

				for (QueryParam q : notParams) {
					if (firstFlag) {
						firstFlag = false;
						sb.append(" ( ");
					} else {
						sb.append(" and ");
					}
					sb.append("not ");

					sb.append(q.toQueryObject().getQuerySql().toString());

					queryObj.setQueryObject(q.toQueryObject());
				}
				sb.append(" ) ");
			}
		}

		queryObj.setQuerySql(sb);
		return queryObj;
	}

	public static void main(String[] args) {
		// queryParam from view layer
//		String[] names = { "age", "[and:a]id", "[and:a]id", "[or:or_b]salary", "[or:or_b]salary", "[or:and_a]age",
//		                  "[or:and_a]title", "[not:or_a]age", "[not:or_a]title" };
//		String[] operators = { ">", ">", "<>", ">", "<", "=", "=", "=", "=" };
//		// this objects are generated at action layer
//		Object[] values = { new Integer(20), new Integer(100), new Integer(200), new Integer(200), new Integer(400),
//		                   new Integer(60), "%manager%", new Integer(50), "cto" };
//		

		String[] names = { "[and:or_b]salary", "[and:or_b]salary", "title" };
		String[] operators = { ">", "<", "="};
		// this objects are generated at action layer
		Object[] values = { new Integer(200), new Integer(400), "cto" };
		QueryParam queryParam = QueryWebUtils.generateQueryParam(names, operators, values);

		System.out.println("Plan SQL:" + queryParam);
		QueryObject queryObject = queryParam.toQueryObject();
		logSql(queryObject.getQuerySql().toString(), queryObject.getParamsMap());

		System.out.println("queryObject SQL:" + queryObject.getQuerySql());

//		// queryParam from service
//		queryParam = new QueryParam();
//		queryParam.andParameter(new QueryParam("age", ">", new Integer(40)));
//		queryParam.andParameter(new QueryParam("age", "<", new Integer(55)));
//		QueryParam innerQueryParam = new QueryParam();
//		innerQueryParam.orParameter(new QueryParam("title", "cto"));
//		QueryParam innerInnerQueryParam = new QueryParam();
//		innerInnerQueryParam.andParameter(new QueryParam("c.name", "like", "%Li"));
//		innerInnerQueryParam.andParameter(new QueryParam("addr.description", "like", "%China%"));
//		innerQueryParam.orParameter(innerInnerQueryParam);
//		queryParam.andParameter(innerQueryParam);
//
//		System.out.println("Plan SQL:" + queryParam);
//		queryObject = queryParam.toQueryObject();
//
//		logSql(queryObject.getQuerySql().toString(), queryObject.getParamsMap());
//
//		System.out.println("queryObject SQL:" + queryObject.getQuerySql());
	}

	public static void logSql(String sql, Map<String, Object> paramMap) {
		StringBuffer log = new StringBuffer(" JDBC查询的sql语句是：").append(sql);
		if (null == paramMap) {
			log.append(" ；参数为空！");
		} else {
			log.append("\n 命名参数以及对应的数值:\n");
			Iterator keys = paramMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				log.append("  {").append(key).append("} = ").append(paramMap.get(key)).append("\n");
			}
		}
		System.out.println(log.toString());
	}
}
