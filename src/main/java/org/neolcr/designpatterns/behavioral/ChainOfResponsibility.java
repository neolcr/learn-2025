package org.neolcr.designpatterns.behavioral;

/**
 * Chain of Responsibility Design Pattern
 * The Chain of Responsibility pattern is a behavioral design pattern that allows an object to pass a request along a
 * chain of potential handlers until one of them handles the request. This pattern decouples the sender of the request
 * from its receiver, allowing multiple objects to handle the request without the sender needing to know which object will ultimately process it.
 * In this pattern, each handler in the chain has a reference to the next handler. When a request is received,
 * the handler can either process the request or pass it to the next handler in the chain.
 * This continues until a handler processes the request or the end of the chain is reached.
 * This pattern is useful in scenarios where multiple objects can handle a request, and the specific handler is
 * not known in advance. It promotes flexibility and extensibility, as new handlers can be added to the chain without modifying existing code.
 *
 * Example use cases include event handling systems, logging frameworks, and request processing in web servers.
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        // Create handlers
        BaseHandler evenHandler = new EvenHandler();
        BaseHandler oddHandler = new OddHandler();

        // Set up the chain
        evenHandler.setNext(oddHandler);

        // Test the chain with different numbers
        int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 11, 15};
        for (int number : numbers) {
            evenHandler.handle(number);
        }
    }
}

// Handler interface
interface Handler {
    void setNext(BaseHandler handler);
    void handle(int number);
}

// Base Handler
abstract class BaseHandler implements Handler {

    protected BaseHandler next;

    @Override
    public void setNext(BaseHandler next) {
        this.next = next;
    }

}

// Concrete Handlers
class EvenHandler extends BaseHandler {

    @Override
    public void handle(int number) {
        if (number % 2 == 0) {
            System.out.println("EvenHandler handled: " + number);
        } else if (next != null) {
            next.handle(number);
        }
    }
}

class OddHandler extends BaseHandler {

    @Override
    public void handle(int number) {
        if (number % 2 != 0) {
            System.out.println("OddHandler handled: " + number);
        } else if (next != null) {
            next.handle(number);
        }
    }
}
