package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;
import org.jay.frame.jdbc.model.AbstractUser;

@Table(name="admin_user")
public class AdminUser extends AbstractUser {
	
	/**
	 * 账号启用状态
	 */
	public static final long STATUS_DISABLED = 1L;
	
	/**
	 * 账号禁用状态
	 */
	public static final long STATUS_ENABLED = 2L;
	
	
	@Column(name="group_id")
	private Long groupId;

	@Column(name="status")
	private Long status;
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getGroupId(){
		return this.groupId;
	}
	
	public void setGroupId(Long groupId){
		this.groupId = groupId;
	}
}
