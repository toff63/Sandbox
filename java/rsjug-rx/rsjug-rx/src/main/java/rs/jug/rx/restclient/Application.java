package rs.jug.rx.restclient;

import java.util.concurrent.ExecutionException;

import org.asynchttpclient.ListenableFuture;
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
	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Override
	public void run(String... args) throws Exception {
		runAsynchronouslyRx();
		runAsynchronously();
	}

	private void runAsynchronouslyRx() {
		ListenableFuture<Quote> f = restGateway.getQuote();
		Observable<Quote> quoteStream = Observable.from(f)
				                                  .doOnError(t -> handleErrors(t));
		quoteStream.subscribe(quote -> log.info(quote.toString()));
	}

	private void runAsynchronously() {
		try {
			ListenableFuture<Quote> f = restGateway.getQuote();
			log.info(f.get().toString());
		} catch (InterruptedException | ExecutionException e) {
			handleErrors(e);
		}
	}

	private void handleErrors(Throwable t){
		throw new RuntimeException("Failed to retrieve quote", t);
	}
}
