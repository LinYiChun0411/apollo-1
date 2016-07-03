package org.jay.frame.jdbc.support;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jay.frame.util.StringUtil;
import org.springframework.util.Assert;

public final class QuerySqlParser {

	protected static Log log = LogFactory.getLog(QuerySqlParser.class);

	public QuerySqlParser() {
	}

	/**
	 * 去除select 子句，未考虑union的情况
	 */
	public static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除orderby 子句
	 */
	public static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static String buildCountSql(String hql) {
		return " select count(*) " + removeSelect(removeOrders(hql));
	}

	public static String buildCountSql(String hql, String countcolumn) {
		return " select count(" + countcolumn + ") "
				+ removeSelect(removeOrders(hql));
	}

	public static final String _FROM = " FROM ";
	public static final String _WHERE = " WHERE ";
	public static final String _ORDER = " ORDER ";
	public static final String _GROUP = " GROUP ";
	public static final String _HAVING = " HAVING ";
	public static final String _BY = " BY ";
	public static final String _ASC = " ASC ";
	public static final String _DESC = " DESC ";

	public static final byte _before = 0;
	public static final byte _after = 1;

	/**
	 * 返回sqlStr 中的subid所在的位置(flag=0之前，flag=1之后)
	 * 
	 * @param sqlStr
	 *            输入的sql
	 * @param subid
	 *            关键字
	 * @param flag
	 *            _before = 0;_after = 1;
	 * @return 返回 subid所在的before/after位置
	 */
	public static int getPos(String sqlStr, String subid, byte flag) {
		try {
			int sign = 0;
			String tempStr;
			boolean isSingleQuotes = false;
			int isValueStr = 0;
			for (int i = 0; i < sqlStr.length() - subid.length(); i++) {
				if (sqlStr.charAt(i) == '\'') {
					if (isSingleQuotes) {
						isSingleQuotes = false;
						isValueStr--;
						continue;
					} else {
						isSingleQuotes = true;
						isValueStr++;
						continue;
					}
				}
				if (isSingleQuotes || isValueStr != 0) {
					continue;
				}
				if (sqlStr.charAt(i) == '(') {
					sign++;
				}
				if (sqlStr.charAt(i) == ')') {
					sign--;
				}
				tempStr = sqlStr.substring(i, i + subid.length());

				if (subid.equalsIgnoreCase(tempStr)) {
					if (sign == 0) {
						if (flag == QuerySqlParser._before) {
							return i;
						} else if (flag == QuerySqlParser._after) {
							return i + subid.length();
						} else {
							// after 默认是subid 之后的位置
							return i + subid.length();
						}
					}
				}
			}
			return -1;
		} catch (Exception ex) {
			return -1;
		}
	}

	/**
	 * private int _getAfterOrderPosition(String sqlStr) = getPos(sqlStr,
	 * QuerySqlParser._ORDER, QuerySqlParser._BY) private int
	 * _getAfterGroupPosition(String sqlStr) = getPos(sqlStr,
	 * QuerySqlParser._GROUP, QuerySqlParser._BY) 获取关键字 subid nextid 字段所在的位置
	 * 
	 * @param sqlStr
	 * @param subid
	 * @param nextid
	 * @return 获取关键字 subid nextid 字段所在的位置
	 */
	public static int getPos(String sqlStr, String subid, String nextid) {
		try {
			int sign = 0;
			String tempStr;
			boolean isSingleQuotes = false;
			int isValueStr = 0;
			for (int i = 0; i < sqlStr.length() - subid.length(); i++) {
				if (sqlStr.charAt(i) == '\'') {
					if (isSingleQuotes) {
						isSingleQuotes = false;
						isValueStr--;
						continue;
					} else {
						isSingleQuotes = true;
						isValueStr++;
						continue;
					}
				}
				if (isSingleQuotes || isValueStr != 0) {
					continue;
				}
				if (sqlStr.charAt(i) == '(') {
					sign++;
				}
				if (sqlStr.charAt(i) == ')') {
					sign--;
				}
				tempStr = sqlStr.substring(i, i + subid.length());

				if (subid.equalsIgnoreCase(tempStr)
						|| subid.equalsIgnoreCase(tempStr.trim())) {
					if (sign == 0) {
						return sqlStr.toUpperCase().indexOf(nextid, i)
								+ nextid.length();
					}
				}
			}
			return -1;
		} catch (Exception ex) {
			return -1;
		}
	}

