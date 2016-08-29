package rs.jug.rx.qconsf2014.netflix;

import rs.jug.rx.qconsf2014.netflix.gateway.Rating;
import rs.jug.rx.qconsf2014.netflix.gateway.VideoMetadata;

public class VideoDetails {

	public String video;
	public Rating rating;
	public VideoMetadata metadata;
	
	public VideoDetails(String video, Rating rating, VideoMetadata metadata) {
		this.video = video;
		this.rating = rating;
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "VideoDetails [video=" + video + ", rating=" + rating + ", metadata=" + metadata + "]";
	}
	
	
}
