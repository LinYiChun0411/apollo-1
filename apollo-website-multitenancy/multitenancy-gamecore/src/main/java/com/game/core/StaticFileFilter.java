package com.game.core;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jay.frame.filter.SysFilter;


import com.game.model.SysStation;
import com.game.util.WebUtil;


public class StaticFileFilter extends SysFilter{

	public void destroy() {
		
	}
	
	protected boolean isFilterEnabled() {
		return false;
	}
	
	Logger logger = Logger.getLogger(getClass().getName());

	@Override
	protected void processFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		/**
		 * 公共资源目录
		 */
		if(isNotMemberSource(request)){
			filterChain.doFilter(request, response);
			return;
		}
		
		String filePath = getPostUrl(request);
		if(filePath != null){
			request.getRequestDispatcher(filePath).forward(request, response);
		}else{
			response.setStatus(404);
		}
	}
	
	/**
	 *  判断请求路径是不是common目录
	 * @param url
	 * @return
	 */
	public static String getPostUrl(HttpServletRequest request){
		String url = request.getRequestURI();
		String basePath = request.getContextPath();
		SysStation station = WebUtil.getStation(request);
		if(station == null){
			return null;
		}
		return  SystemConfig.SOURCE_FOLDER_MEMBER + "/" + station.getFloder() + url.substring(basePath.length());
	}
	/**
	 * 非站点界面资源目录
	 * @param request
	 * @return
	 */
	private boolean isNotMemberSource(HttpServletRequest request){
		String url = request.getRequestURI();
		String basePath = request.getContextPath();
		if(url.startsWith(basePath + SystemConfig.SOURCE_FOLDER_COMMON)){//公共目录
			return true;
		}
		
		if(url.startsWith(basePath + SystemConfig.SOURCE_FOLDER_ADMIN)){//后台总管理目录
			return true;
		}

		if(url.startsWith(basePath + SystemConfig.SOURCE_FOLDER_AGENT)){//代理管理目录
			return true;
		}

		if(url.startsWith(basePath + SystemConfig.SOURCE_FOLDER_MOBILE)){//手机端目录
			return true;
		}
		
		return false;
	}

	@Override
	protected void doFinally(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		
	}
}
