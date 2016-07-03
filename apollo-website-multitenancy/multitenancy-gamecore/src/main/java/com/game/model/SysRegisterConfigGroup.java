package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "sys_register_config_group")
public class SysRegisterConfigGroup {

	@Column(name = "id", primarykey = true)
	private Long id;

	@Column(name = "conf_id")
	private Long configId;

	@Column(name = "station_id")
	private Long stationId;

	@Column(name = "show_val")
	private Long showVal;

	@Column(name = "validate_val")
	private Long validateVal;

	@Column(name = "required_val")
	private Long requiredVal;

	@Column(name = "status_validate_val")
	private Long statusValidateVal;

	@Column(name = "source")
	private String source;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConfigId() {
		return configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long getShowVal() {
		return showVal;
	}

	public void setShowVal(Long showVal) {
		this.showVal = showVal;
	}

	public Long getValidateVal() {
		return validateVal;
	}

	public void setValidateVal(Long validateVal) {
		this.validateVal = validateVal;
	}

	public Long getRequiredVal() {
		return requiredVal;
	}

	public void setRequiredVal(Long requiredVal) {
		this.requiredVal = requiredVal;
	}

	public Long getStatusValidateVal() {
		return statusValidateVal;
	}

	public void setStatusValidateVal(Long statusValidateVal) {
		this.statusValidateVal = statusValidateVal;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
