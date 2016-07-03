package com.game.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AgentBaseConfigValue;
import com.game.model.SysRegisterConfigGroup;

@Repository
public class SysRegisterConfigGroupDao extends JdbcUtilImpl<SysRegisterConfigGroup> {

	public List<Map> getRegConfValsByStationId(Long stationId) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT *");
		sql_sb.append(" FROM sys_register_config_group");
		sql_sb.append(" WHERE station_id = :stationId ");
		Map paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", stationId);

		return super.selectCamelListMap(sql_sb.toString(), paramMap);
	}

	public void delStationConfByPlat(Long stationId, Long platform) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("DELETE");
		sql_sb.append(" FROM sys_register_config_group g USING sys_register_config r");
		sql_sb.append(" WHERE g.conf_id = r.id AND r.platform = :platform AND station_id = :stationId");
		Map paramMap = new HashMap<String, Object>();
		paramMap.put("platform", platform);
		paramMap.put("stationId", stationId);
		super.update(sql_sb.toString(), paramMap);
	}
}
