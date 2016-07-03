package com.game.util;

import javax.servlet.http.HttpServletRequest;

import com.game.model.SysStation;
import com.game.service.FrameService;

public class WebUtil {
	
	/**
	 * 去除URL参数部分
	 * 	例如 url = admin/getUser.do?test=true
	 *         返回"a.com/admin/getUser.do"
	 *         
	 * @param url
	 * 			
	 */
	public static String toContentURL(String url){
		int index = url.indexOf("?");
		if(index == -1){
			return url;
		}
		return url.substring(0,index);
	}

	/**
	 * 转换成模块链接地址
	 * 例如 url = admin/permission/getUser.do?test=true
	 * 		   返回 admin/permission
	 * @param url
	 */
	public static String toModuleURL(String url){
		url = toContentURL(url);
		int index = url.lastIndexOf("/");
		return url.substring(0, index);
	}
	
	/**
	 * 通过请求路径 获取主域名
	 * @param requestUrl
	 * @return
	 */
	public static String getDomain(String requestUrl){
		int index = requestUrl.indexOf("http://");
		//去掉http头
		if(index > -1){
			requestUrl = requestUrl.substring(index + "http://".length());
		}
		//去掉www.
		if(requestUrl.indexOf("www.") == 0){
			requestUrl = requestUrl.substring(4);
		}
		
		if((index = requestUrl.indexOf("/"))> -1){
			requestUrl = requestUrl.substring(0, index);
		}
		
		//截掉端口号
		index = requestUrl.indexOf(":");
		if(index > -1){
			requestUrl = requestUrl.substring(0, index);
		}
		return requestUrl;
	}
	
	
	private static FrameService frameService;
	
	private static FrameService getFrameService(){
		if(frameService == null){
			frameService = (FrameService)SpringUtil.getBean("frameServiceImpl");
		}
		return frameService;
	}
	
	public static SysStation getStation(HttpServletRequest request){
		StringBuffer requestUrl = request.getRequestURL(); 
		String domain = WebUtil.getDomain(requestUrl.toString());
		FrameService frame = getFrameService();
		SysStation station = frame.getStation(domain);
		if(station == null){
			return null;
		}
		return station;
	}
}
