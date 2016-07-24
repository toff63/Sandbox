package rs.jug.rx.restclient;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import rs.jug.rx.restclient.contract.Quote;

public class JsonReader {
	private final ObjectReader reader = new ObjectMapper().readerFor(Quote.class);
	public Quote read(InputStream is){
		try {
			return reader.readValue(is);
		} catch (IOException e) {
			throw new RuntimeException("Failed to parser stream", e);
		}
	}
}
