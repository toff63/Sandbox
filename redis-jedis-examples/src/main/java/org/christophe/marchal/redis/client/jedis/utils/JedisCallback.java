package org.christophe.marchal.redis.client.jedis.utils;

import redis.clients.jedis.Jedis;

public interface JedisCallback<T> {

	public T process(Jedis jConnection);
	
}
