package com.game.service;

import com.game.model.SysStation;

/**
 * 非Controller调用接口
 * @author admin
 *
 */
public interface FrameService {
	
	public SysStation getStation(String domain);
	
}
