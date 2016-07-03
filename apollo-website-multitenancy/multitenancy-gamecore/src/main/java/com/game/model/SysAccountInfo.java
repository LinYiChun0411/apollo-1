package com.game.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.Table;

@Table(name = "sys_account_info")
public class SysAccountInfo {

	@Column(name = "account_id", primarykey = true, generator = Column.PK_BY_HAND)
	private Long accountId;

	@Column(name = "bank_id")
	private Long bankId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "card_no")
	private String cardNo;

	@Column(name = "receipt_pwd")
	private String receiptPwd;

	@Column(name = "phone")
	private String phone;

	@Column(name = "province")
	private String province;

	@Column(name = "city")
	private String city;

	@Column(name = "email")
	private String email;

	@Column(name = "bank_address")
	private String bankAddress;

	@Column(name = "draw_user")
	private String drawUser;

	@Column(name = "qq")
	private String qq;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getReceiptPwd() {
		return receiptPwd;
	}

	public void setReceiptPwd(String receiptPwd) {
		this.receiptPwd = receiptPwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getDrawUser() {
		return drawUser;
	}

	public void setDrawUser(String drawUser) {
		this.drawUser = drawUser;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
}