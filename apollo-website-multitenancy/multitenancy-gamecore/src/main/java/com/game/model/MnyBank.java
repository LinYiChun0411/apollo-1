package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "mny_bank_list")
public class MnyBank {

	@Column(name = "id", primarykey = true)
	private Long id;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "bank_key")
	private String bankKey;

	@Column(name = "bank_type")
	private Long bankType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankKey() {
		return bankKey;
	}

	public void setBankKey(String bankKey) {
		this.bankKey = bankKey;
	}

	public Long getBankType() {
		return bankType;
	}

	public void setBankType(Long bankType) {
		this.bankType = bankType;
	}
}
