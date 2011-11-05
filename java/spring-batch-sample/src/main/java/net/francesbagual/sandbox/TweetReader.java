package net.francesbagual.sandbox;

import java.util.Iterator;

import org.springframework.batch.item.ItemReader;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * {@link ItemReader} with hard-coded input data.
 */

public class TweetReader implements ItemReader<Status> {
	
	private Iterator<Status> statuses;
	
	public TweetReader() {
		Twitter twitter = new TwitterFactory().getInstance();
		try {
			statuses = twitter.getUserTimeline("toff63").iterator();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads next record from input
	 */
	public Status read() throws Exception {
		return statuses.next();
	}

}
