package com.game.util;

import org.jay.frame.jdbc.model.User;
import org.jay.frame.util.SysUtil;

public class UserUtil extends SysUtil{
	
	public static boolean isSuperAdmin(){
		if(!StationUtil.isAdminStation()){
			return false;
		}
		User user = getCurrentUser();
		if(user == null){
			return false;
		}
		return "root".equals(user.getLoginAccount());
	}
	
}
