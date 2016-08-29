package rs.jug.rx.composition.domain;

public class Social {

	public String facebookUrl;

	
	
	public Social(String facebookUrl) {
		super();
		this.facebookUrl = facebookUrl;
	}



	@Override
	public String toString() {
		return "Social [facebookUrl=" + facebookUrl + "]";
	}
	
	
}
