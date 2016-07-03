package com.game.controller.agent.report;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.agent.BaseAgentController;
import com.game.core.SystemConfig;
import com.game.model.vo.ReportVo;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/report/global")
public class GlobalReportController extends BaseAgentController {

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return super.goPage("/page/report/global.jsp");
	}

	@ResponseBody
	@RequestMapping("/list")
	public void list() {
		ReportVo rpv = new ReportVo();
		super.renderJson(rpv);
	}

	@ResponseBody
	@RequestMapping("/save")
	public void save() {
		super.renderSuccess();
	}
}
