package com.game.controller.agent.finance;

import javax.servlet.http.HttpServletRequest;

import org.jay.frame.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.agent.BaseAgentController;
import com.game.core.SystemConfig;
import com.game.model.dictionary.MoneyRecordType;
import com.game.model.vo.MnyMoneyVo;
import com.game.service.MnyMoneyRecordService;
import com.game.service.MnyMoneyService;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/finance/memmnyope")
public class MemMnyOpeController extends BaseAgentController {

	@Autowired
	private MnyMoneyService mnyMoneyService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return super.goPage("/page/finance/memmnyope.jsp");
	}

	@ResponseBody
	@RequestMapping("/memmny")
	public void list() {
		String account = super.$("account");
		super.renderJson(mnyMoneyService.getMoneyByAccount(account));
	}

	@ResponseBody
	@RequestMapping("/save")
	public void save() {
		Long accountId = super.$cl("id");
		long type = super.$cl("type");
		String money = super.$c("money");
		String remark = super.$("remark");
		MnyMoneyVo moneyVo = new MnyMoneyVo();
		moneyVo.setAccountId(accountId);
		moneyVo.setMoneyRecordType(MoneyRecordType.getMoneyRecordType(type));
		moneyVo.setMoney(StringUtil.toBigDecimal(money));
		moneyVo.setRemark(remark);
		super.renderJson(mnyMoneyService.updateMoney(moneyVo));
	}
}
