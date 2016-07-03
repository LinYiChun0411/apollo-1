package com.game.service;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.SysRegisterConfig;
import com.game.model.vo.RegisterConfigVo;

public interface SysRegisterConfigService {

	public Page<Map> getPageConfig(RegisterConfigVo rcvo);

	public List<Map> getStationRegConf(RegisterConfigVo rcvo);

	public List<Map> getStationRegVals();

	public void saveConfig(SysRegisterConfig src);

	public void saveStationConfGroup(List<Map> datas);

	public void updStatus(Long srcId, Long status);

	public void delConfig(Long srcId);
}
