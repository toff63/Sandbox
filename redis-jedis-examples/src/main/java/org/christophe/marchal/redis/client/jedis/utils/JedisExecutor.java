package org.christophe.marchal.redis.client.jedis.utils;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.PipelineBlock;

public class JedisExecutor {

	private JedisPool pool;

	public void close(){
		pool.destroy();
	}


	public <T> T set(JedisCallback<T> callback, String key, String value){
		Jedis jConnection = pool.getResource();

		try{
			String res = jConnection.set(key, value);
			return callback.handleResult(res);

		} catch(Exception e){
			callback.handleErrors(e);
		}
		finally {
			pool.returnResource(jConnection);
		}
		return null;
	}

	public <T> T mget( JedisCallback<T> callback, String... keys){
		Jedis jConnection = pool.getResource();
		try{
			List<String> res = jConnection.mget(keys);
			return callback.handleResult(res);

		} catch(Exception e){
			callback.handleErrors(e);
		}
		finally {
			pool.returnResource(jConnection);
		}
		return null;
	}

	public List<String> mget( JedisCallback<List<String>> callback, byte... keys){
		Jedis jConnection = pool.getResource();
		try{
			List<byte[]> res = jConnection.mget(keys);
			return callback.handleResult(res);

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

	public <T> T lpush(JedisCallback<T> callback, String key, String value){
		Jedis jConnection = pool.getResource();
		try{
			Long l = jConnection.lpush(key, value);
			return callback.handleResult(l);
		}catch (Exception e) {
			callback.handleErrors(e);
		}
		finally{
			pool.returnResource(jConnection);
		}
		return null;
	}


	public <T> T pipeline(JedisCallback<T> callback, PipelineBlock pipelineBlock){
		Jedis jedis = pool.getResource();
		try{
			List<Object> results = jedis.pipelined(pipelineBlock);
			return callback.handleResult(results);
		} catch (Exception e) {
			callback.handleErrors(e);
		} finally{
			pool.returnResource(jedis);
		}
		return null;
	}
	
	public void setPool(JedisPool pool) {
		this.pool = pool;
	}
}
