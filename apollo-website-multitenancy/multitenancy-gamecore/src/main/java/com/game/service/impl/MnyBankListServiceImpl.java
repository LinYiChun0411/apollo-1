package com.game.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.MnyBankListDao;
import com.game.service.MnyBankListService;

@Repository
public class MnyBankListServiceImpl implements MnyBankListService {

	@Autowired
	MnyBankListDao mnyBankListDao;

	@Override
	public List<Map> getBankList() {
		return mnyBankListDao.getBankList();
	}

}
