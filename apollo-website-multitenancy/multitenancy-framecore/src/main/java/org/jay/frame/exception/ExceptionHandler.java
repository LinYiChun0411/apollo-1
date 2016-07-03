package org.jay.frame.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExceptionHandler {
	
	/**
	 * 异常处理
	 * @param exception
	 * @param response
	 * @param request
	 */
	public void resolveException(Exception exception, HttpServletResponse response,HttpServletRequest request);
	
}
