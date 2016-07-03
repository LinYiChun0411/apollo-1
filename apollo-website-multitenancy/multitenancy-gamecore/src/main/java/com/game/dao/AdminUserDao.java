package com.game.dao;

import java.util.HashMap;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AdminUser;

@Repository
public class AdminUserDao extends JdbcUtilImpl<AdminUser>{
	/**
	 * 通过账号和密码获取用户 
	 * 用于登陆验证
	 * @param account
	 * @param pwd
	 * @return
	 */
	public AdminUser getLoginUser(String account,String pwd){
		String sql = "SELECT * FROM admin_user WHERE flag_active >= 1 and account = :account and password = :pwd ";
		return super.query21Model(sql,MixUtil.newHashMap("account",account,"pwd",pwd));
	}
	
	/**
	 * 得到用记集合 
	 * 用于用户管理
	 * @param userId
	 * @return
	 */
	public Page getUsers(Long userId){
		StringBuilder sql_sb = new StringBuilder("");
		sql_sb.append("SELECT a.*,b.account AS creator,c.account AS modify_user,d.name group_name");
		sql_sb.append(" FROM admin_user a "
					+ " LEFT JOIN admin_user b ON(a.create_user_id = b.id)");
		sql_sb.append(" LEFT JOIN admin_user c ON (a.modify_user_id = c.id)");
		sql_sb.append(" LEFT JOIN admin_user_group d on(a.group_id = d.id)");
		sql_sb.append(" WHERE a.flag_active >= 1 AND a.id != :id");
		return super.page2CamelMap(sql_sb.toString(), MixUtil.newHashMap("id",userId));
	}
	
	/**
	 * 通过用户ID获取用户 
	 * @param userId
	 * @return
	 */
	public AdminUser getUserById(Long userId){
		String sql = "SELECT * FROM admin_user WHERE flag_active >= 1 and id = :userId";
		return super.query21Model(sql,MixUtil.newHashMap("userId",userId));
	}
	
	public boolean isUsedGroup(String groupIds){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		StringBuilder sql_sb = new StringBuilder("");
		sql_sb.append("SELECT count(1)");
		sql_sb.append(" FROM admin_user");
		sql_sb.append(" WHERE flag_active >= 1 and group_id in (");
		String[] ids = groupIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			if(i>0){
				sql_sb.append(",");
			}
			sql_sb.append(":groupId_"+i);
			paramMap.put("groupId_"+i, Long.valueOf(ids[i]));
		}
		sql_sb.append(")");
		return super.queryForInt(sql_sb.toString(),paramMap) > 0;
	}
	
	public void closeOrOpen(Integer status,Integer id){
		StringBuilder sb = new StringBuilder("update admin_user set status= :status");
		if(id>0){
			sb.append(" where id=:id");
		}
		Map<Object,Object> map=new HashMap<Object,Object>();
		map.put("status", status);
		map.put("id", id);
		super.update(sb.toString(), map);
	}
}
