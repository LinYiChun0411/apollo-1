package com.game.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.jay.frame.jdbc.annotation.Column;
import org.jay.frame.util.StringUtil;
import org.jay.frame.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.SysRegisterConfigDao;
import com.game.dao.SysRegisterConfigGroupDao;
import com.game.model.SysRegisterConfig;
import com.game.model.SysRegisterConfigGroup;
import com.game.model.vo.RegisterConfigVo;
import com.game.service.SysRegisterConfigService;
import com.game.util.StationUtil;

@Repository
public class SysRegisterConfigServiceImpl implements SysRegisterConfigService {

	@Autowired
	private SysRegisterConfigDao srcDao;
	@Autowired
	private SysRegisterConfigGroupDao srcGroupDao;

	@Override
	public void updStatus(Long srcId, Long status) {
		SysRegisterConfig src = srcDao.get(srcId);
		if (src.getStatus() != status) {
			src.setStatus(status);
			srcDao.save(src);
		}
	}

	@Override
	public Page<Map> getPageConfig(RegisterConfigVo rcvo) {
		return srcDao.getPage(rcvo);
	}

	@Override
	public void saveConfig(SysRegisterConfig config) {
		SysRegisterConfig saveConfig = srcDao.get(config.getId());
		if (saveConfig == null) {
			saveConfig = config;
		} else {
			long type = saveConfig.getType();
			if (type == SysRegisterConfig.TYPE_MEMBER) {
				saveConfig.setShow(config.getShow());
				saveConfig.setValidate(config.getValidate());
				saveConfig.setRequired(config.getRequired());
				saveConfig.setRegex(config.getRegex());
			} else if (type == SysRegisterConfig.TYPE_AGENT) {
				saveConfig.setStatusValidate(saveConfig.getStatusValidate());
			} else {
				throw new GenericException("非法操作");
			}
			saveConfig.setName(config.getName());
			saveConfig.setType(config.getType());
			saveConfig.setStatus(config.getStatus());
		}
		srcDao.save(saveConfig);
	}

	@Override
	public void delConfig(Long srcId) {
		srcDao.delete(srcId);
	}

	@Override
	public List<Map> getStationRegConf(RegisterConfigVo rcvo) {
		rcvo.setStationId(StationUtil.getStationId());
		if (Validator.isNull(rcvo.getPlatform())) {
			rcvo.setPlatform(SysRegisterConfig.TYPE_MEMBER);
		}
		return srcDao.getStationRegConf(rcvo);
	}

	@Override
	public List<Map> getStationRegVals() {
		Long stationId = StationUtil.getStationId();
		return srcGroupDao.getRegConfValsByStationId(stationId);
	}

	@Override
	public void saveStationConfGroup(List<Map> datas) {
		if (datas == null || datas.size() == 0) {
			throw new GenericException("保存数据为空！");
		}
		Long stationId = StationUtil.getStationId();
		Long platfrom = StringUtil.toLong(datas.get(0).get("platform"));
		if (platfrom.longValue() == 0l) {
			throw new GenericException("数据异常！");
		}
		Long configId = -1l;
		Long showVal = 1l;
		Long validateVal = 1l;
		Long requiredVal = 1l;
		Long statusValidateVal = 1l;
		// String confName = "";
		List<SysRegisterConfigGroup> saveDatas = new ArrayList<SysRegisterConfigGroup>();
		SysRegisterConfigGroup entity = null;
		for (Map paramData : datas) {
			configId = StringUtil.toLong(paramData.get("id"));
			if (configId.longValue() == 0l) {
				throw new GenericException("数据异常！");
			}
//			if (StringUtil.isNotEmpty(paramData.get("name"))) {
//				confName = paramData.get("name").toString();
//			}

			showVal = getVal(paramData.get("showVal"));
			validateVal = getVal(paramData.get("validateVal"));
			requiredVal = getVal(paramData.get("requiredVal"));
			statusValidateVal = getVal(paramData.get("statusValidateVal"));
			entity = new SysRegisterConfigGroup();
			entity.setConfigId(configId);
			entity.setStationId(stationId);
			if (platfrom.longValue() == SysRegisterConfig.TYPE_MEMBER) {
				entity.setRequiredVal(requiredVal);
				entity.setShowVal(showVal);
				entity.setValidateVal(validateVal);
				// if (showVal.intValue() == 1 && (validateVal.intValue() == 2
				// || requiredVal.intValue() == 2)) {
				// throw new GenericException(confName + "的显示设置为“否”,不能必输或验证！！");
				// }

			} else if (platfrom.longValue() == SysRegisterConfig.TYPE_AGENT) {
				entity.setStatusValidateVal(statusValidateVal);
			}
			saveDatas.add(entity);
		}

		srcGroupDao.delStationConfByPlat(stationId, platfrom);
		srcGroupDao.batchInsert(saveDatas);
	}

	private Long getVal(Object obj) {
		Long res = 1l;
		if (obj == null) {
			return res;
		}
		res = StringUtil.toLong(obj);
		if (res.longValue() == 0l) {
			return 1l;
		}
		return res;
	}
}