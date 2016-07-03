package com.game.model;

import java.math.BigDecimal;
import java.util.Date;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;
import org.jay.frame.jdbc.model.BaseModel;

@Table(name = "mny_money_record")
public class MnyMoneyRecord extends BaseModel {

	@Column(name = "id", primarykey = true)
	private Long id;

	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "before_money")
	private BigDecimal beforeMoney;

	@Column(name = "after_money")
	private BigDecimal afterMoney;

	@Column(name = "money")
	private BigDecimal money;

	@Column(name = "type")
	private Long type;

	@Column(name = "create_user_id")
	private Long createUserId;

	@Column(name = "create_datetime")
	private Date createDatetime;
	
	@Column(name = "remark")
	private String remark;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBeforeMoney() {
		return beforeMoney;
	}

	public void setBeforeMoney(BigDecimal beforeMoney) {
		this.beforeMoney = beforeMoney;
	}

	public BigDecimal getAfterMoney() {
		return afterMoney;
	}

	public void setAfterMoney(BigDecimal afterMoney) {
		this.afterMoney = afterMoney;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}