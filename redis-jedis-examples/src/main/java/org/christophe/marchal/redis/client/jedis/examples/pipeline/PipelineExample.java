package org.christophe.marchal.redis.client.jedis.examples.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.christophe.marchal.redis.client.jedis.utils.JedisExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PipelineExample {
	
	private static ApplicationContext ac;
	
	public static void main(String[] args) throws InterruptedException {
		ac = new GenericXmlApplicationContext("classpath:META-INF/spring-jedisbeans.xml");
		JedisExecutor je = ac.getBean(JedisExecutor.class);
		je.cleanKeys();
		
		List<String> ids = new ArrayList<String>();
		for (int i = 0 ; i < 10000 ; i ++){
			ids.add(String.valueOf(i));
		}
		
		insertIds(je, ids);
	}
	
	
	private static void insertIds(JedisExecutor je, List<String> ids) {}
	
}
