package net.francesbagual.sandbox;

import net.francesbagual.sandbox.model.Tweet;

import org.springframework.batch.item.ItemProcessor;

import twitter4j.Status;

public class TweetProcessor implements ItemProcessor<Status, Tweet>{

	@Override
	public Tweet process(Status item) throws Exception {
		Tweet t = new Tweet();
		t.setUserName(item.getUser().getName());
		t.setText(item.getText());
		return t;
	}

}
