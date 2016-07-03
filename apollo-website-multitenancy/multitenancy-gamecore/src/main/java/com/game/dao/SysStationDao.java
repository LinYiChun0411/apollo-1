package com.game.dao;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.game.model.SysStation;
import com.game.model.SysStationDomain;
import com.game.model.vo.StationVo;

@Repository
public class SysStationDao extends JdbcUtilImpl<SysStation> {
	/**
	 * 通过域名获取站点信息
	 * 
	 * @param domain
	 * @return
	 */

	public SysStation getStation(String domain) {

		String sql = "select b.* from  sys_station_domain a " + "LEFT JOIN sys_station b  "
				+ " ON(b.id = a.station_id) " + " WHERE b.status = " + SysStation.STATUS_ENABLE + " AND a.status = "
				+ SysStationDomain.STATUS_ENABLE + " AND a.domain = :domain ";

		return super.query21Model(sql, MixUtil.newHashMap("domain", domain));
	}

	public Page<Map> getPage(StationVo svo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT s.*,a.account");
		sql_sb.append(" FROM sys_station s LEFT JOIN sys_account a ON s.account_id = a.id");
		sql_sb.append(" WHERE a.flag_active >= 1");
		Map paramMap = MixUtil.newHashMap();
		String account = svo.getAccount();
		String name = svo.getName();
		if (StringUtil.isNotEmpty(account)) {
			paramMap.put("account", account);
			sql_sb.append(" AND a.account = :account");
		}

		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name);
			sql_sb.append(" AND s.name = :name");
		}

		sql_sb.append(" ORDER BY s.id");

		return super.page2CamelMap(sql_sb.toString(),paramMap);
	}

	public List<Map> getStationCombo() {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT id,'('||floder||')'||name AS name");
		sql_sb.append(" FROM sys_station ORDER BY id");

		return super.selectCamelListMap(sql_sb.toString());
	}
}
