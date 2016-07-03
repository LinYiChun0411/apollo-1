package com.game.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;
import org.springframework.stereotype.Repository;

import com.game.model.SysProposal;
import com.game.model.vo.ProposalVo;

@Repository
public class SysProposalDao extends JdbcUtilImpl<SysProposal> {

	public Page<Map> getPage(ProposalVo pvo) {

		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT p.*,s.name AS station_name,a.account AS creator");
		sql_sb.append(" FROM sys_proposal p LEFT JOIN sys_station s ON p.station_id = s.id");
		sql_sb.append(" LEFT JOIN sys_account a ON p.create_user_id = a.id");
		sql_sb.append(" WHERE 1=1");

		String name = pvo.getName();
		Long stationId = pvo.getStationId();
		Date begin = pvo.getBegin();
		Date end = pvo.getEnd();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name + "%");
			sql_sb.append(" AND p.name LIKE :name");
		}

		if (Validator.isNotNull(stationId)) {
			paramMap.put("stationId", stationId);
			sql_sb.append(" AND p.station_id=:stationId");
		}

		if (begin != null) {
			paramMap.put("begin", begin);
			sql_sb.append(" AND p.create_datetime >=:begin");
		}

		if (end != null) {
			paramMap.put("end", end);
			sql_sb.append(" AND p.create_datetime <=:end");
		}

		return super.page2CamelMap(sql_sb.toString(), paramMap);
	}
}
