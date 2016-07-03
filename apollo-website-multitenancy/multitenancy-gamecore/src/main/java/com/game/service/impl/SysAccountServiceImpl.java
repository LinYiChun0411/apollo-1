package com.game.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.SysUtil;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.core.SystemConfig;
import com.game.dao.SysAccountDao;
import com.game.dao.SysAccountInfoDao;
import com.game.model.SysAccount;
import com.game.model.SysAccountInfo;
import com.game.model.vo.AccountVo;
import com.game.service.SysAccountService;
import com.game.util.MD5Util;
import com.game.util.StationUtil;

@Repository
public class SysAccountServiceImpl implements SysAccountService {

	@Autowired
	private SysAccountDao sysAccountDao;

	@Autowired
	private SysAccountInfoDao sysAccountInfoDao;

	@Override
	public Page<Map> getPage(AccountVo avo) {
		return sysAccountDao.getPage(avo);
	}

	@Override
	public void saveAccount(SysAccount account) {

		sysAccountDao.save(account);
	}

	@Override
	public void updPwd(Long id, String pwd, String rpwd) {
		if (!pwd.equals(rpwd)) {
			throw new GenericException("两次密码不一致!");
		}

		if (id == null || id == 0l) {
			throw new GenericException("该用户不存在或已删除!");
		}

		SysAccount account = sysAccountDao.get(id);

		if (account == null) {
			throw new GenericException("该用户不存在或已删除!");
		}

		if (StringUtil.isNotEmpty(pwd)) {
			pwd = MD5Util.getMD5String(account.getAccount(), pwd);
			account.setPassword(pwd);
		}
		sysAccountDao.save(account);
	}

	@Override
	public void updStatus(SysAccount account) {

		Long accountId = account.getId();
		Long status = account.getAccountStatus();
		SysAccount saveAccount = null;

		if (Validator.isNull(accountId)) {
			throw new GenericException("该用户不存在或已删除!");
		}

		if (Validator.isNull(status)) {
			throw new GenericException("参数异常!");
		}

		saveAccount = sysAccountDao.get(accountId);
		saveAccount.setAccountStatus(status);

		sysAccountDao.save(saveAccount);
	}

	@Override
	public List<SysAccount> getAccountsByType(Long type) {
		return sysAccountDao.queryAccountsByType(type);
	}

	@Override
	public SysAccount doLoginForAgent(String account, String pwd) {
		SysAccount agent = sysAccountDao.getLoginAgent(account, pwd);
		if (agent == null) {
			throw new GenericException("账户不存在或密码错误");
		}
		if (agent.getAccountStatus() == SysAccount.ACCOUNT_STATUS_DISABLED) {
			throw new GenericException("账户已被禁用");
		}

		HttpSession session = SysUtil.getSession();
		session.setAttribute(SystemConfig.SESSION_AGENT_KEY, agent);
		return agent;
	}

	@Override
	public SysAccount doLoginForMember(String account, String pwd) {
		SysAccount member = sysAccountDao.getLoginMember(account);
		if (member == null || !StringUtils.equals(pwd, member.getPassword())) {
			throw new GenericException("账户不存在或密码错误");
		}
		if (member.getAccountStatus() == SysAccount.ACCOUNT_STATUS_DISABLED) {
			throw new GenericException("账户已被禁用");
		}

		HttpSession session = SysUtil.getSession();
		session.setAttribute(SystemConfig.SESSION_MEMBER_KEY, member);
		return member;
	}

	@Override
	public void doLogoutForMember() {
		HttpSession session = SysUtil.getSession();
		if (session != null)
			session.removeAttribute(SystemConfig.SESSION_MEMBER_KEY);
	}

	@Override
	public Page<Map> getMemberPage(AccountVo accountVo) {
		Long stationId = StationUtil.getStationId();
		accountVo.setAccountType(SysAccount.ACCOUNT_PLATFORM_MEMBER);
		accountVo.setStationId(stationId);
		return sysAccountDao.getPage(accountVo);
	}

	@Override
	public void saveAccountInfo(AccountVo accountVo) {
		Long accountId = accountVo.getId();
		if (Validator.isNull(accountId)) {
			throw new GenericException("该用户不存在");
		}

		SysAccountInfo sai = new SysAccountInfo();
		sai.setAccountId(accountId);
		sai.setBankId(accountVo.getBankId());
		sai.setCardNo(accountVo.getCardNo());
		sai.setUserName(accountVo.getUserName());
		sai.setPhone(accountVo.getPhone());
		sai.setQq(accountVo.getQq());
		sai.setEmail(accountVo.getEmail());
		sai.setDrawUser(accountVo.getDrawUser());
		sai.setBankAddress(accountVo.getBankAddress());
		sysAccountInfoDao.save(sai);

		SysAccount sa = sysAccountDao.get(accountId);
		sa.setAccountStatus(accountVo.getStatus());
		sysAccountDao.save(sa);
	}

	@Override
	public void saveNewAccount(AccountVo accountVo) {

		SysAccount agent = null;
		AccountVo agentVo = new AccountVo();
		Long agentId = accountVo.getAgentId();
		if (Validator.isNull(agentId)) {
			agentVo.setAccount(accountVo.getAgentName());
			agentVo.setAccountType(SysAccount.ACCOUNT_PLATFORM_AGENT);
			agent = sysAccountDao.queryAccount(agentVo);
		} else {
			agent = sysAccountDao.get(agentId);
		}
		if (agent == null) {
			throw new GenericException("所属上级不存在");
		}

		SysAccount sa = null;
		Long accountId = accountVo.getId();
		if (Validator.isNull(accountId)) {
			sa = new SysAccount();
			sa.setAccount(accountVo.getAccount());
			sa.setAccountType(accountVo.getAccountType());
			sa.setStationId(agent.getStationId());
			// 是会员判断站点下是否存在相同会员，是租户就判断是否存在相同账号
			if ((sysAccountDao.isNotUnique(sa, "account") && sa.getAccountType().intValue() == SysAccount.ACCOUNT_PLATFORM_AGENT) || (sysAccountDao.isNotUnique(sa, "account,stationId") && sa.getAccountType().intValue() == SysAccount.ACCOUNT_PLATFORM_MEMBER)) {
				throw new GenericException("该用户名已经被使用");
			}
			sa.setAgentId(agent.getId());
			sa.setAgentName(agent.getAccount());
		} else {
			sa = sysAccountDao.get(accountId);
		}

		SysAccountInfo sai = new SysAccountInfo();
		sai.setAccountId(accountId);
		sai.setBankId(accountVo.getBankId());
		sai.setCardNo(accountVo.getCardNo());
		sai.setUserName(accountVo.getUserName());
		sai.setPhone(accountVo.getPhone());
		sai.setQq(accountVo.getQq());
		sai.setEmail(accountVo.getEmail());
		sai.setDrawUser(accountVo.getDrawUser());
		sai.setBankAddress(accountVo.getBankAddress());
		sysAccountInfoDao.save(sai);

		sa.setAccountStatus(accountVo.getStatus());
		sysAccountDao.save(sa);
	}
}