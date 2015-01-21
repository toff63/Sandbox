package net.francesbagual.matandoif.polymorphism;

public class Bike implements Vehicule {

	@Override
	public boolean isEligible(String type) {
		return false;
	}

	@Override
	public Integer numberOfWheels() {
		// TODO Auto-generated method stub
		return null;
	}

}
