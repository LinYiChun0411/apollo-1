package org.jay.frame.exception;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.jay.frame.util.JsonUtil;
import org.jay.frame.util.ActionUtil;
import org.jay.frame.util.StringUtil;
/**
 * 业务异常
 * @author admin
 *
 */
public class GenericException extends RuntimeException implements AppException {

	protected static Log log = LogFactory.getLog(GenericException.class);
	
	private static final long serialVersionUID = -8871479382891407809L;
	protected boolean rollback = true;
	protected String errorCode = "";
	protected String fullMessage = "";
	protected Object args[] = null;
	
	protected boolean logFlag = false; 
	private String forward;

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public GenericException() {
		super();
		handle();
	}

	public GenericException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
		handle();
	}

	public GenericException(String errorCode, Object args[]) {
		super(errorCode);
		this.errorCode = errorCode;
		this.args = args;
		handle();
	}

	public GenericException(String errorCode, Object arg1) {
		super(errorCode);
		this.errorCode = errorCode;
		this.args = new Object[] { arg1 };
		handle();
	}

	public GenericException(String errorCode, Object arg1, Object arg2) {
		super(errorCode);
		this.errorCode = errorCode;
		this.args = new Object[] { arg1, arg2 };
		handle();
	}

	public GenericException(Throwable t) {
		super(t);
		handle();
	}

	public GenericException(String errorCode, Throwable t) {
		super(errorCode, t);
		this.errorCode = errorCode;
		handle();
	}

	public GenericException(String errorCode, Object args[], Throwable t) {
		super(errorCode, t);
		this.errorCode = errorCode;
		this.args = args;
		handle();
	}

	public GenericException(String errorCode, Throwable t, Object arg1) {
		super(errorCode, t);
		this.errorCode = errorCode;
		this.args = new Object[] { arg1 };
		handle();
	}

	public GenericException(String errorCode, Throwable t, Object arg1, Object arg2) {
		super(errorCode, t);
		this.errorCode = errorCode;
		this.args = new Object[] { arg1, arg2 };
		handle();
	}

	/**
	 * 返回错误代码
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return this.errorCode;
	}

	/**
	 * 返回错误信息，i18n信息
	 */
	public String getErrorMessage() {
		return  StringUtil.formate(this.errorCode, this.args); 
	}

	/**
	 * 返回出错的堆栈信息
	 */
	public String getStackTraceMessage() {
		return this.makeStackTraceMessage();
	}

	/**
	 * 构造出错的堆栈信息
	 * 
	 * @return 堆栈信息
	 */
	private String makeStackTraceMessage() {
		StackTraceElement[] traces = this.getStackTrace();
		// 如果是由RuntimeException引起的则定位到RuntimeException
		if (this.getCause() != null && this.getCause() instanceof RuntimeException) {
			traces = this.getCause().getStackTrace();
		}
		StringBuffer sb = new StringBuffer(64);
		//LocalizedTextUtil.findDefaultText("err_location",new Locale("zh_CN"))
		if (traces != null && traces.length > 0) {
			//err_location
			sb.append(StringUtil.formate(ErrorCode.MSG_ERR_LOCATION, new Object[]{traces[0].getClassName(),traces[0].getMethodName(),traces[0].getLineNumber()}));
		}
		return sb.toString();
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getFullMessage() {
		return this.fullMessage;
	}

	public boolean isRollback() {
		return rollback;
	}

	/**
	 * 转换为json格式的提示信息
	 */
    
    public String toString() {
    	return  toJsonMsg();
    }
    
    public String toJsonMsg() {
    	Map result = new HashMap(); 
		result.put("msg", this.getErrorMessage());
		result.put("success", false);
	    return JsonUtil.toJson(result);
    }

	public String[] getResponseHeader() {
		return new String[]{ActionUtil.HEADER_CEIPSTATE_BIZ};
	}
	

	public void handle() {
		StringBuffer sb = new StringBuffer(64);
		sb.append(StringUtil.formate(this.errorCode, this.args));
		sb.append("\n");
		sb.append(this.makeStackTraceMessage());
		this.fullMessage = sb.toString();
		
		Level level = this.getLogLevel();
		if(level == Level.OFF){
			return;
		}
		if(level == Level.DEBUG){
			log.debug(this.fullMessage);
		}else if(level == Level.INFO){
			log.info(this.fullMessage);
		}else if(level == Level.WARN){
			log.warn(this.fullMessage);
		}else if(level == Level.ERROR){
			log.error(this.fullMessage);
		}else if(level == Level.FATAL){
			log.fatal(this.fullMessage);
		}
	}

	public Level getLogLevel() {
		return Level.INFO;
	}
}
