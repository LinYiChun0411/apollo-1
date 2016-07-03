package com.game.dao;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;

import com.game.model.SysThirdPlatform;
import com.game.model.ThirdStationGroup;
import com.game.util.StationUtil;

@Repository
public class ThirdStationGroupDao extends JdbcUtilImpl<ThirdStationGroup> {

	public Page<Map> getAgentPage() {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append(
				"SELECT ts.id,st.id AS third_platform_id,st.name AS third_name,ts.name,ts.order_no,ts.remark,ts.status,ts.station_id");
		sql_sb.append(" FROM sys_third_platform st LEFT JOIN third_station_group ts ON st.id = ts.third_platform_id");
		sql_sb.append(" WHERE st.flag_active >= 1 AND st.status = ");
		sql_sb.append(SysThirdPlatform.STATUS_ENABLE);
		sql_sb.append(" AND (ts.station_id is NULL OR ts.station_id = :stationId)");
		sql_sb.append(" ORDER BY ts.order_no");

		return super.page2CamelMap(sql_sb.toString(), MixUtil.newHashMap("stationId", StationUtil.getStationId()));
	}

	public List<Map> getThirdList() {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT * ");
		sql_sb.append(" FROM third_station_group");
		sql_sb.append(" WHERE status =");
		sql_sb.append(SysThirdPlatform.STATUS_ENABLE);
		sql_sb.append(" AND station_id = :stationId");
		sql_sb.append(" ORDER BY order_no");

		return super.selectCamelListMap(sql_sb.toString(), MixUtil.newHashMap("stationId", StationUtil.getStationId()));
	}
}
