package net.francesbagual.sandbox;

import net.francesbagual.sandbox.TweetWriter;

import junit.framework.TestCase;

public class ExampleItemWriterTests extends TestCase {

	private TweetWriter writer = new TweetWriter();
	
	public void testWrite() throws Exception {
		writer.write(null); // nothing bad happens
	}

}
