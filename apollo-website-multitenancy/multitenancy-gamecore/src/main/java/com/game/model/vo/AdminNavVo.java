package com.game.model.vo;

public class AdminNavVo {

	/**
	 * 代理菜单
	 */
	private AdminMenuNode menuNode;

	/**
	 * 一级导航索引
	 */
	private Integer agentMenuFst;

	/**
	 * 导航展示
	 */
	private boolean show;

	/**
	 * 二级导航索引
	 */
	private Integer agentMenuSec;

	public AdminMenuNode getMenuNode() {
		return menuNode;
	}

	public void setMenuNode(AdminMenuNode menuNode) {
		this.menuNode = menuNode;
	}

	public Integer getAgentMenuFst() {
		return agentMenuFst;
	}

	public void setAgentMenuFst(Integer agentMenuFst) {
		this.agentMenuFst = agentMenuFst;
	}

	public Integer getAgentMenuSec() {
		return agentMenuSec;
	}

	public void setAgentMenuSec(Integer agentMenuSec) {
		this.agentMenuSec = agentMenuSec;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
}
