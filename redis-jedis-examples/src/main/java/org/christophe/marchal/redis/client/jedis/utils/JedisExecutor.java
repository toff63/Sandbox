package org.christophe.marchal.redis.client.jedis.utils;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.PipelineBlock;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisExecutor {

	private JedisPool pool;
	private JedisPool slavePool;

	public void close(){
		pool.destroy();
		slavePool.destroy();
	}


	public <T> T set(JedisCallback<T> callback, String key, String value){
		Jedis jConnection = null;
		try{
			jConnection = pool.getResource();
			String res = jConnection.set(key, value);
			return callback.handleResult(res);

		} catch(JedisConnectionException jce){
			changeSlaveToMaster();
			set(callback, key, value);
		}
		catch(Exception e){
			callback.handleErrors(e);
		}
		finally {
			pool.returnResource(jConnection);
		}
		return null;
	}

	public <T> T mget( JedisCallback<T> callback, String... keys){
		Jedis jConnection = null;
		try{
			jConnection = pool.getResource();
			List<String> res = jConnection.mget(keys);
			return callback.handleResult(res);
		} catch(JedisConnectionException jce){
			changeSlaveToMaster();
			mget(callback, keys);
		}  catch(Exception e){ 
			callback.handleErrors(e);
		}
		finally {
			pool.returnResource(jConnection);
		}
		return null;
	}

	public List<String> mget( JedisCallback<List<String>> callback, byte... keys){
		Jedis jConnection = null;
		try{
			jConnection = pool.getResource();
			List<byte[]> res = jConnection.mget(keys);
			return callback.handleResult(res);
		} catch(JedisConnectionException jce){
			changeSlaveToMaster();
			mget(callback, keys);
		} catch(Exception e){
			callback.handleErrors(e);
		}
		finally {
			pool.returnResource(jConnection);
		}
		return null;
	}
	
	private void changeSlaveToMaster() {
		if(pool.equals(slavePool)) return;
		pool = slavePool;
		Jedis jedis = pool.getResource();
		try{
			// Change the slave to master
			jedis.slaveofNoOne();
		} finally{
			pool.returnResource(jedis);
		}
		
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
		Jedis jConnection = null;
		try{
			jConnection = pool.getResource();
			Long l = jConnection.lpush(key, value);
			return callback.handleResult(l);
		} catch(JedisConnectionException jce){
			changeSlaveToMaster();
			lpush(callback, key, value);
		}catch (Exception e) { 
			callback.handleErrors(e);
		}
		finally{
			pool.returnResource(jConnection);
		}
		return null;
	}

	public <T> T pipeline(PipelineBlock pipelineBlock, JedisCallback<T> callback){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			List<Object> results = jedis.pipelined(pipelineBlock);
			return callback.handleResult(results);
		} catch(JedisConnectionException jce){
			changeSlaveToMaster();
			pipeline(pipelineBlock, callback);
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
	
	public void setSlavePool(JedisPool slavePool) {
		this.slavePool = slavePool;
	}
}
