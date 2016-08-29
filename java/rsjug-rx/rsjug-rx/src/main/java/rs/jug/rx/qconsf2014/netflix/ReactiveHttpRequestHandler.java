package rs.jug.rx.qconsf2014.netflix;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.sse.ServerSentEvent;
import rs.jug.rx.qconsf2014.netflix.gateway.Rating;
import rs.jug.rx.qconsf2014.netflix.gateway.VideoMetadata;
import rs.jug.rx.qconsf2014.netflix.gateway.VideoServiceGateway;
import rx.Observable;

public class ReactiveHttpRequestHandler {
	private VideoServiceGateway videoServiceGateway = new VideoServiceGateway();
	public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
		// first request User object
		return getUser(request.getQueryParameters().get("userId")).flatMap(user -> {
			// then fetch personal catalog
			Observable<Map<String, Object>> catalog = getPersonalizedCatalog(user).flatMap(catalogList -> {
				return catalogList.videos().<Map<String, Object>>flatMap(video -> {
					Observable<Rating> rating = videoServiceGateway.rating(video);
					Observable<VideoMetadata> metadata = videoServiceGateway.metadata(video);
					return Observable.zip(rating, metadata, (r, m) -> {
						return combineVideoData(video, r, m);
					});
				});
			});
			// and fetch social data in parallel
			Observable<Map<String, Object>> social = getSocialData(user).map(s -> {
				return s.getDataAsMap();
			});
			// merge the results
			return Observable.merge(social, catalog);
		}).flatMap(data -> {
			// output as SSE as we get back the data (no waiting until all is done)
			return response.writeAndFlush(new ServerSentEvent(toByteBuffer(data)).content());
		});
	}
	
	private Map<String, Object>combineVideoData(String video, Rating rating, VideoMetadata metadata){
		Map<String, Object> videoDetails = new HashMap<>();
		videoDetails.put(video, new VideoDetails(video, rating, metadata));
		return videoDetails;
	}
	
	private ByteBuf toByteBuffer(Map<String, Object> data) {
		return Unpooled.copiedBuffer(data.toString() +  "\n", Charset.forName("UTF-8"));
	}
	
	private Observable<SocialData> getSocialData(String user) {
		return Observable.just(new SocialData());
	}

	private Observable<String> getUser(List<String> list){
		return Observable.just("frances_bagual");
	}
	
	private Observable<Catalog> getPersonalizedCatalog(String list){
		return Observable.just(new Catalog());
	}
}
