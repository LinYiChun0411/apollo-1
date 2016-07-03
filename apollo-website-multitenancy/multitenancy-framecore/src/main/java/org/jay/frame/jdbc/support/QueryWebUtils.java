package org.jay.frame.jdbc.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;
import org.jay.frame.FrameProperites;
import org.jay.frame.util.StringPool;
import org.jay.frame.util.StringUtil;
import org.springframework.util.Assert;


public class QueryWebUtils {

	private QueryWebUtils() {
	}

	private static final Logger logger = Logger.getLogger(QueryWebUtils.class);
	
	/**
	 * 参数生成器
	 */
	private static QueryWebParameterCreator webParamCreator = null;
	/**
	 * 是否起始化参数生成器
	 */
	private static boolean isInitCreator = false;
	
	// 参数key总数量
	private static final String KEY_COUNTS = "KEY_COUNTS";

	// 过滤条件
	private static final String FILTER_PREFIX = "f_";
	private static final String OR_SPLIT = "_OR_";
	// 排序条件
	// private static final String SORT_PREFIX = "s_";

	private static final char SPLIT = '^';
	
	private static final String SPLIT_STRING = "^";

	/**
	 * Return a map containing all parameters with the given prefix. Maps single values to String and multiple values to
	 * List. 并且追加 一个参数总个数key="KEY_COUNTS"
	 * <p>
	 * For example, with a prefix of "spring_", "spring_param1" and "spring_param2" result in a Map with "param1" and
	 * "param2", "KEY_COUNTS" as keys.
	 * <p>
	 * Similar to Spring 2.0's <code>WebUtils.getParametersStartingWith</code>, but more flexible and compatible with
	 * Servlet 2.2.
	 * 
	 * @param request
	 *            HTTP request in which to look for parameters
	 * @param prefix
	 *            the beginning of parameter names (if this is null or the empty string, all parameters will match)
	 * @return map containing request parameters <b>without the prefix</b>, containing either a String or a String array
	 *         as values
	 * @see javax.servlet.ServletRequest#getParameterNames
	 * @see javax.servlet.ServletRequest#getParameterValues
	 * @see javax.servlet.ServletRequest#getParameterMap
	 */
	public static Map getParametersStartingWith(ServletRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map params = new TreeMap();
		if (prefix == null) {
			prefix = "";
		}
		int count = 0;
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			// System.out.println("ppp---------"+paramName+"-----------"+request.getParameterValues(paramName));
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				//
				String[] values = request.getParameterValues(paramName);
				
				if(StringUtils.containsIgnoreCase(unprefixed, OR_SPLIT)){
					String[] arrStrings = StringUtil.split(unprefixed, OR_SPLIT);
					for(String pString : arrStrings){
						if (values == null) {
							// Do nothing, no values found at all.
						} else if (values.length > 1) {
							params.put("[or:or_b]"+pString, Arrays.asList(values));
							count++;
						} else {
							// 暂时忽略对 is NULL or is not NULL的查询处理
							if (StringUtil.isNotEmpty(values[0])) {
								params.put("[or:or_b]"+pString, values[0]);
								count++;
							}
						}
					}
				}else{
					//combo的默认值为0
					if (values == null) {
						// Do nothing, no values found at all.
					} else if (values.length > 1) {
						params.put(unprefixed, Arrays.asList(values));
						count++;
					} else {
						// 暂时忽略对 is NULL or is not NULL的查询处理
						if (StringUtil.isNotEmpty(values[0]) && isNullCombo(paramName,values[0]) == false) {
							params.put(unprefixed, values[0]);
							count++;
						}
					}
				}
			}
		}
		params.put(KEY_COUNTS, new Integer(count));
		return params;
	}
	private static boolean isNullCombo(String paramName,String value){
		String comboTag = SPLIT_STRING + QueryParam.OPERATOR_NAME_MAP.get(QueryParam.OPERATOR_CEQ);
		int index = paramName.indexOf(comboTag);
		if(index == -1 || value == null){
			return false;
		}
		if("0".equals(value)){
			return true;
		}
		return false;
	}
