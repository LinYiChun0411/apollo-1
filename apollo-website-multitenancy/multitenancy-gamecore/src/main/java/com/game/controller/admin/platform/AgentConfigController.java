package com.game.controller.admin.platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.AgentBaseConfig;
import com.game.model.AgentBaseConfigValue;
import com.game.model.vo.AgentConfigVo;
import com.game.service.AgentBaseConfigService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/agentconfig")
public class AgentConfigController extends BaseAdminController {

	@Autowired
	private AgentBaseConfigService configService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/platform/agentconfig.jsp");
	}

	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		String name = super.$("name");
		AgentConfigVo acvo = new AgentConfigVo();
		acvo.setName(name);
		super.render(configService.getPageConfig(acvo));
	}

	@RequestMapping(value = "/stationset")
	@ResponseBody
	public void stationset() {
		Long stationId = super.$long("stationId");
		List<Map> confs = configService.getConfigAll();
		List<Map> pm = configService.getConfigsByStationId(stationId);
		Map data = new HashMap();
		data.put("confs", confs);
		data.put("pm", pm);
		super.renderJson(data);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(AgentBaseConfig config) {
		configService.saveConfig(config);
		super.renderSuccess();
	}

	@RequestMapping(value = "/saveset")
	@ResponseBody
	public void saveset(AgentBaseConfig config) {
		long stationId = $cl("stationId");
		String idsStr = $c("ids");
		String[] ids = idsStr.split(",");
		List<AgentBaseConfigValue> ms = new ArrayList<AgentBaseConfigValue>();
		for (int i = 0; i < ids.length; i++) {
			AgentBaseConfigValue m = new AgentBaseConfigValue();
			m.setStationId(stationId);
			m.setConfigId(StringUtil.toLong(ids[i]));
			ms.add(m);
		}
		configService.saveAgentConfVals(stationId, ms);
		super.renderSuccess();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		Long confId = super.$long("id");
		super.isNotNull(confId, "不存在此配置!");
		configService.delete(confId);
		super.renderSuccess();
	}

	@RequestMapping(value = "/updStatus")
	@ResponseBody
	public void updStatus() {
		Long confId = super.$long("id");
		Long status = super.$long("status");
		super.isNotNull(confId, "不存在此配置!");
		configService.updStatus(confId, status);
		super.renderSuccess();
	}
}
