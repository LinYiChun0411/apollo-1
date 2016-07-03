package com.game.service.impl;

import java.util.Map;

import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.MnyMoneyRecordDao;
import com.game.model.vo.MnyMoneyRecordVo;
import com.game.service.MnyMoneyRecordService;
import com.game.util.StationUtil;

@Repository
public class MnyMoneyRecordServiceImpl implements MnyMoneyRecordService {

	@Autowired
	MnyMoneyRecordDao moneyRecordDao;

	@Override
	public Page<Map> getMoneyRecord(MnyMoneyRecordVo moneyRecordVo) {
		moneyRecordVo.setStationId(StationUtil.getStationId());
		return moneyRecordDao.getMemMnyRdPage(moneyRecordVo);
	}

	@Override
	public void moneyOperate(MnyMoneyRecordVo moneyRecordVo) {
		// TODO Auto-generated method stub
		
	}
	
}