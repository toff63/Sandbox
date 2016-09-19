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

	private Map<String, Serie> seenFilmsByUser = new HashMap<>();
	private Map<String, Social> socialMetadataByUser = new HashMap<>();
	private Random random = new Random();
	
	public ServiceImpl() {
		seenFilmsByUser.put("francesbagual", new Serie("Stranger Things"));		
		seenFilmsByUser.put("rsjug", new Serie("Narcos"));		
		socialMetadataByUser.put("francesbagual", new Social("https://www.facebook.com/christophe.marchal63"));
		socialMetadataByUser.put("rsjug", new Social("https://www.facebook.com/groups/rsjug/"));
	}
	
	@Override
	public Observable<User> getUser(String userId) {
		return toObservableWithLatency(new User(userId));
	}

	@Override
	public Observable<Serie> seenSeries(User user) {
		return toObservableWithLatency(seenFilmsByUser.get(user.id));
	}

	@Override
	public Observable<Social> socialInformation(User user) {
		return toObservableWithLatency(socialMetadataByUser.get(user.id));
	}

	private <T> Observable<T> toObservableWithLatency(T o){
		return Observable.create(s -> {
			addLatency();
			s.onNext(o);
			s.onCompleted();
		});
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