	/**
	 * 取ASC/DESC之前的位置
	 * 
	 * @param sqlStr
	 *            输入的条件
	 * @return -1 异常不处理
	 */
	private static int _getBeforeAscPosition(String sqlStr) {
		try {
			int sign = 0;
			String tempStr;
			boolean isSingleQuotes = false;
			int isValueStr = 0;
			for (int i = 0; i < sqlStr.length() - " ASC".length() + 1; i++) {
				if (sqlStr.charAt(i) == '\'') {
					if (isSingleQuotes) {
						isSingleQuotes = false;
						isValueStr--;
						continue;
					} else {
						isSingleQuotes = true;
						isValueStr++;
						continue;
					}
				}
				if (isSingleQuotes || isValueStr != 0) {
					continue;
				}

				if (sqlStr.charAt(i) == '(') {
					sign++;
				}
				if (sqlStr.charAt(i) == ')') {
					sign--;
				}
				tempStr = sqlStr.substring(i, i + " ASC".length());

				if (" ASC".equalsIgnoreCase(tempStr)
						|| "ASC".equalsIgnoreCase(tempStr.trim())) {
					if (sign == 0) {
						return i;
					}
				}
			}

			sign = 0;
			isSingleQuotes = false;
			isValueStr = 0;
			for (int i = 0; i < sqlStr.length() - " DESC".length() + 1; i++) {
				if (sqlStr.charAt(i) == '\'') {
					if (isSingleQuotes) {
						isSingleQuotes = false;
						isValueStr--;
						continue;
					} else {
						isSingleQuotes = true;
						isValueStr++;
						continue;
					}
				}
				if (isSingleQuotes || isValueStr != 0) {
					continue;
				}

				if (sqlStr.charAt(i) == '(') {
					sign++;
				}
				if (sqlStr.charAt(i) == ')') {
					sign--;
				}
				tempStr = sqlStr.substring(i, i + " DESC".length());

				if (" DESC".equalsIgnoreCase(tempStr)
						|| "DESC".equalsIgnoreCase(tempStr.trim())) {
					if (sign == 0) {
						return i;
					}
				}
			}
			return -1;
		} catch (Exception ex) {
			log.error("#####################获取 排序字段起始位置出现错误　: "
					+ ex.getMessage());
			return -1;
		}
	}

	/**
	 * 取GROUP BY,ORDER BY 所在位置
	 * 
	 * @param sqlStr
	 * @return -1: 异常时返回-1
	 */
	private static int _getBeforeGroupOrderPosition(String sqlStr) {
		try {
			int sign = 0;
			String tempStr;
			boolean isSingleQuotes = false;
			int isValueStr = 0;
			for (int i = 0; i < sqlStr.length() - " ORDER ".length(); i++) {

				if (sqlStr.charAt(i) == '\'') {
					if (isSingleQuotes) {
						isSingleQuotes = false;
						isValueStr--;
						continue;
					} else {
						isSingleQuotes = true;
						isValueStr++;
						continue;
					}
				}
				if (isSingleQuotes || isValueStr != 0) {
					continue;
				}

				if (sqlStr.charAt(i) == '(') {
					sign++;
				}
				if (sqlStr.charAt(i) == ')') {
					sign--;
				}
				tempStr = sqlStr.substring(i, i + " ORDER ".length());
				
				if (" ORDER ".equalsIgnoreCase(tempStr)
						|| " GROUP ".equalsIgnoreCase(tempStr)) {
					if (sign == 0) {
						return i;
						// return sqlStr.toUpperCase().indexOf(" BY ", i) + 4;
					}
				}

//				if (" ORDER ".equalsIgnoreCase(tempStr)
//						|| " GROUP ".equalsIgnoreCase(tempStr)
//						|| "ORDER".equalsIgnoreCase(tempStr.trim())
//						|| "GROUP".equalsIgnoreCase(tempStr.trim())) {
//					if (sign == 0) {
//						return i;
//						// return sqlStr.toUpperCase().indexOf(" BY ", i) + 4;
//					}
//				}
			}
			return -1;
		} catch (Exception ex) {
			return -1;
		}
	}

