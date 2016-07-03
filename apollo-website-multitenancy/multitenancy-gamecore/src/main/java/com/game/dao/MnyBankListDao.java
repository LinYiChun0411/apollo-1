package com.game.dao;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.springframework.stereotype.Repository;

import com.game.model.MnyBank;

@Repository
public class MnyBankListDao extends JdbcUtilImpl<MnyBank> {

	public List<Map> getBankList() {
		StringBuilder sql_sb = new StringBuilder("");
		sql_sb.append("SELECT *");
		sql_sb.append(" FROM mny_bank_list");

		return super.selectCamelListMap(sql_sb.toString(), null);
	}
}
