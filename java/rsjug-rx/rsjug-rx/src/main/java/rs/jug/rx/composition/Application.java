package rs.jug.rx.composition;

import rs.jug.rx.composition.domain.Rating;
import rs.jug.rx.composition.domain.SerieMetadata;
import rs.jug.rx.composition.domain.Social;
import rs.jug.rx.composition.domain.User;
import rs.jug.rx.composition.service.SerieDetails;
import rs.jug.rx.composition.service.Service;
import rs.jug.rx.composition.service.ServiceImpl;
import rx.Observable;
import rx.schedulers.Schedulers;

public class Application {

	public static void main(String[] args) {
		Service service = new ServiceImpl();
		series(service);
		serieDetailsAndSocial(service);
	}

	private static void series(Service service) {
		Observable.just("francesbagual", "rsjug")
		.flatMap(service::getUser)
		.flatMap(service::seenSeries)	
		.subscribe(System.out::println);
	}

	private static void serieDetailsAndSocial(Service service) {
		Observable.just("francesbagual", "rsjug")
		.flatMap(service::getUser)
		.flatMap(user -> {
			Observable<SerieDetails> series = serieDetails(user, service);
			Observable<Social> socialInformation= service.socialInformation(user);
			return Observable.merge(socialInformation, series);
		})
		.observeOn(Schedulers.io())
		.subscribe(System.out::println);
	}
	
	private static Observable<SerieDetails> serieDetails(User user, Service service) {
		return service.seenSeries(user).flatMap(serie -> {
			Observable<Rating> rating = service.rating(serie);
			Observable<SerieMetadata> metadata = service.serieMetadata(serie);
			return Observable.zip(rating, metadata, (r, m) -> new SerieDetails(serie, r, m));
		});
	}
}
