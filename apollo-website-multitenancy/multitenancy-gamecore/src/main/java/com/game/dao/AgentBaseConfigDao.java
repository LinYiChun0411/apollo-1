package com.game.dao;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.game.model.AgentBaseConfig;
import com.game.model.vo.AgentConfigVo;

@Repository
public class AgentBaseConfigDao extends JdbcUtilImpl<AgentBaseConfig> {

	public Page<Map> getPageConfig(AgentConfigVo acvo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT *");
		sql_sb.append(" FROM agent_base_config");
		sql_sb.append(" WHERE 1=1");

		Map paramMap = MixUtil.newHashMap();
		String name = acvo.getName();
		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name + "%");
			sql_sb.append(" AND name LIKE :name");
		}

		return super.page2CamelMap(sql_sb.toString(), paramMap);
	}

	public List<Map> getConfigAll(AgentConfigVo acvo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT *");
		sql_sb.append(" FROM agent_base_config");
		sql_sb.append(" WHERE status=:status");

		Map paramMap = MixUtil.newHashMap("status", AgentBaseConfig.AGENT_BASE_CONFIG_ENABLE);
		String name = acvo.getName();
		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name + "%");
			sql_sb.append(" AND name LIKE :name");
		}

		return super.selectCamelListMap(sql_sb.toString(), paramMap);
	}

}
