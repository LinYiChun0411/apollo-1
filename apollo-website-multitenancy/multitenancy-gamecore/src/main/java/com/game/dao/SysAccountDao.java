package com.game.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;
import org.springframework.stereotype.Repository;

import com.game.model.SysAccount;
import com.game.model.vo.AccountVo;
import com.game.util.StationUtil;

@Repository
public class SysAccountDao extends JdbcUtilImpl<SysAccount> {
	public SysAccount queryDdgd(String account, Long type) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT * ");
		sql_sb.append(" FROM sys_account a");
		sql_sb.append(" WHERE a.account = :account AND a.account_type = :type");

		return super.query21Model(sql_sb.toString(), MixUtil.newHashMap("account", account, "type", type));
	}

	public List<SysAccount> queryAccountsByType(Long type) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT * ");
		sql_sb.append(" FROM sys_account a");
		sql_sb.append(" WHERE a.account_type = :type");

		return super.query2Model(sql_sb.toString(), MixUtil.newHashMap("type", type));
	}

	/**
	 * 代理登录
	 * 
	 * @param account
	 * @param pwd
	 * @param ddgdId
	 * @return
	 */
	public SysAccount getLoginAgent(String account, String pwd) {
		return getLoginAccount(account, pwd, SysAccount.ACCOUNT_PLATFORM_AGENT);
	}

	/**
	 * 会员登录
	 * 
	 * @param account
	 * @param pwd
	 * @param ddgdId
	 * @return
	 */
	public SysAccount getLoginMember(String account) {
		SysAccount acc = super.query21Model("select * from sys_account where station_id=:stationId and account=:account and account_type=:type", MixUtil.newHashMap("stationId", StationUtil.getStationId(), "account", account, "type", SysAccount.ACCOUNT_PLATFORM_MEMBER));
		return acc;
	}

	/**
	 * 通过账号和密码获取用户 用于登陆验证
	 * 
	 * @param account
	 * @param pwd
	 * @param type
	 * @param platform
	 * @return
	 */
	public SysAccount getLoginAccount(String account, String pwd, Long platform) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT a.*");
		sql_sb.append(" FROM sys_account a INNER JOIN sys_station s ON a.id = s.account_id");
		sql_sb.append(" WHERE flag_active >= 1 AND s.id = :stationId");
		sql_sb.append(" AND account = :account AND password = :pwd");
		if (platform == SysAccount.ACCOUNT_PLATFORM_MEMBER) {
			sql_sb.append(" AND account_type=:type");
		} else if (platform == SysAccount.ACCOUNT_PLATFORM_AGENT) {
			sql_sb.append(" AND account_type>:type");
		}
		return super.query21Model(sql_sb.toString(), MixUtil.newHashMap("stationId", StationUtil.getStationId(), "account", account, "pwd", pwd, "type", SysAccount.ACCOUNT_PLATFORM_MEMBER));
	}

	/**
	 * 通过账号和密码获取用户 用于登陆验证
	 * 
	 * @param accountVo
	 * @return
	 */
	public SysAccount queryAccount(AccountVo avo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT *");
		sql_sb.append(" FROM sys_account");
		sql_sb.append(" WHERE flag_active >= 1");

		Map paramMap = MixUtil.newHashMap();
		if (StringUtil.isNotEmpty(avo.getAccount())) {
			sql_sb.append(" AND account = :account");
			paramMap.put("account", avo.getAccount());
		}

		if (Validator.isNotNull(avo.getStationId())) {
			sql_sb.append(" AND a.station_id = :stationId");
			paramMap.put("stationId", avo.getStationId());
		}

		if (Validator.isNotNull(avo.getAccountType())) {
			sql_sb.append(" AND account_type=:type");
			paramMap.put("type", avo.getAccountType());
		}
		List<SysAccount> accounts = super.query2Model(sql_sb.toString(), paramMap);
		if (accounts == null || accounts.size() == 0) {
			return null;
		}
		return accounts.get(0);
	}

	public Page<Map> getPage(AccountVo accountVo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT a.id, a.account, a.create_datetime, a.account_status, a.account_type, a.agent_id,a.agent_name, i.*, m.money ");
		sql_sb.append(" FROM sys_account a LEFT JOIN sys_account_info i ON a.id = i.account_id ");
		sql_sb.append(" LEFT JOIN mny_money m ON a.id = m.account_id");
		sql_sb.append(" WHERE flag_active >=1");

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(accountVo.getAccount())) {
			paramMap.put("account", accountVo.getAccount() + "%");
			sql_sb.append(" AND a.account LIKE :account");
		}
		if (Validator.isNotNull(accountVo.getStatus())) {
			paramMap.put("status", accountVo.getStatus());
			sql_sb.append(" AND a.account_status=:status");
		}
		if (Validator.isNotNull(accountVo.getAccountType())) {
			paramMap.put("accountType", accountVo.getAccountType());
			sql_sb.append(" AND a.account_type=:accountType");
		}

		if (Validator.isNotNull(accountVo.getStationId())) {
			paramMap.put("stationId", accountVo.getStationId());
			sql_sb.append(" AND a.station_id=:stationId");
		}

		if (Validator.isNotNull(accountVo.getAgentId())) {
			paramMap.put("agentId", accountVo.getAgentId());
			sql_sb.append(" AND a.agent_id=:agentId");
		}
		if (StringUtil.isNotEmpty(accountVo.getAgentName())) {
			paramMap.put("agentName", accountVo.getAgentName());
			sql_sb.append(" AND a.agent_name=:agentName");
		}
		sql_sb.append(" ORDER BY a.create_datetime DESC");
		return super.page2CamelMap(sql_sb.toString(), paramMap);
	}
}