	/**
	 * 取 WHERE ORDER BY GROUP BY之前 的位置 WHERE , GROUP , ORDER
	 * 
	 * @param sqlStr
	 * @return
	 */
	private static int _getBeforeWhereOrderGroupPosition(String sqlStr) {
		try {
			int sign = 0;
			String tempStr;
			boolean isSingleQuotes = false;
			int isValueStr = 0;
			for (int i = 0; i < sqlStr.length() - " WHERE ".length(); i++) {
				if (sqlStr.charAt(i) == '\'') {
					if (isSingleQuotes) {
						isSingleQuotes = false;
						isValueStr--;
						continue;
					} else {
						isSingleQuotes = true;
						isValueStr++;
						continue;
					}
				}
				if (isSingleQuotes || isValueStr != 0) {
					continue;
				}

				if (sqlStr.charAt(i) == '(') {
					sign++;
				}
				if (sqlStr.charAt(i) == ')') {
					sign--;
				}
				tempStr = sqlStr.substring(i, i + " WHERE ".length());

				if (" WHERE ".equalsIgnoreCase(tempStr)
						|| " GROUP ".equalsIgnoreCase(tempStr)
						|| " ORDER ".equalsIgnoreCase(tempStr)) {
					if (sign == 0) {
						return i;
					}
				}
			}
			return -1;
		} catch (Exception ex) {
			return -1;
		}
	}

	/**
	 * 获取select 条件
	 * 
	 * @param whersql
	 *            String
	 * @return 异常时返回 "*"
	 */
	public static String getSelect(String sqlStr) {
		if (StringUtil.isEmpty(sqlStr)) {
			return "";
		}
		String tempStr = sqlStr.toUpperCase();
		try {
			// from所在的位置
			int fromPos = getPos(sqlStr, QuerySqlParser._FROM,
					QuerySqlParser._before);
			if (fromPos == -1) {
				log.error("[getSelect].错误" + sqlStr + "], 没有from关键字!");
				return "";
			} else {
				return sqlStr.substring(tempStr.indexOf("SELECT ")
						+ "SELECT ".length(), fromPos);
			}
		} catch (Exception ex) {
			log.error("[getSelect]异常:" + ex.getMessage());
			return "*";
		}
	}

	/**
	 * @param sqlStr
	 * @return
	 * @throws Exception
	 */
	public static String getFrom(String sqlStr) throws Exception {
		if (StringUtil.isEmpty(sqlStr)) {
			return "";
		}
		try {
			String tempStr = sqlStr.toUpperCase();

			int fromPos = getPos(tempStr, QuerySqlParser._FROM,
					QuerySqlParser._after);

			if (fromPos == -1) {
				log.error("[getFrom].错误" + sqlStr + "], 没有from关键字!");
				return "";
			} else {
				int wgoPos = _getBeforeWhereOrderGroupPosition(tempStr);

				if (wgoPos == -1) { // where , group , order , having
					return sqlStr.substring(fromPos - 1);
				} else {
					return sqlStr.substring(fromPos - 1, wgoPos);
				}
			}
		} catch (Exception ex) {
			log.error("[getFrom] :" + ex.getMessage());
			return "";
		}
	}

	/**
	 * 获取 {WHERE} .. {group} {order} 之间的字符串
	 * 
	 * @param whersql
	 *            String
	 * @return String
	 */
	public static String getWhere(String sqlStr) {
		if (StringUtil.isEmpty(sqlStr)) {
			return "";
		}
		try {
			String tempStr = sqlStr.toUpperCase();
			// where 关键字之后的位置
			int wherePos = getPos(sqlStr, QuerySqlParser._WHERE,
					QuerySqlParser._after);

			if (wherePos == -1) {
				log.info("[getWhere]. " + sqlStr + "]");
				return "";
			} else {
				// 是否有排序/分组的情况
				int goPos = _getBeforeGroupOrderPosition(tempStr);

				if (goPos == -1) { // where , group , order ,没有考虑 having
					return sqlStr.substring(wherePos - 1);
				} else {
					return sqlStr.substring(wherePos - 1, goPos);
				}
			}
		} catch (Exception ex) {
			log.error("[getWhere] :" + ex.getMessage());
			return "";
		}
	}

