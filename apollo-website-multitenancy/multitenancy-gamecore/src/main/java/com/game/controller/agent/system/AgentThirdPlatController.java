package com.game.controller.agent.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.agent.BaseAgentController;
import com.game.core.SystemConfig;
import com.game.model.ThirdStationGroup;
import com.game.service.ThirdPlatformService;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/system/thirdplat")
public class AgentThirdPlatController extends BaseAgentController {

	@Autowired
	private ThirdPlatformService thirdPlatService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/system/thirdplat.jsp");
	}

	@ResponseBody
	@RequestMapping("/list")
	public void list() {
		super.render(thirdPlatService.getAgentPage());
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
		thirdPlatService.saveThirdGroup(tsg);
		super.renderSuccess();
	}
}
