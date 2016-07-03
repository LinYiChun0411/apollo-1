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
import com.game.service.MnyComRecordService;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/comrecord")
public class MnyComRecordController extends BaseAgentController {

	@Autowired
	private MnyComRecordService comRecordService;

	@NotNeedLogin
	@ResponseBody
	@RequestMapping("/index")
	public void index() {
		super.render(comRecordService.getPage());
	}

	@NotNeedLogin
	@ResponseBody
	@RequestMapping("/deposit")
	public void deposit() {
		long memberId = super.$long("memberId");
		String moneyStr = super.$("money");
		
		super.isNotNull(memberId, "会员不存在!");
		super.isNotNull(moneyStr, "金额格式错误!");
		
		MnyMoneyVo moneyVo = new MnyMoneyVo();
		moneyVo.setAccountId(memberId);
		moneyVo.setMoneyRecordType(MoneyRecordType.DEPOSIT_ARTIFICIAL);
		
		if(StringUtil.isNumber(moneyStr)){
			moneyVo.setMoney(new BigDecimal(moneyStr));
		}

		comRecordService.deposit(moneyVo);
	}
	
	public static void main(String[] args) {
		System.out.println(MoneyRecordType.values().length);
	}
}
