package rs.jug.rx.qconsf2014.netflix.gateway;

import java.util.Random;

import rx.Observable;

public class VideoServiceGateway {
	Random random = new Random();
	public Observable<Rating> rating(String video) {
		addLatency();
		return Observable.just(new Rating());
	}
	public Observable<VideoMetadata> metadata(String video) {
		addLatency();
		return Observable.just(new VideoMetadata());
	}
	
	private void addLatency(){
		int latency = random.nextInt(5000);
		try {
			Thread.sleep(latency);
		} catch (InterruptedException e) {
			// Stop adding latency
		}
	}
}
