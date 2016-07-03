package com.game.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.cache.CacheType;
import com.game.cache.CacheUtil;
import com.game.dao.SysStationDao;
import com.game.model.SysStation;
import com.game.service.FrameService;

@Repository
public class FrameServiceImpl implements FrameService{

	@Autowired
	private SysStationDao stationDao;
	
	public SysStation getStation(String domain) {
		SysStation station = CacheUtil.getCache(CacheType.STATION_DOMAIN, domain, SysStation.class);
		if(station != null){
			return station;
		}
		station = stationDao.getStation(domain);
		System.out.println("站点缓存过期，读取缓存【"+domain+"】");
		if(station == null){//站点不存在 或者已经停用
			return null;
		}
		CacheUtil.addCache(CacheType.STATION_DOMAIN, domain, station);  
		return station;
	}
	
}
