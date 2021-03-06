package rs.jug.rx.composition;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.sse.ServerSentEvent;
import rs.jug.rx.composition.domain.Rating;
import rs.jug.rx.composition.domain.SerieMetadata;
import rs.jug.rx.composition.domain.Social;
import rs.jug.rx.composition.domain.User;
import rs.jug.rx.composition.service.SerieDetails;
import rs.jug.rx.composition.service.Service;
import rs.jug.rx.composition.service.ServiceImpl;
import rx.Observable;
import rx.schedulers.Schedulers;

public class SeriesHttpHandler {
	Service service = new ServiceImpl();

	public Observable<Void> handle(
			HttpServerRequest<ByteBuf> request, 
			HttpServerResponse<ByteBuf> response) {
		return serieDetailsAndSocialAsync(userId(request))
				.flatMap(data -> response.writeAndFlush(serverSideEvent(data)));
	}

	private ByteBuf serverSideEvent(Object data) {
		return new ServerSentEvent(toByteBuffer(data)).content();
	}

	private ByteBuf toByteBuffer(Object data) {
		return Unpooled.copiedBuffer(data.toString() + "\n", Charset.forName("UTF-8"));
	}

	private Observable<String> userId(HttpServerRequest<ByteBuf> request) {
		return Observable.from(request.getQueryParameters().get("userId"));
	}
	
	private Observable<Object> serieDetailsAndSocialAsync(Observable<String> userIds) {
		return userIds.flatMap(service::getUser).flatMap(user -> {
			Observable<SerieDetails> series = serieDetails(user).subscribeOn(Schedulers.io());
			Observable<Social> socialInformation = service.socialInformation(user);
			return Observable.merge(socialInformation, series).subscribeOn(Schedulers.io());
		});
	}

	private Observable<Object> serieDetailsAndSocial(Observable<String> userIds) {
		return userIds.flatMap(service::getUser).flatMap(user -> {
			Observable<SerieDetails> series = serieDetails(user);
			Observable<Social> socialInformation = service.socialInformation(user);
			return Observable.merge(socialInformation, series);
		});
	}

	private Observable<SerieDetails> serieDetails(User user) {
		return service.seenSeries(user).flatMap(serie -> {
			Observable<Rating> rating = service.rating(serie);
			Observable<SerieMetadata> metadata = service.serieMetadata(serie);
			return Observable.zip(rating, metadata, (r, m) -> new SerieDetails(serie, r, m));
		});
	}
}
