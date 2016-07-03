package com.game.core;

public class SystemConfig {
	/**
	 * 总控后台域名
	 */
	public static String CONTROL_DOMAIN = null;

	/**
	 * 代理平台首页浏览器访问路径
	 */
	public final static String AGENT_CONTROL_PATH = "/agent";
	/**
	 * 总控后台首页浏览器访问路径
	 */
	public final static String ADMIN_CONTROL_PATH = "/admin";
	/**
	 * 手机端首页浏览器访问路径
	 */
	public final static String MOBILE_CONTROL_PATH = "/mobile";

	/**
	 * 会员资源目录 存放css、js、jsp 图片等
	 */
	public final static String SOURCE_FOLDER_MEMBER = "/member";

	/**
	 * 总控后台资源目录 存放css、js、jsp 图片等
	 */
	public final static String SOURCE_FOLDER_ADMIN = "/admin2";

	/**
	 * 代理后台资源目录 存放css、js、jsp 图片等
	 */
	public final static String SOURCE_FOLDER_AGENT = "/agent";

	/**
	 * 手机端资源目录 存放css、js、jsp 图片等
	 */
	public final static String SOURCE_FOLDER_MOBILE = "/mobile";

	/**
	 * 公共资源目录 存放css、js、jsp 图片等
	 */
	public static String SOURCE_FOLDER_COMMON = "/common";

	/**
	 * 总控用户 session key
	 */
	public final static String SESSION_ADMIN_KEY = "USER_SESSION_ADMIN";

	/**
	 * 会员账号 session key
	 */
	public final static String SESSION_MEMBER_KEY = "USER_SESSION_MEMBER";

	/**
	 * 代理账号 session key
	 */
	public final static String SESSION_AGENT_KEY = "USER_SESSION_AGENT";
}
