package com.game.controller.admin;

import org.jay.frame.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.core.SystemConfig;
import com.game.model.AdminDictionary;
import com.game.permission.annotation.CheckType;
import com.game.permission.annotation.Permission;
import com.game.service.AdminDictionaryService;
import com.game.service.AdminUserGroupService;
import com.game.service.SysAccountService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH+"/dictionary")
public class AdminDictionaryController extends BaseAdminController{
	
	@Autowired
	private AdminDictionaryService adminDictionaryService; 
	@Autowired
	private AdminUserGroupService adminUserGroupService; 
	@Autowired
	private SysAccountService sysAccountService; 
	
	@Permission(CheckType.OPEN)
	@ResponseBody
	@RequestMapping("/user/group")
	public void userGroup(){
		super.renderJson(JsonUtil.toJson(adminUserGroupService.getGroupCombo()));
	}
	
	@Permission(CheckType.OPEN)
	@ResponseBody
	@RequestMapping("/user/status")
	public void userStatus(){
		super.renderJson(JsonUtil.toJson(adminDictionaryService.getUserDictionarys(AdminDictionary.USER_STATUS_TYPE)));
	}
	
	@Permission(CheckType.OPEN)
	@ResponseBody
	@RequestMapping("/account/type/ddgd")
	public void accountTypeOfDdgd(){
		super.renderJson(JsonUtil.toJson(sysAccountService.getAccountsByType(AdminDictionary.ACCOUNT_TYPE_DDGD)));
	}
}
