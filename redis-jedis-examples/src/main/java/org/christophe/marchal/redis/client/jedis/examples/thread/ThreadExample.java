package org.christophe.marchal.redis.client.jedis.examples.thread;

import java.math.BigDecimal;

import org.christophe.marchal.redis.client.jedis.utils.JedisCallback;
import org.christophe.marchal.redis.client.jedis.utils.JedisExecutor;
import org.christophe.marchal.redis.client.jedis.utils.RedisOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * <p>This class compute sum from 1 to N of 1/(n*n) 
 * In theory this sum tend to Pi*Pi/6
 * It creates POOL_SIZE threads computing BUCKET_SIZE sum value and store it in redis.
 * Once everything is computed, it get values from redis and sum them to get </p>
 * 
 * 
 * 
 * @author Christophe Marchal
 * @since Apr 9, 2011
 */
public class ThreadExample {

	private static ApplicationContext ac;
	public static final double N = 10000000;
	public static final int POOL_SIZE = 150;
	public static final int BUCKET_SIZE = 100;




	public static void main(String[] args) throws InterruptedException {
		ac = new GenericXmlApplicationContext("classpath:META-INF/spring-jedisbeans.xml");
		JedisExecutor je = ac.getBean(JedisExecutor.class);
		Engine engine = ac.getBean(Engine.class);
		Chronometer chronometer = ac.getBean(Chronometer.class);
		try{
			je.flushAll();
			Long start = chronometer.start();
			BigDecimal d = engine.compute();
			Long time = chronometer.stop(start);
			System.out.println("Computing Pi thanks to the 2-serie: Sum of 1/(n*n) = Pi * Pi /6 when n tend to infinity");
			System.out.println("Computing with " + POOL_SIZE + " threads buckets of " + BUCKET_SIZE);
			engine.displayResult(d);
			System.out.println("\n Time spent computing: " + time/1000 + " sec");
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			je.close();
		}


	}


}
