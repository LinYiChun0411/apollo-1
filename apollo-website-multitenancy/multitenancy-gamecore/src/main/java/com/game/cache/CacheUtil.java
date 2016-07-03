package com.game.cache;

import org.jay.frame.util.JsonUtil;

import com.game.cache.redis.RedisAPI;


public class CacheUtil {
	
	/**
	 * 增加缓存
	 * @param type
	 * @param key
	 * @param val
	 */
	public static void addCache(CacheType type,String key,String val){
		String cacheKey = toKey(type,key);
		CacheConfig cfg = CacheManager.getCacheConifg(type);
		RedisAPI.addCache(cacheKey, val,cfg.getTimeout(),cfg.getSelectDb());
	}
	
	/**
	 * 获取缓存
	 * @param type
	 * @param key
	 * @return
	 */
	public static String getCache(CacheType type,String key){
		String cacheKey = toKey(type,key);
		CacheConfig cfg = CacheManager.getCacheConifg(type);
		return RedisAPI.getCache(cacheKey,cfg.getSelectDb());
	}
	
	/**
	 * 转换成缓存key
	 * @param type
	 * @param key
	 * @return
	 */
	private static String toKey(CacheType type,String key){
		return type.name() + "_" + key;
	}
	/**
	 * 从缓存中取出字符串，转换成对象
	 * @param type
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T getCache(CacheType type,String key,Class<T> clazz){
		String cacheKey = toKey(type,key);
		String json = getCache(type,key);
		if(json == null){
			return null;
		}
		if(clazz == String.class){
			return (T)json;
		}
		return JsonUtil.toBean(json, clazz);
	}
	/**
	 * 将一个对象转换成json，存入缓存
	 * @param type
	 * @param key
	 * @param model
	 * @param timeout
	 * 			 单位分钟 null值则为永久
	 */
	public static void addCache(CacheType type,String key,Object model){
		String json = null;
		if(model.getClass() == String.class){
			json = (String)model;
		}else{
			json = JsonUtil.toJson(model);
		}
		addCache(type, key,json);
	}
	

	/**
	 * 从redis中读取缓存，若不存在通过reader加载数据再放入缓存堆里
	 * @param reader
	 * @param clazz
	 * @param type
	 * @param key
	 * @param timeout
	 * 				过期时间（秒）
	 * @return
	 */
	public static <T> T getNull2Set(DataReader<T> reader,Class<T> clazz,CacheType type,String key){
		T t = CacheUtil.getCache(type, key,clazz);
		if(t != null){
			return t;
		}else{
			System.out.println("redis读取不到缓存["+type.name() +"_"+ key +"]");
		}
		t = reader.getData();
		CacheUtil.addCache(type, key, t);
		return t;
	}
	
	public static String getNull2Set(DataReader<String> reader,CacheType type,String key){
		return getNull2Set(reader,String.class,type,key);
	}
}
