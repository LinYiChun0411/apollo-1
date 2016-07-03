package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "agent_menu_group")
public class AgentMenuGroup {
	/**
	 * 主键
	 */
	@Column(name = "id", primarykey = true)
	private Long id;

	/**
	 * 关联sys_account表 id
	 */
	@Column(name = "agent_id")
	private Long agentId;

	/**
	 * 关联agent_menu表id
	 */
	@Column(name = "menu_id")
	private Long menuId;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
}
