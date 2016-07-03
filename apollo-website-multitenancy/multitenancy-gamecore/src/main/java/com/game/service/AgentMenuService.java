package com.game.service;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.AgentMenu;
import com.game.model.vo.AgentMenuNode;

public interface AgentMenuService {
	
	/**
	 * 根据用户组别 获取到二级菜单
	 * 优先从缓存中取，若缓存没有则从数据库中取再放入缓存堆中
	 * @return
	 */
	public AgentMenuNode getMenuCache(Long agentId);
	
	/**
	 * 读取到三级按钮
	 * @param groupId
	 * @return
	 */
	public AgentMenuNode getAllLevelThirdMenu();
	
	/**
	 * 读取用户组，下面的所有权限菜单
	 * @param groupId
	 * @return
	 */
	public List<AgentMenu> getAgentPermissionMenu(Long agentId);
	
	public Page<Map> getMenuPage(Long parentId);
	
	public void saveMenu(AgentMenu menu);
}
