package com.game.service;

import org.jay.frame.jdbc.Page;

import com.game.model.vo.MnyMoneyVo;

public interface MnyDrawRecordService {
	public Page getPage();
	
	/**
	 * 
	 * @param moneyVo
	 */
	public void withdraw(MnyMoneyVo moneyVo);
}
