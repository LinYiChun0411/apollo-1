package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

import java.util.Date;

@Table(name = "sys_station")
public class SysStation {
	/**
	 * 禁用 、关闭
	 */
	public static final long STATUS_DISABLE = 1L;
	/**
	 * 启用
	 */
	public static final long STATUS_ENABLE = 2L;

	@Column(name = "id", primarykey = true)
	private Long id;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "status")
	private Long status;

	/**
	 * 站点对应的文件夹目录
	 */
	@Column(name = "floder", length = 50)
	private String floder;

	@Column(name = "create_datetime")
	private Date createDatetime;

	@Column(name = "close_time")
	private Date closeTime;

	@Column(name = "open_time")
	private Date openTime;

	@Column(name = "account", temp = true)
	private String account;

	@Column(name = "account_id")
	private Long accountId;

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

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getFloder() {
		return this.floder;
	}

	public void setFloder(String floder) {
		this.floder = floder;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
}