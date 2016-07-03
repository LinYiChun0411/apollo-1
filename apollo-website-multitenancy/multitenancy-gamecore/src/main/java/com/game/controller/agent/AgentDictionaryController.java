package com.game.controller.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jay.frame.util.ActionUtil;
import org.jay.frame.util.JsonUtil;
import org.jay.frame.util.MixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.core.SystemConfig;
import com.game.model.dictionary.MoneyRecordType;
import com.game.permission.annotation.CheckType;
import com.game.permission.annotation.Permission;
import com.game.service.MnyBankListService;
import com.game.service.ThirdPlatformService;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/dictionary")
public class AgentDictionaryController extends ActionUtil {

	@Autowired
	private MnyBankListService mnyBankListService;

	@Autowired
	private ThirdPlatformService thirdPlatformService;

	@Permission(CheckType.OPEN)
	@ResponseBody
	@RequestMapping("/bank/list")
	public void queryBankList() {
		super.renderJson(JsonUtil.toJson(mnyBankListService.getBankList()));
	}

	@Permission(CheckType.OPEN)
	@ResponseBody
	@RequestMapping("/third/list")
	public void queryThirdList() {
		super.renderJson(JsonUtil.toJson(thirdPlatformService.getThirdList()));
	}

	@Permission(CheckType.OPEN)
	@ResponseBody
	@RequestMapping("/money/record/type")
	public void queryMnyRdType() {
		List<Map> mrts = new ArrayList<Map>();
		MoneyRecordType[] types = MoneyRecordType.values();
		for (int i = 0; i < types.length; i++) {
			mrts.add(MixUtil.newHashMap("type", types[i].getType(), "name", types[i].getName()));
		}
		super.renderJson(JsonUtil.toJson(mrts));
	}
}
