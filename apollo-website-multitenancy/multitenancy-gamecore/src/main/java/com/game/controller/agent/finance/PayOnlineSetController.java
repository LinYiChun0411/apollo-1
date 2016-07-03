package com.game.controller.agent.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.agent.BaseAgentController;
import com.game.core.SystemConfig;
import com.game.model.AgentBaseConfigValue;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/finance/payonlineset")
public class PayOnlineSetController extends BaseAgentController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return super.goPage("/page/finance/payonlineset.jsp");
	}

	@ResponseBody
	@RequestMapping("/list")
	public void list() {
		List<Map> confs = new ArrayList<Map>();
		Map ent = null;
		Date cur = new Date();
		for (int i = 0; i < 10; i++) {
			ent = new HashMap();
			ent.put("id", i);
			ent.put("account", "test" + i);
			ent.put("remark", "这只是测试数据的第" + i + "条！");
			ent.put("createDatetime", cur);
			confs.add(ent);
		}

		Map data = MixUtil.newHashMap("rows", confs, "total", confs.size());
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
		super.renderSuccess();
	}
}
