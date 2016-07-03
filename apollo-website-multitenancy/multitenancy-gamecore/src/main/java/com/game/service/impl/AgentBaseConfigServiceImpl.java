package com.game.service.impl;

import java.util.List;
import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.AgentBaseConfigDao;
import com.game.dao.AgentBaseConfigValueDao;
import com.game.model.AgentBaseConfig;
import com.game.model.AgentBaseConfigValue;
import com.game.model.vo.AgentConfigVo;
import com.game.service.AgentBaseConfigService;
import com.game.util.StationUtil;

@Repository
public class AgentBaseConfigServiceImpl implements AgentBaseConfigService {

	@Autowired
	AgentBaseConfigValueDao configValueDao;

	@Autowired
	AgentBaseConfigDao configDao;

	@Override
	public List<Map> getConfigAll() {
		AgentConfigVo acvo = new AgentConfigVo();
		return configDao.getConfigAll(acvo);
	}

	@Override
	public List<Map> getConfigs() {
		Long stationId = StationUtil.getStationId();
		return configValueDao.getConfsBak(stationId);
	}

	@Override
	public void saveConfigValue(AgentBaseConfigValue abcv) {
		Long stationId = StationUtil.getStationId();
		Long id = abcv.getId();
		AgentBaseConfigValue saveAbcv = null;

		if (Validator.isNull(id)) {
			if (configValueDao.isNotUnique(abcv, "stationId,configId")) {
				throw new GenericException("该配置已存在！");
			}
			saveAbcv = abcv;
		} else {
			saveAbcv = configValueDao.get(id);
			saveAbcv.setValue(abcv.getValue());
		}
		saveAbcv.setStationId(stationId);
		configValueDao.save(saveAbcv);

	}

	@Override
	public Page<Map> getPageConfig(AgentConfigVo acvo) {
		return configDao.getPageConfig(acvo);
	}

	@Override
	public void updStatus(Long confId, Long status) {
		AgentBaseConfig conf = configDao.get(confId);
		if (conf.getStatus() != status) {
			conf.setStatus(status);
			configDao.save(conf);
		}
	}

	@Override
	public void delete(Long acId) {
		configDao.delete(acId);
	}

	@Override
	public void saveConfig(AgentBaseConfig abc) {
		Long id = abc.getId();
		AgentBaseConfig saveAbc = null;

		if (Validator.isNull(id)) {
			if (configDao.isNotUnique(abc, "name")) {
				throw new GenericException("该配置已存在！");
			}
			saveAbc = abc;
		} else {
			saveAbc = configDao.get(id);
			saveAbc.setName(abc.getName());
			saveAbc.setExpand(abc.getExpand());
			saveAbc.setInitValue(abc.getInitValue());
			saveAbc.setRemark(abc.getRemark());
			saveAbc.setSource(abc.getSource());
			saveAbc.setTitle(abc.getTitle());
			saveAbc.setType(abc.getType());
			saveAbc.setKey(abc.getKey());
			saveAbc.setStatus(abc.getStatus());
		}
		configDao.save(saveAbc);

	}

	@Override
	public List<Map> getConfigsByStationId(Long stationId) {
		return configValueDao.getConfs(stationId);
	}

	@Override
	public void saveAgentConfVals(Long stationId, List<AgentBaseConfigValue> vals) {
		configValueDao.deleteAll(stationId);
		configValueDao.batchInsert(vals);
	}

}
