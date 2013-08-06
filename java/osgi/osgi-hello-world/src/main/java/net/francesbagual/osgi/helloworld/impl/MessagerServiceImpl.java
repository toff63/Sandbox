package net.francesbagual.osgi.helloworld.impl;

import net.francesbagual.osgi.helloworld.api.MessagerService;

public class MessagerServiceImpl implements MessagerService{

	private Parrot parrot = new Parrot();
	
	public String hello(String message){
		return parrot.repeat("Hello, " + message);
	}

}
