package net.francesbagual.sandbox; 

import net.francesbagual.sandbox.TweetReader;

import junit.framework.TestCase;

public class ExampleItemReaderTests extends TestCase {

	private TweetReader reader = new TweetReader();
	
	public void testReadOnce() throws Exception {
		assertNotNull(reader.read());
	}

}
