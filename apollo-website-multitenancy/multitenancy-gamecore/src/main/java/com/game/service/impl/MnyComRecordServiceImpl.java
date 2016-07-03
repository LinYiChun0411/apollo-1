package com.game.service.impl;

import java.math.BigDecimal;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.MnyComRecordDao;
import com.game.dao.MnyMoneyDao;
import com.game.dao.SysAccountDao;
import com.game.model.MnyComRecord;
import com.game.model.MnyMoney;
import com.game.model.SysAccount;
import com.game.model.vo.MnyMoneyVo;
import com.game.service.MnyComRecordService;
import com.game.service.MnyMoneyService;

@Repository
public class MnyComRecordServiceImpl implements MnyComRecordService {

	@Autowired
	MnyComRecordDao comRecordDao;
	
	@Autowired
	SysAccountDao accountDao;

	@Autowired
	MnyMoneyService moneyService;
	
	@Autowired
	MnyMoneyDao moneyDao;

	@Override
	public Page getPage() {
		return comRecordDao.getPage();
	}

	@Override
	public void deposit(MnyMoneyVo moneyVo) {
		
		//判断数据合理性
		SysAccount member = accountDao.get(moneyVo.getAccountId());
		if(member == null){
			throw new GenericException("会员不存在！");
		}
		//判断代理余额是否足够
		MnyMoney agentMoney = moneyDao.get(member.getAgentId());
		if(agentMoney.getMoney().compareTo(moneyVo.getMoney()) == -1){
			throw new GenericException("代理余额不够！");
		}
		
		//保存入款记录
		MnyComRecord record = new MnyComRecord();
		record.setType(moneyVo.getMoneyRecordType().getType());
		record.setMemberId(moneyVo.getAccountId());
		record.setMoney(moneyVo.getMoney());
		comRecordDao.save(record);
		//增加会员金额
		moneyService.updateMoney(moneyVo);
		//扣除代理金额
		moneyVo.setAccountId(agentMoney.getAccountId());
		moneyService.updateMoney(moneyVo);
	}
}