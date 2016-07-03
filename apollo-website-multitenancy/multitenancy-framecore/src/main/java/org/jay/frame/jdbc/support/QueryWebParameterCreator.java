package org.jay.frame.jdbc.support;

import javax.servlet.ServletRequest;

public interface QueryWebParameterCreator {
	/**
	 * 生成查询参数
	 * @param request
	 * @return
	 */
	public QueryWebParameter generate(ServletRequest request);
}
