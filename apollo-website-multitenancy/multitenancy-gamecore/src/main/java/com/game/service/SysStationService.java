package com.game.service;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.SysStation;
import com.game.model.vo.StationVo;

public interface SysStationService {

	public List<Map> getStationCombo();

	public Page<Map> getPage(StationVo svo);

	public void saveStation(SysStation station);

	public void updStatus(Long stationId, Long status);
}
