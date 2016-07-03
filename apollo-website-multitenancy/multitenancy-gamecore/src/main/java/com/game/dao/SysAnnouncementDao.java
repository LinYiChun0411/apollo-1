package com.game.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.game.model.SysAnnouncement;
import com.game.model.vo.AnnouncementVo;

@Repository
public class SysAnnouncementDao extends JdbcUtilImpl<SysAnnouncement> {

	public Page<Map> getPage(AnnouncementVo ancvo) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT a.*,c.account AS creator,m.account AS modify_user");
		sql_sb.append(" FROM sys_announcement a LEFT JOIN admin_user c ON a.create_user_id = c.id");
		sql_sb.append(" LEFT JOIN admin_user m ON a.modify_user_id = m.id");
		sql_sb.append(" WHERE 1=1 ");

		Long type = ancvo.getType();
		Date begin = ancvo.getBegin();
		Date end = ancvo.getEnd();
		String name = ancvo.getName();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (type != null && type != 0l) {
			paramMap.put("type", type);
			sql_sb.append(" AND a.type=:type");
		}
		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name + "%");
			sql_sb.append(" AND a.name LIKE :name");
		}

		if (begin != null) {
			paramMap.put("begin", begin);
			sql_sb.append(" AND a.begin_datetime >=:begin");
		}

		if (end != null) {
			paramMap.put("end", end);
			sql_sb.append(" AND a.end_datetime <=:end");
		}

		return super.page2CamelMap(sql_sb.toString(), paramMap);
	}
}
