package com.game.controller.agent.system;

import java.util.List;
import java.util.Map;

import org.jay.frame.util.MixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.game.controller.agent.BaseAgentController;
import com.game.core.SystemConfig;
import com.game.model.vo.RegisterConfigVo;
import com.game.service.SysRegisterConfigService;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/system/registerconf")
public class AgentRegisterConfigController extends BaseAgentController {

	@Autowired
	private SysRegisterConfigService srcService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/system/registerconfig.jsp");
	}

	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		Long platform = super.$long("platform");
		RegisterConfigVo rcvo = new RegisterConfigVo();
		rcvo.setPlatform(platform);
		List<Map> data = srcService.getStationRegConf(rcvo);
		Map res = MixUtil.newHashMap("data", data);
		super.renderJson(res);
	}

	@RequestMapping("/groups")
	@ResponseBody
	public void groups() {
		List<Map> data = srcService.getStationRegVals();
		Map res = MixUtil.newHashMap("data", data);
		super.renderJson(res);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public void save() {
		String json = super.$c("data");
		List<Map> datas = JSONArray.parseArray(json, Map.class);

		srcService.saveStationConfGroup(datas);
		super.renderSuccess();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		Long srcId = super.$long("id");
		super.isNotNull(srcId, "不存在此属性!");
		srcService.delConfig(srcId);
		super.renderSuccess();
	}

	@RequestMapping(value = "/updStatus")
	@ResponseBody
	public void updStatus() {
		Long srcId = super.$long("id");
		Long status = super.$long("status");
		super.isNotNull(srcId, "不存在此属性!");
		srcService.updStatus(srcId, status);
		super.renderSuccess();
	}
}
