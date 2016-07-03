package com.game.controller;

import javax.servlet.http.HttpServletRequest;

import org.jay.frame.util.SysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.game.controller.member.BaseMemberController;
import com.game.core.SystemConfig;
import com.game.model.AdminUser;
import com.game.model.vo.AdminMenuNode;
import com.game.permission.annotation.NotNeedLogin;
import com.game.service.AdminMenuService;
import com.game.util.StationUtil;
import com.game.util.UserUtil;

@Controller
public class HomepageController extends BaseMemberController{
	
	
	@Autowired
	private AdminMenuService adminMenuService;
	
	@NotNeedLogin
	@RequestMapping("/index")
	public String index(HttpServletRequest request){
		if(StationUtil.isAdminPage()){//总控后台
			if(SysUtil.isLogin()){
				AdminUser user = (AdminUser)UserUtil.getCurrentUser();
				AdminMenuNode menu = adminMenuService.getMenuCache(user.getGroupId());
				request.setAttribute("menu", menu.getNodes());
				return SystemConfig.SOURCE_FOLDER_ADMIN + "/page/home.jsp";
			}else{
				return SystemConfig.SOURCE_FOLDER_ADMIN + "/index.jsp";
			}
		}
		//会员端
		return super.goPage("/index.jsp");
	}
	
}
