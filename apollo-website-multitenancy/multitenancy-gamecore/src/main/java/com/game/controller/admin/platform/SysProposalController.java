package com.game.controller.admin.platform;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.vo.ProposalVo;
import com.game.service.SysProposalService;
import com.game.util.DateUtil;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/proposal")
public class SysProposalController extends BaseAdminController {

	@Autowired
	private SysProposalService sysProposalService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/platform/proposal.jsp");
	}

	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		String name = super.$("name");
		Long stationId = super.$long("stationId");
		String begin = super.$("beginDatetime");
		String end = super.$("endDatetime");

		Date beginDate = DateUtil.toDate(begin);
		Date endDate = DateUtil.toDate(end);

		ProposalVo pvo = new ProposalVo();
		pvo.setName(name);
		pvo.setBegin(beginDate);
		pvo.setEnd(endDate);
		pvo.setStationId(stationId);

		super.render(sysProposalService.getPage(pvo));
	}

	@RequestMapping(value = "/read")
	@ResponseBody
	public void read() {
		Long proposalId = super.$long("proposalId");
		super.isNotNull(proposalId, "该记录不存在!");
		sysProposalService.readProposal(proposalId);
		super.renderSuccess();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		Long proposalId = super.$long("proposalId");
		super.isNotNull(proposalId, "该记录不存在!");
		sysProposalService.delProposal(proposalId);
		super.renderSuccess();
	}
}
