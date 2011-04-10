package org.christophe.marchal.redis.client.jedis.examples.thread;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.christophe.marchal.redis.client.jedis.utils.BigSquareRoot;
import org.christophe.marchal.redis.client.jedis.utils.JedisCallback;
import org.christophe.marchal.redis.client.jedis.utils.JedisExecutor;
import org.christophe.marchal.redis.client.jedis.utils.RedisOperation;

import redis.clients.jedis.Jedis;

public class Engine {

	JedisExecutor jexecutor;

	// Initialize to indice to return 0 for the first thread.
	private int nextIndice = 1 - ThreadExample.BUCKET_SIZE;
	private Map<String, Long> statistics = new TreeMap<String, Long>();

	private List<String> redisKeys = new ArrayList<String>();

	public BigDecimal compute() throws InterruptedException{
		feedRedis();
		return computeResult();
	}

	/**
	 * it returns the next indices to be computed
	 */
	public synchronized long getIndice() {
		if(nextIndice < ThreadExample.N){
			nextIndice +=ThreadExample.BUCKET_SIZE;
			redisKeys.add(String.valueOf(nextIndice));
			return nextIndice;
		} else return -1;
	}

	public synchronized void updateStatistic(String threadName) {
		Long l = 1L;
		if(statistics.containsKey(threadName)){
			l = statistics.get(threadName);
			l++;
		}
		statistics.put(threadName, l);
	}

	private void feedRedis() throws InterruptedException {
		List<Thread> threads = new ArrayList<Thread>(ThreadExample.POOL_SIZE);
		for(int i = 0; i < ThreadExample.POOL_SIZE; i++){
			Thread t = new Thread(new MyRunnable(jexecutor, this), "Thread " + i);
			threads.add(t);
			t.start();
		}
		for(int i = 0; i < ThreadExample.POOL_SIZE; i++){
			threads.get(i).join();
		}
	}

	
	private BigDecimal computeResult() {
		Class<?>[] clazz = {String[].class};
		Object[] os = redisKeys.toArray(new String[redisKeys.size()]);
		BigDecimal d =	jexecutor.execute(os, clazz, new JedisCallback<BigDecimal>() {

			public RedisOperation getOperation() {
				return RedisOperation.MGET;
			}


			public BigDecimal handleResult(Object o) {
				List<String> ls = (List<String>) o;
				BigDecimal res = new BigDecimal(0, MathContext.DECIMAL128);
				int i = 0;
				for(String s : ls){
					if(s != null){
						res = res.add(new BigDecimal(s));
						i++;
					}
				}
				return res;
			}

			public void handleErrors(Throwable e) {
				// TODO Auto-generated method stub

			}



		});
		return d;
	}

	public void setJexecutor(JedisExecutor jexecutor) {
		this.jexecutor = jexecutor;
	}
	
	public void displayResult(BigDecimal d){
		BigDecimal val =  d.multiply(BigDecimal.valueOf(6));
		System.out.println("Sum of 1/(n*n) from 1 to "+ ThreadExample.N +" equals " + d);
		BigSquareRoot bigSquareRoot = new BigSquareRoot();
		System.out.println("Computed Pi = " + bigSquareRoot.get(val));
		System.out.println("Real Pi value = " + Math.PI);
		System.out.println("Number of threads which worked: " + statistics.size());
	}
}