//	/**
//	 * for CXF:
//	 * 
//	 * @param mdMap
//	 * @param prefix
//	 * @return
//	 */
//	public static Map getParametersStartingWith(MetadataMap<String, String> mdMap, String prefix) {
//		Assert.notNull(mdMap, "Request must not be null");
//
//		Map params = new TreeMap();
//		if (prefix == null) {
//			prefix = "";
//		}
//		int count = 0;
//
//		for (String key : mdMap.keySet()) {
//			if ("".equals(prefix) || key.startsWith(prefix)) {
//				String unprefixed = key.substring(prefix.length());
//				List<String> values = mdMap.get(key);
//				
//				if(StringUtils.containsIgnoreCase(unprefixed, OR_SPLIT)){
//					String[] arrStrings = StringUtil.split(unprefixed, OR_SPLIT);
//					for(String pString : arrStrings){
//						if (values == null) {
//							// Do nothing, no values found at all.
//						} else if (values.size() > 1) {
//							params.put("[and:or_b]"+pString, values);
//							count++;
//						} else {
//							// 暂时忽略对 is NULL or is not NULL的查询处理
//							if (StringUtil.isNotEmpty(values.get(0))) {
//								params.put("[and:or_b]"+pString, values.get(0));
//								count++;
//							}
//						}
//					}
//				}else{
//					if (values == null) {
//						// Do nothing, no values found at all.
//					} else if (values.size() > 1) {
//						params.put(unprefixed, values);
//						count++;
//					} else {
//						// 暂时忽略对 is NULL or is not NULL的查询处理
//						if (StringUtil.isNotEmpty(values.get(0))) {
//							params.put(unprefixed, values.get(0));
//							count++;
//						}
//					}
//				}
//			}
//		}
//
//		params.put(KEY_COUNTS, new Integer(count));
//		return params;
//	}

	/**
	 * 获取'f_'开头的所有参数值 查询参数的名称规则"f_[and/or/not:a/b/c...]queryParameterName^OperatorShortName"<br>
	 * 1) f_:以f_为查询参数的前缀；前缀名称如果改变为prefix话，调用QueryWebUtils.generateQueryObject( request,prefix);<br>
	 * 2) [and/or/not:a/b/c...]:表示需要合并为and 或者or的参数组，冒号隔开后的参数，其中a/b/c...为合并一起的分组符合。<br>
	 * 3) queryParameterName：sql语句中需要的条件，比如表的字段名，如果是多表情况，可能要加上表的别名,比如t1.id<br>
	 * 4) ^:作为参数和操作符号的分隔符,必须的分隔符，如果没有的话，系统默认查询条件为 like <br>
	 * 5) 操作符简称OperatorShortName：各个操作符命名对应
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static QueryWebParameter generateQueryWebParameter(ServletRequest request) {
		if(!isInitCreator){
			initCreator();
			isInitCreator = true;
		}
		if(webParamCreator != null){
			return webParamCreator.generate(request);
		}
		return generateQueryWebParameter(request, FILTER_PREFIX);
	}
	
	private static void initCreator(){
		if(StringUtil.isEmpty(FrameProperites.PAGING_CALLBACK_CLASS)){
			webParamCreator = null;
			return;
		}
		try {
			webParamCreator = (QueryWebParameterCreator)Class.forName(FrameProperites.PAGING_CALLBACK_CLASS).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/**
	 * 从HTTP request 中构造查询参数
	 * 
	 * @param request
	 *            HTTP request in which to look for parameters
	 * @param prefix
	 *            参数前缀，为空时获取全部参数
	 * 
	 * 查询参数的名称规则"f_[and/or/not:a/b/c...]queryParameterName^OperatorShortName"<br>
	 * 1)
	 * f_:以f_为查询参数的前缀；前缀名称如果改变为prefix话，调用QueryWebUtils.generateQueryObject(request,prefix);<br>
	 * 2) [and/or/not:a/b/c...]:表示需要合并为and
	 * 或者or的参数组，冒号隔开后的参数，其中a/b/c...为合并一起的分组符合。<br>
	 * 3) queryParameterName：sql语句中需要的条件，比如表的字段名，如果是多表情况，可能要加上表的别名,比如t1.id<br>
	 * 4) 分隔符^:作为参数和操作符号的分隔符,必须的分隔符，如果没有的话，系统默认查询条件为 like <br>
	 * 5) 操作符简称OperatorShortName：各个操作符命名对应，请参看@see QueryParam <br>
	 * 对应关系如下：<br>
	 * EQ = "=";<br>
	 * NE = "<>";<br>
	 * GE = ">=";<br>
	 * GT = ">";<br>
	 * LE = "<=";<br>
	 * LT = "<";<br>
	 * IS = "is";<br>
	 * NIS = "is not";<br>
	 * IN = "in";<br>
	 * NIN = "not in";<br>
	 * LIKE = "like";<br>
	 * 以下情况，查询值中系统会自动添加 '%'+searchValue+'%'<br>
	 * INCLUDE = "include";<br>
	 */
	public static QueryWebParameter generateQueryWebParameter(ServletRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		int defStartPageNo = FrameProperites.PAGING_PAGE_NUMBER_START_FROM_ZERO ? 0 : 1;
		int pageNo = StringUtil.toInt(((HttpServletRequest) request).getParameter(FrameProperites.PAGING_PARAM_PAGE_NUMBER), defStartPageNo);
		if(FrameProperites.PAGING_PAGE_NUMBER_START_FROM_ZERO){
			pageNo = pageNo + 1;
		}
		int pageSize = StringUtil.toInt(((HttpServletRequest) request).getParameter(FrameProperites.PAGING_PARAM_PAGE_SIZE),
				FrameProperites.PAGING_DEFAULT_PAGE_SIZE );
		// start limit
		// 排序条件
		String sortfield = ((HttpServletRequest) request).getParameter("sortfield");
		
		String sorttype = ((HttpServletRequest) request).getParameter("sorttype");
		return generateQueryWebParameter(getParametersStartingWith(request, FILTER_PREFIX), pageNo, pageSize,
		                                 sortfield, sorttype);
	}

	/**
	 * 如果为空，返回""
	 * @param map
	 * @param key
	 * @return
	 */