	/**
	 * @param sqlStr
	 * @return
	 */
	private static String getQueryWhere(String sqlStr) {
		if (StringUtil.isEmpty(sqlStr)) {
			return "";
		}
		try {
			String tempStr = sqlStr.toUpperCase();
			int goPos = _getBeforeGroupOrderPosition(tempStr);

			if (goPos == -1) { // where , group , order , having
				return sqlStr;
			} else {
				return sqlStr.substring(0, goPos);
			}
		} catch (Exception ex) {
			log.error("[getWhere] :" + ex.getMessage());
			return "*";
		}
	}

	/**
	 * 获取group by之后的分组条件
	 * 
	 * @param sqlStr
	 * @return 异常时返回""
	 * @throws Exception
	 */
	public static String getGroup(String sqlStr) throws Exception {
		try {
			int groupPos = getPos(sqlStr, QuerySqlParser._GROUP,
					QuerySqlParser._BY);

			if (groupPos == -1) {
				return "";
			} else {
				int orderPos = getPos(sqlStr, QuerySqlParser._ORDER,
						QuerySqlParser._before);
				if (orderPos == -1) {
					return sqlStr.substring(groupPos - 1);
				} else {
					return sqlStr.substring(groupPos - 1, orderPos);
				}
			}
		} catch (Exception ex) {
			log.error("[getGroup] :" + ex.getMessage());
			return "";
		}
	}

	/**
	 * ORDER, ASC/DESC 获取排序条件
	 * 
	 * @param sqlstr
	 *            String
	 * @return String
	 */
	public static String getOrder(String sqlStr) {
		try {
			int orderPos = getPos(sqlStr, QuerySqlParser._ORDER,
					QuerySqlParser._BY);
			if (orderPos == -1) {
				return "";
			} else {
				int ascPos = _getBeforeAscPosition(sqlStr);
				if (ascPos == -1) {
					return sqlStr.substring(orderPos).trim();
				} else {
					return sqlStr.substring(orderPos, ascPos).trim();
				}
			}
		} catch (Exception ex) {
			log.error("[getOrder] :" + ex.getMessage());
			return "";
		}
	}

	/**
	 * /ASC/DESC
	 * 
	 * @param sqlStr
	 * @return
	 */
	public static String getAsc(String sqlStr) {
		try {
			int orderPos = _getBeforeAscPosition(sqlStr);
			if (orderPos == -1) {
				return "";
			} else {
				return sqlStr.substring(orderPos).trim();
			}
		} catch (Exception ex) {
			log.error("[getOrderAsc]异常:" + ex.getMessage());
			return "";
		}
	}

	/**
	 * 5. select count(*) from ... where ... 组合求总记录的sql语句
	 * 
	 * @param whersql
	 *            String
	 * @return String
	 */
	public static String getCountSql(String sqlStr) {
		String sqlselect = getSelect(sqlStr);
		int pos = sqlselect.toLowerCase().indexOf("distinct");
		String countcolumn = "*";
		if(pos != -1){
			//获取
			countcolumn = StringUtils.substringBefore(sqlselect, ",");
		}
		return getCountSql(sqlStr, countcolumn);
	}

	public static String getCountSql(String sqlStr, String countcolumn) {
		try {
			int fromPos = getPos(sqlStr, QuerySqlParser._FROM,
					QuerySqlParser._before);

			 int groupPos = getPos(sqlStr, QuerySqlParser._GROUP,
			 QuerySqlParser._before);
//			int groupPos = -1;

			int orderPos = getPos(sqlStr, QuerySqlParser._ORDER,
					QuerySqlParser._before);

			if (fromPos == -1) {
				log.error("[getCountSql]. 错误的sql " + sqlStr + "], 没有from关键字!");
				return "";
			} else {
				if (groupPos != -1) { // group, group
					return "select count(" + countcolumn + ") "
							+ sqlStr.substring(fromPos, groupPos) + " GROUP BY "+getGroup(sqlStr);
				} else if (orderPos != -1) {
					return "select count(" + countcolumn + ") "
							+ sqlStr.substring(fromPos, orderPos);
				} else {
					return "select count(" + countcolumn + ") "
							+ sqlStr.substring(fromPos);
				}
			}
		} catch (Exception ex) {
			log.error("[getCountSql]. 异常" + sqlStr + "]");
			return "";
		}
	}

