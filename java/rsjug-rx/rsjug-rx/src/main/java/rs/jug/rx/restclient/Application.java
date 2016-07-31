package rs.jug.rx.restclient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
		get5QuotesRx();
	}

	private void get5QuotesRx(){
		List<Observable<Quote>> observables = 
			IntStream.range(0, 5)
			         .mapToObj(i -> Observable.from(restGateway.getQuote()))
			         .collect(Collectors.toList());
		Observable.from(observables)
		          .doOnError(t -> handleErrors(t))
		          .subscribe(quote -> log.info(quote.toString()));
	}
	
	private void get5QuotesAsync(){
		List<ListenableFuture<Quote>> futureQuotes = 
			IntStream.range(0, 5)
			         .mapToObj(i -> restGateway.getQuote())
			         .collect(Collectors.toList());
		for(ListenableFuture<Quote> futureQuote: futureQuotes){
			try {
				log.info(futureQuote.get().toString());
			} catch (InterruptedException | ExecutionException e) {
				handleErrors(e);
			}
		}
	}
	
	private void runAsynchronouslyRx() {
		ListenableFuture<Quote> f = restGateway.getQuote();
		Observable.from(f)
                  .doOnError(t -> handleErrors(t))
                  .subscribe(quote -> log.info(quote.toString()));
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
