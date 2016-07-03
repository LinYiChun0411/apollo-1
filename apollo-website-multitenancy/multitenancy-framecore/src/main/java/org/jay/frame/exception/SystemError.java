package org.jay.frame.exception;

import org.apache.log4j.Level;
import org.jay.frame.util.ActionUtil;


public class SystemError extends GenericException{


	public SystemError() {
		super();
	}

	public SystemError(String errorCode) {
		super(errorCode);
		
	}

	public SystemError(String errorCode, Object args[]) {
		super(errorCode, args);
	}

	public SystemError(String errorCode, Object arg1) {
		super(errorCode,arg1);
	}

	public SystemError(String errorCode, Object arg1, Object arg2) {
		super(errorCode,arg1, arg2);
	}

	public SystemError(Throwable t) {
		super(t);
	}

	public SystemError(String errorCode, Throwable t) {
		super(errorCode, t);
	}

	public SystemError(String errorCode, Object args[], Throwable t) {
		super(errorCode,args, t);
	}

	public SystemError(String errorCode, Throwable t, Object arg1) {
		super(errorCode, t, arg1);
	}

	public SystemError(String errorCode, Throwable t, Object arg1, Object arg2) {
		super(errorCode, t,arg1,arg2);
	}
	
	public Level getLogLevel(){
		return Level.ERROR;
	}
	
	public String[] getResponseHeader() {
		return new String[]{ActionUtil.HEADER_CEIPSTATE_ERROR};
	}
	
}
