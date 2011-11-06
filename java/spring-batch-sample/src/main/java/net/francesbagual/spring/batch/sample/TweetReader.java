package net.francesbagual.spring.batch.sample;

import java.util.Iterator;

import org.springframework.batch.item.ItemReader;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetReader implements ItemReader<Status> {

	private Iterator<Status> statuses;
	private String userId;
	private Paging currentPage;
	private int pageSize;
	private int numberOfPages;

	public void init() {
		currentPage = new Paging(1, pageSize);
		getNextStatuses();
	}

	@Override public Status read(){
		if(isFinnished()) return null;
		if(reachedEndOfPage()){
			currentPage.setPage(currentPage.getPage() + 1);
			getNextStatuses();
		}
		return statuses.next();
	}

	private boolean isFinnished() {
		return currentPage.getPage() + 1 > numberOfPages;
	}

	private boolean reachedEndOfPage() {
		return !statuses.hasNext();
	}

	private void getNextStatuses() {
		Twitter twitter = new TwitterFactory().getInstance();
		try {
			statuses = twitter.getUserTimeline(userId, currentPage).iterator();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
}
