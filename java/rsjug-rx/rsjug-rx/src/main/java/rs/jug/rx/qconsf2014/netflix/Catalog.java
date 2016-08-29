package rs.jug.rx.qconsf2014.netflix;

import rx.Observable;

public class Catalog {
	public Observable<String> videos (){
		return Observable.just("The Matrix", "The Matrix Reloaded", "The Matrix Revolution");
	}
}
