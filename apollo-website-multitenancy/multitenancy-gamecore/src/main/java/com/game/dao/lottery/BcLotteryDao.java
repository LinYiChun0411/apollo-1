package com.game.dao.lottery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.JdbcUtilImpl;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.MixUtil;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.game.model.lottery.BcLottery;

@Repository
public class BcLotteryDao extends JdbcUtilImpl<BcLottery> {

	/**
	 * 查询彩种列表
	 * 
	 * @return
	 */
	public Page page(String name, Integer stationId, Integer state) {
		StringBuilder sb = new StringBuilder("select * from bc_lottery where 1=1");
		if (!StringUtils.isEmpty(state)) {
			sb.append(" and status = :state");
		}

		if (!StringUtils.isEmpty(name)) {
			sb.append(" and name = :name");
		}

		if (StringUtils.isEmpty(stationId)) {
			sb.append(" and station_id = 0");
		} else if (stationId == -1) {
			sb.append(" and station_id > 0");
		} else {
			sb.append(" and station_id = :stationId");
		}
		sb.append(" order by sort_no desc");
		return super.paged2Obj(sb.toString(), MixUtil.newHashMap("name", name, "stationId", stationId, "state", state));
	}

	public BcLottery getOne(Integer stationId, String code) {
		StringBuilder sb = new StringBuilder("select * from bc_lottery where 1=1");
		if (!StringUtils.isEmpty(stationId)) {
			sb.append(" and station_id = :stationId");
		}
		if (!StringUtils.isEmpty(code)) {
			sb.append(" and code = :code");
		}
		return super.query21Model(sb.toString(), MixUtil.newHashMap("stationId", stationId, "code", code));

	}

	/**
	 * 站点彩种用 彩种打开状态的才显示
	 * 
	 * @param name
	 * @param stationId
	 * @param state
	 * @return
	 */
	public Page page2(String name, Integer stationId) {
		StringBuilder sb = new StringBuilder("select * from bc_lottery where 1=1");

		if (!StringUtils.isEmpty(name)) {
			sb.append(" and name = :name");
		}

		if (StringUtils.isEmpty(stationId)) {
			sb.append(" and station_id = 0");
		} else if (stationId == -1) {
			sb.append(" and station_id > 0");
		} else {
			sb.append(" and station_id = :stationId");
		}
		sb.append(" and code in (select code from bc_lottery where status = 2 and station_id = 0)");
		sb.append(" order by sort_no desc");
		return super.paged2Obj(sb.toString(), MixUtil.newHashMap("name", name, "stationId", stationId));
	}

	public List<BcLottery> getCzGroup() {
//		StringBuilder sb = new StringBuilder("select code from bc_lottery where 1=1");
		StringBuilder sb = new StringBuilder("SELECT name,code from bc_lottery group by code,name");
		return super.query2Model(sb.toString());
	}

	/**
	 * 根据主键ID更改状态
	 * 
	 * @param bid
	 * @param status
	 */
	public void updateState(Integer bid, Integer status) {
		if (!StringUtils.isEmpty(bid) && !StringUtils.isEmpty(status)) {
			StringBuilder sb = new StringBuilder("UPDATE bc_lottery set status = :status where id= :bid");
			Map<Object, Object> paramMap = new HashMap<Object, Object>();
			paramMap.put("status", status);
			paramMap.put("bid", bid);
			super.update(sb.toString(), paramMap);
		}
	}

	public void sava(BcLottery bc) {
		super.save(bc);
	}

	public int update(BcLottery bc) {
		return super.update(bc);
	}

	public void del(Integer bid) {
		if (!StringUtils.isEmpty(bid)) {
			super.delete(bid);
		}

	}

	public List<BcLottery> find(Long stationId, Integer status, Integer type, Integer viewGroup) {
		StringBuilder sb = new StringBuilder("select * from bc_lottery where 1=1");
		Map<Object, Object> paramMap = new HashMap<Object, Object>();
		if (stationId != null) {
			sb.append(" and station_id=:stationId");
			paramMap.put("stationId", stationId);
		}
		if (status != null) {
			sb.append(" and status=:status");
			paramMap.put("status", status);
		}
		if (type != null) {
			sb.append(" and type=:type");
			paramMap.put("type", type);
		}
		if (viewGroup != null) {
			sb.append(" and view_group=:viewGroup");
			paramMap.put("viewGroup", viewGroup);
		}
		sb.append(" order by sort_no desc");
		return query2Model(sb.toString(), paramMap);
	}
}
