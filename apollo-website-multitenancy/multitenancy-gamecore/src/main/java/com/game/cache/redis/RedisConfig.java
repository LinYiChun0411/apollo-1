package com.game.cache.redis;

public class RedisConfig {
	//服务器地址
	private String host = "127.0.0.1";
	
	//端口
	private int port = 6379; 
	
	//密码
	private String password = null;
	
	//默认数据库
	private int defaultDb = 0; 
	
	// 默认超时时间 10s
	private int timeout = 10 * 1000; 
	
	//默认最大连接数  如果赋值为-1，则表示不限制；
	private int maxActive = -1;
	
	//最大空闲连接数
	private int maxIdle = 100;
	
	//最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
	private int maxWait = 100 * 1000;
	
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
    private boolean testOnBorrow = false;  
    
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDefaultDb() {
		return defaultDb;
	}

	public void setDefaultDb(int defaultDb) {
		this.defaultDb = defaultDb;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
}
