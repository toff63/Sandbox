package org.christophe.marchal.redis.client.jedis.examples.pipeline;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.christophe.marchal.redis.client.jedis.examples.thread.Chronometer;
import org.christophe.marchal.redis.client.jedis.utils.JedisCallback;
import org.christophe.marchal.redis.client.jedis.utils.JedisExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import redis.clients.jedis.PipelineBlock;
import redis.clients.jedis.Protocol;

public class PipelineExample {

	private static ApplicationContext ac;

	public static void main(String[] args) throws InterruptedException {
		ac = new GenericXmlApplicationContext("classpath:META-INF/spring-jedisbeans.xml");
		JedisExecutor je = ac.getBean(JedisExecutor.class);
		Chronometer chronometer = ac.getBean(Chronometer.class);

		je.flushAll();

		Long begin = chronometer.start();
		insertPushIds(je);
		Long time = chronometer.stop(begin);

		System.out.println("Time without pipeline: " + time);
		
		Long pipelineBegin = chronometer.start();
		pipelineInsertPushIds(je);
		Long pipelineTime = chronometer.stop(pipelineBegin);
		System.out.println("Time without pipeline: " + pipelineTime);

	}

	private static void pipelineInsertPushIds(JedisExecutor je) {
		je.pipeline(new PipelineBlock() {

			@Override
			public void execute() {
				for(int i = 0; i < 10000 ; i++){
					lpush("pipelinePush", String.valueOf(i));
				}		
			}
		}, new JedisCallback<Object>() {

			public Object handleResult(Object res) {
				List<byte[]> listResult = (List<byte[]>) res;
				for(byte[] o : listResult){
					try {
						if(!"OK".getBytes(Protocol.CHARSET).equals( o)){
							System.out.println("A lpush didn't work");
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return null;
			} 

			public void handleErrors(Throwable e) {
				// TODO Auto-generated method stub

			}
		});

	}


	private static void insertPushIds(JedisExecutor je) {
		String key = "pushIds";
		for(int i = 0; i < 10000 ; i++){
			je.lpush(new JedisCallback<Long>() {

				public Long handleResult(Object res) {
					return (Long) res;
				}

				public void handleErrors(Throwable e) {
					// TODO Auto-generated method stub

				}
			}, key, String.valueOf(i));
		}
	}

}
