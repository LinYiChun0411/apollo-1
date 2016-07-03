package com.game.controller.admin.system;

import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.AdminUserGroup;
import com.game.service.AdminUserGroupService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH+"/group")
public class AdminUserGroupController extends BaseAdminController{
	
	@Autowired
	private AdminUserGroupService adminUserGroupServices; 
	
	@ResponseBody
	@RequestMapping("/list")
	public void list(){
		Page page = adminUserGroupServices.getGroups();
		super.renderJson(page.toJsonStr());
	}
	
	@RequestMapping("/index")
	public String index(){
		return super.goPage("/page/system/usergroup.jsp");
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(AdminUserGroup group) {
	
		adminUserGroupServices.saveGroup(group);
		super.renderSuccess();
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		String ids = super.$("ids");
		super.isNotNull(ids, "参数异常!");
		adminUserGroupServices.deleteGroup(ids);
		super.renderSuccess();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public void del(Integer id) {
		adminUserGroupServices.del(id);
		super.renderSuccess();
	}
	
}



