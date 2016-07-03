package org.jay.frame.jdbc;

import java.nio.charset.Charset;

public class Constants {
	
	public static final String SYS_SESSION_TEMP = "TEMP_";
	public static final String SYS_SESSION_USER = "SYS_USER_SESSION";
	public static final String WebFrameNameSpace = "hh_";
	public static final String SYS_USER = WebFrameNameSpace+"user";
	public static final String SYS_COMPANY = WebFrameNameSpace+"company";
	public static final String SYS_REST = WebFrameNameSpace+"restaurant";
	public static final String SYS_MENU = WebFrameNameSpace + "menu";
	public static final String SYS_VERI_CODE = WebFrameNameSpace + "verification_code";
	public static final String SYS_SALE_TYPE = WebFrameNameSpace + "sale_type";
	/**
	 * SYS_EXCEPT_CODE 用于表示用户登录的时候是否需要验证
	 *                                          当用户刚注册完的时候 登录后台是不需要验证的
	 */
	public static final String SYS_EXCEPT_CODE = WebFrameNameSpace + "except_code";
	public static final String SYS_SESSION_COMPANY = "SYS_COMPANY_SESSION";
	public static final String SYS_SESSION_ROLE = "SYS_SESSION_ROLE";
	public static final String SYS_SESSION_USER_CODE = "SYS_SESSION_USER_CODE";
	public static final String SYS_PAGE_ENCODE = "UTF-8";
	public static final Charset CHARSET_UTF8 = Charset.forName(SYS_PAGE_ENCODE);
	public static final String SYS_DATE_FORMAT = "yyyy-MM-dd";
	public static final String SYS_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String MYSQL_DATE_TO_STR = "%Y-%m-%d";
	
	public static final String SYS_AJAX_RESULT_FLAG = "success";
	public static final String SYS_AJAX_RESULT_MESSAGE = "msg";
	public static final String SYS_AJAX_RESULT_DATA = "data";
	public static final String RELOG_FLAG_KEY = "login";
	public static final String SESSION_OUT = "session_out";
	public static final String NO_AUTHORITY_ACCESS = "no_authority_access"; // 访问限制
	
	/**
	 * 分页查询的获取方式 1---查询处总记录；再查询分页数据 2---滚动指针法；断开连接取结果集 3---也是滚动指针，利用hibernate内置的方法
	 */
	public final static int COUNT_MODE = 1;

	public final static int SCROLL_MODE = 2;

	public final static int LIST_MODE = 3;
	
	
	public static final String INSERT_ACTION = "insert";
	public static final String UPDATE_ACTION = "update";
	public static final String CHECK_ACTION = "check";
	public static final String DELETE_ACTION = "delete";
	/**
	 * 可配置的常量 DBType(数据库类型：1为ORACLE,2为SYBASE,3为SQLSERVER,4为DB2,5为MYSQL,6为POSGRESQL)
	 */
	public static final int ORACLE = 1;
	public static final int SYBASE = 2;
	public static final int SQLSERVER = 3;
	public static final int DB2 = 4;
	public static final int MYSQL = 5;
	public static final int POSGRESQL = 6;
	
	public static final String SORT_TYPE_ASC = "asc";
	public static final String SORT_TYPE_DESC = "desc";

	public static final String NEWLINE = "\n";
	public static final String NULLSTR = "  ";
	public static final String EMPTYSTR = "";
	public static final String SEPATOR_ITEM = ", ";
	public static final String SEPATOR_SINGLE_LINE = "|";
	public static final String SEPATOR_DOUBLE_LINE = "||";
	
	
	public static final String TXT_FILENAME=".txt";
	
	/**
	 * 批量处理数
	 */
	public final static int DEFAULT_BATCH_SIZE = 20;

	/**
	 * 树的初始层级,默认为1
	 */
	public static final int TREE_INIT_LEVEL = 1;
	
}
