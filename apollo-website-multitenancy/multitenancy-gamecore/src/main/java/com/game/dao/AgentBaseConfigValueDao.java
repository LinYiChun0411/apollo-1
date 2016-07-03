package com.game.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AgentBaseConfigValue;

@Repository
public class AgentBaseConfigValueDao extends JdbcUtilImpl<AgentBaseConfigValue> {

	public List<Map> getConfs(Long stationId) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT *");
		sql_sb.append(" FROM agent_base_config_value");
		sql_sb.append(" WHERE station_id = :stationId");
		sql_sb.append(" ORDER BY id");
		Map paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", stationId);

		return super.selectCamelListMap(sql_sb.toString(), paramMap);
	}

	public List<Map> getConfsBak(Long stationId) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append(
				"SELECT b.id as config_id,b.name,b.key,b.remark,b.type,b.title,b.expand,v.id,COALESCE(v.value, b.init_value) as value,b.source");
		sql_sb.append(" FROM agent_base_config b LEFT JOIN agent_base_config_value v ON b.id=v.config_id");
		sql_sb.append(" WHERE b.status = ");
		sql_sb.append(AgentBaseConfigValue.AGENT_BASE_CONFIG_ENABLE);
		sql_sb.append(" AND v.station_id = :stationId");
		sql_sb.append(" ORDER BY b.id");
		Map paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", stationId);

		return super.selectCamelListMap(sql_sb.toString(), paramMap);
	}

	public void deleteAll(long stationId) {
		String sql = " delete from agent_base_config_value where station_id = :stationId ";
		super.update(sql, MixUtil.newHashMap("stationId", stationId));
	}
}
