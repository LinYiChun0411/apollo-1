package org.jay.frame.exception;

import java.util.HashMap;
import java.util.Map;

import org.jay.frame.util.JsonUtil;
import org.jay.frame.util.ActionUtil;




/**
 * 
 * @desc NoLoginException
 *
 */

public class NoLoginException  extends PermissionCheckException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8892110214468674687L;


	public NoLoginException() {
		super("登陆超时,请重新登陆系统!");
	}

	public NoLoginException(String msg) {
		super(msg);
	}

	public NoLoginException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoLoginException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
    public String toJsonMsg() {
    	Map result = new HashMap();
		result.put("msg", "登陆超时,请重新登陆系统");
		result.put("success", false);
		//result.put("fullmessage", getFullMessage());
	    return JsonUtil.toJson(result);
    }
    
	public String[] getResponseHeader() {
		return new String[]{ActionUtil.HEADER_CEIPSTATE_NO_LOGIN};
	}
}
