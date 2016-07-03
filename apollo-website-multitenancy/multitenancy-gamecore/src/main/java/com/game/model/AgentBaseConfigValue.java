package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "agent_base_config_value")
public class AgentBaseConfigValue {

	/**
	 * 禁用
	 */
	public static Long AGENT_BASE_CONFIG_DISABLE = 1L;
	
	/**
	 * 启用
	 */
	public static Long AGENT_BASE_CONFIG_ENABLE = 2L;

	@Column(name = "id", primarykey = true)
	private Long id;

	@Column(name = "station_id")
	private Long stationId;

	@Column(name = "config_id")
	private Long configId;

	@Column(name = "value")
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
