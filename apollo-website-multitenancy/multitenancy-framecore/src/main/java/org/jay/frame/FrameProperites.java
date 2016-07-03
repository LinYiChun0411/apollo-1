package org.jay.frame;

import org.jay.frame.jdbc.DBType;
import org.jay.frame.jdbc.Page;

public class FrameProperites {
	/**
	 * 执行jdbc时控制台显示SQL语句
	 */
	public static boolean JDBC_SHOW_SQL = false;
	
	
	/**
	 * 线程变量 数据回调接口
	 */
	public static String THREAD_DATA_CALLBACK_CLASS = null;
	
	/**
	 * 异常处理类
	 */
	public static String EXCEPTION_HANDLER_CLASS = "org.jay.frame.exception.DefaultExceptionHandler";
	
	/**
	 * 页码起始位置 默认为true
	 * true:页码从0开始计算，
	 * false:页码从1开始计算
	 */
	public static boolean PAGING_PAGE_NUMBER_START_FROM_ZERO = false;
	
	/**
	 * 自定义生成 pageNo 、pageSize的回调类
	 */
	public static String PAGING_CALLBACK_CLASS = null;
	
	/**
	 * 默认分页条数
	 * request中没有找到pageSize的值，默认分页条数将生效
	 */
	public static int PAGING_DEFAULT_PAGE_SIZE = 20;
	
	/**
	 * 分页参数：页码
	 */
	public static String PAGING_PARAM_PAGE_NUMBER = "pageNo";
	/**
	 * 分页参数：每页条数
	 */
	public static String PAGING_PARAM_PAGE_SIZE = "pageSize";
	
	/**
	 * {@link Page} toJsonStr() 方法转成json总条数的参数名
	 */
	public static String PAGING_JSON_TOTAL = "total";
	/**
	 * {@link Page} toJsonStr() 方法转成json数据集合的参数名
	 */
	public static String PAGING_JSON_ROWS = "rows";
	
	/**
	 * 数据库类型
	 */
	public static DBType DB_TYPE = null;
}
