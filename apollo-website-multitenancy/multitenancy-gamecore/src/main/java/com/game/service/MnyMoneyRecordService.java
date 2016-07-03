package com.game.service;

import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.vo.MnyMoneyRecordVo;

public interface MnyMoneyRecordService {

	public Page<Map> getMoneyRecord(MnyMoneyRecordVo moneyRecordVo);

	public void moneyOperate (MnyMoneyRecordVo moneyRecordVo);

}
