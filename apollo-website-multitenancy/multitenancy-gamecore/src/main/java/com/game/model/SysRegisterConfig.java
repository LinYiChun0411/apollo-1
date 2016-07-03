package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;
import org.jay.frame.jdbc.model.BaseModel;

@Table(name = "sys_register_config")
public class SysRegisterConfig extends BaseModel {

	/**
	 * 禁用 、关闭
	 */
	public static final long STATUS_DISABLE = 1L;
	/**
	 * 启用
	 */
	public static final long STATUS_ENABLE = 2L;

	public static final long TYPE_MEMBER = 1L;
	public static final long TYPE_AGENT = 2L;

	@Column(name = "id", primarykey = true)
	private Long id;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "status")
	private Long status;

	@Column(name = "show")
	private Long show;

	@Column(name = "validate")
	private Long validate;

	@Column(name = "required")
	private Long required;

	@Column(name = "status_validate")
	private Long statusValidate;

	@Column(name = "type")
	private Long type;

	@Column(name = "platform")
	private Long platform;

	@Column(name = "regex")
	private String regex;

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getShow() {
		return show;
	}

	public void setShow(Long show) {
		this.show = show;
	}

	public Long getValidate() {
		return validate;
	}

	public void setValidate(Long validate) {
		this.validate = validate;
	}

	public Long getRequired() {
		return required;
	}

	public void setRequired(Long required) {
		this.required = required;
	}

	public Long getStatusValidate() {
		return statusValidate;
	}

	public void setStatusValidate(Long statusValidate) {
		this.statusValidate = statusValidate;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getPlatform() {
		return platform;
	}

	public void setPlatform(Long platform) {
		this.platform = platform;
	}
}
