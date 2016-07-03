package org.jay.frame.exception;

import org.apache.log4j.Level;
import org.jay.frame.util.ActionUtil;

public class PropertiesException extends GenericException{

	public PropertiesException(String msg) {
		super(msg);
	}

	public String[] getResponseHeader() {
		return new String[]{ActionUtil.HEADER_CEIPSTATE_ERROR};
	}
	
	public Level getLogLevel(){
		return Level.ERROR;
	}
}
