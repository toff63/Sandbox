package rs.jug.rx.composition.service;

import rs.jug.rx.composition.domain.Rating;
import rs.jug.rx.composition.domain.Serie;
import rs.jug.rx.composition.domain.SerieMetadata;

public class SerieDetails {
	public Serie serie;
	public Rating rating;
	public SerieMetadata metadata;
	
	public SerieDetails(Serie serie, Rating rating, SerieMetadata metadata) {
		super();
		this.serie = serie;
		this.rating = rating;
		this.metadata = metadata;
	}
	
	@Override
	public String toString() {
		return "SerieDetails [serie=" + serie + ", rating=" + rating + ", metadata=" + metadata + "]";
	}
	
	
}
