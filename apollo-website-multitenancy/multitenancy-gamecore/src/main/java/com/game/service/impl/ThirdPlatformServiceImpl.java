package com.game.service.impl;

import java.util.List;
import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.ThirdPlatformDao;
import com.game.dao.ThirdStationGroupDao;
import com.game.model.SysThirdPlatform;
import com.game.model.ThirdStationGroup;
import com.game.model.vo.ThirdPlatVo;
import com.game.service.ThirdPlatformService;
import com.game.util.StationUtil;

@Repository
public class ThirdPlatformServiceImpl implements ThirdPlatformService {

	@Autowired
	private ThirdPlatformDao thirdPlatDao;

	@Autowired
	private ThirdStationGroupDao thirdStationGroupDao;

	@Override
	public Page<Map> getPage(ThirdPlatVo tpv) {
		Page<Map> page = thirdPlatDao.getPage(tpv);
		return page;
	}

	@Override
	public Page<Map> getAgentPage() {
		Page<Map> page = thirdStationGroupDao.getAgentPage();
		return page;
	}

	@Override
	public void saveThirdPlat(SysThirdPlatform SysThirdPlat) {
		Long thirdplatId = SysThirdPlat.getId();

		SysThirdPlatform saveThirdPlat = null;
		if (thirdplatId == null) {

			saveThirdPlat = SysThirdPlat;
		} else {
			saveThirdPlat = thirdPlatDao.get(thirdplatId);
			saveThirdPlat.setName(SysThirdPlat.getName());
			saveThirdPlat.setStatus(SysThirdPlat.getStatus());
			saveThirdPlat.setRemark(SysThirdPlat.getRemark());
		}
		thirdPlatDao.save(saveThirdPlat);
	}

	@Override
	public void updStatus(Long stationId, Long status) {
		SysThirdPlatform thirdPlat = thirdPlatDao.get(stationId);
		if (thirdPlat.getStatus() != status) {
			thirdPlat.setStatus(status);
			thirdPlatDao.save(thirdPlat);
		}
	}

	@Override
	public void saveThirdGroup(ThirdStationGroup tsg) {
		Long id = tsg.getId();
		tsg.setStationId(StationUtil.getStationId());
		ThirdStationGroup saveTsg = null;

		if ((id == null || id == 0l) && thirdStationGroupDao.isNotUnique(tsg, "stationId,thirdPlatformId")) {
			throw new GenericException("本平台已经启用:\"" + tsg.getThirdName() + "\"");
		}

		if (id != null && id > 0l) {
			saveTsg = thirdStationGroupDao.get(id);
			saveTsg.setOrderNo(tsg.getOrderNo());
			saveTsg.setRemark(tsg.getRemark());
			saveTsg.setName(tsg.getName());
			saveTsg.setStatus(tsg.getStatus());
		} else {
			saveTsg = tsg;
			saveTsg.setId(null);
			saveTsg.setStatus(ThirdStationGroup.STATUS_ENABLE);
		}
		if (saveTsg.getStatus() == null || saveTsg.getStatus() == 0l) {
			saveTsg.setStatus(ThirdStationGroup.STATUS_ENABLE);
		}

		thirdStationGroupDao.save(saveTsg);
	}

	@Override
	public List<Map> getThirdList() {
		return thirdStationGroupDao.getThirdList();

	}
}