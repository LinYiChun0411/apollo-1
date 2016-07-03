package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;
import org.jay.frame.jdbc.model.AbstractUser;

@Table(name = "sys_account")
public class SysAccount extends AbstractUser {
	/**
	 * 账号启用状态
	 */
	public static final long ACCOUNT_STATUS_DISABLED = 1L;

	/**
	 * 账号禁用状态
	 */
	public static final long ACCOUNT_STATUS_ENABLED = 2L;

	/**
	 * 代理平台账号
	 */
	public static final long ACCOUNT_PLATFORM_AGENT = 2;

	/**
	 * 会员平台账号
	 */
	public static final long ACCOUNT_PLATFORM_MEMBER = 1;

	@Column(name = "account_type")
	private Long accountType;

	@Column(name = "account_status")
	private Long accountStatus;

	@Column(name = "agent_id")
	private Long agentId;

	@Column(name = "agent_name")
	private String agentName;

	@Column(name = "station_id")
	private Long stationId;

	public Long getAccountType() {
		return accountType;
	}

	public void setAccountType(Long accountType) {
		this.accountType = accountType;
	}

	public Long getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Long accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
}