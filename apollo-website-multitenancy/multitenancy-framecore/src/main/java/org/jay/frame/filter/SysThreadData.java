package org.jay.frame.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jay.frame.jdbc.model.User;


public class SysThreadData {
	//当前系统用户
	private User sysUser;
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private HttpSession session;	
	
	/**
	 * 自定义数据类型
	 */
	private Object sysData;
	
	public Object getSysData() {
		return sysData;
	}

	public void setSysData(Object sysData) {
		this.sysData = sysData;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public User getSysUser() {
		return sysUser;
	}

	public void setSysUser(User sysUser) {
		this.sysUser = sysUser;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
