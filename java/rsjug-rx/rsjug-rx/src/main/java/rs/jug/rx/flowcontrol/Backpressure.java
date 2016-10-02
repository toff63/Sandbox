package rs.jug.rx.flowcontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class Backpressure {

	public static void main(String[] args) {
//		noBackPressureNeeded();
//		backPressureNeeded();
		blockingOperator();
//		temporalOperatorSample();
//		temporalOperatorThrottleFirst();
//		temporalOperatorDebounce();
//		temporalOperatorBuffer();
//		temporalOperatorBufferDebounce();
//		temporalOperatorWindowTime();
//		temporalOperatorWindowNumber();
	}



	public static void noBackPressureNeeded() {
		Observable.from(iterable()).take(1000).map(i -> i * 2).map(i -> i.toString()).subscribe(System.out::println);
	}

	public static void backPressureNeeded() {
		Observable.from(iterable()).take(1000).map(i -> i * 2).map(i -> i.toString())
				.observeOn(Schedulers.computation()).subscribe(System.out::println);
	}
	
	public static void blockingOperator() {
		Observable.from(iterable()).take(1000).map(i -> i * 2).map(i -> i.toString())
				.observeOn(Schedulers.computation())
				.toBlocking()
				.subscribe(System.out::println);
	}

	// Get the LATEST emitted event every 10 ms
	public static void temporalOperatorSample() {
		Observable.range(0, 10000000).sample(10, TimeUnit.MILLISECONDS).subscribe(System.out::println);
	}

	// Get the NEXT emitted event every 10 ms
	public static void temporalOperatorThrottleFirst() {
		Observable.range(0, 10000000).throttleFirst(10, TimeUnit.MILLISECONDS).subscribe(System.out::println);
	}

	// Only get an element if followd by 10 ms without new elements
	public static void temporalOperatorDebounce() {
		Observable.<Integer>create(subscriber -> {
			emitEvents(subscriber, 0, 5);    sleep(100);
			emitEvents(subscriber, 6, 20);   sleep(100);
			emitEvents(subscriber, 21, 25); sleep(100);
		}).subscribeOn(Schedulers.newThread())
		.debounce(10, TimeUnit.MILLISECONDS)
		.toBlocking()
		.subscribe(System.out::println);
	}
	
	// Only get an element if followd by 10 ms without new elements
	public static void temporalOperatorBuffer() {
		Observable.<Integer>create(subscriber -> {
			emitEvents(subscriber, 0, 5);    sleep(100);
			emitEvents(subscriber, 6, 20);   sleep(100);
			emitEvents(subscriber, 21, 25); sleep(100);
		}).subscribeOn(Schedulers.newThread())
		.buffer(10, TimeUnit.MILLISECONDS)
		.filter(list -> list.size()>0)
		.toBlocking()
		.subscribe(System.out::println);
	}
	
	private static void temporalOperatorBufferDebounce() {
		// Broadcast events and accept subscribe/unsubscribe
		Observable<Integer> intermittentBursts = 
				Observable.<Integer>create(subscriber -> {
			emitEvents(subscriber, 0, 5);    sleep(100);
			emitEvents(subscriber, 6, 20);   sleep(100);
			emitEvents(subscriber, 21, 25); sleep(100);
		}).subscribeOn(Schedulers.newThread())
		.publish().refCount();
		Observable<Integer> debounced = intermittentBursts.debounce(10, TimeUnit.MILLISECONDS);
		Observable<List<Integer>> buffered = intermittentBursts.buffer(debounced);
		buffered
		.toBlocking()
		.subscribe(System.out::println);		
	}
	
	
	// Buffer item during 10ms before returning them as a list
	public static void temporalOperatorWindowTime() {
		Observable.range(0, 10000000)
		.window(50, TimeUnit.MILLISECONDS)
		.flatMap(list -> list.count())
		.subscribe(System.out::println);
	}
	
	// Buffer x items before returning them as a list
	public static void temporalOperatorWindowNumber() {
		Observable.range(0, 10000000)
		.window(50000)
		.flatMap(list -> list.count())
		.subscribe(System.out::println);
	}

	private static void emitEvents(Subscriber<? super Integer> subscriber, int start, int end) {
		for (int i = start; i < end + 1; i++)
			subscriber.onNext(i);
	}

	public static void sleep(int timeMilliseconds) {
		try {
			Thread.sleep(timeMilliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Iterable<Integer> iterable() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 100000; i++)
			list.add(i);
		Iterable<Integer> iterable = list;
		return iterable;
	}
}
