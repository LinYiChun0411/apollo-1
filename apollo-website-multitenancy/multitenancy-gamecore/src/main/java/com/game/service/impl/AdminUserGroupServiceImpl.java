package com.game.service.impl;

import java.util.List;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.game.dao.AdminUserDao;
import com.game.dao.AdminUserGroupDao;
import com.game.model.AdminUserGroup;
import com.game.service.AdminUserGroupService;

@Repository
public class AdminUserGroupServiceImpl implements AdminUserGroupService{

	@Autowired
	private AdminUserGroupDao userGroupDao;
	
	@Autowired
	private AdminUserDao userDao;
	
	@Override
	public Page getGroups() {
		return userGroupDao.getGroups();
	}

	@Override
	public void saveGroup(AdminUserGroup group) {
		
		if(userGroupDao.isNotUnique(group, "name")){
			throw new GenericException("已经存在名为：\""+group.getName()+"\"的用户组别！");
		}
		
		
		userGroupDao.save(group);
		
	}

	@Override
	public void deleteGroup(String ids) {
		if(userDao.isUsedGroup(ids)){
			throw new GenericException("您要删除的组别正在被用户使用！");
		}
		userGroupDao.deletes(ids);
	}

	@Override
	public AdminUserGroup getGroupById(Long groupId) {
		return userGroupDao.getGroupById(groupId);
	}

	@Override
	public List<AdminUserGroup> getGroupCombo() {
		return userGroupDao.getGroupAll();
	}
	
	@Override
	public void del(Integer id) {
		if(!StringUtils.isEmpty(id)&&id>0){
			userGroupDao.delete(id);
		}else{
			throw new GenericException("参数不正确!");
		}
		
	}
	
}
