package org.jay.frame.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jay.frame.exception.NoLoginException;
import org.jay.frame.filter.SysThreadData;
import org.jay.frame.filter.ThreadVariable;
import org.jay.frame.jdbc.model.User;

public class SysUtil {
	/**
	 * 判断当前是否有登陆
	 * @return
	 */
	public static boolean isLogin(){
		return getCurrentUser() != null;
	}
	
	/**
	 * 获取当前登陆用户
	 * @return
	 */
	public static User getCurrentUser(){
		SysThreadData data = ThreadVariable.get();
		return data.getSysUser();
	}
	
	/**
	 * 获取当前登陆用户id
	 * @return
	 */
	public static Long getUserId(){
		User user = getCurrentUser();
		if(user == null){
			return null;
		}
		return user.getSysUserId();
	}
	
	/**
	 * 检测当前用户是否有登陆
	 */
	public static User checkLogin(){
		User user = getCurrentUser();
		if(user == null){
			throw new NoLoginException();
		}
		return user;
	}
	
	/**
	 * 获取当前用户的response
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		return ThreadVariable.get().getResponse();
	}
	
	/**
	 * 获取当前用户的session
	 * @return
	 */	
	public static HttpSession getSession(){
		return ThreadVariable.get().getSession();
	}
	
	/**
	 * 获取当前用户的request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return ThreadVariable.get().getRequest();
	}
	/**
	 * 获取用户自定义数据
	 * @return
	 */
	public static Object getCustomData(){
		return ThreadVariable.get().getSysData();
	}
}


