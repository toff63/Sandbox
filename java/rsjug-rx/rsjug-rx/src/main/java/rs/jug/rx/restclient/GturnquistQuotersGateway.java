package rs.jug.rx.restclient;

import java.io.Closeable;
import java.io.IOException;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.DisposableBean;

import rs.jug.rx.restclient.contract.Quote;

public class GturnquistQuotersGateway implements DisposableBean {

	private static AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
	private JsonReader reader;

	public GturnquistQuotersGateway(JsonReader reader) {
		this.reader = reader;
	}

	public ListenableFuture<Quote> getQuote() {
		return asyncHttpClient.prepareGet("http://gturnquist-quoters.cfapps.io/api/random")
				.execute(new AsyncCompletionHandler<Quote>() {

					@Override
					public Quote onCompleted(Response response) throws Exception {
						return reader.read(response.getResponseBodyAsStream());
					}
				});
	}

	@Override
	public void destroy() throws Exception {
		asyncHttpClient.close();
	}

}
