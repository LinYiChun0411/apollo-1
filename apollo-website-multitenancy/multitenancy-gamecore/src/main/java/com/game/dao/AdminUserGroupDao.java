package com.game.dao;

import java.util.List;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AdminUserGroup;

@Repository
public class AdminUserGroupDao extends JdbcUtilImpl<AdminUserGroup>{
	
	/**
	 * 
	 * @return
	 */
	public Page<AdminUserGroup> getGroups(){
		String sql = "SELECT * FROM admin_user_group";
		return super.paged2Obj(sql);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<AdminUserGroup> getGroupAll(){
		String sql = "SELECT * FROM admin_user_group";
		return super.query2Model(sql);
	}
	
	/**
	 * 
	 * @param groupId
	 * @return
	 */
	public AdminUserGroup getGroupById(Long groupId){
		String sql = "SELECT * FROM admin_user_group WHERE  id = :groupId";
		return super.query21Model(sql,MixUtil.newHashMap("groupId",groupId));
	}
}
