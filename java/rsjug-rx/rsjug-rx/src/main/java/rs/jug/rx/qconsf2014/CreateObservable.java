package rs.jug.rx.qconsf2014;

import rx.Observable;
import rx.schedulers.Schedulers;

public class CreateObservable {

	public static void main(String[] args) {
		// One event
		Observable.create(subscriber -> {
			subscriber.onNext("Hello World!");
			subscriber.onCompleted();
		}).subscribe(System.out::println);
		// Two events
		Observable.create(subscriber -> {
			subscriber.onNext("Hello");
			subscriber.onNext("World!");
			subscriber.onCompleted();
		}).subscribe(System.out::println);
		
		// Equivalent to
		Observable.just("Hello", "World!")
		          .subscribe(System.out::println);
		
		// Handle errors
		Observable.just("Hello", "World!")
		          .subscribe(System.out::println,
		        		  Throwable::printStackTrace,
		        		  () -> System.out.println("Done"));
		
		// Propagate error
		Observable.create(subscriber -> {
			try{
				subscriber.onNext("Hello World!");
				subscriber.onNext(throwException());
				subscriber.onCompleted();
			} catch (Throwable e) {
				subscriber.onError(e);
			}
		})
		.onErrorResumeNext(e -> Observable.just("Fallback data"))
		.subscribe(System.out::println);
		
		// Add concurrency 
		Observable.create(subscriber -> {
				try{
					subscriber.onNext(getData());
					subscriber.onCompleted();
				} catch (Throwable e) {
					subscriber.onError(e);
				}
		}).subscribeOn(Schedulers.io())
		  .subscribe(System.out::println);
		
		// Add concurrency with transformation and error handling
		Observable.create(subscriber -> {
				try{
					subscriber.onNext(getData());
					subscriber.onCompleted();
				} catch (Throwable e) {
					subscriber.onError(e);
				}
		}).subscribeOn(Schedulers.io())
		  .map(data -> data + "_transformed")
		  .onErrorResumeNext(e -> Observable.just("Fallback data"))
		  .subscribe(System.out::println);
	}

	private static String getData() throws InterruptedException{
		return "Hello world";
	}
	private static String throwException(){
		throw new RuntimeException("Error");
	}
}
