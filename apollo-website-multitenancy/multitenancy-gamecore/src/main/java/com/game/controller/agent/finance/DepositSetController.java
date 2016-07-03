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
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/finance/depositset")
public class DepositSetController extends BaseAgentController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return super.goPage("/page/finance/depositset.jsp");
	}

	@ResponseBody
	@RequestMapping("/list")
	public void list() {
		List<Map> confs = new ArrayList<Map>();
		Map ent = null;
		Date cur = new Date();
		for (int i = 0; i < 2; i++) {
			ent = new HashMap();
			ent.put("id", i);
			ent.put("bank", "中国银行");
			ent.put("cardNo", "95599 8088 3324 1423");
			ent.put("userName", "杨顶天");
			ent.put("address", "厦门支行");
			ent.put("userGroup", "默认会员组");
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
