package com.game.service;

import java.math.BigDecimal;
import java.util.Map;

import com.game.model.vo.MnyMoneyVo;

public interface MnyMoneyService {

	/**
	 * 金额变动
	 */
	public BigDecimal updateMoney(MnyMoneyVo moneyVo);

	public BigDecimal checkMoney(Long memberId, BigDecimal money);

	public Map getMoneyByAccount(String account);

}
