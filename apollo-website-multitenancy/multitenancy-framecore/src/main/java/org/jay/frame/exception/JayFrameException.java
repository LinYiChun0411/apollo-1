package org.jay.frame.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jay.frame.util.JsonUtil;
import org.jay.frame.util.ActionUtil;
import org.jay.frame.util.StringUtil;

/**
 * 非业务性异常，例如判断对象是否为空，若为必须参数为空则可能抛出
 * 该异常。
 * @author Administrator
 *
 */
public class JayFrameException extends RuntimeException implements AppException{
	//对象为空
	public static final int EMPTY_ERROR = 10000; 
	//缺少参数
	public static final int MISSING_PARAMETER = 10001;
	//一个对象所有属性都是空的
	public static final int EMPTY_PROPERTY_OBJECT = 10002;
	//无法通过一个model的属性找到匹配DB列
	public static final int UNKNOW_COLUMN = 10003;
	//对象不是一个JdbcModel
	public static final int NOT_JDBC_MODEL = 10004;
	//两个对象 不是同一个类
	public static final int NOT_THE_SAME_CLASS = 10005;
	//缓存数据出错
	public static final int CACHE_DATA_ERROR = 10006;
	//缺少必要参数，在开发过程中  修改model未重启tomcat可能导致出现这样异常
	public static final int MODEL_MISSING_SYS_FIELD = 10007;
	//找不到类
	public static final int CLASS_NOT_FOUND = 10008;
	
	//当前表对应的model没有继承BaseModel  没有flag_active字段 不能逻辑删除
	public static final int NOT_BASE_MODEL = 10009;
	
	//未知异常
	public static final int UNKNOW = 99999;
	
	protected int errorCode; //错误编码序列
	protected boolean rollback = true;
	protected String errorMessage = "";
	protected String fullMessage = "";
	protected boolean doLog = false; 
	protected Logger logger = Logger.getLogger(JayFrameException.class);
	protected Object args[] = null;
	
	public JayFrameException(int code,String ...args){
		String msg = getErrorMessage(code);
		this.args = args;
		this.errorMessage = this.getErrorMessage(code);
		this.errorCode = code;
	}

	public JayFrameException(Exception e){
		this.errorCode = UNKNOW;
		this.args =  new String [] {e.getMessage()};
		this.errorMessage = getErrorMessage(UNKNOW);
		
	}
    public String toString() {
    	return  toJsonMsg();
    }
	public String getErrorMessage(int code){
		switch (code) {
			case EMPTY_ERROR:
				return "["+EMPTY_ERROR+"] Object is empty";
			case MISSING_PARAMETER:
				return StringUtil.formate("["+MISSING_PARAMETER+"] Missing paramerter : {0}", this.args);
			case EMPTY_PROPERTY_OBJECT :
				return StringUtil.formate("["+EMPTY_PROPERTY_OBJECT+"] All of property in {0} are empty", this.args);
			case UNKNOW_COLUMN :
				return StringUtil.formate("["+UNKNOW_COLUMN+"] Could not find DB property {0} in {1}", this.args);
			case NOT_JDBC_MODEL:
				return StringUtil.formate("["+NOT_JDBC_MODEL+"] Class [{0}] is not a JdbcModel", this.args);
			case NOT_THE_SAME_CLASS:
				return StringUtil.formate("["+NOT_THE_SAME_CLASS+"] class {0} and {1} are not the same class ", this.args);
			case CACHE_DATA_ERROR :
				return StringUtil.formate("["+CACHE_DATA_ERROR+"] Could not get required object from cache", this.args);
			case MODEL_MISSING_SYS_FIELD:
				return "["+MODEL_MISSING_SYS_FIELD+"] Missing system field : [This error maybe happen when you modify model but you do not restart server]";
			case CLASS_NOT_FOUND : 
				return StringUtil.formate("["+CLASS_NOT_FOUND+"] Class not found : {0}", this.args);
			case NOT_BASE_MODEL:
				return "["+CLASS_NOT_FOUND+"] Class is not extends org.jay.frame.jdbc.model.BaseModel,so could not use fake delete ";
			case UNKNOW:
				return StringUtil.formate("["+UNKNOW+"] Unknow exception : {0}", this.args);
		}
		return null;
	}
	private void handle() {
		StringBuffer sb = new StringBuffer(64);
		sb.append("ErrorCode["+errorCode+"]:"+this.errorMessage);
		sb.append("\n");
		sb.append(this.makeStackTraceMessage());
		this.fullMessage = sb.toString();
		
		Level level = this.getLogLevel();
		if(level == Level.OFF){
			return;
		}
	
		if(level == Level.DEBUG){
			logger.debug(this.fullMessage);
		}else if(level == Level.INFO){
			logger.info(this.fullMessage);
		}else if(level == Level.WARN){
			logger.warn(this.fullMessage);
		}else if(level == Level.ERROR){
			logger.error(this.fullMessage);
		}else if(level == Level.FATAL){
			logger.fatal(this.fullMessage);
		}
	}
	/**
	 * 抛给前台的错误信息
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}

	public String getFullMessage() {
		return this.fullMessage;
	}

	public String getStackTraceMessage() {
		return this.makeStackTraceMessage();
	}

	public boolean isRollback() {
		return this.rollback;
	}

	public String toJsonMsg() {
		Map result = new HashMap(); 
		result.put("msg", this.getErrorMessage());
		result.put("rollback", new Boolean(rollback));
		result.put("success", false);
		result.put("fullmessage", this.getFullMessage());
	    return JsonUtil.toJson(result);
	}

	public boolean hasDoneLog() {
		return doLog;
	}
	
	public void doLog(Logger log){
		if(this.doLog == false){
			log.error(this.fullMessage);
			doLog = true;
		}
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
		if (traces != null && traces.length > 0) {
			sb.append(StringUtil.formate(ErrorCode.MSG_ERR_LOCATION, new Object[]{traces[0].getClassName(),traces[0].getMethodName(),traces[0].getLineNumber()}));
		}
		return sb.toString();
	}
	
	public String[] getResponseHeader() {
		return new String[]{ActionUtil.HEADER_CEIPSTATE_ERROR};
	}
	
	public Level getLogLevel() {
		return Level.ERROR;
	}
}