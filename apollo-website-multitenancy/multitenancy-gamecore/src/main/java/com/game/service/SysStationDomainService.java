package com.game.service;

import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.SysStationDomain;
import com.game.model.vo.StationVo;

public interface SysStationDomainService {
	public Page<Map> getPage(StationVo svo);

	public void saveDomain(SysStationDomain domain);

	public void updStatus(Long stationId, Long status);
	
	public void deleteDomain(Long domainId);
}
