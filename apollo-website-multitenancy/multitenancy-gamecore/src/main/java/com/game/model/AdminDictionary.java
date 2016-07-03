package com.game.model;

public class AdminDictionary  {
	
	/**
	 * 用户状态
	 */
	public static final long USER_STATUS_TYPE = 1L;
	/**
	 * 用户禁用
	 */
	public static final long USER_STATUS_DISABLED = 1L;
	/**
	 * 用户启用
	 */
	public static final long USER_STATUS_ENABLED = 2L;
	
	/**
	 * 用户分组
	 */
	public static final long USER_GROUP_TYPE = 2L;
	
	/**
	 * 用户ROOT
	 */
	public static final long USER_GROUP_ROOT = 0L;
	/**
	 * 超级管理员
	 */
	public static final long USER_GROUP_SUPER_MANAGER = 1L;
	/**
	 * 管理员
	 */
	public static final long USER_GROUP_MANAGER = 2L;
	
	/**
	 * 用户ROOT
	 */
	public static final long ACCOUNT_TYPE_DDGD = 2L;
}