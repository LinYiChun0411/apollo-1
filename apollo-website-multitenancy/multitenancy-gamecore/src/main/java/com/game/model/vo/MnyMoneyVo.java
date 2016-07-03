package com.game.model.vo;

import java.math.BigDecimal;

import com.game.model.dictionary.MoneyRecordType;

public class MnyMoneyVo {

	/**
	 * 金额
	 */
	private BigDecimal money;

	/**
	 * 会员ID
	 */
	private Long accountId;

	/**
	 * 货币类型
	 */
	private MoneyRecordType moneyRecordType;

	/**
	 * 备注
	 */
	private String remark;

	public MnyMoneyVo() {
	}

	public MoneyRecordType getMoneyRecordType() {
		return moneyRecordType;
	}

	public void setMoneyRecordType(MoneyRecordType moneyRecordType) {
		this.moneyRecordType = moneyRecordType;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
