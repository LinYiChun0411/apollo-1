package com.game.service;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.AgentBaseConfig;
import com.game.model.AgentBaseConfigValue;
import com.game.model.vo.AgentConfigVo;

public interface AgentBaseConfigService {

	public Page<Map> getPageConfig(AgentConfigVo acvo);

	public List<Map> getConfigAll();

	public List<Map> getConfigs();

	public void saveAgentConfVals(Long stationId, List<AgentBaseConfigValue> vals);

	public List<Map> getConfigsByStationId(Long stationId);

	public void saveConfig(AgentBaseConfig abc);

	public void saveConfigValue(AgentBaseConfigValue abcv);

	public void updStatus(Long confId, Long status);

	public void delete(Long acId);
}
