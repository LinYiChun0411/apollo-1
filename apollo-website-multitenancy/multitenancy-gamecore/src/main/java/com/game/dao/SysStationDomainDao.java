package com.game.dao;

import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.game.model.SysStationDomain;
import com.game.model.vo.StationVo;

@Repository
public class SysStationDomainDao extends JdbcUtilImpl<SysStationDomain> {
	public Page<Map> getPage(StationVo svo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT d.*,s.name,s.floder");
		sql_sb.append(" FROM sys_station_domain d LEFT JOIN sys_station s ON d.station_id = s.id");
		sql_sb.append(" WHERE d.flag_active >=1");

		Map paramMap = MixUtil.newHashMap();
		String domain = svo.getDomain();
		String name = svo.getName();
		if (StringUtil.isNotEmpty(domain)) {
			paramMap.put("domain", domain+"%");
			sql_sb.append(" AND d.domain LIKE :domain");
		}

		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name);
			sql_sb.append(" AND s.name = :name");
		}
		sql_sb.append(" ORDER BY s.id");

		return super.page2CamelMap(sql_sb.toString(), paramMap);
	}
}
