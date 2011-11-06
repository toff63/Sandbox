package net.francesbagual.spring.batch.sample;

import java.util.List;

import net.francesbagual.spring.batch.sample.dao.TweetDao;
import net.francesbagual.spring.batch.sample.model.Tweet;

import org.springframework.batch.item.ItemWriter;


public class TweetWriter implements ItemWriter<Tweet> {

	private TweetDao dao;

	@Override public void write(List<? extends Tweet> tweets){
		for(Tweet tweet : tweets){
			dao.save(tweet);
		}
	}
	
	public void setDao(TweetDao dao) {
		this.dao = dao;
	}

}
