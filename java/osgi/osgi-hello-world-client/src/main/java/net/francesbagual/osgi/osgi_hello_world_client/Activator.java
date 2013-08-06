package net.francesbagual.osgi.osgi_hello_world_client;

import net.francesbagual.osgi.helloworld.api.MessagerService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator, ServiceListener {

	private BundleContext context;
	private ServiceReference<MessagerService> serviceReference;

	public void start(BundleContext context) throws Exception {
		this.context = context;
		registerListener(context);
		MessagerService messager = getService(context);
		new MessagerClient(messager).printHelloWorld();
	}

	public void stop(BundleContext context) throws Exception {
		if(serviceReference != null) context.ungetService(serviceReference);
		context.removeServiceListener(this);
	}

	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()) {
			case ServiceEvent.UNREGISTERING:{ 
				context.ungetService(event.getServiceReference());
				break;
			}
			case ServiceEvent.REGISTERED:{ 
				serviceReference = (ServiceReference<MessagerService>) event.getServiceReference();
				MessagerService messager = context.getService(serviceReference);
				new MessagerClient(messager).printHelloWorld();
				break;
			}
		}
	}
	

	private MessagerService getService(BundleContext context) {
		serviceReference = context.getServiceReference(MessagerService.class);
		MessagerService messager = context.getService(serviceReference);
		return messager;
	}

	private void registerListener(BundleContext context) {
		context.addServiceListener(this);
	}

}
