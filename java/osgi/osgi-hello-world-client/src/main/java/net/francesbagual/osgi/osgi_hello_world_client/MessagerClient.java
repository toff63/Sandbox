package net.francesbagual.osgi.osgi_hello_world_client;

import java.lang.reflect.Method;

import net.francesbagual.osgi.helloworld.api.Messager;


public class MessagerClient {

	private Messager messager;

	public MessagerClient(Messager messager){
		this.messager = messager;
	}

	public void printHelloWorld(){
		System.out.println(messager.hello("World"));

		System.out.println("Try to access Parrot by reflection");
		try{
			Class<?> parrotClass = Class.forName("net.francesbagual.osgi.helloworld.impl.Parrot");
			Object parrotInstance = parrotClass.newInstance();
			Method repeatMethod = parrotClass.getMethod("repeat");
			repeatMethod.invoke(parrotInstance);
		}catch(Throwable t){System.out.println(t.getMessage());}

	}
}
