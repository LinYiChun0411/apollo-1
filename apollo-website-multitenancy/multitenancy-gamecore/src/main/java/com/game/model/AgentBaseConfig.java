package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "agent_base_config")
public class AgentBaseConfig {

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

	@Column(name = "name")
	private String name;

	@Column(name = "key")
	private String key;

	@Column(name = "remark")
	private String remark;

	@Column(name = "type")
	private String type;

	@Column(name = "title")
	private String title;

	@Column(name = "expand")
	private String expand;

	@Column(name = "init_value")
	private String initValue;

	@Column(name = "source")
	private String source;

	@Column(name = "status")
	private Long status;

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
	}

	public String getInitValue() {
		return initValue;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
}
