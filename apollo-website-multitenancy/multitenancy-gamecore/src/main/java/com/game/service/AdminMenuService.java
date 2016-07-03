package com.game.service;

import java.util.List;

import org.jay.frame.jdbc.Page;

import com.game.model.AdminMenu;
import com.game.model.vo.AdminMenuNode;

public interface AdminMenuService {
	
	/**
	 * 根据用户组别 获取到二级菜单
	 * 优先从缓存中取，若缓存没有则从数据库中取再放入缓存堆中
	 * @return
	 */
	public AdminMenuNode getMenuCache(Long groupId);
	
	/**
	 * 读取到三级按钮
	 * @param groupId
	 * @return
	 */
	public AdminMenuNode getAllLevelThirdMenu();
	
	/**
	 * 读取用户组，下面的所有权限菜单
	 * @param groupId
	 * @return
	 */
	public List<AdminMenu> getGroupPermissionMenu(Long groupId);
	
	public Page<AdminMenu> getMenuPage(Long parentId);
	
	public void saveMenu(AdminMenu menu);
}
