package com.game.dao;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.springframework.stereotype.Repository;

import com.game.model.MnyComRecord;

@Repository
public class MnyComRecordDao extends JdbcUtilImpl<MnyComRecord> {

	public Page<MnyComRecord> getPage() {
		StringBuilder sql_sb = new StringBuilder("");
		sql_sb.append("SELECT *");
		sql_sb.append(" FROM mny_com_record");

		return super.paged2Obj(sql_sb.toString());
	}
}
