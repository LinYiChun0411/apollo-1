package com.game.controller.admin.system;

import javax.servlet.http.HttpServletRequest;

import org.jay.frame.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.controller.admin.BaseAdminController;
import com.game.core.SystemConfig;
import com.game.model.AdminMenu;
import com.game.model.AdminUser;
import com.game.model.vo.AdminMenuNode;
import com.game.model.vo.AdminNavVo;
import com.game.permission.annotation.CheckType;
import com.game.permission.annotation.Permission;
import com.game.service.AdminMenuService;
/**
 * 菜单栏
 * @author admin
 *
 */
import com.game.util.UserUtil;

@Controller
@RequestMapping(SystemConfig.ADMIN_CONTROL_PATH + "/menu")
public class AdminMenuController extends BaseAdminController {

	@Autowired
	private AdminMenuService adminMenuService;

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
		AdminMenuNode node = adminMenuService.getMenuCache(groupId);
		super.renderJson(node);
	}

	/**
	 * 获取当前用户菜单栏
	 */
	@ResponseBody
	@RequestMapping("/menunav")
	@Permission(CheckType.OPEN)
	public void menunav(HttpServletRequest request) {
		long groupId = 0L;
		if (UserUtil.isSuperAdmin()) {
			groupId = 0L;
		} else {
			AdminUser user = (AdminUser) UserUtil.getCurrentUser();
			groupId = user.getGroupId().longValue();
		}
		AdminMenuNode node = adminMenuService.getMenuCache(groupId);

		AdminNavVo nav = new AdminNavVo();
		nav.setMenuNode(node);
		super.renderJson(nav);
	}

	@RequestMapping("/index")
	public String index() {
		return super.goPage("/page/system/menu.jsp");
	}

	@ResponseBody
	@RequestMapping("/getMenuTree")
	public void getMenuTree() {
		Long parentId = $long("parentId");
		if (parentId == null) {
			parentId = 0L;
		}
		super.render(adminMenuService.getMenuPage(parentId));
	}

	@ResponseBody
	@RequestMapping("/saveMenu")
	public void saveMenu() {
		String json = super.$c("data");
		AdminMenu menu = JsonUtil.toBean(json, AdminMenu.class);
		super.isNotNull(menu.getName(), "菜单名不能为空");
		super.isNotNull(menu.getLevel(), "级别不为空，数据异常");
		adminMenuService.saveMenu(menu);
		super.renderSuccess();
	}

	@ResponseBody
	@RequestMapping("/updMenu")
	public void updMenu(AdminMenu menu) {
		super.isNotNull(menu.getName(), "菜单名不能为空");
		super.isNotNull(menu.getLevel(), "级别不为空，数据异常");
		adminMenuService.saveMenu(menu);
		super.renderSuccess();
	}
}
