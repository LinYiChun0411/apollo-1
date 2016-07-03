package org.jay.frame.util;

import java.util.HashMap;
import java.util.Set;

import org.jay.frame.jdbc.Constants;



public class SqlUtil {
	/* DBType(1-ORACLE,2-SYBASE,3-SQLSERVER,4-DB2,5-MySql) */
	public static int DBType = 0;

	public static final String SELECT = "select";

	public static final String WHERE = "where";

	public static final String FROM = "from";

	public static final String BLANK = " ";

	private SqlUtil() {
	}

	/**
	 * 
	 */
	public static String substr() {
		String sql = "";
		switch (SqlUtil.DBType) {
			case Constants.SYBASE:
			case Constants.SQLSERVER:
			case Constants.ORACLE:
			case Constants.DB2:
				sql = "SUBSTR";
				break;
			case Constants.MYSQL:
			case Constants.POSGRESQL:
				sql = "SUBSTRING";
				break;
		}
		return sql;
	}

	/**
	 * 
	 */
	public static String len() {
		String sql = "";
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
			case Constants.MYSQL:
				sql = "LENGTH";
				break;
			case Constants.POSGRESQL:	
			case Constants.SYBASE:
				sql = "CHAR_LENGTH";
				break;
			case Constants.SQLSERVER:
				sql = "LEN";
				break;
			case Constants.DB2:
				sql = "LENGTH";
				break;
		}
		return sql;
	}

	/**
	 * 
	 */
	public static String mod(String num1, String divisor) {
		String sql = "";
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
			case Constants.DB2:
			case Constants.MYSQL:
			case Constants.POSGRESQL:
				sql = "MOD(" + num1 + ", " + divisor + ")";
				break;
			case Constants.SYBASE:
			case Constants.SQLSERVER:
				sql = num1 + " % " + divisor;
				break;
		}
		return sql;
	}

	/**
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static String linkTable(String A, String B) {
		String sql = "";
		if (SqlUtil.DBType == Constants.MYSQL) {
			throw new RuntimeException("mysql不支持,JoinUtil");
		}
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
				sql = A + " = " + B + "(+)";
				break;
			case Constants.SYBASE:
			case Constants.SQLSERVER:
				sql = A + " *= " + B;
				break;
			case Constants.DB2:
				sql = A + " = " + B;
				break;
		}

		return sql;
	}

	/**
	 */
	public static String linkColumn() {
		if (SqlUtil.DBType == Constants.MYSQL) {
			throw new RuntimeException("mysql不支持,linkColumn(col1,col2,...)");
		}
		String sql = "";
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
			case Constants.DB2:
				sql = "||";
				break;
			case Constants.SYBASE:
			case Constants.SQLSERVER:
				sql = "+";
				break;
		}
		return sql;
	}

	/**
	 * 
	 */
	private static String isNull() {
		String sql = "";
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
				sql = "Nvl";
				break;
			case Constants.DB2:
			case Constants.MYSQL:
			case Constants.SYBASE:
			case Constants.SQLSERVER:
				sql = "IsNull";
				break;
		}
		return sql;
	}

	/**
	 * 
	 * @param fieldName
	 * @param consMap
	 * @param defValue
	 * @return
	 */
	public static String condition(String fieldName, HashMap consMap, String defValue) {
		if (consMap == null || consMap.isEmpty()) {
			return "";
		}

		Set keySet = consMap.keySet();
		Object[] keyArray = keySet.toArray();
		if (keyArray.length < 1) {
			return "";
		}

		StringBuffer conditions = new StringBuffer();

		switch (SqlUtil.DBType) {
			case Constants.ORACLE: {
				conditions.append("DECODE(").append(fieldName).append(",");

				for (int i = 0; i < keyArray.length; i++) {
					conditions.append((String) keyArray[i]).append(",").append(consMap.get(keyArray[i])).append(",");
				}
				conditions.append(defValue).append(")");
				break;
			}
			case Constants.SYBASE:
			case Constants.SQLSERVER:
			case Constants.MYSQL:
			case Constants.DB2: {
				conditions.append(" CASE ");
				String nullValue = String.valueOf(consMap.get(null));

				if (nullValue == null || "".equals(nullValue)) {
					conditions.append(fieldName).append(" ");
					for (int i = 0; i < keyArray.length; i++) {
						conditions.append(" WHEN ").append((String) keyArray[i]).append(" THEN ")
						          .append(consMap.get(keyArray[i])).append(" ");
					}
				} else {
					for (int i = 0; i < keyArray.length; i++) {
						conditions.append(" WHEN ").append(fieldName);
						if ((String) keyArray[i] == null) {
							conditions.append(" IS NULL THEN ").append(nullValue);
						} else {
							conditions.append(" = ").append((String) keyArray[i]).append(" THEN ")
							          .append(consMap.get(keyArray[i])).append(" ");
						}

					}
				}

				conditions.append(" ELSE ").append(defValue).append(" END ");
				break;
			}
		}
		return conditions.toString();
	}

	/**
	 * 
	 * @author: chenzp
	 */
	public static String removeStrInColumn(String column, String removeStr) {
		return SqlUtil.replace(column, removeStr, "''");
	}

	/**
	 * 
	 * @author: chenzp
	 */
	public static String replace(String column, String oldStr, String newStr) {
		StringBuffer sql = new StringBuffer();
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
			case Constants.DB2:
			case Constants.MYSQL:
				sql.append("REPLACE(").append(column).append(",").append(oldStr).append(",").append(newStr).append(")");
				break;
			case Constants.SYBASE:
				break;
			case Constants.SQLSERVER:
				sql.append("STUFF(").append(column).append(",").append(SqlUtil.pos(column, oldStr, 0)).append(",")
				   .append(oldStr.length()).append(",").append(newStr).append(")");
				break;
		}
		return sql.toString();
	}

	/**
	 * 
	 * @author: chenzp
	 */
	public static String substr(String column, int start) {
		return SqlUtil.substr(column, start, -1);
	}

	/**
	 * 
	 * @author: chenzp
	 */
	public static String substr(String column, int start, int len) {
		return new StringBuffer().append(SqlUtil.substr()).append("(").append(column).append(",").append(start)
		                         .append((len > 0 ? "," + len : "")).append(")").toString();
	}

	/**
	 * 
	 * @author: chenzp
	 */
	public static String substr(String column, String start) {
		return SqlUtil.substr(column, start, null);
	}

	public static String substr(String column, String start, String len) {
		return new StringBuffer().append(SqlUtil.substr()).append("(").append(column).append(",").append(start)
		                         .append((len != null ? "," + len : "")).append(")").toString();
	}

	/**
	 * 字段的值长度
	 * 
	 * @author: chenzp
	 */
	public static String len(String column) {
		return SqlUtil.len() + "(" + column + ")";
	}

	/**
	 * 连接列
	 * 
	 * @author: chenzp
	 */
	public static String linkColumn(String col1, String col2) {
		if (SqlUtil.DBType == Constants.MYSQL) {
			return new StringBuffer("CONCAT(").append(col1).append(",").append(col2).append(")").toString();
		}
		return new StringBuffer(16).append(col1).append(SqlUtil.linkColumn()).append(col2).toString();
	}

	/**
	 * 连接列
	 * 
	 * @author: chenzp
	 */
	public static String linkColumn(String[] cols) {
		StringBuffer sql = new StringBuffer();
		if (SqlUtil.DBType == Constants.MYSQL) {
			for (int i = 0; i < cols.length; i++) {
				sql.append(i == 0 ? "CONCAT(" : ",").append(cols[i]);
				sql.append(i == cols.length - 1 ? "" : ")");
			}
		} else {
			for (int i = 0; i < cols.length; i++) {
				sql.append(i == 0 ? "" : SqlUtil.linkColumn()).append(cols[i]);
			}
		}
		return sql.toString();
	}

	/**
	 *是否为空
	 * 
	 * @author: chenzp
	 */
	public static String isNull(String column, String notNullValue, String nullValue) {
		if (SqlUtil.DBType == Constants.ORACLE) {
			return new StringBuffer("NVL2(").append(column).append(",").append(notNullValue).append(",")
			                                .append(nullValue).append(")").toString();
		} else {
			HashMap caseMap = new HashMap();
			caseMap.put(null, nullValue);
			return SqlUtil.condition(column, caseMap, notNullValue);
		}
	}

	/**
	 * 是否为空
	 * 
	 * @author: chenzp
	 */
	public static String isNull(String column, String nullValue) {
		if (SqlUtil.DBType == Constants.DB2)
			return isNull(column, column, nullValue);
		else
			return SqlUtil.isNull() + "(" + column + "," + nullValue + ")";
	}

	/**
	 * 从pos起，查询column中是否含searchChar
	 * 
	 * @author: chenzp
	 */
	public static String pos(String column, String searchChar, int pos) {
		StringBuffer sql = new StringBuffer();
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
			case Constants.MYSQL:
				sql.append("INSTR(").append(column).append(",").append(searchChar).append(",").append(pos).append(")");
				break;
			case Constants.SYBASE:
			case Constants.SQLSERVER:
			case Constants.DB2:
				sql.append("LOCATE(").append(searchChar).append(",").append(column).append(",").append(pos).append(")");
				break;
		}
		return sql.toString();
	}

	/**
	 * 各类数据库的函数名称toNumber
	 * 
	 * @author: chenzp
	 */
	public static String toNumber() {
		String sql = "";
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
				sql = "TO_NUMBER";
				break;
			case 2:
			case Constants.SQLSERVER:
				sql = "CONVERT";
				break;
			case Constants.DB2:
			case Constants.MYSQL:
				sql = "CAST";
				break;
		}
		return sql;
	}

	/**
	 * 
	 * 
	 * @author: chenzp
	 */
	public static String toNumber(String column) {
		return toNumber() + "(" + column + " as char )";
	}

	/**
	 * 转换为数字
	 * 
	 * @author: HuangYy
	 */
	public static String toNumber(String column, int length) {
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
				return toNumber() + "(" + column + ")";
			case Constants.SYBASE:
			case Constants.MYSQL:
			case Constants.SQLSERVER:
			case Constants.DB2:
				return toNumber() + "(" + column + " as char(" + length + ") )";
		}
		return "";
	}

	/**
	 * 在字段前面添加 appendStr
	 * 
	 * @author: chenzp
	 */
	public static String appendBefore(String column, int totalLen, String appendStr) {
		StringBuffer sql = new StringBuffer();
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
			case Constants.MYSQL:
				sql = sql.append("LPAD(").append(column).append(",").append(totalLen).append(",").append(appendStr)
				         .append(")");
				break;
			case Constants.SYBASE:
			case Constants.SQLSERVER:
			case Constants.DB2:
				sql = sql.append("INSERT(").append(column).append(",1,0,REPEAT(").append(appendStr).append(",")
				         .append(totalLen).append("-").append(SqlUtil.len(column)).append("))");
				break;
		}
		return sql.toString();
	}

	/**
	 * 各类数据库的分页语句
	 * 
	 * @param sql
	 * @param firstNum
	 * @param lastNum
	 * @return
	 */
	public static String getLimitString(String sql, int firstNum, int lastNum) {
		switch (SqlUtil.DBType) {
			case Constants.ORACLE:
				return getOracleLimitString(sql, firstNum, lastNum);
			case Constants.SYBASE:
				return getSqlServerLimitString(sql, firstNum, lastNum);
			case Constants.SQLSERVER:
				return getDB2LimitString(sql, firstNum, lastNum);
			case Constants.DB2:
				return getDB2LimitString(sql, firstNum, lastNum);
			case Constants.MYSQL:
				return getMySQLLimitString(sql, firstNum, lastNum);
			case Constants.POSGRESQL:
				return getPostgreLimitString(sql, firstNum, lastNum);
			default:
				return getOracleLimitString(sql, firstNum, lastNum);
		}
	}
	
	/**
	 * mysql分页
	 * 
	 * @param sql
	 * @param firstNum
	 * @param lastNum
	 * @return
	 */
	public static String getMySQLLimitString(String sql, int firstNum, int lastNum) {
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 16);
		pagingSelect.append(sql).append(" LIMIT ");
		pagingSelect.append(firstNum).append(",").append(lastNum - firstNum);
		return pagingSelect.toString();
	}
	
	/**
	 * postgre分页
	 * 
	 * @param sql
	 * @param firstNum
	 * @param lastNum
	 * @return
	 */
	public static String getPostgreLimitString(String sql, int firstNum, int lastNum) {
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 16);
		pagingSelect.append(sql).append(" LIMIT ");
		pagingSelect.append(lastNum - firstNum).append(" OFFSET ").append(firstNum);
		return pagingSelect.toString();
	}

	/**
	 * oracle分页
	 * 
	 * @param sql
	 * @param firstNum
	 * @param lastNum
	 * @return
	 */
	public static String getOracleLimitString(String sql, int firstNum, int lastNum) {
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 120);
		pagingSelect.append("SELECT * FROM ( ").append(" SELECT A.*, rownum r ").append(" FROM ( ");
		pagingSelect.append(sql).append(" ) A ").append(" WHERE rownum <= ");
		pagingSelect.append(lastNum).append("  ) B ").append(" WHERE r > ").append(firstNum);
		return pagingSelect.toString();
	}

	/**
	 * db2分页
	 * 
	 * @param sql
	 * @param firstNum
	 * @param lastNum
	 * @return
	 */
	public static String getDB2LimitString(String sql, int firstNum, int lastNum) {
		StringBuffer rownumber = new StringBuffer(50).append(" rownumber() over(");
		int orderByIndex = sql.toLowerCase().indexOf("order by");
		if (orderByIndex > 0) {
			rownumber.append(sql.substring(orderByIndex));
		}
		rownumber.append(") as rownum,");
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 120);
		pagingSelect.append("select * from ( ").append(sql);
		pagingSelect.insert(getAfterSelectInsertPoint(sql), rownumber.toString()).append(" ) as temp where rownum ");
		pagingSelect.append("between ").append(firstNum + 1).append(" and ").append(lastNum);

		return pagingSelect.toString();
	}

	/**
	 * sqlserver的分页查询
	 * 
	 * @param querySelect
	 * @param limit
	 * @return
	 */
	public static String getSqlServerLimitString(String querySelect, int limit) {
		querySelect = querySelect.trim();
		return new StringBuffer(querySelect.length() + 6).append(querySelect)
		                                                 .insert(getAfterSelectInsertPoint(querySelect),
		                                                         " top " + limit).toString().trim();

	}

	public static String getSqlServerLimitString(String sql, int startIndex, int endIndex) {
		sql = sql.trim();
		int m = getAfterSelectInsertPoint(sql);
		int n = sql.indexOf(",", m);
		int l = sql.toLowerCase().indexOf("from", m);
		String key = sql.substring(m + 1, n).trim();
		String temp = sql.substring(0, m + 1) + " " + key + " " + sql.substring(l);
		StringBuffer querySql = new StringBuffer();
		querySql.append(getSqlServerLimitString(sql, endIndex)).append(" and  ");
		querySql.append(key).append(" not in ( ").append(getSqlServerLimitString(temp, startIndex)).append(" ) ");
		return querySql.toString();
	}

	/**
	 * @param sql
	 * @return
	 */
	public static int getAfterSelectInsertPoint(String sql) {
		String sqlStr = sql.trim().toLowerCase();
		return sqlStr.startsWith("select distinct") ? 15 : 6;
	}

}
