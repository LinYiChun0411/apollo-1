package com.game.service.impl;

import java.math.BigDecimal;

import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.MnyDrawRecordDao;
import com.game.model.MnyDrawRecord;
import com.game.model.vo.MnyMoneyVo;
import com.game.service.MnyDrawRecordService;
import com.game.service.MnyMoneyService;
import com.game.util.Snowflake;

@Repository
public class MnyDrawRecordServiceImpl implements MnyDrawRecordService {

	@Autowired
	MnyDrawRecordDao drawRecordDao;

	@Autowired
	MnyMoneyService moneyService;

	@Override
	public Page getPage() {
		return drawRecordDao.getPage();
	}

	@Override
	public void withdraw(MnyMoneyVo moneyVo) {
		MnyDrawRecord record = new MnyDrawRecord();
		record.setTrueMoney(moneyVo.getMoney());
		record.setFee(new BigDecimal(0));
		record.setType(moneyVo.getMoneyRecordType().getType());
		record.setMemberId(moneyVo.getAccountId());
		record.setDrawMoney(moneyVo.getMoney());
//		record.setOrderNo(Snowflake.getOrderNo()+"");
		drawRecordDao.save(record);
		moneyService.updateMoney(moneyVo);
	}
}