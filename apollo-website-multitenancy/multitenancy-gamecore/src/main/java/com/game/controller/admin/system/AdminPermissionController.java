package com.game.controller.admin.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.AdminGroupMenu;
import com.game.model.vo.AdminMenuNode;
import com.game.service.AdminMenuService;
import com.game.service.AdminPermissionService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH+"/permission")
public class AdminPermissionController extends BaseAdminController{
	
	@Autowired
	private AdminMenuService menuService;
	
	@Autowired
	private AdminPermissionService permissionService;
	
	@RequestMapping("/index")
	public String index(){
		return super.goPage("/page/system/permission.jsp");
	}
	
	@RequestMapping("/getGroupPermission")
	@ResponseBody
	public void getGroupPermission(){
		long groupId = $cl("groupId");
		AdminMenuNode menuNode = menuService.getAllLevelThirdMenu();
		List<AdminGroupMenu> pm = permissionService.getGroupPermission(groupId);
		Map data = new HashMap();
		data.put("menus", menuNode);
		data.put("pm", pm);
		super.renderJson(data);
	}
	
	@RequestMapping("/savePermission")
	@ResponseBody
	public void savePermission(){
		long groupId = $cl("groupId");
		String idsStr = $c("ids");
		String [] ids = idsStr.split(",");
		List<AdminGroupMenu> ms = new ArrayList<AdminGroupMenu>();
		for (int i = 0; i < ids.length; i++) {
			AdminGroupMenu m = new AdminGroupMenu();
			m.setGroupId(groupId);
			m.setMenuId(StringUtil.toLong(ids[i]));
			ms.add(m);
		}
		permissionService.savePermission(groupId, ms);
		super.renderSuccess();
	}
}
