package org.christophe.marchal.redis.client.jedis.utils;




public interface JedisCallback<T> {

	public T handleResult(Object res);
	public void handleErrors(Throwable e);
	
}
