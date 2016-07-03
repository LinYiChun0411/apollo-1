package org.jay.frame.jdbc.model;

public interface User {
	/**
	 * 用户id
	 * @return
	 */
	public Long getSysUserId();
	/**
	 * 用户登录名
	 * @return
	 */
	public String getLoginAccount();
}
