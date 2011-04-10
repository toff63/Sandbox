package org.christophe.marchal.redis.client.jedis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisExecutor {

	private JedisPool pool;

	public void close(){
		pool.destroy();
	}


	public <T> T execute(JedisCallback<T> callback){
		T res = null;
		Jedis jConnection = pool.getResource();

		try{
			res = callback.process(jConnection);
		} 
		finally {
			pool.returnResource(jConnection);
		}
		return res;
	}

	public void voidExecute(JedisCallback<?> callback){
		Jedis jConnection = pool.getResource();

		try{
			callback.process(jConnection);
		} finally {
			pool.returnResource(jConnection);
		}
	}
	
	public void setPool(JedisPool pool) {
		this.pool = pool;
	}
}
