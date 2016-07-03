package com.game.service.impl;

import javax.servlet.http.HttpSession;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.SysUtil;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.game.core.SystemConfig;
import com.game.dao.AdminUserDao;
import com.game.dao.AdminUserGroupDao;
import com.game.model.AdminDictionary;
import com.game.model.AdminUser;
import com.game.model.AdminUserGroup;
import com.game.service.AdminDictionaryService;
import com.game.service.AdminUserService;
import com.game.util.MD5Util;
import com.game.util.UserUtil;

@Repository
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDao userDao;

	@Autowired
	private AdminUserGroupDao userGroupDao;

	@Autowired
	private AdminDictionaryService adminDictionaryService;

	public AdminUser doLogin(String account, String pwd) {
		String password = MD5Util.getMD5String(account, pwd);
		AdminUser user = userDao.getLoginUser(account, password);
		if (user == null) {
			throw new GenericException("账户不存在或密码错误");
		}
		if (user.getStatus().longValue() == AdminUser.STATUS_DISABLED) {
			throw new GenericException("账户已被禁用");
		}

		HttpSession session = SysUtil.getSession();
		session.setAttribute(SystemConfig.SESSION_ADMIN_KEY, user);
		return user;
	}

	public Page getUsers() {
		Page page = userDao.getUsers(UserUtil.getUserId());
		return page;
	}

	public void saveUser(AdminUser user, String pwd, String rpwd) {

		// 新增用户时要判断密码是否为空
		if (StringUtil.isEmpty(pwd) && Validator.isNull(user.getId())) {
			throw new GenericException("密码不能为空!");
		}
		if (!pwd.equals(rpwd)) {
			throw new GenericException("两次密码不一致!");
		}

		AdminUser saveUser = userDao.getUserById(user.getId());
		if (saveUser == null) {
			saveUser = user;
		} else {
			saveUser.setGroupId(user.getGroupId());
			saveUser.setStatus(user.getStatus());
		}
		if (StringUtil.isNotEmpty(pwd)) {
			pwd = MD5Util.getMD5String(user.getAccount(), pwd);
			saveUser.setPassword(pwd);
		}
		userDao.save(saveUser);
	}

	@Override
	public void edit(AdminUser user) {
		AdminUser saveUser = userDao.getUserById(user.getId());
		if (saveUser == null) {
			saveUser = user;
		} else {
			saveUser.setGroupId(user.getGroupId());
			saveUser.setStatus(user.getStatus());
		}
		userDao.update(saveUser);
		
	}
	
	public void deleteUser(String ids) {
		userDao.fakeDeletes(ids);
	}

	@Override
	public AdminUser getUserById(Long userId) {
		return null;
	}

	@Override
	public void closeOrOpen(Integer status,Integer id) {
		if(!StringUtils.isEmpty(status)&&!StringUtils.isEmpty(id)){
			if(status==1){
				userDao.closeOrOpen(2,id);
			}else{
				userDao.closeOrOpen(1,id);
			}
		}else{
			throw new GenericException("参数不正确!");
		}
	}

	@Override
	public void del(Integer id) {
		if(!StringUtils.isEmpty(id)&&id>0){
			userDao.delete(id);
		}else{
			throw new GenericException("参数不正确!");
		}
		
	}

}
