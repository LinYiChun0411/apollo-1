package com.game.model;

import java.util.Date;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;


@Table(name="sys_announcement")
public class SysAnnouncement{
	/**
	 * 公告停用状态
	 */
	public static final long ANNOUNCEMENT_STATUS_DISABLED = 1L;
	
	/**
	 * 公告启用状态
	 */
	public static final long ANNOUNCEMENT_STATUS_ENABLE = 2L;
	
	/**
	 * 公告类别
	 */
	public static final long AANNOUNCEMENT_TYPE_DDGD = 1;
	
	@Column(name="id",primarykey=true)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="content")
	private String content;
	
	@Column(name="create_user_id")
	private Long createUserId;
	
	@Column(name="status")
	private Long status;
	
	@Column(name="type")
	private Long type;
	
	@Column(name="modify_user_id")
	private Long modifyUserId;
	
	@Column(name="begin_datetime")
	private Date beginDatetime;
	
	@Column(name="end_datetime")
	private Date endDatetime;
	
	@Column(name="create_datetime")
	private Date createDatetime;
	
	@Column(name="modify_datetime")
	private Date modifyDatetime;
	
	@Column(name="creator",temp=true)
	private String creator;
	
	@Column(name="modify_user",temp=true)
	private String modifyUser;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getBeginDatetime() {
		return beginDatetime;
	}

	public void setBeginDatetime(Date beginDatetime) {
		this.beginDatetime = beginDatetime;
	}

	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Date getModifyDatetime() {
		return modifyDatetime;
	}

	public void setModifyDatetime(Date modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
}