package rs.jug.rx.basic;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;

public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String args[]) {
		runJava();
		runRx();
	}

	public static void runRx(){
		Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
		          .filter(i -> i % 2 == 0)
		          .take(2)
		          .subscribe(i -> log.info(i.toString()));
	}
	
	public static void runJava(){
		Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
		      .filter(i -> i % 2 == 0)
		      .limit(2)
		      .forEach(i -> log.info(i.toString()));
	}

}
