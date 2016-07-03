package com.game.controller.admin;

import org.jay.frame.util.ActionUtil;

import com.game.core.SystemConfig;


public class BaseAdminController extends ActionUtil{
	
	/**
	 * 获取资源目录
	 * @return
	 */
	public String getDomainFolder(){
		return SystemConfig.SOURCE_FOLDER_ADMIN ;
	}
	
	public String goPage(String jspPath){
		return getDomainFolder() + "/" + jspPath;
	}
	
}
