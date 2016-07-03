package com.game.controller.admin.platform;

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
import com.game.model.AgentMenuGroup;
import com.game.model.vo.AgentMenuNode;
import com.game.service.AdminPermissionService;
import com.game.service.AgentMenuService;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH+"/agentpermission")
public class AgentPermissionController extends BaseAdminController{
	
	@Autowired
	private AgentMenuService menuService;
	
	@Autowired
	private AdminPermissionService permissionService;
	
	@RequestMapping("/index")
	public String index(){
		return super.goPage("/page/platform/permission.jsp");
	}
	
	@RequestMapping("/getPermission")
	@ResponseBody
	public void getGroupPermission(){
		long agentId = $cl("agentId");
		AgentMenuNode menuNode = menuService.getAllLevelThirdMenu();
		List<AgentMenuGroup> pm = permissionService.getAgentPermission(agentId);
		Map data = new HashMap();
		data.put("menus", menuNode);
		data.put("pm", pm);
		super.renderJson(data);
	}
	
	@RequestMapping("/savePermission")
	@ResponseBody
	public void savePermission(){
		long agentId = $cl("agentId");
		String idsStr = $c("ids");
		String [] ids = idsStr.split(",");
		List<AgentMenuGroup> ms = new ArrayList<AgentMenuGroup>();
		for (int i = 0; i < ids.length; i++) {
			AgentMenuGroup m = new AgentMenuGroup();
			m.setAgentId(agentId);
			m.setMenuId(StringUtil.toLong(ids[i]));
			ms.add(m);
		}
		permissionService.saveAgentPermission(agentId, ms);
		super.renderSuccess();
	}
}
