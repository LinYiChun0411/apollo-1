package com.game.controller.agent;

import org.jay.frame.util.ActionUtil;

import com.game.core.SystemConfig;

public class BaseAgentController extends ActionUtil {
	/**
	 * 获取资源目录
	 * 
	 * @return
	 */
	public String getDomainFolder() {
		return SystemConfig.SOURCE_FOLDER_AGENT;
	}

	public String goPage(String jspPath) {
		return getDomainFolder() + "/" + jspPath;
	}
}
