package com.game.dao;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AdminMenu;

@Repository
public class AdminMenuDao extends JdbcUtilImpl<AdminMenu>{
	/**
	 * 根据用户组菜单权限
	 * 用于显示菜单栏
	 * @return
	 */
	public List<AdminMenu> getGroupMenu(Long groupId){
		String sql = "SELECT b.* FROM admin_group_menu a "
				+" LEFT JOIN admin_menu b "
				+ " ON(a.menu_id = b.id) "
				+ " WHERE status = " + AdminMenu.STATUS_ENABLED  
				+ " AND level <= 2 "
				+" AND group_id = :groupId " ;
		return super.query2Model(sql,MixUtil.newHashMap("groupId",groupId));
	}
	
	/**
	 * 获取超级管理员显示菜单栏
	 * 用于显示菜单栏
	 * @return
	 */
	public List<AdminMenu> getRootMenu(){
		String sql = "SELECT * FROM admin_menu "
				+ " WHERE status = " + AdminMenu.STATUS_ENABLED +""
						+ " and level <= 2 ";
		return super.query2Model(sql);
	}
	/**
	 * 用于权限判断
	 * @param groupId
	 * @return
	 */
	public List<AdminMenu> getGroupPermissionMenu(Long groupId){
		String sql = "SELECT b.* FROM admin_group_menu a "
				+" LEFT JOIN admin_menu b "
				+ " ON(a.menu_id = b.id) "
				+ " WHERE status <> " + AdminMenu.STATUS_DISABLED  
				+ " AND level <= 3 "
				+" AND group_id = :groupId " ;
		return super.query2Model(sql,MixUtil.newHashMap("groupId",groupId));
	}
	
	/**
	 * 获取超级管理员显示菜单栏
	 * 用于显示菜单栏
	 * @return
	 */
	public List<AdminMenu> getRootPermissionMenu(){
		String sql = "SELECT * FROM admin_menu "
				+ " WHERE status <> " + AdminMenu.STATUS_DISABLED +""
						+ " and level <= 3 ";
		return super.query2Model(sql);
	}
	
	public Page<AdminMenu> getMenuPage(Long parentId) {
		String sql = "SELECT * FROM admin_menu "
				+ " WHERE parent_id = :parentId "
				+ " ORDER BY sort " ;
		return super.paged2Obj(sql,MixUtil.newHashMap("parentId",parentId));
	}
	
	public List<AdminMenu> getMenuByLevel(int level){
		String sql = "SELECT * FROM admin_menu "
				+ " WHERE level <= :level " ;
		return super.query2Model(sql,MixUtil.newHashMap("level",level));
	}
}
