package com.game.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.permission.annotation.NotNeedLogin;
import com.game.service.SysAccountService;
import com.game.util.MD5Util;

@Controller
public class MemberLoginController extends BaseMemberController {
	@Autowired
	private SysAccountService sysAccountService;

	@NotNeedLogin
	@RequestMapping("/login")
	public String login() {
		String username = $c("username").trim().toLowerCase();
		String password = $c("password");
		password = MD5Util.getMD5String(username, password);
		sysAccountService.doLoginForMember(username, password);
		return "redirect:/lottery/index.do";  
	}

	@NotNeedLogin
	@RequestMapping("/logout")
	public String logout() {
		sysAccountService.doLogoutForMember();
		return "redirect:/index.do";  
	}

}
