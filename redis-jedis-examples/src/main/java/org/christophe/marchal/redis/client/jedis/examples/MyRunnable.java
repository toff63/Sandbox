package org.christophe.marchal.redis.client.jedis.examples;

import java.math.BigDecimal;
import java.math.MathContext;

import org.christophe.marchal.redis.client.jedis.utils.JedisCallback;
import org.christophe.marchal.redis.client.jedis.utils.JedisExecutor;

import redis.clients.jedis.Jedis;

public class MyRunnable implements Runnable{

	private JedisExecutor je;
	private Engine engine;
	
	public MyRunnable( JedisExecutor je, Engine engine){
		this.je = je;
		this.engine = engine;
	}


	public void run() {
		je.voidExecute(new JedisCallback<Object>() {

			public Object process(Jedis jConnection) {
				BigDecimal sum = new BigDecimal(0, MathContext.DECIMAL128);
				long i;
				while( (i = engine.getIndice()) != -1){
					engine.updateStatistic(Thread.currentThread().getName());
					sum = new BigDecimal(0, MathContext.DECIMAL128);
					long end = i + ThreadExample.BUCKET_SIZE;
					for(long j = i ; j < end; j++){
						BigDecimal j2 = BigDecimal.valueOf(j).pow(2);
						BigDecimal val = BigDecimal.valueOf(1).divide(j2,  MathContext.DECIMAL128);
						sum = sum.add(val, MathContext.DECIMAL128);
					}
					jConnection.set(String.valueOf(i), String.valueOf(sum));
				}
				return null;
			}
		});
	}
	

}
