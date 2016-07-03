package com.game.dao;

import java.util.List;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AdminGroupMenu;

@Repository
public class AdminGroupMenuDao extends JdbcUtilImpl<AdminGroupMenu>{
	
	public List<AdminGroupMenu> getGroupPermission(long groupId){
		String sql = "select * from admin_group_menu where group_id = :groupId ";
		return super.query2Model(sql, MixUtil.newHashMap("groupId",groupId));
	}
	
	
	public void deleteAll(long groupId){
		String sql = " delete from admin_group_menu where group_id = :groupId ";
		super.update(sql, MixUtil.newHashMap("groupId",groupId));
	}
}
