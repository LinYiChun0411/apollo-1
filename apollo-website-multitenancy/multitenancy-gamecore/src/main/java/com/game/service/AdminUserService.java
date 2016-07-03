package com.game.service;

import org.jay.frame.jdbc.Page;

import com.game.model.AdminUser;

public interface AdminUserService {
	
	public AdminUser doLogin(String account,String pwd);

	public Page getUsers();
	
	public void saveUser(AdminUser user,String pwd,String rpwd);
	
	public void edit(AdminUser user);
	
	public void deleteUser(String ids);
	
	public void del(Integer id);
	
	public AdminUser getUserById(Long userId);
	
	public void closeOrOpen(Integer status,Integer id);
	
}
