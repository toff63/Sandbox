package org.christophe.marchal.redis.client.jedis.examples.thread;

import java.math.BigDecimal;
import java.math.MathContext;

import org.christophe.marchal.redis.client.jedis.utils.JedisCallback;
import org.christophe.marchal.redis.client.jedis.utils.JedisExecutor;
import org.christophe.marchal.redis.client.jedis.utils.RedisOperation;

import redis.clients.jedis.Jedis;

public class MyRunnable implements Runnable{

	private JedisExecutor je;
	private Engine engine;

	public MyRunnable( JedisExecutor je, Engine engine){
		this.je = je;
		this.engine = engine;
	}


	public void run() {
		long firstIndice;
		while( (firstIndice = engine.getIndice()) != -1){
			getBucketSum(firstIndice);
		}
	}


	private void getBucketSum(long firstIndice) {
		BigDecimal sum = new BigDecimal(0, MathContext.DECIMAL128);
		engine.updateStatistic(Thread.currentThread().getName());
		sum = new BigDecimal(0, MathContext.DECIMAL128);
		long end = firstIndice + ThreadExample.BUCKET_SIZE;
		for(long j = firstIndice ; j < end; j++){
			BigDecimal j2 = BigDecimal.valueOf(j).pow(2);
			BigDecimal val = BigDecimal.valueOf(1).divide(j2,  MathContext.DECIMAL128);
			sum = sum.add(val, MathContext.DECIMAL128);
		}
		
		Object[] os = {String.valueOf(firstIndice) , String.valueOf(sum)};
		Class<?>[] clazz = {String.class, String.class};

		je.execute(os, clazz, new JedisCallback<Object>() {

			public RedisOperation getOperation() {
				return RedisOperation.SET;
			}

			public Object handleResult(Object res) {
				String result = (String)res ;
				if(! "OK".equals(result)){
					System.out.print("Couldn't insert value in redis");
				}
				return null;
			}

			public void handleErrors(Throwable e) {
				// TODO Auto-generated method stub

			}

		});
	}


}
