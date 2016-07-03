package org.jay.frame.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SetCharacterEncodingFilter implements Filter {
	protected String		encoding		= null;

	protected FilterConfig	filterConfig	= null;

	protected boolean		enable			= true;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (enable || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			if (encoding != null) {
				request.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("enable");
		if (value == null) {
			this.enable = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.enable = true;
		} else if (value.equalsIgnoreCase("yes")) {
			this.enable = true;
		} else {
			this.enable = false;
		}
	}

	protected String selectEncoding(ServletRequest request) {

		return (this.encoding);

	}
}