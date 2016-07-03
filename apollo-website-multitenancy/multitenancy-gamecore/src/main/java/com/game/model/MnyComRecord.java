package com.game.model;

import java.math.BigDecimal;
import java.util.Date;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "mny_com_record")
public class MnyComRecord {
	
	@Column(name = "id", primarykey = true)
	private Long id;

	@Column(name = "order_no")
	private String orderNo;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "station_id")
	private Long stationId;

	@Column(name = "money")
	private BigDecimal money;

	@Column(name = "status")
	private Long stauts;
	
	@Column(name = "type")
	private Long type;

	@Column(name = "create_user_id")
	private Long createUserId;

	@Column(name = "create_datetime")
	private Date createDatetime;

	@Column(name = "modify_datetime")
	private Date modifyDatetime;

	@Column(name = "modify_user_id")
	private Long modifyUserId;
	
	@Column(name = "remark")
	private String remark;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Long getStauts() {
		return stauts;
	}

	public void setStauts(Long stauts) {
		this.stauts = stauts;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
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

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
}
