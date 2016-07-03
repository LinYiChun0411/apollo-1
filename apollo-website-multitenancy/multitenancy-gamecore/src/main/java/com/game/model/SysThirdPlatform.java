package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;
import org.jay.frame.jdbc.model.BaseModel;

@Table(name = "sys_third_platform")
public class SysThirdPlatform extends BaseModel {

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

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "status")
	private Long status;

	@Column(name = "remark")
	private String remark;

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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
