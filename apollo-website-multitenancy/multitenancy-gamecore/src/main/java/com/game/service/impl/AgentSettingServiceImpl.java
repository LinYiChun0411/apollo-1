package com.game.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.constant.AgentSettingKey;
import com.game.constant.BusinessConstant;
import com.game.dao.AgentSettingDao;
import com.game.model.AgentSetting;
import com.game.service.AgentSettingService;

@Repository
public class AgentSettingServiceImpl implements AgentSettingService {
	@Autowired
	private AgentSettingDao agentSettingDao;

	@Override
	public String getSettingValueByKey(String key, Long stationId) {
		AgentSetting setting = agentSettingDao.getSettingValueByKey(key, stationId);
		if (setting != null)
			return setting.getValue();
		return null;
	}

	@Override
	public boolean isDuLiCaiPiao(Long stationId) {
		return StringUtils.equals(getSettingValueByKey(AgentSettingKey.onoff_du_li_cai_piao.name(), stationId), BusinessConstant.SWITCH_ON);
	}

	@Override
	public boolean dianZiYouYiHadOpened(Long stationId) {
		return StringUtils.equals(getSettingValueByKey(AgentSettingKey.onoff_dian_zi_you_yi.name(), stationId), BusinessConstant.SWITCH_ON);
	}

	@Override
	public boolean zhenRenYuLeHadOpened(Long stationId) {
		return StringUtils.equals(getSettingValueByKey(AgentSettingKey.onoff_zhen_ren_yu_le.name(), stationId), BusinessConstant.SWITCH_ON);
	}
}
