
package org.jay.frame.exception;
import java.sql.BatchUpdateException;
import org.apache.log4j.Logger;
import org.jay.frame.FrameProperites;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.util.NestedServletException;


public class ExceptionUtil {

	protected static Logger logger = Logger.getLogger(ExceptionUtil.class);

	public static AppException handle(Exception exception) {
		AppException appEx = null;
		Throwable throwable = exception;
		if(throwable == null){
			return null;
		}
		
		//去壳
		if(throwable instanceof NestedServletException){
			throwable = ((NestedServletException)throwable).getCause();
		}
		
		throwable.printStackTrace();
		if (throwable instanceof DataIntegrityViolationException) {
			String messagekey = ErrorCode.MSG_ERR_FRAME_DATAINTEGRITYVIOLATION;
			throwable = new SystemError(messagekey, throwable);
		}

		if (throwable instanceof UncategorizedSQLException || throwable instanceof BatchUpdateException) {
			String errormessage = throwable.getMessage();
			if (null != errormessage && errormessage.indexOf("Data truncation") != -1) {
				String messagekey = ErrorCode.MSG_ERR_FRAME_DATATOOLONG;
				throwable = new SystemError(messagekey, throwable);
			}
		}
		
		if(throwable instanceof AppException){
			appEx = (AppException) throwable;
		}else{
			appEx = new SystemError(throwable.getMessage(), throwable, throwable.getMessage());
		}
		
		return appEx;
	}
	
	private static ExceptionHandler handler = null;
	
	public static void setHandler(ExceptionHandler handler) {
		ExceptionUtil.handler = handler;
	}

	public static ExceptionHandler getHandler(){
		if(handler != null){
			return handler;
		}
		try {
			ExceptionHandler handler = (ExceptionHandler)Class.forName(FrameProperites.EXCEPTION_HANDLER_CLASS).newInstance();
			return handler;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
