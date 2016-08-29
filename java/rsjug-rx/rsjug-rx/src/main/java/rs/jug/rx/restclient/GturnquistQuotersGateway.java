package rs.jug.rx.restclient;

import java.util.Random;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.beans.factory.DisposableBean;


public class GturnquistQuotersGateway implements DisposableBean {

	private static CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();


	public GturnquistQuotersGateway() {
		httpclient.start();
	}

	public Future<HttpResponse> getQuote() {
		final HttpGet request1 = new HttpGet("http://gturnquist-quoters.cfapps.io/api/random");
		return httpclient.execute(request1, null);
	}

	@Override
	public void destroy() throws Exception {
		httpclient.close();
	}

}
