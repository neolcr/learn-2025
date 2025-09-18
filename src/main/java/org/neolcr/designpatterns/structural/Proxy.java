package org.neolcr.designpatterns.structural;

/**
 * Proxy Design Pattern
 * The Proxy Pattern provides a surrogate or placeholder for another object to control access to it.
 * This can be useful for lazy initialization, access control, logging, etc.
 *
 */
public class Proxy {
    public static void main(String[] args) {
        Service service = new ServiceProxy();
        service.request(); // Output: ServiceProxy: delegating request. RealService: Handling request.
        service.request(); // Output: ServiceProxy: delegating request. RealService: Handling request.
    }
}

// Subject interface
interface Service {
    void request();
}

// Real Subject
class RealService implements Service {
    @Override
    public void request() {
        System.out.println("RealService: Handling request.");
    }
}

// Proxy
class ServiceProxy implements Service {
    private RealService realService;

    @Override
    public void request() {
        if (realService == null) {
            System.out.println("ServiceProxy: Initializing RealService.");
            realService = new RealService(); // Lazy initialization
        }
        System.out.println("ServiceProxy: delegating request.");
        realService.request();
    }
}

