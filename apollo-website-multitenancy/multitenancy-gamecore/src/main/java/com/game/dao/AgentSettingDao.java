package com.game.dao;

import java.util.HashMap;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.springframework.stereotype.Repository;

import com.game.cache.CacheType;
import com.game.cache.CacheUtil;
import com.game.model.AgentSetting;

@Repository
public class AgentSettingDao extends JdbcUtilImpl<AgentSetting> {
	/**
	 * 获取站点配置信息
	 * 
	 * @param key
	 * @param stationId
	 * @return
	 */
	public AgentSetting getSettingValueByKey(String key, Long stationId) {
		if (stationId == null) {
			stationId = 0L;
		}
		AgentSetting s = CacheUtil.getCache(CacheType.AGENT_SETTING, stationId + "_" + key, AgentSetting.class);
		if (s != null)
			return s;
		StringBuilder sb = new StringBuilder("select * from agent_setting where key=:key");
		Map<String, Object> map = new HashMap<>();
		map.put("key", key);
		if (stationId != null && stationId != 0L) {
			sb.append(" and station_id=:stationId");
			map.put("stationId", stationId);
		}
		s = query21Model(sb.toString(), map);
		if (s != null) {
			CacheUtil.addCache(CacheType.AGENT_SETTING, stationId + "_" + key, s);
		}
		return s;
	}

}
