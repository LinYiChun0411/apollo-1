package com.game.util;

import org.jay.frame.filter.SysThreadData;
import org.jay.frame.filter.ThreadVariable;
import org.jay.frame.util.SysUtil;

import com.game.core.CustomThreadData;
import com.game.core.StationType;
import com.game.model.SysStation;

public class StationUtil {
	/**
	 * 获取当前用户所登陆的站点类别
	 * @return
	 */
	public static StationType getStationType(){
		CustomThreadData data = (CustomThreadData)SysUtil.getCustomData();
		if(data == null){
			return null;
		}
		return data.getStationType();
	}
	
	/**
	 * 获取当前用户所登陆的站点类别
	 * @return
	 */
	public static Long getStationDdgdId(){
		SysStation station = getStation();
		if(station == null){
			return null;
		}
		return station.getAccountId();
	}
	
	/**
	 * 获取当前用户所登陆的站点的ID
	 * @return
	 */
	public static Long getStationId(){
		SysStation station = getStation();
		if(station == null){
			return null;
		}
		return station.getId();
	}
	/**
	 * 判断当前访问站点是不是总控后台
	 * @return
	 */
	public static boolean isAdminStation(){
		return getStationType() == StationType.ADMIN;
	}
	/**
	 * 判断当前访问站点是不是客户端
	 * @return
	 */
	public static boolean isMemberStation(){
		return getStationType() == StationType.MEMBER;
	}
	/**
	 * 判断当前访问站点是不是代理平台
	 * @return
	 */
	public static boolean isAgentStation(){
		return getStationType() == StationType.AGENT;
	}
	/**
	 * 判断当前访问站点是不是手机端
	 * @return
	 */
	public static boolean isMobileStation(){
		return getStationType() == StationType.MOBILE;
	}
	
	/**
	 * 获取当前线程访问的站点
	 * @return
	 */
	public static SysStation getStation(){
		SysThreadData data = ThreadVariable.get();
		CustomThreadData threadData = (CustomThreadData)data.getSysData();
		if(threadData == null){
			return null;
		}
		return threadData.getStation();
	}
	
	public static boolean isMemberPage(){
		StationType st = getStationType();
		return st == StationType.MEMBER;
	}
	
	public static boolean isAgentPage(){
		StationType st = getStationType();
		return st == StationType.AGENT;
	}
	
	public static boolean isAdminPage(){
		StationType st = getStationType();
		return st == StationType.ADMIN;
	}
	
	public static boolean isMobilePage(){
		StationType st = getStationType();
		return st == StationType.MOBILE;
	}
}
