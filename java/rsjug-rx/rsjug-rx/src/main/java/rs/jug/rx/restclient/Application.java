package rs.jug.rx.restclient;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rs.jug.rx.restclient.contract.Quote;
import rx.Observable;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private GturnquistQuotersGateway restGateway ;

	@Autowired
	private JsonReader jsonReader;

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Override
	public void run(String... args) throws Exception {
		runAsynchronously();
		runAsynchronouslyRx();
		get5QuotesRx();
		get5QuotesAsync();
	}

	private void get5QuotesRx(){
		Observable.just(1, 2, 3, 4, 5)
		.flatMap(i -> Observable.from(restGateway.getQuote()))
		.map(response -> jsonReader.read(content(response)))
		.onErrorReturn(t -> new Quote())
		.map(Quote::toString)
		.subscribe(log::info);
	}

	private InputStream content(HttpResponse response){
		try {
			return response.getEntity().getContent();
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	private void get5QuotesAsync(){
		List<Future<HttpResponse>> futureQuotes = 
				IntStream.range(0, 5)
				.mapToObj(i -> restGateway.getQuote())
				.collect(Collectors.toList());
		for(Future<HttpResponse> futureQuote: futureQuotes){
			try {
				HttpResponse response = futureQuote.get();
				log.info(jsonReader.read(content(response)).toString());
			} catch (InterruptedException | ExecutionException e) {
				handleErrors(e);
			}
		}
	}

	private void runAsynchronouslyRx() {
		Observable.from(restGateway.getQuote())
		.map(response -> jsonReader.read(content(response)))
		.doOnError(t -> handleErrors(t))
		.map(Quote::toString)
		.subscribe(log::info);
	}

	private void runAsynchronously() {
		try {
			HttpResponse response = restGateway.getQuote().get();
			log.info(jsonReader.read(content(response)).toString());
		} catch (InterruptedException | ExecutionException e) {
			handleErrors(e);
		}
	}

	private void handleErrors(Throwable t){
		throw new RuntimeException("Failed to retrieve quote", t);
	}
}
