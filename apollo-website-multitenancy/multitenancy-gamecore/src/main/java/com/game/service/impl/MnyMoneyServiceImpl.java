package com.game.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.MnyMoneyDao;
import com.game.dao.MnyMoneyRecordDao;
import com.game.dao.SysAccountDao;
import com.game.model.MnyMoney;
import com.game.model.MnyMoneyRecord;
import com.game.model.SysAccount;
import com.game.model.vo.MnyMoneyVo;
import com.game.service.MnyMoneyService;
import com.game.util.StationUtil;

@Repository
public class MnyMoneyServiceImpl implements MnyMoneyService {

	@Autowired
	SysAccountDao sysAccountDao;

	@Autowired
	MnyMoneyDao moneyDao;

	@Autowired
	MnyMoneyRecordDao moneyRecordDao;

	@Override
	public BigDecimal updateMoney(MnyMoneyVo moneyVo) {
		Long accountId = moneyVo.getAccountId();
		Long stationId = StationUtil.getStationId();
		
		SysAccount checkAccount = sysAccountDao.get(accountId);
		if(checkAccount == null){
			throw new GenericException("用户不存在！");
		}
		
		if (!StringUtil.equals(checkAccount.getStationId(), stationId)) {
			throw new GenericException("用户不存在！");
		}

		BigDecimal money = moneyVo.getMoney();
		if (money == null || money.compareTo(new BigDecimal(0)) < 1) {
			throw new GenericException("金额格式有误！");
		}

		if (!moneyVo.getMoneyRecordType().isAdd()) {
			money = money.multiply(new BigDecimal(-1));
		}

		BigDecimal[] results = null;
		try {
			results = moneyDao.updateMoney(accountId, money);
		} catch (SQLException sqle) {
			throw new GenericException("操作失败！", sqle);
		}

		if (results == null || results.length != 2) {
			throw new GenericException("未知异常");
		}
		BigDecimal beforeMoney = results[1];
		if (results[0] == null && results[0].intValue() == 0) {
			throw new GenericException("余额不足");
		}

		if (beforeMoney == null) {
			throw new GenericException("金额记录不存在");
		}

		BigDecimal afterMoney = beforeMoney.add(money);
		MnyMoneyRecord record = new MnyMoneyRecord();
		record.setAccountId(accountId);
		record.setMoney(money);
		record.setType(moneyVo.getMoneyRecordType().getType());
		record.setBeforeMoney(beforeMoney);
		record.setAfterMoney(afterMoney);
		record.setRemark(moneyVo.getRemark());
		moneyRecordDao.save(record);
		return afterMoney;
	}

	@Override
	public BigDecimal checkMoney(Long accountId, BigDecimal money) {
		if (accountId == 0) {
			throw new GenericException("该用户不存在！");
		}

		MnyMoney mnyMoney = moneyDao.get(accountId);
		BigDecimal beforeMoney = new BigDecimal(0);
		if (mnyMoney != null) {
			beforeMoney = mnyMoney.getMoney();
		}
		BigDecimal afterMoney = beforeMoney.add(money);
		BigDecimal zero = new BigDecimal(0);

		if (money.compareTo(zero) < 0 && afterMoney.compareTo(zero) < 0) {
			throw new GenericException("余额不足！");
		}

		return mnyMoney.getMoney();
	}

	@Override
	public Map getMoneyByAccount(String account) {
		return moneyDao.getMoneyByAccount(account);
	}
}