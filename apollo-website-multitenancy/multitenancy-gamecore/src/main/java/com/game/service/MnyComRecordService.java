package com.game.service;

import org.jay.frame.jdbc.Page;

import com.game.model.vo.MnyMoneyVo;

public interface MnyComRecordService {
	public Page getPage();

	/**
	 * 人工入款
	 * @param moneyVo
	 */
	public void deposit(MnyMoneyVo moneyVo);

}
