package com.game.dao;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;
import org.springframework.stereotype.Repository;

import com.game.model.SysRegisterConfig;
import com.game.model.vo.RegisterConfigVo;

@Repository
public class SysRegisterConfigDao extends JdbcUtilImpl<SysRegisterConfig> {

	public Page<Map> getPage(RegisterConfigVo rcvo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT r.*");
		sql_sb.append(" FROM sys_register_config r");
		sql_sb.append(" WHERE r.flag_active >= 1");
		Map paramMap = MixUtil.newHashMap();
		Long platform = rcvo.getPlatform();
		String name = rcvo.getName();
		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name + "%");
			sql_sb.append(" AND r.name LIKE :name");
		}

		if (Validator.isNotNull(platform)) {
			paramMap.put("platform", platform);
			sql_sb.append(" AND r.platform = :platform");
		}

		sql_sb.append(" ORDER BY r.id");

		return super.page2CamelMap(sql_sb.toString(), paramMap);
	}

	public List<Map> getStationRegConf(RegisterConfigVo rcvo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("select r.*,g.show_val,g.validate_val,g.required_val,g.status_validate_val");
		sql_sb.append(" FROM sys_register_config r LEFT JOIN sys_register_config_group g ON r.id = g.conf_id");
		sql_sb.append(" WHERE r.flag_active >= 1");
		Map paramMap = MixUtil.newHashMap();
		Long platform = rcvo.getPlatform();
		String name = rcvo.getName();
		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name + "%");
			sql_sb.append(" AND r.name LIKE :name");
		}

		if (Validator.isNotNull(platform)) {
			paramMap.put("platform", platform);
			sql_sb.append(" AND r.platform = :platform");
		}

		sql_sb.append(" ORDER BY r.id");

		return super.selectCamelListMap(sql_sb.toString(), paramMap);
	}
}
