package org.christophe.marchal.redis.client.jedis.examples.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.christophe.marchal.redis.client.jedis.examples.thread.Chronometer;
import org.christophe.marchal.redis.client.jedis.utils.JedisCallback;
import org.christophe.marchal.redis.client.jedis.utils.JedisExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PipelineExample {
	
	private static ApplicationContext ac;
	
	public static void main(String[] args) throws InterruptedException {
		ac = new GenericXmlApplicationContext("classpath:META-INF/spring-jedisbeans.xml");
		JedisExecutor je = ac.getBean(JedisExecutor.class);
		Chronometer chronometer = ac.getBean(Chronometer.class);

		je.flushAll();
		
		List<String> ids = new ArrayList<String>();
		for (int i = 0 ; i < 10000 ; i ++){
			ids.add(String.valueOf(i));
		}
		
		Long begin = chronometer.start();
		insertPushIds(je, ids);
		Long end = chronometer.stop(begin);
	}
	
	
	private static void insertPushIds(JedisExecutor je, List<String> ids) {
		String key = "pushIds";
		for(String s : ids){
			je.lpush(new JedisCallback<Long>() {

				public Long handleResult(Object res) {
					return (Long) res;
				}

				public void handleErrors(Throwable e) {
					// TODO Auto-generated method stub
					
				}
			}, key, s);
		}
	}
	
}