	/**
	 * @desc 简单实现getCountSql
	 */
	/*
	 * public static String getCountSql(String sqlStr,String countcolumn) { try
	 * { int fromPos = getPos(sqlStr, QuerySqlParser._FROM,
	 * QuerySqlParser._before); // int groupPos = getPos(sqlStr,
	 * QuerySqlParser._GROUP, QuerySqlParser._before); int groupPos = -1;
	 * 
	 * int orderPos = getPos(sqlStr, QuerySqlParser._ORDER,
	 * QuerySqlParser._before);
	 * 
	 * if (fromPos == -1) { log.error("[getCountSql]. 错误的sql " + sqlStr + "],
	 * 没有from关键字!"); return ""; } else { if (groupPos != -1) { // group, group
	 * return "select count("+countcolumn+") " + sqlStr.substring(fromPos,
	 * groupPos); } else if (orderPos!=-1) { return
	 * "select count("+countcolumn+") " + sqlStr.substring(fromPos, orderPos); }
	 * else { return "select count("+countcolumn+") " +
	 * sqlStr.substring(fromPos); } } } catch (Exception ex) {
	 * log.error("[getCountSql]. 异常" + sqlStr + "]"); return ""; } }
	 */

	/**
	 * 
	 */
	public static String getQuerySqlStartWithoutWhere(String sqlStr,
			String appendWhere) {
		try {
			if (appendWhere.trim().toLowerCase().indexOf("where") == 0) {
				try {
					int pos = appendWhere.toLowerCase().indexOf("where ");
					if (pos != -1) {
						appendWhere = appendWhere.substring(pos
								+ "where ".length());
					}
				} catch (Exception ex) {

				}
			}

			/** ***去掉前置的'and'[add by hjjiang]**** */
			int andpos = appendWhere.trim().toLowerCase().indexOf("and");
			if (andpos == 0) {
				appendWhere = appendWhere.substring(4);
			}

			/**
			 * where order by asc/desc
			 */
			String appWhere = getQueryWhere(appendWhere);// 动态查询的where
			String appOrder = getOrder(appendWhere);
			String appAsc = getAsc(appendWhere);

			String sqlselect = getSelect(sqlStr); // SELECT
			String sqlfrom = getFrom(sqlStr); // FROM
			String sqlwhere = getWhere(sqlStr); // 原有的WHERE 条件
			String sqlgroup = getGroup(sqlStr); // GROUP 分组条件
			String sqlorder = getOrder(sqlStr); // ORDER ASC/DESC

			String sqlorderasc = getAsc(sqlStr); // ORDER ASC/DESC
			StringBuffer opSql = new StringBuffer(sqlselect.length() * 2);
			opSql.append("SELECT ").append(sqlselect).append(" FROM ").append(
					sqlfrom);
			if (StringUtil.isEmpty(appWhere)) {
				if (StringUtil.isNotEmpty(sqlwhere)) {
					opSql.append(" WHERE " + sqlwhere);
				}
			} else {
				if (StringUtil.isNotEmpty(sqlwhere)) {
					opSql.append(" WHERE (" + sqlwhere + ") AND (" + appWhere
							+ ")");
				} else {
					opSql.append(" WHERE " + appWhere);
				}
			}

			if (StringUtil.isNotEmpty(sqlgroup)) {
				opSql.append(" GROUP BY " + sqlgroup);
			}
			if (StringUtil.isNotEmpty(sqlorder)) {
				if (StringUtil.isEmpty(appOrder) || StringUtil.isEmpty(appAsc)) {
					opSql.append(" ORDER BY " + sqlorder + " " + sqlorderasc);
				} else {
					opSql.append(" ORDER BY " + appOrder + " " + appAsc);
				}
			} else {
				if (StringUtil.isNotEmpty(appOrder)
						&& StringUtil.isNotEmpty(appAsc)) {
					opSql.append(" ORDER BY " + appOrder + " " + appAsc);
				}
			}
			return opSql.toString();
		} catch (Exception ex) {
			log.error("[getQuerySql]. 错误：" + sqlStr + "]");
			return sqlStr;
		}
	}

