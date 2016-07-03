package com.game.service;

import java.util.List;

import com.game.model.AdminGroupMenu;
import com.game.model.AgentMenuGroup;

public interface AdminPermissionService {

	public List<AdminGroupMenu> getGroupPermission(long groupId);

	public void savePermission(long groupId, List<AdminGroupMenu> menus);
	
	public List<AgentMenuGroup> getAgentPermission(long accountId);

	public void saveAgentPermission(long accountId, List<AgentMenuGroup> menus);
}
