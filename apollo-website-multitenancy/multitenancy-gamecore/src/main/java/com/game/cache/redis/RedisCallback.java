package com.game.cache.redis;

import redis.clients.jedis.Jedis;

public interface RedisCallback {
	public void execute(Jedis jedis);
}
