
package org.jay.frame.exception;
import org.apache.log4j.Level;
/**
 * 异常接口类
 * 
 */
public interface AppException {

	/**
	 * @return 错误信息代码
	 */
	public String getMessage();

	/**
	 * @return 全部的错误信息,包括错误堆栈信息
	 */
	public String getFullMessage();

	/**
	 * 取得错误信息
	 * 
	 * @return 错误信息
	 */
	public String getErrorMessage();

	/**
	 * 取得第一层错误堆栈
	 * 
	 * @return 第一层错误堆栈信息
	 */
	public String getStackTraceMessage();

	/**
	 * 是否需要回滚
	 * 
	 * @return
	 */
	public boolean isRollback();
	
	
	/**
	 * 转换成json
	 * 
	 * @return
	 */
	public String toJsonMsg();
	
	/**
	 * 获取日志级别
	 * @return
	 */
	public Level getLogLevel();
	/**
	 * 响应头部
	 * @return
	 */
	public String[] getResponseHeader();

}
