package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;
import org.jay.frame.jdbc.model.BaseModel;

@Table(name = "sys_station_domain")
public class SysStationDomain extends BaseModel {
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

	@Column(name = "station_id")
	private Long stationId;

	@Column(name = "domain", length = 200)
	private String domain;

	// -- 状态 1-停用 2-启用,
	@Column(name = "status")
	private Long status;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStationId() {
		return this.stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
}
