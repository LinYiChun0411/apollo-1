package com.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.AdminGroupMenuDao;
import com.game.dao.AgentMenuGroupDao;
import com.game.model.AdminGroupMenu;
import com.game.model.AgentMenuGroup;
import com.game.service.AdminPermissionService;

@Repository
public class AdminPermissionServiceImpl implements AdminPermissionService{

	@Autowired
	private AdminGroupMenuDao groupMenuDao;
	
	@Autowired
	private AgentMenuGroupDao agentMenuGroupDao;
	
	@Override
	public List<AdminGroupMenu> getGroupPermission(long groupId) {
		return groupMenuDao.getGroupPermission(groupId);
	}

	@Override
	public void savePermission(long groupId, List<AdminGroupMenu> menus) {
		groupMenuDao.deleteAll(groupId);
		groupMenuDao.batchInsert(menus);
	}
	
	@Override
	public List<AgentMenuGroup> getAgentPermission(long accountId) {
		return agentMenuGroupDao.getAgentPermission(accountId);
	}

	@Override
	public void saveAgentPermission(long accountId, List<AgentMenuGroup> menus) {
		agentMenuGroupDao.deleteAll(accountId);
		agentMenuGroupDao.batchInsert(menus);
	}
}
