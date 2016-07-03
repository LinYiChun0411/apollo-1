package com.game.cache.redis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import org.jay.frame.util.StringUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisAPI {

	private static RedisConfig cfg = new RedisConfig();
	
	private static JedisPool pool = null;  
	/**
	 * 获取连接池
	 * @return
	 */
	public static JedisPool getPool(){
		return pool;
	}
	/**
	 * 初始化连接池
	 * @throws IOException
	 */
	public synchronized static void initPool() throws IOException{
		if(pool == null){
			
			Properties prop = new Properties();   
		    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("redis.properties");   
		    if(in == null){
		    	throw new FileNotFoundException("文件 /WEB-INF/classes/redis.properties 不存在");
		    }
            prop.load(in);   
            String param = prop.getProperty("redis.host");
            if(StringUtil.isNotEmpty(param)){
            	cfg.setHost(param);
            }
    
            param = prop.getProperty("redis.port"); 
            if(StringUtil.isNotEmpty(param)){
            	cfg.setPort(Integer.parseInt(param));
            }
            
            param = prop.getProperty("redis.password"); 
            if(StringUtil.isNotEmpty(param)){
            	cfg.setPassword(param);
            }
            
            param = prop.getProperty("redis.default.db"); 
            if(StringUtil.isNotEmpty(param)){
            	cfg.setDefaultDb(Integer.parseInt(param));
            	//defaultDb = Integer.parseInt(param);
            }
      
            param = prop.getProperty("redis.timeout"); 
            if(StringUtil.isNotEmpty(param)){
            	cfg.setTimeout(Integer.parseInt(param));
            	//timeout = Integer.parseInt(param);
            }
            
            param = prop.getProperty("redis.maxActive"); 
            if(StringUtil.isNotEmpty(param)){
            	cfg.setMaxActive(Integer.parseInt(param));
            }
            
            param = prop.getProperty("redis.maxWait"); 
            if(StringUtil.isNotEmpty(param)){
            	cfg.setMaxWait(Integer.parseInt(param));
            }
            
            param = prop.getProperty("redis.maxIdle"); 
            if(StringUtil.isNotEmpty(param)){
            	cfg.setMaxIdle(Integer.parseInt(param));
            }
            
            param = prop.getProperty("redis.testOnBorrow"); 
            if(StringUtil.isNotEmpty(param)){
            	cfg.setTestOnBorrow(Boolean.parseBoolean(param));
            }
	      
			JedisPoolConfig config = new JedisPoolConfig();  
			
            config.setMaxTotal(cfg.getMaxActive());
            
            config.setMaxIdle(cfg.getMaxIdle());  
             
            config.setMaxWaitMillis(cfg.getMaxWait());
            
            config.setTestOnBorrow(cfg.isTestOnBorrow());  
			
			pool = new JedisPool(config,cfg.getHost(), cfg.getPort(),cfg.getTimeout(),cfg.getPassword(),cfg.getDefaultDb(),null);  
		}
		testConnection();
	}
	
	/**
	 * 测试连接是否成功
	 */
	private static void testConnection(){
        Jedis jedis = null;  
        try {  
            jedis = pool.getResource();  
            System.out.println("【Redis连接池创建成功】");
        } finally {  
        	returnResource(pool, jedis);
        }  
	}
	
	/** 
     * 返还到连接池 
     *  
     * @param pool  
     * @param redis 
     */  
    public static void returnResource(JedisPool pool, Jedis redis) {  
        if (redis != null) {  
            pool.returnResource(redis);  
        }  
    } 
    
    /** 
     * 获取数据 
     *  
     * @param key 
     * @return 
     */  
    public static String getCache(String key,int db){  
        String value = null;  
        Jedis jedis = null;  
        try {  
            jedis = pool.getResource();  
            jedis.select(db);
            value = jedis.get(key);  
        }finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
        return value;  
    }
    /**
     * 写入缓存
     * @param key
     * 			键
     * @param val
     * 			值
     * @param timeout
     * 			超时时间单位（秒），0：永不超时  
     * @param db
     * 			数据
     */
    public static void addCache(String key ,String val,int timeout,int db){
        Jedis jedis = null;  
        try {  
            jedis = pool.getResource();  
            jedis.select(db);
            if(timeout <= 0){
            	jedis.set(key, val);
            }else{
            	jedis.setex(key, timeout, val);
            }
        }finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
    }
    
    public static void addCache(String key ,String val,Integer timeout){
    	addCache(key ,val,timeout,0);
    }
    
    public static void operateCache(RedisCallback callback){
        Jedis jedis = null;  
        try {  
            jedis = pool.getResource();  
            callback.execute(jedis);
        }finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
    }
    
    public static Set<String> getCacheList(String keyHeader,int db){
        Jedis jedis = null;  
        try {  
            jedis = pool.getResource();  
            jedis.select(db);
            Set<String> keys = jedis.keys(keyHeader+"*");
            return keys;
        }finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
    }
    /**
     * 删除缓存
     * @param db
     * @param keys
     */
    public static void delCache(int db,String... keys){
        Jedis jedis = null;  
        try {  
            jedis = pool.getResource();  
            jedis.select(db);
            jedis.del(keys);
        }finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
    }
}
