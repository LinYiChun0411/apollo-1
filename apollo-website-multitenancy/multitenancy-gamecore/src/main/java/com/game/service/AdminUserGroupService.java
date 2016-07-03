package com.game.service;

import java.util.List;

import org.jay.frame.jdbc.Page;

import com.game.model.AdminUserGroup;

public interface AdminUserGroupService {
	
	public Page getGroups();
	
	public List<AdminUserGroup> getGroupCombo();
	
	public void saveGroup(AdminUserGroup group);
	
	public void deleteGroup(String ids);
	
	public void del(Integer id);
	
	public AdminUserGroup getGroupById(Long groupId);
	
}
