package com.game.controller.admin.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.vo.AccountVo;
import com.game.service.SysAccountService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/account")
public class SysAccountController extends BaseAdminController {

	@Autowired
	private SysAccountService sysAccountService;

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/platform/account.jsp");
	}

	@RequestMapping("/list")
	@ResponseBody
	public void list() {
		String account = super.$("account");
		Long status = super.$long("accountStatus");
		Long accountType = super.$long("accountType");
		AccountVo avo = new AccountVo();
		avo.setAccountType(accountType);
		avo.setStatus(status);
		avo.setAccount(account);
		super.render(sysAccountService.getPage(avo));
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public void save() {

		Long id = super.$long("id");
		String account = super.$("account");
		Long accountType = super.$long("accountType");
		Long accountStatus = super.$long("accountStatus");
		Long bankId = super.$long("bankId");
		String cardNo = super.$("cardNo");
		String userName = super.$("userName");
		String phone = super.$("phone");
		String qq = super.$("qq");
		String email = super.$("email");
		String drawUser = super.$("drawUser");
		String bankAddress = super.$("bankAddress");
		String agentName = super.$("agentName");
		Long agentId = super.$long("agentId");

		AccountVo accountVo = new AccountVo();
		accountVo.setId(id);
		accountVo.setAccount(account);
		accountVo.setBankId(bankId);
		accountVo.setStatus(accountStatus);
		accountVo.setAccountType(accountType);
		accountVo.setCardNo(cardNo);
		accountVo.setBankId(bankId);
		accountVo.setUserName(userName);
		accountVo.setPhone(phone);
		accountVo.setQq(qq);
		accountVo.setEmail(email);
		accountVo.setDrawUser(drawUser);
		accountVo.setBankAddress(bankAddress);
		accountVo.setAgentName(agentName);
		accountVo.setAgentId(agentId);
		sysAccountService.saveNewAccount(accountVo);
		super.renderSuccess();
	}

	@RequestMapping(value = "/updpwd")
	@ResponseBody
	public void updpwd() {
		Long id = super.$long("id");
		String pwd = super.$("pwd");
		String rpwd = super.$("rpwd");
		super.isNotNull(pwd, "新密码不能为空!");
		super.isNotNull(rpwd, "确认密码不能为空!");
		sysAccountService.updPwd(id, pwd, rpwd);
		super.renderSuccess();
	}
}
