package org.christophe.marchal.redis.client.jedis.utils;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisExecutor {

	private JedisPool pool;

	public void close(){
		pool.destroy();
	}


	public <T> T execute(Object[] args, Class<?>[] clazz, JedisCallback<T> callback){
		Jedis jConnection = pool.getResource();

		try{
			RedisOperation op = callback.getOperation();

			if(RedisOperation.MGET.equals(op)){
				Object res = getMset(jConnection, args, clazz); 
				return callback.handleResult(res);
			} else if (RedisOperation.SET.equals(op)){
				Object res;
				res = handleSet(jConnection, args, clazz); 
				return callback.handleResult(res);
				 
			}
		} catch(Exception e){
			callback.handleErrors(e);
		}
		finally {
			pool.returnResource(jConnection);
		}
		return null;
	}

	public void flushAll(){
		Jedis jConnection = pool.getResource();
		try{
			jConnection.flushAll();
		}
		finally {
			pool.returnResource(jConnection);
		}
	}

	private Object handleSet(Jedis jConnection, Object[] args,
			Class<?>[] clazz) {
		if(clazz[0].equals(String.class) && clazz[1].equals(String.class)){
			String key = (String) args[0];
			String value = (String) args[1];
			return  jConnection.set(key, value);
			
		} else if (clazz[0].equals(Byte[].class) && clazz[1].equals(Byte[].class)){
			byte[] keys = (byte[]) args[0];
			byte[] values = (byte[]) args[1];
			return jConnection.set(keys, values);
		}
		return null;
	}


	private Object getMset(Jedis jConnection,
			Object[] args, Class<?>[] clazz) {
		if(clazz[0].equals(String[].class)){
			String[] keys = (String[]) args;
			return jConnection.mget(keys);
		} else if (clazz[0].equals(Byte[].class)){
			byte[] keys = (byte[]) args[0];
			return jConnection.mget(keys);
		}
		return null;
	}
	
	public void setPool(JedisPool pool) {
		this.pool = pool;
	}
}
