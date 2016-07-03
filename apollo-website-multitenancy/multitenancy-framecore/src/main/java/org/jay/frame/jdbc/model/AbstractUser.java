package org.jay.frame.jdbc.model;

import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.jdbc.annotation.ParentModel;

import com.alibaba.fastjson.annotation.JSONField;
/**
 *  用户表对应的模型对象需要继承该对象    
 *  系统将使用AbstractUser抽象对象来判断当前是否存在登录用户
 */
@ParentModel
public abstract class AbstractUser extends BaseModel implements User{
	
	@Column(name="id",primarykey=true)
	public Long id;

	@Column(name="account")
	public String account;
	
	@JSONField(serialize = false)
	@Column(name="password")
	public String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Long getSysUserId(){
		return this.id;
	}
	
	@Override
	public String getLoginAccount(){
		return this.account;
	}
}
