package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name="admin_user_group")
public class AdminUserGroup {
	
	/**
	 * 账号启用状态
	 */
	public static final long STATUS_DISABLED = 1L;
	
	/**
	 * 账号禁用状态
	 */
	public static final long STATUS_ENABLED = 2L;
	
	@Column(name="id",primarykey=true)
	private Long id;
	
	@Column(name="name")
	private String name;

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
}
