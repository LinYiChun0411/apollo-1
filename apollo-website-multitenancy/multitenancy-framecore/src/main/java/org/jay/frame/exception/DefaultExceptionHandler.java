package org.jay.frame.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jay.frame.util.ActionUtil;

public class DefaultExceptionHandler implements ExceptionHandler {

	public void resolveException(Exception exception, HttpServletResponse response, HttpServletRequest request) {
		AppException appEx = ExceptionUtil.handle(exception);
		if(ActionUtil.isAjaxRequest(request)){//响应json
			ActionUtil.render("application/json", appEx.toJsonMsg(), appEx.getResponseHeader(), response);
		}else{
			ActionUtil.renderHtml("<html><body>500 ERROR </br> "+appEx.getFullMessage()+"</body></html>", null);
		}
	}
}
