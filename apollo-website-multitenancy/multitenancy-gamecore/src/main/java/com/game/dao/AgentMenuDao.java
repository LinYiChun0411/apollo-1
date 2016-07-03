package com.game.dao;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AgentMenu;

@Repository
public class AgentMenuDao extends JdbcUtilImpl<AgentMenu> {
	/**
	 * 根据用户组菜单权限 用于显示菜单栏
	 * 
	 * @return
	 */
	public List<AgentMenu> getAgentMenu(Long agentId) {
		StringBuilder sql_sb = new StringBuilder("");
		sql_sb.append("SELECT m.*");
		sql_sb.append(" FROM agent_menu_group a INNER JOIN agent_menu m ON a.menu_id = m.id");
		sql_sb.append(" WHERE a.agent_id = :agentId");

		return super.query2Model(sql_sb.toString(), MixUtil.newHashMap("agentId", agentId));
	}

	/**
	 * 获取超级管理员显示菜单栏 用于显示菜单栏
	 * 
	 * @return
	 */
	public List<AgentMenu> getRootMenu() {
		String sql = "SELECT * FROM agent_menu " + " WHERE status = " + AgentMenu.STATUS_ENABLED + ""
				+ " and level <= 2 ";
		return super.query2Model(sql);
	}

	/**
	 * 用于权限判断
	 * 
	 * @param groupId
	 * @return
	 */
	public List<AgentMenu> getAgentPermissionMenu(Long agentId) {

		StringBuilder sql_sb = new StringBuilder("");
		sql_sb.append("SELECT m.*");
		sql_sb.append(" FROM agent_menu_group a INNER JOIN agent_menu m ON a.menu_id = m.id");
		sql_sb.append(" WHERE a.agent_id = :agentId");
		sql_sb.append("status <> ");
		sql_sb.append(AgentMenu.STATUS_DISABLED);
		sql_sb.append(" AND level <= 3");

		return super.query2Model(sql_sb.toString(), MixUtil.newHashMap("agentId", agentId));
	}

	/**
	 * 获取超级管理员显示菜单栏 用于显示菜单栏
	 * 
	 * @return
	 */
	public List<AgentMenu> getRootPermissionMenu() {
		String sql = "SELECT * FROM agent_menu " + " WHERE status <> " + AgentMenu.STATUS_DISABLED + ""
				+ " and level <= 3 ";
		return super.query2Model(sql);
	}

	public Page<Map> getMenuPage(Long parentId) {
		String sql = "SELECT * FROM agent_menu " + " WHERE parent_id = :parentId " + " ORDER BY sort ";
		return super.page2CamelMap(sql, MixUtil.newHashMap("parentId", parentId));
	}

	public List<AgentMenu> getMenuByLevel(int level) {
		String sql = "SELECT * FROM agent_menu " + " WHERE level <= :level ";
		return super.query2Model(sql, MixUtil.newHashMap("level", level));
	}
}
