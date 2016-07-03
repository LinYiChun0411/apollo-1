package com.game.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.SysAccountDao;
import com.game.dao.SysAccountInfoDao;
import com.game.dao.SysStationDao;
import com.game.model.SysAccount;
import com.game.model.SysAccountInfo;
import com.game.model.SysStation;
import com.game.model.vo.StationVo;
import com.game.service.SysStationService;

@Repository
public class SysStationServiceImpl implements SysStationService {

	@Autowired
	private SysStationDao sysStationDao;

	@Autowired
	private SysAccountDao sysAccountDao;

	@Autowired
	private SysAccountInfoDao sysAccountInfoDao;

	@Override
	public Page<Map> getPage(StationVo svo) {
		return sysStationDao.getPage(svo);
	}

	@Override
	public void saveStation(SysStation station) {
		Long stationId = station.getId();

		if (sysStationDao.isNotUnique(station, "floder")) {
			throw new GenericException("名别:\"" + station.getFloder() + "\"已经存在");
		}

		//stationId为空则为新增站点
		SysStation saveStation = null;
		if (stationId == null) {
			SysAccount account = new SysAccount();
			account.setAccount(station.getAccount());
			account.setAccountType(SysAccount.ACCOUNT_PLATFORM_AGENT);
			//是否是新租户
			boolean newUser = false;
			if (sysAccountDao.isNotUnique(account, "account,accountType")) {
				account = sysAccountDao.queryDdgd(account.getAccount(), SysAccount.ACCOUNT_PLATFORM_AGENT);
			} else {
				//租户不存在，创建新租户
				account.setAgentId(0L);
				account.setAgentName(account.getAccount());
				account.setAccountType(SysAccount.ACCOUNT_PLATFORM_AGENT);
				newUser = true;
			}
			account = sysAccountDao.save(account);
			if (newUser) {
				SysAccountInfo aif = new SysAccountInfo();
				aif.setAccountId(account.getId());
				sysAccountInfoDao.save(aif);
			}

			station.setCreateDatetime(new Date());
			saveStation = station;
			saveStation.setAccountId(account.getId());
			
			if (!newUser && sysStationDao.isNotUnique(saveStation, "accountId")) {
				throw new GenericException("账号为:\"" + station.getAccount() + "\"的租户已经绑定其他站点!");
			}

		} else {
			//修改站点
			saveStation = sysStationDao.get(stationId);
			saveStation.setName(station.getName());
			saveStation.setStatus(station.getStatus());
			saveStation.setFloder(station.getFloder());
		}
		sysStationDao.save(saveStation);
	}

	@Override
	public void updStatus(Long stationId, Long status) {
		SysStation station = sysStationDao.get(stationId);
		if (station.getStatus() != status) {
			station.setStatus(status);
			sysStationDao.save(station);
		}
	}

	@Override
	public List<Map> getStationCombo() {
		return sysStationDao.getStationCombo();
	}
}