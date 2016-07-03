package com.game.controller.admin;

import javax.servlet.http.HttpSession;

import org.jay.frame.util.SysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.core.SystemConfig;
import com.game.permission.annotation.NotNeedLogin;
import com.game.service.AdminUserService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH)
public class AdminLoginController extends BaseAdminController {

	@Autowired
	private AdminUserService adminUserService;

	@NotNeedLogin
	@ResponseBody
	@RequestMapping("/login")
	public void login() {
		String account = $c("account").trim().toLowerCase();
		String password = $c("password");
		adminUserService.doLogin(account, password);
		renderSuccess();
	}

	@NotNeedLogin
	@RequestMapping("/logout")
	public String logout() {
		HttpSession session = SysUtil.getSession();
		session.removeAttribute(SystemConfig.SESSION_ADMIN_KEY);
		return super.goPage("/index.jsp");
	}

	@NotNeedLogin
	@RequestMapping("/timeout")
	public String timeout() {
		return super.goPage("/page/timeout.jsp");
	}
}
