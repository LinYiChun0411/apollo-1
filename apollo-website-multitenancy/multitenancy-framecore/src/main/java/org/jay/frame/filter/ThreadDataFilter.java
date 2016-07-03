package org.jay.frame.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jay.frame.FrameProperites;
import org.jay.frame.jdbc.Constants;
import org.jay.frame.jdbc.model.AbstractUser;
import org.jay.frame.util.StringUtil;





public class ThreadDataFilter extends SysFilter{
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * 数据回调
	 */
	private static DataCallback dc;
	
	private static boolean initFlag = false;
	
	private static DataCallback getDataCallback() {
		if(initFlag){
			return dc;
		}
		initFlag = true;
		if(StringUtil.isEmpty(FrameProperites.THREAD_DATA_CALLBACK_CLASS)){
			return dc;
		}
		try {
			dc = (DataCallback)Class.forName(FrameProperites.THREAD_DATA_CALLBACK_CLASS).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dc;
	}
	
	@Override
	protected void processFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		String uri = request.getRequestURI();
		uri = StringUtils.substringAfter(uri, request.getContextPath());
		HttpSession session = request.getSession();
	
		SysThreadData threadData = new SysThreadData();
		
		threadData.setRequest(request);
		threadData.setResponse(response);
		threadData.setSession(session);
		DataCallback dc = getDataCallback();
		ThreadVariable.set(threadData);
		if(dc != null){
			threadData.setSysData(dc.initData(threadData));
		}
	    processFilter(ThreadDataFilter.class, request, response, filterChain);
	}

	@Override
	protected void doFinally(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		ThreadVariable.clear();
	}
}
