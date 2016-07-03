package com.game.controller.agent.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.agent.BaseAgentController;
import com.game.core.SystemConfig;
import com.game.model.AgentBaseConfigValue;
import com.game.service.AgentBaseConfigService;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/system/baseconfig")
public class AgentConfigValueController extends BaseAgentController {

	@Autowired
	AgentBaseConfigService agentBaseConfigService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return super.goPage("/page/system/baseconfig.jsp");
	}

	@ResponseBody
	@RequestMapping("/list")
	public void list() {
		List<Map> confs = agentBaseConfigService.getConfigs();
		Map data = MixUtil.newHashMap("data", confs);
		super.renderJson(data);
	}

	@ResponseBody
	@RequestMapping("/save")
	public void save() {
		String pk = super.$("pk");
		String configId = super.$("configId");
		String value = super.$("value");
		AgentBaseConfigValue abcv = new AgentBaseConfigValue();
		abcv.setId(StringUtil.toLong(pk));
		abcv.setConfigId(StringUtil.toLong(configId));
		abcv.setValue(value);
		agentBaseConfigService.saveConfigValue(abcv);
		super.renderSuccess();
	}
}
