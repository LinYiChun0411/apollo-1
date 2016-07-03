package com.game.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.constant.AgentSettingKey;
import com.game.permission.annotation.NotNeedLogin;
import com.game.service.AgentSettingService;
import com.game.util.StationUtil;

@Controller("/agentSetting")
public class AgentSettingController extends BaseMemberController {
	@Autowired
	private AgentSettingService agentSettingService;
	
	@NotNeedLogin
	@ResponseBody
	@RequestMapping("/saiCheSwfStatus")
	public String saiCheSwfStatus() {
		return agentSettingService.getSettingValueByKey(AgentSettingKey.onoff_sai_che_swf.name(), StationUtil.getStationId());  
	}
}
