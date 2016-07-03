
package org.jay.frame.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.jay.frame.exception.ExceptionUtil;
import org.jay.frame.util.StringPool;



public abstract class BaseFilter implements Filter {
	


	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
	                    throws IOException, ServletException {
		
		if (null ==servletRequest.getCharacterEncoding() && this.encoding != null) {
            servletRequest.setCharacterEncoding(this.encoding);
        }

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		try {
			processFilter(request, response, filterChain);
		} catch (Exception ex) {
			String uri = request.getRequestURI();
			uri = StringUtils.substringAfter(uri, request.getContextPath());
			ExceptionUtil.getHandler().resolveException(ex, response, request);
		} finally{
			doFinally(request, response, filterChain);
		}
	}
	
	public FilterConfig getFilterConfig() {
		return _filterConfig;
	}

	public void destroy() {
	}

	protected abstract Log getTimeLog();

	protected boolean isFilterEnabled() {
		return _filterEnabled;
	}

	protected abstract void processFilter(HttpServletRequest request, HttpServletResponse response,
	                    FilterChain filterChain) throws IOException, ServletException;

	
	protected abstract void doFinally(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain);
	
	protected void processFilter(Class<?> filterClass, HttpServletRequest request, HttpServletResponse response,
	                    FilterChain filterChain) throws IOException, ServletException {
		
		Log log = this.getTimeLog();
		if(log.isDebugEnabled() && isFilterEnabled()){
	    	long startTime = System.currentTimeMillis();
	    	try{
	    		filterChain.doFilter(request, response);
	    	}finally{
				long endTime = System.currentTimeMillis();
				String desc = " " + request.getRequestURI() + " [D:"+ (endTime - startTime) + "ms]";
				this.getTimeLog().debug(desc);	
	    	}
		}else{
			filterChain.doFilter(request, response);
		}
	}

	private static final String _DEPTHER = "DEPTHER";

	private FilterConfig _filterConfig;
	protected String encoding = null;
	private Class<?> _filterClass = getClass();
	private boolean _filterEnabled = true;
	

}