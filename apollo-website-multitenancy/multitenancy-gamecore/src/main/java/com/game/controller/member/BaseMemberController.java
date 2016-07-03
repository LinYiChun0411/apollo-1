package com.game.controller.member;

import org.jay.frame.util.ActionUtil;

import com.game.core.SystemConfig;
import com.game.model.SysStation;
import com.game.util.WebUtil;

public class BaseMemberController extends ActionUtil{
	
	/**
	 * 获取站点资源目录
	 * @return
	 */
	public String getDomainFolder(){
		SysStation station = WebUtil.getStation(super.getRequest());
		return SystemConfig.SOURCE_FOLDER_MEMBER + "/" + station.getFloder();
	}

	public String goPage(String jspPath){
		return getDomainFolder() + "/" + jspPath;
	}
}
