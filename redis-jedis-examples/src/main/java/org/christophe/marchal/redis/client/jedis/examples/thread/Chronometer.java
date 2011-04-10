package org.christophe.marchal.redis.client.jedis.examples.thread;

public class Chronometer {

	
	public Long start(){
		return System.currentTimeMillis();
	}
	
	public Long stop(Long start){
		return System.currentTimeMillis() - start;
	}
}
