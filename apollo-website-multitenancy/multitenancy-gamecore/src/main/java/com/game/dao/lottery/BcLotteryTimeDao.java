package com.game.dao.lottery;

import java.util.Date;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.game.model.lottery.BcLotteryTime;

@Repository
public class BcLotteryTimeDao extends JdbcUtilImpl<BcLotteryTime> {
		/**
		 * @param qihao
		 * @return
		 */
		public Page page(Integer qihao,String lotType){
			StringBuilder sb = new StringBuilder("select * from bc_lottery_time where 1=1");
			if(!StringUtils.isEmpty(qihao)){
				sb.append(" and action_no = :qihao");
			}
			if(!StringUtils.isEmpty(lotType)){
				if("-1".equals(lotType)){
					
				}else{
					sb.append(" and lot_code = :lotType");
				}
				
			}
			sb.append(" order by action_no ASC");
			return super.page2CamelMap(sb.toString(), MixUtil.newHashMap("qihao",qihao,"lotType",lotType));
		}
		
		public void updateTime(Date timeVal, Integer openId){
			StringBuilder sb = new StringBuilder("update bc_lottery_time set action_time=:timeVal where id= :openId");
			super.update(sb.toString(), MixUtil.newHashMap("timeVal",timeVal,"openId",openId));
		}
}
