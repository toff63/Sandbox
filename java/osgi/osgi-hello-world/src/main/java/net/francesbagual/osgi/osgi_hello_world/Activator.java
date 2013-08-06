package net.francesbagual.osgi.osgi_hello_world;

import net.francesbagual.osgi.helloworld.api.MessagerService;
import net.francesbagual.osgi.helloworld.impl.MessagerServiceImpl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private ServiceRegistration<MessagerService> serviceRegistration;
	
    public void start(BundleContext context) throws Exception {
    	serviceRegistration = context.registerService(MessagerService.class, 
    			                                      new MessagerServiceImpl(), 
    			                                      null);
    }

    public void stop(BundleContext context) throws Exception {
    	serviceRegistration.unregister();
    }

}

