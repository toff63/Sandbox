package net.francesbagual.spring.batch.sample.dao;

import net.francesbagual.spring.batch.sample.model.Tweet;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class TweetDao {

	private DBCollection collection;
	
	public TweetDao() {
		try {
			Mongo mongo = new Mongo();
			DB db = mongo.getDB( "twitter" );
			collection = db.getCollection("tweet");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void save(Tweet twit) {
		BasicDBObject task = new BasicDBObject();
		task.put("user", twit.getUserName());
		task.put("twit", twit.getText());
		collection.insert(task);
	}

}