	/**
	 * select ... from ... where ... order...
	 * 
	 * @param sqlStr
	 *            String
	 * @param appendWhere
	 *            String
	 * @return String
	 */
	public static String getQuerySql(String sqlStr, String appendWhere) {
		try {
			if (appendWhere == null || "".equals(appendWhere.trim())) {
				return sqlStr;
			}
			if (appendWhere.trim().toLowerCase().indexOf("where") == 0) {
				try {
					int pos = appendWhere.toLowerCase().indexOf("where ");
					if (pos != -1) {
						appendWhere = appendWhere.substring(pos
								+ "where ".length());
					}
				} catch (Exception ex) {

				}
			}

			/** ***去掉前置的'and'[]**** */
			int andpos = appendWhere.trim().toLowerCase().indexOf("and");
			if (andpos == 0) {
				appendWhere = appendWhere.substring(4);
			}

			/**
			 * where order by asc/desc
			 */
			String appWhere = getQueryWhere(appendWhere);// 动态查询的where
			String appOrder = getOrder(appendWhere);
			String appAsc = getAsc(appendWhere);

			String sqlselect = getSelect(sqlStr); // SELECT
			String sqlfrom = getFrom(sqlStr); // FROM
			String sqlwhere = getWhere(sqlStr); // 原有的WHERE 条件
			String sqlgroup = getGroup(sqlStr); // GROUP 分组条件
			String sqlorder = getOrder(sqlStr); // ORDER ASC/DESC

			String sqlorderasc = getAsc(sqlStr); // ORDER ASC/DESC
			StringBuffer opSql = new StringBuffer(sqlselect.length() * 2);
			opSql.append("SELECT ").append(sqlselect).append(" FROM ").append(
					sqlfrom);
			if (StringUtil.isEmpty(appWhere)) {
				if (StringUtil.isNotEmpty(sqlwhere)) {
					opSql.append(" WHERE " + sqlwhere);
				}
			} else {
				if (StringUtil.isNotEmpty(sqlwhere)) {
					opSql.append(" WHERE (" + sqlwhere + ") AND (" + appWhere
							+ ")");
				} else {
					opSql.append(" WHERE " + appWhere);
				}
			}

			if (StringUtil.isNotEmpty(sqlgroup)) {
				opSql.append(" GROUP BY " + sqlgroup);
			}
			if (StringUtil.isNotEmpty(sqlorder)) {
				if (StringUtil.isEmpty(appOrder) || StringUtil.isEmpty(appAsc)) {
					opSql.append(" ORDER BY " + sqlorder + " " + sqlorderasc);
				} else {
					opSql.append(" ORDER BY " + appOrder + " " + appAsc);
				}
			} else {
				if (StringUtil.isNotEmpty(appOrder)
						&& StringUtil.isNotEmpty(appAsc)) {
					opSql.append(" ORDER BY " + appOrder + " " + appAsc);
				}
			}
			return opSql.toString();
		} catch (Exception ex) {
			log.error("[getQuerySql]. 错误：" + sqlStr + "]");
			return sqlStr;
		}
	}

	public static String getQuerySqlMultiOrder(String sqlStr, String appendWhere) {
		try {
			try {
				int pos = appendWhere.toLowerCase().indexOf("where ");
				if (pos != -1) {
					appendWhere = appendWhere
							.substring(pos + "where ".length());
				}
			} catch (Exception ex) {

			}
			/**
			 * where order by asc/desc
			 */
			String appWhere = getQueryWhere(appendWhere);
			String appOrder = getOrder(appendWhere);
			String appAsc = getAsc(appendWhere);

			String sqlselect = getSelect(sqlStr); // SELECT
			String sqlfrom = getFrom(sqlStr); // FROM
			String sqlwhere = getWhere(sqlStr); // WHERE
			String sqlgroup = getGroup(sqlStr); // GROUP
			String sqlorder = getOrder(sqlStr); // ORDER ASC/DESC

			String sqlorderasc = getAsc(sqlStr); // ORDER ASC/DESC
			StringBuffer opSql = new StringBuffer(sqlselect.length() * 2);
			opSql.append("SELECT ").append(sqlselect).append(" FROM ").append(
					sqlfrom);
			if (StringUtil.isEmpty(appWhere)) {
				if (StringUtil.isNotEmpty(sqlwhere)) {
					opSql.append(" WHERE " + sqlwhere);
				}
			} else {
				if (StringUtil.isNotEmpty(sqlwhere)) {
					opSql.append(" WHERE (" + sqlwhere + ") AND (" + appWhere
							+ ")");
				} else {
					opSql.append(" WHERE " + appWhere);
				}
			}

			if (StringUtil.isNotEmpty(sqlgroup)) {
				opSql.append(" GROUP BY " + sqlgroup);
			}
			if (StringUtil.isNotEmpty(sqlorder)) {
				if (StringUtil.isEmpty(appOrder) || StringUtil.isEmpty(appAsc)) {
					opSql.append(" ORDER BY " + sqlorder + " " + sqlorderasc);
				} else {
					opSql.append(" ORDER BY " + appOrder + " " + appAsc);
				}
			} else {
				if (StringUtil.isNotEmpty(appOrder)
						&& StringUtil.isNotEmpty(appAsc)) {
					opSql.append(" ORDER BY " + appOrder + " " + appAsc);
				}
			}
			return opSql.toString();
		} catch (Exception ex) {
			log.error("[getQuerySqlMultiOrder].  " + sqlStr + "]");
			return sqlStr;
		}
	}

