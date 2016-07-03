package com.game.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.util.StringUtil;

import com.game.cache.CacheType;
import com.game.cache.CacheUtil;
import com.game.cache.DataReader;
import com.game.model.AdminMenu;
import com.game.model.AdminUser;
import com.game.service.AdminMenuService;
import com.game.util.SpringUtil;
import com.game.util.UserUtil;

public class PermissionManager {

	/**
	 * 总控后台权限Map
	 * 获取当前用户权限 key : url
	 * 	value ：{@link} AdminMenu status字段
	 * @return
	 */
	public static Map<String, Long> getAdminPermissionMap() {
		AdminUser user = (AdminUser) UserUtil.getCurrentUser();
		final Long groupId = user.getGroupId();
		Map map = CacheUtil.getNull2Set(new DataReader<Map>() {
			public Map getData() {
				AdminMenuService as = (AdminMenuService) SpringUtil.getBean("adminMenuServiceImpl");
				List<AdminMenu> menu = as.getGroupPermissionMenu(groupId);
				return toPermissionMap(menu);
			}
		}, Map.class, CacheType.ADMIN_GROUP_PERMISSION_MAP, groupId + "");
		return map;
	}
	
	private static Map<String, Long> toPermissionMap(List<AdminMenu> menu) {
		Map<String, Long> map = new HashMap<String, Long>();
		for (int i = 0; i < menu.size(); i++) {
			AdminMenu m = menu.get(i);
			String modulePath = StringUtil.trim2Null(m.getModulePath());
			if(StringUtil.isNotEmpty(modulePath)){
				map.put(modulePath, m.getStatus());
			}
			String functionPath = StringUtil.trim2Null(m.getUrl());
			if(StringUtil.isNotEmpty(functionPath)){
				map.put(functionPath, m.getStatus());
			}
		}
		return map;
	}
}
