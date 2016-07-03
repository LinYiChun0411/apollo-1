package com.game.dao;

import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.StringUtil;
import org.springframework.stereotype.Repository;

import com.game.model.SysThirdPlatform;
import com.game.model.vo.ThirdPlatVo;

@Repository
public class ThirdPlatformDao extends JdbcUtilImpl<SysThirdPlatform> {

	public Page<Map> getPage(ThirdPlatVo tpv) {
		StringBuilder sql_sb = new StringBuilder();
		sql_sb.append("SELECT t.*,m.account AS modify_user,c.account AS create_user");
		sql_sb.append(" FROM sys_third_platform t LEFT JOIN admin_user m ON t.modify_user_id = m.id");
		sql_sb.append(" LEFT JOIN admin_user c ON t.create_user_id = c.id");
		sql_sb.append(" WHERE t.FLAG_ACTIVE >=  1");
		Map paramMap = MixUtil.newHashMap();
		String name = tpv.getName();
		if (StringUtil.isNotEmpty(name)) {
			paramMap.put("name", name + "%");
			sql_sb.append(" AND t.name LIKE :name");
		}
		sql_sb.append(" ORDER BY t.id");

		return super.page2CamelMap(sql_sb.toString(),paramMap);
	}
}
