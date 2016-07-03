package com.game.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class SystemServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7141349724752494667L;

	private static final Map<String, String> SOURCE_MAPPING = new HashMap<String,String>();
	
	static{
		SOURCE_MAPPING.put("/agent","/agent/index.do");
		SOURCE_MAPPING.put("/agent/","/agent/index.do");
		SOURCE_MAPPING.put("/mobile/","/mobile/index.do");
	}
	
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
    	doPost(req, resp);
    }  
    
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {
    	String base = req.getContextPath();
    	String uri = req.getRequestURI();
    	String urlKey = StringUtils.substringAfter(uri, base);
    	String path = SOURCE_MAPPING.get(urlKey);
    	req.getRequestDispatcher(path).forward(req, resp);
    }  
}
