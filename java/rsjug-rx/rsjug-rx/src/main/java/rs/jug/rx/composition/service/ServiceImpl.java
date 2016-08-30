package rs.jug.rx.composition.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import rs.jug.rx.composition.domain.Rating;
import rs.jug.rx.composition.domain.Serie;
import rs.jug.rx.composition.domain.SerieMetadata;
import rs.jug.rx.composition.domain.Social;
import rs.jug.rx.composition.domain.User;
import rx.Observable;

public class ServiceImpl implements Service {

	private Map<String, Observable<Serie>> seenFilmsByUser = new HashMap<>();
	private Map<String, Observable<Social>> socialMetadataByUser = new HashMap<>();
	private Random random = new Random();
	
	public ServiceImpl() {
		seenFilmsByUser.put("francesbagual", Observable.just(new Serie("Stranger Things")));		
		seenFilmsByUser.put("rsjug", Observable.just(new Serie("Narcos")));		
		socialMetadataByUser.put("francesbagual", Observable.just(new Social("https://www.facebook.com/christophe.marchal63")));
		socialMetadataByUser.put("rsjug", Observable.just(new Social("https://www.facebook.com/groups/rsjug/")));
	}
	
	@Override
	public Observable<User> getUser(String userId) {
		addLatency();
		return Observable.just(new User(userId));
	}

	@Override
	public Observable<Serie> seenSeries(User user) {
		addLatency();
		return seenFilmsByUser.get(user.id);
	}

	@Override
	public Observable<Social> socialInformation(User user) {
		addLatency();
		return socialMetadataByUser.get(user.id);
	}

	@Override
	public Observable<Rating> rating(Serie serie) {
		addLatency();
		return Observable.just(new Rating());
	}

	@Override
	public Observable<SerieMetadata> serieMetadata(Serie serie) {
		addLatency();
		return Observable.just(new SerieMetadata());
	}
	
	private void addLatency(){
		int latency = random.nextInt(1000);
		try {
			Thread.sleep(latency);
		} catch (InterruptedException e) {
			// Stop adding latency
		}
	}

}
