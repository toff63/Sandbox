package net.francesbagual.osgi.osgi_hello_world_client;

import net.francesbagual.osgi.helloworld.api.Messager;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        new MessagerClient(new Messager()).printHelloWorld();
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
