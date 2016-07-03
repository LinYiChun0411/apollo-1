package com.game.dao.lottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.jay.frame.util.Validator;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.game.model.lottery.BcLotteryPlayGroup;

@Repository
public class BcLotteryPlayGroupDao extends JdbcUtilImpl<BcLotteryPlayGroup> {

	public Page page(String name, Integer type, Long stationId) {
		StringBuilder sb = new StringBuilder("select * from bc_lottery_play_group where 1=1");
		Map<String, Object> map = new HashMap<>();
		if (!StringUtils.isEmpty(type)) {
			sb.append(" and lot_type =:type");
			map.put("type", type);
		}
		if (stationId != null) {
			sb.append(" and station_id =:stationId");
			map.put("stationId", stationId);
		}
		sb.append(" order by sort_no desc");
		return super.paged2Obj(sb.toString(), map);
	}

	public void closeOrOpen(Integer status, Integer id) {
		StringBuilder sb = new StringBuilder("update bc_lottery_play_group set status= :status");
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (Validator.isNotNull(id)) {
			sb.append(" where id=:id");
			map.put("id", id);
		}
		map.put("status", status);
		super.update(sb.toString(), map);
	}

	public List<BcLotteryPlayGroup> findByLotTypeAndStationId(Integer type, Long stationId) {
		StringBuilder sb = new StringBuilder("select * from bc_lottery_play_group where 1=1");
		Map<String, Object> map = new HashMap<>();
		if (!StringUtils.isEmpty(type)) {
			sb.append(" and lot_type=:lotType");
			map.put("lotType", type);
		}
		if (stationId != null) {
			sb.append(" and station_id =:stationId");
			map.put("stationId", stationId);
		}
		sb.append(" order by sort_no desc");
		return this.query2Model(sb.toString(), map);
	}
}