//	public static String getValue(MetadataMap<String, String> map, String key) {
//		try {
//			List<String> startLs = map.get(key);
//			if (startLs != null && startLs.size() > 0) {
//				return startLs.get(0);
//			}
//		} catch (Exception ex) {
//
//		}
//		return StringPool.BLANK;
//	}
	
//	public static QueryWebParameter generateQueryWebParameter(MetadataMap<String, String> map) {
//		return generateQueryWebParameter(map, FILTER_PREFIX);
//	}

//	public static QueryWebParameter generateQueryWebParameter(MetadataMap<String, String> map, String prefix) {
////		Assert.notNull(map, "map must not be null");
//
//		// 由于ext传递的是start limit
//		// int pageNo = StringUtil.toInt(((HttpServletRequest) request).getParameter(Constants.pageNo), 1);
//		// int pageSize = StringUtil.toInt(((HttpServletRequest) request).getParameter(Constants.pageSize),
//		// Constants.DEFAULT_PAGE_SIZE);
//
//		int start = StringUtil.toInt(getValue(map, Constants.start), 0);
//		int limit = StringUtil.toInt(getValue(map, Constants.limit), Constants.DEFAULT_PAGE_SIZE);
//		int pageSize = limit;
//		int pageNo = start / limit + 1;
//		// start limit
//		// 排序条件
//		String sortfield = getValue(map, "sortfield");
//		String sorttype = getValue(map, "sorttype");
//		return generateQueryWebParameter(getParametersStartingWith(map, FILTER_PREFIX), pageNo, pageSize, sortfield,
//		                                 sorttype);
//	}

	public static QueryWebParameter generateQueryWebParameter(Map map, int pageNo, int pageSize, String sortfield,
	                    String sorttype) {
		QueryWebParameter params = new QueryWebParameter();
		try {
			int counts = StringUtil.toInt(map.get(KEY_COUNTS));
			map.remove(KEY_COUNTS);
			if (counts > 0) {
				String[] searchNames = new String[counts];
				String[] operators = new String[counts];
				Object[] searchValues = new Object[counts];

				Iterator keyIter = map.keySet().iterator();
				int i = 0;
				while (keyIter.hasNext()) {
					// ^XX 表示操作符，默认是'like'
					String key = (String) keyIter.next();
					int pos1 = key.lastIndexOf(SPLIT);
					// ?YY之后表示需要类型转换，目前支持Date类型转换
					int pos2 = key.lastIndexOf('?');
					Object value = map.get(key);
					if (pos1 != -1) {
						searchNames[i] = key.substring(0, pos1);
						// 如果有类型转换需要，目前支持
						// TODO 这里留待扩展，可以转换成更多的类型
						if (pos2 != -1) {
							String opName = key.substring(pos1 + 1, pos2);
							operators[i] = transferOperator(opName);
							// 判断是否需要转换成模糊查询
							searchValues[i] = transferValue(value, opName);
							searchNames[i] = key.substring(0, pos1) + key.substring(pos2);
						} else {
							String opName = key.substring(pos1 + 1);
							operators[i] = transferOperator(opName);
							searchValues[i] = transferValue(value, opName);
						}
					} else {
						searchNames[i] = key;
						// 系统默认是模糊查询，大小写模糊
						if (value instanceof String) {
							logger.info("The value type is string and set the operator:like.");
							operators[i] = QueryParam.OPERATOR_INCLUDE;
							value = "%" + StringUtil.toUpperCase(StringUtil.trim2Empty(value)) + "%";
							searchValues[i] = value;
						} else {
							logger.info("The value type is " + value.getClass().getName()
							    + " and set the operator:equal");
							operators[i] = QueryParam.OPERATOR_EQ;
							searchValues[i] = value;
						}
					}
					i++;
				}
				params.setName(searchNames);
				params.setActualValues(searchValues);
				params.setOperator(operators);
			}

			params.setPageNo(pageNo);
			params.setPageSize(pageSize);
			params.setStartIndex((pageNo - 1) * pageSize);
			//处理grid带有过滤和排序的情况
			if(sortfield != null && sortfield.indexOf(StringPool.QUESTION) != -1){
				sortfield = StringUtils.substringBefore(sortfield, StringPool.QUESTION);
			}
			//
			if(sortfield != null && sortfield.indexOf(StringPool.PIPE) != -1){
				sortfield = StringUtils.substringAfterLast(sortfield, StringPool.PIPE);
			}
			if(sortfield != null && sortfield.indexOf(SPLIT_STRING) != -1){
				sortfield = StringUtils.substringBefore(sortfield, SPLIT_STRING);
			}
			params.setSortfield(sortfield);
			params.setSorttype(sorttype);
		} catch (Exception ex) {
			logger.error("generateQueryWebParameter(" + map + ") exception==" + ex.getMessage());
		}

		return params;
	}

	/**
	 * 判断是否需要转换成模糊查询 这里可以扩展出更多的类型转换
	 * 
	 * @param value
	 * @param opName
	 */
	private static Object transferValue(Object value, String operator) {
		if ("INCLUDE".equalsIgnoreCase(operator) && value instanceof String) {
			return "%" + StringUtil.toUpperCase(StringUtil.trim2Empty(value)) + "%";
		} else if ("ILIKE".equalsIgnoreCase(operator) && value instanceof String) {
			return StringUtil.trim2Empty(value) + "%";
		} else if ("NIN".equalsIgnoreCase(operator) || ("IN".equalsIgnoreCase(operator))) {
			//对NIN/IN直接转换为List作为参数。
			return StringUtil.split2List(StringUtil.trim2Empty(value));
		}
		return value;
	}

	/**
	 * 转化操作符,大小写对照表
	 * 
	 * @param operator
	 * @return
	 */
	private static String transferOperator(String oper) {
		String operator = oper.toUpperCase();
		if (operator.startsWith("N")) {
			if ("NE".equals(operator))
				return QueryParam.OPERATOR_NE;
			if ("NIS".equals(operator))
				return QueryParam.OPERATOR_NIS;
			if ("NIN".equals(operator))
				return QueryParam.OPERATOR_NIN;
		} else {
			if("CEQ".equals(operator))
				return QueryParam.OPERATOR_CEQ;
			if("DEQ".equals(operator))
				return QueryParam.OPERATOR_DEQ;
			if("DGE".equals(operator))
				return QueryParam.OPERATOR_DGE;
			if("DLE".equals(operator))
				return QueryParam.OPERATOR_DLE;
			if ("EQ".equals(operator))
				return QueryParam.OPERATOR_EQ;
			if ("GE".equals(operator))
				return QueryParam.OPERATOR_GE;
			if ("GT".equals(operator))
				return QueryParam.OPERATOR_GT;
			if ("LE".equals(operator))
				return QueryParam.OPERATOR_LE;
			if ("LT".equals(operator))
				return QueryParam.OPERATOR_LT;
			if ("LIKE".equals(operator))
				return QueryParam.OPERATOR_LIKE;
			if ("ILIKE".equals(operator))
				return QueryParam.OPERATOR_LIKE;
			if ("INCLUDE".equals(operator))
				return QueryParam.OPERATOR_INCLUDE;
			if ("IS".equals(operator))
				return QueryParam.OPERATOR_IS;
			if ("IN".equals(operator))
				return QueryParam.OPERATOR_IN;
		}
		logger.error("The operator " + operator + " could be incorrect! replace to 'like' ");
		return QueryParam.OPERATOR_LIKE;
	}

	/**
	 * 将单个jsp参数转换为查询参数对象
	 * 
	 * @param name
	 * @param operator
	 * @param value
	 * @return QueryParam
	 */
	public static QueryParam generateQueryParam(String name, String operator, Object value) {
		QueryParam innerQueryParam = null;
		if (operator.indexOf("and_") != -1 && value instanceof List) {
			innerQueryParam = new QueryParam();
			List valueList = (List) value;
			for (int x = valueList.size() - 1; x >= 0; x--) {
				innerQueryParam.andParameter(new QueryParam(name, operator.replaceFirst("and_", ""), valueList.get(x)));
			}

		} else if (operator.indexOf("or_") != -1 && value instanceof List) {
			innerQueryParam = new QueryParam();
			List valueList = (List) value;
			for (int x = valueList.size() - 1; x >= 0; x--) {
				innerQueryParam.orParameter(new QueryParam(name, operator.replaceFirst("or_", ""), valueList.get(x)));
			}

		} else if (operator.indexOf("not_") != -1 && value instanceof List) {
			innerQueryParam = new QueryParam();
			List valueList = (List) value;
			for (int x = valueList.size() - 1; x >= 0; x--) {
				innerQueryParam.notParameter(new QueryParam(name, operator.replaceFirst("not_", ""), valueList.get(x)));
			}
		} else if (operator.indexOf("sql_") != -1) {
			innerQueryParam = new QueryParam(new StringBuffer().append(name).append(' ')
			                                                   .append(operator.replaceFirst("sql_", "")).append(' ')
			                                                   .append(value).toString());
		} else {
			innerQueryParam = new QueryParam(name, operator, value);
		}
		return innerQueryParam;
	}

	/**
	 * 生成查询条件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static QueryObject generateQueryObject(ServletRequest request, String prefix) {
		QueryWebParameter webParam = generateQueryWebParameter(request, prefix);
		QueryParam queryParam = generateQueryParam(webParam.getName(), webParam.getOperator(),
		                                           webParam.getActualValues());

		QueryObject queryObject = queryParam.toQueryObject();
		// 增加排序条件
		queryObject.setOrderby(webParam.getSortfield(), webParam.getSorttype());
		return queryObject;
	}

	/**
	 * 生成查询条件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static QueryObject generateQueryObject(ServletRequest request) {
		return generateQueryObject(request, FILTER_PREFIX);
	}

	/**
	 * 自己传入参数生成查询条件
	 * 
	 * @param names
	 * @param operators
	 * @param values
	 * @return
	 */
	public static QueryObject generateQueryObject(String[] names, String[] operators, Object[] values) {
		return generateQueryObject(names, operators, values, null, null);
	}

	/**
	 * 
	 * @param names
	 * @param operators
	 * @param values
	 * @param sortfield
	 *            排序列
	 * @param sorttype
	 *            升降序
	 * @return
	 */
	public static QueryObject generateQueryObject(String[] names, String[] operators, Object[] values,
	                    String sortfield, String sorttype) {
		QueryParam queryParam = generateQueryParam(names, operators, values);
		QueryObject queryObject = queryParam.toQueryObject();
		// 增加排序条件
		queryObject.setOrderby(sortfield, sorttype);
		return queryObject;
	}

	/**
	 * 将多个jsp参数转换为查询参数对象，条件嵌套
	 * 
	 * @param names
	 * @param operators
	 * @param values
	 * @return QueryParam
	 */
	public static QueryParam generateQueryParam(String[] names, String[] operators, Object[] values) {
		QueryParam queryParam = new QueryParam();

		Map andParameters = null;
		Map orParameters = null;
		Map notParameters = null;
		if (null == names || null == values || null == operators) {
			return queryParam;
		}
		for (int i = 0, max = names.length; i < max; i++) {
			QueryParam innerQueryParam = null;
			String name = names[i];
			boolean groupFlag = name.startsWith("[");
			int pos = name.indexOf(":");
			if (groupFlag && name.indexOf("]") > pos) {
				int posEnd = name.indexOf("]");
				innerQueryParam = generateQueryParam(name.substring(posEnd + 1, name.length()), operators[i], values[i]);
				if (pos == -1) {
					// key not exists, like [and]xxx instead of [and:xx]xxx
					String operator = name.substring(1, posEnd);

					if ("and".equals(operator.toLowerCase())) {
						queryParam.andParameter(innerQueryParam);
					} else if ("or".equals(operator.toLowerCase())) {
						queryParam.orParameter(innerQueryParam);
					} else if ("not".equals(operator.toLowerCase())) {
						queryParam.notParameter(innerQueryParam);
					}
				} else {// 内层有嵌套条件
					String operator = name.substring(1, pos);
					String key = name.substring(pos + 1, posEnd);
					if ("and".equals(operator.toLowerCase())) {
						andParameters = putParameters(andParameters, key, innerQueryParam);
					} else if ("or".equals(operator.toLowerCase())) {
						orParameters = putParameters(orParameters, key, innerQueryParam);
					} else if ("not".equals(operator.toLowerCase())) {
						notParameters = putParameters(notParameters, key, innerQueryParam);
					}
				}
			} else {// 单个条件
				innerQueryParam = generateQueryParam(name, operators[i], values[i]);
				if (innerQueryParam == null) {
					continue;
				}
				queryParam.andParameter(innerQueryParam);
			}
		}
		if (andParameters != null) {// and 嵌套
			QueryParam innerQueryParam = new QueryParam();
			for (Iterator keyIter = andParameters.keySet().iterator(); keyIter.hasNext();) {
				String key = StringUtil.strnull(keyIter.next());

				List parameters = (List) andParameters.get(key);
				if (parameters != null && parameters.size() > 0) {
					QueryParam innerInnerQueryParam = new QueryParam();

					for (Iterator paramIt = parameters.iterator(); paramIt.hasNext();) {
						QueryParam p = (QueryParam) paramIt.next();

						if (key.startsWith("or_")) {
							innerInnerQueryParam.orParameter(p);
						} else {
							innerInnerQueryParam.andParameter(p);
						}
					}
					innerQueryParam.andParameter(innerInnerQueryParam);
				}
			}
			queryParam.andParameter(innerQueryParam);
		}
		if (orParameters != null) {// or 嵌套
			QueryParam innerQueryParam = new QueryParam();
			// orParameters: Map<String, List<QueryParam>>
			for (Iterator keyIter = orParameters.keySet().iterator(); keyIter.hasNext();) {
				String key = StringUtil.strnull(keyIter.next());
				// List<QueryParam>
				List parameters = (List) orParameters.get(key);
				if (parameters != null && parameters.size() > 0) {
					QueryParam innerInnerQueryParam = new QueryParam();

					for (Iterator paramIt = parameters.iterator(); paramIt.hasNext();) {
						QueryParam p = (QueryParam) paramIt.next();

						if (key.startsWith("or_")) {
							innerInnerQueryParam.orParameter(p);
						} else {
							innerInnerQueryParam.andParameter(p);
						}
					}
					innerQueryParam.andParameter(innerInnerQueryParam);
				}
			}
			queryParam.orParameter(innerQueryParam);
		}
		if (notParameters != null) {// not 嵌套
			QueryParam innerQueryParam = new QueryParam();

			for (Iterator keyIter = notParameters.keySet().iterator(); keyIter.hasNext();) {
				String key = StringUtil.strnull(keyIter.next());
				// List<QueryParam>
				List parameters = (List) notParameters.get(key);
				if (parameters != null && parameters.size() > 0) {
					QueryParam innerInnerQueryParam = new QueryParam();

					for (Iterator paramIt = parameters.iterator(); paramIt.hasNext();) {
						QueryParam p = (QueryParam) paramIt.next();

						if (key.startsWith("or_")) {
							innerInnerQueryParam.orParameter(p);
						} else {
							innerInnerQueryParam.andParameter(p);
						}
					}
					innerQueryParam.andParameter(innerInnerQueryParam);
				}
			}
			queryParam.notParameter(innerQueryParam);
		}
		return queryParam;
	}

	/**
	 * 转换为嵌套参数
	 * 
	 * @param parameterMap
	 * @param key
	 * @param queryParam
	 * @return
	 */
	private static Map putParameters(Map parameterMap, String key, QueryParam queryParam) {
		if (parameterMap == null) {
			parameterMap = new HashMap();
		}
		List parameters = (List) parameterMap.get(key);
		if (parameters == null) {
			parameters = new ArrayList();
		}
		parameters.add(queryParam);
		parameterMap.put(key, parameters);
		return parameterMap;
	}

	public static void main(String[] args) {
		// queryParam from view layer
		String[] names = {};
		String[] operators = {};
		// this objects are generated at action layer
		Object[] values = {};
		QueryParam queryParam = QueryWebUtils.generateQueryParam(names, operators, values);

		System.out.println("Plan SQL:" + queryParam);
		QueryObject queryObject = queryParam.toQueryObject();
		// logSql(queryObject.getQuerySql().toString(),
		// queryObject.getParamsMap());

		System.out.println("queryObject SQL:" + StringUtils.substringAfterLast("so.dept_id|so.dept_name", StringPool.PIPE));
		System.out.println("getOrderbySql SQL:" + queryObject.getOrderbySql());
	}
}
