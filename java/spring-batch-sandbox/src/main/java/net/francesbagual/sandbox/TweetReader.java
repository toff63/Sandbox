package net.francesbagual.sandbox;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("reader")
public class TweetReader implements ItemReader<Status> {
	
	/**
	 * Reads next record from input
	 */
	public Status read() throws Exception {
		Twitter twitter = new TwitterFactory().getInstance();
		return twitter.getUserTimeline("toff63", new Paging(1)).iterator().next();
	}

}
