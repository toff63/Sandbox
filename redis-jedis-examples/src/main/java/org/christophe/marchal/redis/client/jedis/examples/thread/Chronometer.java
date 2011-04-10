package org.christophe.marchal.redis.client.jedis.examples.thread;

public class Chronometer {

	private Long begin;
	
	public void start(){
		begin = System.currentTimeMillis();
	}
	
	public Long stop(){
		return System.currentTimeMillis() - begin;
	}
}
