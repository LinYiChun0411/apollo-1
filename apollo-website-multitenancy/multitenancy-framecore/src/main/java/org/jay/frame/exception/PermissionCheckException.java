package org.jay.frame.exception;

import java.util.HashMap;
import java.util.Map;

import org.jay.frame.util.ActionUtil;
import org.jay.frame.util.JsonUtil;



public class PermissionCheckException  extends GenericException implements AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8892110214468674687L;


	public PermissionCheckException() {
		super("你没有该项操作的权限，请与管理员联系!");
	}

	public PermissionCheckException(String msg) {
		super(msg);
	}

	public PermissionCheckException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PermissionCheckException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @return
	 */
    public String toJsonMsg() {
    	Map result = new HashMap(); 
		result.put("msg","你没有该项操作的权限，请与管理员联系!");
		result.put("success", false);
	    return JsonUtil.toJson(result);
    }
    
	public String[] getResponseHeader() {
		return new String[]{ActionUtil.HEADER_CEIPSTATE_NO_PERMISSION};
	}
}