	public static boolean isFilterGroupBy(String sqlStr) {
		String groupby = " GROUP BY ";
		String tempStr;
		boolean isFilter = false;

		for (int i = 0, len = sqlStr.length() - groupby.length(); i < len; i++) {
			tempStr = sqlStr.substring(i, i + groupby.length());

			if (tempStr.equalsIgnoreCase(groupby)) {
				if (!isGroupByInline(i + groupby.length(), i, sqlStr)) {
					isFilter = true;
					break;
				}
			}
		}

		return isFilter;
	}

	private static boolean isGroupByInline(int pos1, int pos2, String sqlStr) {
		boolean isInline = false;
		boolean isRightExists = false;
		boolean isLeftExists = false;

		for (int i = pos1, len = sqlStr.length(), sign = 0; i < len; i++) {
			if (sqlStr.charAt(i) == '(') {
				sign++;
			} else if (sqlStr.charAt(i) == ')') {
				if (sign == 0) {
					isRightExists = true;
					break;
				} else if (sign > 0) {
					sign--;
				}
			}
		}

		if (isRightExists) {
			for (int i = pos2, sign = 0; i > 0; i--) {
				if (sqlStr.charAt(i) == ')') {
					sign++;
				} else if (sqlStr.charAt(i) == '(') {
					if (sign == 0) {
						isLeftExists = true;
						break;
					} else if (sign > 0) {
						sign--;
					}
				}
			}
		}

		if (isLeftExists && isRightExists) {
			isInline = true;
		}

		return isInline;
	}

	public static void main(String[] args) {
		try {
			String sqlstr = " select a, b, c, (select x, y, decode(z,1,'A', 2, 'B', 'C'), name, password, to_char(birthdate, 'yyyy-mm-dd') as bbdate from m, n where m.x=n.y) "
					+ "from t1, t2, t3 "
					+ "where t1.a=t2.a and t1.a=t3.a and t2.c=t3.c "
					+ "group by a, x, z " + "order by z asc, y desc, b asc";

			System.out.println("\nSELECT  = \n"
					+ QuerySqlParser.getSelect(sqlstr));
			System.out.println("\nWHERE   = \n"
					+ QuerySqlParser.getWhere(sqlstr));
			System.out.println("\nGROUP   = \n"
					+ QuerySqlParser.getGroup(sqlstr));
			System.out.println("\nORDER   = \n"
					+ QuerySqlParser.getOrder(sqlstr));
			System.out
					.println("\nORDERASC= \n" + QuerySqlParser.getAsc(sqlstr));

			System.out.println("\nCOUNT SQL GROUP = \n"
					+ QuerySqlParser.getCountSql(sqlstr));

			System.out
					.println("\nCOUNT SQL ORDER = \n"
							+ QuerySqlParser
									.getCountSql(" select distinct a, b, c, (select x, y, decode(z,1,'A', 2, 'B', 'C'), name, password, to_char(birthdate, 'yyyy-mm-dd') as bbdate from m, n where m.x=n.y) "
											+ "from t1, t2, t3 "
											+ "where t1.a=t2.a and t1.a=t3.a and t2.c=t3.c group by z,y,x order by z, y, b desc "));

			System.out.println("\nOPSQL   = \n"
					+ QuerySqlParser.getQuerySql(sqlstr, "where x=1 and y=2"));
			

		} catch (Exception ex) {
		}
	}
}
