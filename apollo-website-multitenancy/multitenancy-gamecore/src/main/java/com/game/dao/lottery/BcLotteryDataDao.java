package com.game.dao.lottery;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.game.model.lottery.BcLotteryData;

@Repository
public class BcLotteryDataDao  extends JdbcUtilImpl<BcLotteryData> {
	public Page page(String name,Integer status){
		StringBuilder sb = new StringBuilder("select * from bc_lottery_data where 1=1");
		if(!StringUtils.isEmpty(name)){
			sb.append(" and name like :name");
		}
		if(!StringUtils.isEmpty(status)){
			sb.append(" and status = :status");
		}
		sb.append(" order by qi_hao desc");
		return super.page2CamelMap(sb.toString(), MixUtil.newHashMap("name",name+ "%","status",status));
	}
}
