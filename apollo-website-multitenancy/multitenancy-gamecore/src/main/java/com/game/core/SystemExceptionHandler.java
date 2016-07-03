package com.game.core;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jay.frame.exception.AppException;
import org.jay.frame.exception.ExceptionHandler;
import org.jay.frame.exception.ExceptionUtil;
import org.jay.frame.exception.NoLoginException;
import org.jay.frame.util.ActionUtil;

import com.game.util.StationUtil;

public class SystemExceptionHandler implements ExceptionHandler{

	public void resolveException(Exception exception, HttpServletResponse response, HttpServletRequest request) {
		AppException appEx = ExceptionUtil.handle(exception);
		if(ActionUtil.isAjaxRequest(request)){//响应json
			ActionUtil.render("application/json", appEx.toJsonMsg(), appEx.getResponseHeader(), response);
		}else{
			if(appEx.getClass() == NoLoginException.class){ //未登入，跳回登入界面
				try {
					String base = request.getContextPath();
					if(StationUtil.isAgentStation()){
						response.sendRedirect(base+"/agent"); 
					}else if(StationUtil.isAdminPage()){
						response.sendRedirect(base+"/admin/timeout.do"); 
					}else if(StationUtil.isMobilePage()){
						response.sendRedirect(base+"/mobile"); 
					}else{
						response.sendRedirect(base+"/"); 
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return; 
			}
			ActionUtil.renderHtml("<html><body>500 ERROR </br> "+appEx.getFullMessage()+"</body></html>", null);
		}
	}

}
