package rs.jug.rx.composition.service;

import rs.jug.rx.composition.domain.Rating;
import rs.jug.rx.composition.domain.Serie;
import rs.jug.rx.composition.domain.SerieMetadata;
import rs.jug.rx.composition.domain.Social;
import rs.jug.rx.composition.domain.User;
import rx.Observable;

public interface Service {

	Observable<User> getUser(String userId);
	Observable<Serie> seenSeries(User user);
	Observable<Rating> rating(Serie serie);
	Observable<SerieMetadata> serieMetadata(Serie serie);
	Observable<Social> socialInformation(User user);
}
