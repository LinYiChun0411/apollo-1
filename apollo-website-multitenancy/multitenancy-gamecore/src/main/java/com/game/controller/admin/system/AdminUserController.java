package com.game.controller.admin.system;

import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.AdminUser;
import com.game.service.AdminUserService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH+"/user")
public class AdminUserController extends BaseAdminController{
	
	@Autowired
	private AdminUserService adminUserService; 
	
	@ResponseBody
	@RequestMapping("/list")
	public void list(){
		Page page = adminUserService.getUsers();
		super.renderJson(page.toJsonStr());
	}
	
	@RequestMapping("/index")
	public String index(){
		return super.goPage("/page/system/usermanager.jsp");
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(AdminUser user) {
		String pwd = super.$("pwd");
		String rpwd = super.$("rpwd");
	
		adminUserService.saveUser(user,pwd,rpwd);
		super.renderSuccess();
	}
	
	@RequestMapping(value = "/edit")
	@ResponseBody
	public void edit(AdminUser user) {
		adminUserService.edit(user);
		super.renderSuccess();
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete() {
		String ids = super.$("ids");
		adminUserService.deleteUser(ids);
		super.renderSuccess();
	}
	
	/**
	 * 状态改变
	 * @param status
	 */
	@RequestMapping(value = "/closeOrOpen")
	@ResponseBody
	public void closeOrOpen(Integer status,Integer id) {
		adminUserService.closeOrOpen(status,id);
		super.renderSuccess();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public void del(Integer id) {
		adminUserService.del(id);
		super.renderSuccess();
	}
	
	
}


