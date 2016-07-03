package com.game.model;

import java.math.BigDecimal;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "mny_money")
public class MnyMoney {

	@Column(name = "account_id", primarykey = true, generator = Column.PK_BY_HAND)
	private Long accountId;

	@Column(name = "money")
	private BigDecimal money;

	@Column(name = "money_type_id")
	private Long moneyTypeId;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Long getMoneyTypeId() {
		return moneyTypeId;
	}

	public void setMoneyTypeId(Long moneyTypeId) {
		this.moneyTypeId = moneyTypeId;
	}
}
