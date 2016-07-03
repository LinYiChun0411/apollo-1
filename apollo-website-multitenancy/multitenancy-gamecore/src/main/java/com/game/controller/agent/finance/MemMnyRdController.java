package com.game.controller.agent.finance;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.agent.BaseAgentController;
import com.game.core.SystemConfig;
import com.game.model.ThirdStationGroup;
import com.game.model.vo.MnyMoneyRecordVo;
import com.game.service.MnyMoneyRecordService;
import com.game.util.DateUtil;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/finance/memmnyrd")
public class MemMnyRdController extends BaseAgentController {

	@Autowired
	private MnyMoneyRecordService mnyMoneyService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return super.goPage("/page/finance/memmnyrd.jsp");
	}

	@ResponseBody
	@RequestMapping("/list")
	public void list() {
		String account = super.$("account");
		String begin = super.$("begin");
		String end = super.$("end");
		Long type = super.$long("type");

		MnyMoneyRecordVo moneyRecordVo = new MnyMoneyRecordVo();
		moneyRecordVo.setAccount(account);
		moneyRecordVo.setType(type);
		moneyRecordVo.setBegin(DateUtil.toDate(begin));
		moneyRecordVo.setEnd(DateUtil.getTomorrow(DateUtil.toDate(end)));
		super.render(mnyMoneyService.getMoneyRecord(moneyRecordVo));
	}

	@ResponseBody
	@RequestMapping("/save")
	public void save() {
		String remark = super.$("remark");
		String custom = super.$("name");
		String thirdName = super.$("thirdName");
		Long id = super.$long("id");
		Long thirdId = super.$long("thirdPlatformId");
		Long orderNo = super.$long("orderNo");
		Long status = super.$long("status");
		ThirdStationGroup tsg = new ThirdStationGroup();
		tsg.setThirdName(thirdName);
		tsg.setRemark(remark);
		tsg.setName(custom);
		tsg.setId(id);
		tsg.setThirdPlatformId(thirdId);
		tsg.setOrderNo(orderNo);
		tsg.setStatus(status);
		// accountService.saveThirdGroup(tsg);
		super.renderSuccess();
	}
}
