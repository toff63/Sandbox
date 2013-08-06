package net.francesbagual.osgi.helloworld.api;

import net.francesbagual.osgi.helloworld.impl.Parrot;

public class Messager {

	private Parrot parrot = new Parrot();
	
	public String hello(String message){
		return parrot.repeat("Hello, " + message);
	}
}
