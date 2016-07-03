package com.game.service;

import java.util.List;
import java.util.Map;

import org.jay.frame.jdbc.Page;

import com.game.model.SysThirdPlatform;
import com.game.model.ThirdStationGroup;
import com.game.model.vo.ThirdPlatVo;

public interface ThirdPlatformService {

	public Page<Map> getPage(ThirdPlatVo tpv);
	
	public Page<Map> getAgentPage();

	public void saveThirdPlat(SysThirdPlatform thirdPlat);

	public void updStatus(Long thirdPlatId, Long status);
	
	public void saveThirdGroup(ThirdStationGroup tsg);
	
	public List<Map> getThirdList();
}
