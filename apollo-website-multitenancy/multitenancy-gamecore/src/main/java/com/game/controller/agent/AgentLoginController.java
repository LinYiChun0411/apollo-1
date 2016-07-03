package com.game.controller.agent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jay.frame.util.StringUtil;
import org.jay.frame.util.SysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.core.SystemConfig;
import com.game.model.SysAccount;
import com.game.model.vo.AgentMenuNode;
import com.game.model.vo.AgentNavVo;
import com.game.permission.annotation.NotNeedLogin;
import com.game.service.AgentMenuService;
import com.game.service.SysAccountService;
import com.game.util.MD5Util;
import com.game.util.UserUtil;

@Controller
@RequestMapping(SystemConfig.AGENT_CONTROL_PATH)
public class AgentLoginController extends BaseAgentController {

	@Autowired
	private SysAccountService sysAccountService;

	@Autowired
	private AgentMenuService agentMenuService;

	@NotNeedLogin
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		if (SysUtil.isLogin()) {
			return super.goPage("/page/home.jsp");
		} else {
			return super.goPage("/index.jsp");
		}
	}

	@NotNeedLogin
	@ResponseBody
	@RequestMapping("/login")
	public void login() {
		String account = $c("account").trim().toLowerCase();
		String password = $c("password");
		password = MD5Util.getMD5String(account, password);
		sysAccountService.doLoginForAgent(account, password);
		renderSuccess();
	}

	@NotNeedLogin
	@RequestMapping("/logout")
	public String logout() {
		HttpSession session = SysUtil.getSession();
		session.removeAttribute(SystemConfig.SESSION_AGENT_KEY);
		return super.goPage("/index.jsp");
	}

	@ResponseBody
	@RequestMapping("/nav")
	public void nav(HttpServletRequest request) {
		AgentNavVo nav = new AgentNavVo();
		SysAccount user = (SysAccount) UserUtil.getCurrentUser();
		AgentMenuNode menu = agentMenuService.getMenuCache(user.getId());
		nav.setMenuNode(menu);
		nav.setDepositCount(0);
		nav.setWithdrawCount(0);
		nav.setOnlineUser(0);
		super.renderJson(nav);
	}
}
