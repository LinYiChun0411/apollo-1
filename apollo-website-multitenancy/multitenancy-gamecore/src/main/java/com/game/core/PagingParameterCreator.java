package com.game.core;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.jay.frame.jdbc.support.QueryWebParameter;
import org.jay.frame.jdbc.support.QueryWebParameterCreator;
import org.jay.frame.jdbc.support.QueryWebUtils;
import org.jay.frame.util.StringUtil;

public class PagingParameterCreator implements QueryWebParameterCreator{
	
	private final Map EMPTY_MAP = new TreeMap();
	
	@Override
	public QueryWebParameter generate(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		int pageNo = 1;//默认第一页
		int pageSize = 20; //默认20条
		String pageNoParam = req.getParameter("page");
		String pageSizeParam = req.getParameter("rows");
		if(pageNoParam != null && pageSizeParam != null){
			//easyui 表格
			pageNo = StringUtil.toInt(pageNoParam);
			pageSize = StringUtil.toInt(pageSizeParam);
		}else if((pageNoParam = req.getParameter("pageNumber")) != null && (pageSizeParam = req.getParameter("pageSize")) != null ){
			//bootstrap table
			pageNo = StringUtil.toInt(pageNoParam);
			pageSize = StringUtil.toInt(pageSizeParam);
		}
		
		return 	QueryWebUtils.generateQueryWebParameter(EMPTY_MAP, pageNo, pageSize,null, null);
	}
	
	
}
