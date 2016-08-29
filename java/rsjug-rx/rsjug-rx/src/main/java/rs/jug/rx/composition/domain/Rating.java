package rs.jug.rx.composition.domain;

import java.util.Random;

public class Rating {

	Random random = new Random();
	private int numberOfStar = random.nextInt(5);
	public int getNumberOfStar() {
		return numberOfStar;
	}
	@Override
	public String toString() {
		return "Rating [numberOfStar=" + numberOfStar + "]";
	}
	
	
}