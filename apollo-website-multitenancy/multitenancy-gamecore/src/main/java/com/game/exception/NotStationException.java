package com.game.exception;

import java.util.HashMap;
import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.util.ActionUtil;


/**
 * 不是一个有效站点
 * @author admin
 *
 */
public class NotStationException extends GenericException{
	
	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public NotStationException(String domain) {
		super("Station ["+domain+"] 404 not found");
		this.domain = domain;
	}

    public String toJsonMsg() {
    	Map result = new HashMap(); 
		result.put("msg","Station 404 not found!");
		result.put("success", false);
		result.put("fullmessage", this.getFullMessage());
	    return result.toString();
    }
    
	public String[] getResponseHeader() {
		return new String[]{ActionUtil.HEADER_CEIPSTATE_BIZ};
	}
}
