package com.game.service.impl;

import java.util.List;

import org.jay.frame.jdbc.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.game.dao.SysCacheDao;
import com.game.model.SysCache;
import com.game.service.SysCacheService;

@Repository
public class SysCacheServiceImpl implements SysCacheService{

	@Autowired
	private SysCacheDao cacheDao;
	
	@Override
	public Page<SysCache> getPage() {
		return cacheDao.getPage();
	}

	@Override
	public void saveCache(SysCache cache) {
		Long cacheId = cache.getId();
		if(cacheId != null){//修改
			SysCache oldCache = cacheDao.get(cacheId);
			oldCache.setDb(cache.getDb());
			oldCache.setDataType(cache.getDataType());
			oldCache.setKey(cache.getKey());
			oldCache.setRemark(cache.getRemark());
			oldCache.setName(cache.getName());
			oldCache.setTimeout(cache.getTimeout());
			oldCache.setExpression(cache.getExpression());
			cacheDao.save(oldCache);
		}else{ //新增
			cacheDao.save(cache);
		}
	}

	@Override
	public void delCache(long cacheId) {
		cacheDao.delete(cacheId);
	}

	@Override
	public List<SysCache> getAll() {
		return cacheDao.getAll();
	}
}
