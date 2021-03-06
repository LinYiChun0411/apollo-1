package com.game.controller.agent.member;

import javax.servlet.http.HttpServletRequest;

import org.jay.frame.jdbc.Page;
import org.jay.frame.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.agent.BaseAgentController;
import com.game.core.SystemConfig;
import com.game.model.SysAccount;
import com.game.model.vo.AccountVo;
import com.game.service.SysAccountService;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH + "/member/search")
public class MemberSearchController extends BaseAgentController {

	@Autowired
	private SysAccountService accountService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return super.goPage("/page/member/search.jsp");
	}

	@ResponseBody
	@RequestMapping("/list")
	public void list() {
		String account = super.$("account");
		if (StringUtil.isNotEmpty(account)) {
			AccountVo accountVo = new AccountVo();
			accountVo.setAccount(account);
			super.render(accountService.getMemberPage(accountVo));
		} else {
			super.render(new Page());
		}
	}

	@ResponseBody
	@RequestMapping("/updStatus")
	public void updStatus() {
		Long id = super.$long("id");
		Long accountStatus = super.$long("accountStatus");

		SysAccount account = new SysAccount();
		account.setId(id);
		account.setAccountStatus(accountStatus);
		accountService.updStatus(account);
		super.renderSuccess();
	}

	@ResponseBody
	@RequestMapping("/save")
	public void save() {
		Long id = super.$long("id");
		Long bankId = super.$long("bankId");
		Long accountStatus = super.$long("accountStatus");
		String cardNo = super.$("cardNo");
		String userName = super.$("userName");
		String phone = super.$("phone");
		String qq = super.$("qq");
		String email = super.$("email");
		String drawUser = super.$("drawUser");
		String bankAddress = super.$("bankAddress");

		AccountVo accountVo = new AccountVo();
		accountVo.setId(id);
		accountVo.setBankId(bankId);
		accountVo.setStatus(accountStatus);
		accountVo.setCardNo(cardNo);
		accountVo.setBankId(bankId);
		accountVo.setUserName(userName);
		accountVo.setPhone(phone);
		accountVo.setQq(qq);
		accountVo.setEmail(email);
		accountVo.setDrawUser(drawUser);
		accountVo.setBankAddress(bankAddress);
		accountService.saveAccountInfo(accountVo);
		super.renderSuccess();
	}
}
