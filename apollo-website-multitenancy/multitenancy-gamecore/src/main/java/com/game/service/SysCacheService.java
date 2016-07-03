package com.game.service;

import org.jay.frame.jdbc.Page;

import com.game.model.SysCache;

import java.util.List;

public interface SysCacheService {

	public Page<SysCache> getPage();
	
	public void saveCache(SysCache cache);
	
	public void delCache(long cacheId);
	
	
	public List<SysCache> getAll();
}
