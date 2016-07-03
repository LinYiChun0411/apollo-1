package com.game.controller.agent.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/finance/payonlinerd")
public class PayOnlineRdController extends BaseAgentController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return super.goPage("/page/finance/payonlinerd.jsp");
	}

	@ResponseBody
	@RequestMapping("/list")
	public void list() {
		List<Map> confs = new ArrayList<Map>();
		Map ent = null;
		Date cur = new Date();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			ent = new HashMap();
			ent.put("id", i);
			ent.put("orderNo", cur.getTime() + "" + r.nextInt(9999));
			ent.put("money", 1000 * i);
			ent.put("fee", 1000 * i * 5 / 1000);
			ent.put("giveMoney", 1000 * i * 5 / 1000);
			ent.put("account", "test" + i);
			ent.put("status", i % 2);
			ent.put("bank", "中国银行");
			ent.put("actionAndAddr", "ATM存款<br>厦门");
			ent.put("remark", "测试数据的第" + i + "条！");
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
