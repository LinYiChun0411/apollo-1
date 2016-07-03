package com.game.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.MnyMoney;
import com.game.util.StationUtil;

@Repository
public class MnyMoneyDao extends JdbcUtilImpl<MnyMoney> {
	public BigDecimal[] updateMoney(long accountId, BigDecimal optMoney) throws SQLException {
		String sql = "SELECT optmoney(" + accountId + "," + optMoney + ")";
		return (BigDecimal[]) (super.queryForArray(sql).getArray());
	}

	public Map getMoneyByAccount(String account) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT a.account,m.*");
		sql_sb.append(" FROM sys_account a INNER JOIN mny_money m ON a.id = m.account_id");
		sql_sb.append(" WHERE a.flag_active >=1 AND a.station_id = :stationId");
		sql_sb.append(" AND a.account = :account");
		return super.selectSingleCamelMap(sql_sb.toString(),
				MixUtil.newHashMap("stationId", StationUtil.getStationId(), "account", account));
	}

}
