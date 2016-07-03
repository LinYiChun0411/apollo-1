package com.game.model.vo;

import java.util.Date;

public class MnyMoneyRecordVo {

	/**
	 * 会员ID
	 */
	private Long accountId;

	/**
	 * 会员账号
	 */
	private String account;

	/**
	 * 记录类型
	 */
	private Long type;

	private Long stationId;

	private Date begin;
	private Date end;

	public MnyMoneyRecordVo() {
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
