package com.game.service;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.SysAccount;
import com.game.model.vo.AccountVo;

public interface SysAccountService {
	public Page<Map> getPage(AccountVo avo);

	public void saveAccount(SysAccount account);

	public void saveAccountInfo(AccountVo accountVo);
	
	public void saveNewAccount(AccountVo accountVo);

	public void updStatus(SysAccount account);

	public void updPwd(Long id, String pwd, String rpwd);

	public List<SysAccount> getAccountsByType(Long type);

	public SysAccount doLoginForAgent(String account, String pwd);

	public SysAccount doLoginForMember(String account, String pwd);

	public Page<Map> getMemberPage(AccountVo accountVo);

	public void doLogoutForMember();
}
