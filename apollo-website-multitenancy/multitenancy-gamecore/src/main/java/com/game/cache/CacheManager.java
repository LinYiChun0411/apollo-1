package com.game.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jay.frame.exception.GenericException;
import org.jay.frame.util.StringUtil;
import org.springframework.aop.ThrowsAdvice;

import com.game.model.SysCache;
import com.game.service.SysCacheService;
import com.game.util.SpringUtil;

public class CacheManager {
	private static final Map<String,CacheConfig> configMap = new HashMap<String,CacheConfig>();
	/**
	 * 缓存配置
	 */
	public static void loadConfigMap(){
		configMap.clear();
		SysCacheService cacheService = (SysCacheService)SpringUtil.getBean("sysCacheServiceImpl");
		List<SysCache> list = cacheService.getAll();
		for (int i = 0; i < list.size(); i++) {
			SysCache cache = list.get(i);
			configMap.put(StringUtil.trim2Empty(cache.getKey()),new CacheConfig(StringUtil.toInt(cache.getTimeout()),StringUtil.toInt(cache.getDb())));
		}
	}
	
	/**
	 * 获取缓存配置
	 * @param type
	 * @return
	 */
	public static CacheConfig getCacheConifg(CacheType type){
		if(type == null){
			return null;
		}
		CacheConfig cfg = configMap.get(type.name());
		if(cfg == null){
			throw new GenericException("未找到对应的缓存["+type.name()+"]配置，请在后台总控[缓存模块]新增对应的缓存配置,然后重启web服务器");
			//cfg = CacheConfig.getDefaultConfig();
		}
		return cfg;
	}
	
	
}

class CacheConfig{
	
	public CacheConfig(){}
	
	public CacheConfig(int timeout,int db){
		this.timeout = timeout;
		this.selectDb = db;
	}
	
	public static CacheConfig getDefaultConfig(){
		CacheConfig cfg = new CacheConfig();
		cfg.selectDb = 0;
		cfg.timeout = 0;
		return cfg;
	}
	
	private int selectDb;


	/**
	 * 超时时间 0:不超时  单位秒
	 */
	private int timeout;
	

	public int getSelectDb() {
		return selectDb;
	}
	public void setSelectDb(int selectDb) {
		this.selectDb = selectDb;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}