package com.game.dao;

import java.util.HashMap;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;
import org.springframework.stereotype.Repository;

import com.game.model.MnyMoneyRecord;
import com.game.model.SysAccount;
import com.game.model.vo.MnyMoneyRecordVo;

@Repository
public class MnyMoneyRecordDao extends JdbcUtilImpl<MnyMoneyRecord> {

	public Page<Map> getMemMnyRdPage(MnyMoneyRecordVo moneyVo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT r.*,a.account");
		sql_sb.append(" FROM mny_money_record r INNER JOIN sys_account a ON r.account_id = a.id");
		sql_sb.append(" WHERE a.account_type =");
		sql_sb.append(SysAccount.ACCOUNT_PLATFORM_MEMBER);
		sql_sb.append(" AND a.flag_active >=1 ");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(moneyVo.getAccount())) {
			paramMap.put("account", moneyVo.getAccount());
			sql_sb.append(" AND a.account=:account");
		}

		if (Validator.isNotNull(moneyVo.getStationId())) {
			paramMap.put("stationId", moneyVo.getStationId());
			sql_sb.append(" AND a.station_id=:stationId");
		}
		
		if (Validator.isNotNull(moneyVo.getType())) {
			paramMap.put("type", moneyVo.getType());
			sql_sb.append(" AND r.type=:type");
		}

		if (Validator.isNotNull(moneyVo.getAccountId())) {
			paramMap.put("accountId", moneyVo.getAccountId());
			sql_sb.append(" AND a.id=:accountId");
		}
		
		if (StringUtil.isNotEmpty(moneyVo.getBegin())) {
			paramMap.put("begin", moneyVo.getBegin());
			sql_sb.append(" AND r.create_datetime >=:begin");
		}
		
		if (StringUtil.isNotEmpty(moneyVo.getEnd())) {
			paramMap.put("end", moneyVo.getEnd());
			sql_sb.append(" AND r.create_datetime <=:end");
		}
		
		sql_sb.append(" ORDER BY r.create_datetime DESC");

		return super.page2CamelMap(sql_sb.toString(), paramMap);
	}
}
