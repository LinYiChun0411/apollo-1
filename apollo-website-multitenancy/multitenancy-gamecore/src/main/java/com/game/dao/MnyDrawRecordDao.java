package com.game.dao;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.springframework.stereotype.Repository;

import com.game.model.MnyDrawRecord;

@Repository
public class MnyDrawRecordDao extends JdbcUtilImpl<MnyDrawRecord> {

	public Page<MnyDrawRecord> getPage() {
		StringBuilder sql_sb = new StringBuilder("");
		sql_sb.append("SELECT *");
		sql_sb.append(" FROM mny_draw_record");

		return super.paged2Obj(sql_sb.toString());
	}
}
