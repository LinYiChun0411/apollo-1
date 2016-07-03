package com.game.controller.agent;

import java.math.BigDecimal;

import org.jay.frame.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.core.SystemConfig;
import com.game.model.dictionary.MoneyRecordType;
import com.game.model.vo.MnyMoneyVo;
import com.game.permission.annotation.NotNeedLogin;
import com.game.service.MnyDrawRecordService;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/drawrecord")
public class MnyDrawRecordController extends BaseAgentController {

	@Autowired
	private MnyDrawRecordService drawRecordService;

	@NotNeedLogin
	@ResponseBody
	@RequestMapping("/index")
	public void index() {
		super.render(drawRecordService.getPage());
	}

	@NotNeedLogin
	@ResponseBody
	@RequestMapping("/withdraw")
	public void withdraw() {
		long memberId = super.$long("memberId");
		String moneyStr = super.$("money");

		super.isNotNull(memberId, "会员不存在!");
		super.isNotNull(moneyStr, "金额格式错误!");
		
		MnyMoneyVo moneyVo = new MnyMoneyVo();
		moneyVo.setAccountId(memberId);
		moneyVo.setMoneyRecordType(MoneyRecordType.WITHDRAW_ARTIFICIAL);
		
		if(StringUtil.isNumber(moneyStr)){
			moneyVo.setMoney(new BigDecimal(moneyStr));
		}

		drawRecordService.withdraw(moneyVo);
	}
}
