package net.francesbagual.matandoif.polymorphism;

import java.util.HashMap;
import java.util.Map;

public class KillingIfByPolymorphism {

	private final Map<String, Integer> vehiculeWheels = new HashMap<String, Integer>(){{
			put("car", 4);
			put("moto", 2);
			put("bike", 2);
			put("truck", 8);
	}};
	
	public Integer numberOfWheel(String typeOfVehicule) {
		if(vehiculeWheels.containsKey(typeOfVehicule)) 
			return vehiculeWheels.get(typeOfVehicule);
		throw new IllegalArgumentException("Unsupported type of vehicule: " + typeOfVehicule);
	}
}

