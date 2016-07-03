package com.game.model;

import java.util.Date;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;


@Table(name="sys_proposal")
public class SysProposal{
	/**
	 * 投诉建议未读状态
	 */
	public static final long PROPOSAL_STATUS_DISABLED = 1L;
	
	/**
	 * 投诉建议已读状态
	 */
	public static final long PROPOSAL_STATUS_ENABLE = 2L;
	
	/**
	 * 投诉建议类别
	 */
	public static final long PROPOSAL_TYPE_DDGD = 1;
	
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
	
	@Column(name="station_id")
	private Long stationId;
	
	@Column(name="create_datetime")
	private Date createDatetime;
	
	@Column(name="creator",temp=true)
	private String creator;
	
	@Column(name="station_name",temp=true)
	private String stationName;
	
	@Column(name="qq",temp=true)
	private String qq;
	
	@Column(name="phone",temp=true)
	private String phone;

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

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}