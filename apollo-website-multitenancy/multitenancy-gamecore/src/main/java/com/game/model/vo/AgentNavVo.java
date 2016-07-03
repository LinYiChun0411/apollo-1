package com.game.model.vo;

public class AgentNavVo {

	/**
	 * 在线人数
	 */
	private Integer onlineUser;

	/**
	 * 入款单数
	 */
	private Integer depositCount;

	/**
	 * 出款单数
	 */
	private Integer withdrawCount;

	/**
	 * 代理菜单
	 */
	private AgentMenuNode menuNode;
	
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

	public Integer getOnlineUser() {
		return onlineUser;
	}

	public void setOnlineUser(Integer onlineUser) {
		this.onlineUser = onlineUser;
	}

	public Integer getDepositCount() {
		return depositCount;
	}

	public void setDepositCount(Integer depositCount) {
		this.depositCount = depositCount;
	}

	public Integer getWithdrawCount() {
		return withdrawCount;
	}

	public void setWithdrawCount(Integer withdrawCount) {
		this.withdrawCount = withdrawCount;
	}

	public AgentMenuNode getMenuNode() {
		return menuNode;
	}

	public void setMenuNode(AgentMenuNode menuNode) {
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
