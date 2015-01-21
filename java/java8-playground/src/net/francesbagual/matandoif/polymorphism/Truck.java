package net.francesbagual.matandoif.polymorphism;

public class Truck implements Vehicule {

	@Override
	public boolean isEligible(String type) {
		return "truck".equals(type);
	}

	@Override
	public Integer numberOfWheels() {
		return 8;
	}

}

