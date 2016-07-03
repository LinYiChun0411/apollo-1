package com.game.controller.admin.platform;

import org.jay.frame.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.AdminUser;
import com.game.model.AgentMenu;
import com.game.model.vo.AgentMenuNode;
import com.game.permission.annotation.CheckType;
import com.game.permission.annotation.Permission;
import com.game.service.AgentMenuService;
/**
 * 菜单栏
 * @author admin
 *
 */
import com.game.util.UserUtil;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/agentmenu")
public class AgentMenuController extends BaseAdminController {

	@Autowired
	private AgentMenuService agentMenuService;

	/**
	 * 获取当前用户菜单栏
	 */
	@ResponseBody
	@RequestMapping("/getCurMenu")
	@Permission(CheckType.OPEN)
	public void getCurMenu() {
		long groupId = 0L;
		if (UserUtil.isSuperAdmin()) {
			groupId = 0L;
		} else {
			AdminUser user = (AdminUser) UserUtil.getCurrentUser();
			groupId = user.getGroupId().longValue();
		}
		AgentMenuNode node = agentMenuService.getMenuCache(groupId);
		super.renderJson(node);
	}

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/platform/menu.jsp");
	}

	@ResponseBody
	@RequestMapping("/getMenuTree")
	public void getMenuTree() {
		Long parentId = $long("parentId");
		if (parentId == null) {
			parentId = 0L;
		}
		super.render(agentMenuService.getMenuPage(parentId));
	}

	@ResponseBody
	@RequestMapping("/saveMenu")
	public void saveMenu() {
		String json = super.$c("data");
		AgentMenu menu = JsonUtil.toBean(json, AgentMenu.class);
		super.isNotNull(menu.getName(), "菜单名不能为空");
		super.isNotNull(menu.getLevel(), "级别不为空，数据异常");
		agentMenuService.saveMenu(menu);
		super.renderSuccess();
	}

	@ResponseBody
	@RequestMapping("/updMenu")
	public void updMenu(AgentMenu menu) {
		super.isNotNull(menu.getName(), "菜单名不能为空");
		super.isNotNull(menu.getLevel(), "级别不为空，数据异常");
		agentMenuService.saveMenu(menu);
		super.renderSuccess();
	}
}
