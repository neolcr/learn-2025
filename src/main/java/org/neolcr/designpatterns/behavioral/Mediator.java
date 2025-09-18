package org.neolcr.designpatterns.behavioral;

/**
 * Mediator Design Pattern
 * The Mediator pattern defines an object that encapsulates how a set of objects interact.
 * This pattern promotes loose coupling by keeping objects from referring to each other explicitly,
 * and it lets you vary their interaction independently.
 * Key Components:
 * Mediator: An interface that defines the communication methods between Colleague objects.
 * ConcreteMediator: A class that implements the Mediator interface and coordinates communication between Colleague objects.
 * Colleague: An interface or abstract class that defines the common behavior for all Colleague objects.
 * ConcreteColleague: A class that implements the Colleague interface and communicates with other Colleague objects through the Mediator.
 *
 */
public class Mediator {
    public static void main(String[] args) {
        //Mediator controls communication between objects.
        //
        //UserA and UserB don’t talk to each other directly — they only know the Mediator.
        //
        //This reduces coupling: if we add more users, we don’t change existing ones.
        ChatMediator chatMediator = new ChatMediator();

        UserA userA = new UserA(chatMediator);
        UserB userB = new UserB(chatMediator);

        chatMediator.setUserA(userA);
        chatMediator.setUserB(userB);

        userA.send("Hello, UserB!");
        userB.send("Hi, UserA! How are you?");
    }
}

// Mediator Interface
interface SenderMediator {
    void sendMessage(String message, Colleague colleague);
}

// Colleague
abstract class Colleague {
    protected SenderMediator mediator;

    public Colleague(SenderMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive(String message);
}

// Concrete Colleagues
class UserA extends Colleague {
    public UserA(SenderMediator mediator) {
        super(mediator);
    }

    @Override
    public void receive(String message) {
        System.out.println("UserA received: " + message);
    }

    public void send(String message) {
        mediator.sendMessage(message, this);
    }
}

class UserB extends Colleague {
    public UserB(SenderMediator mediator) {
        super(mediator);
    }

    @Override
    public void receive(String message) {
        System.out.println("UserB received: " + message);
    }

    public void send(String message) {
        mediator.sendMessage(message, this);
    }
}

// Concrete Mediator
class ChatMediator implements SenderMediator {
    private UserA userA;
    private UserB userB;

    public void setUserA(UserA userA) {
        this.userA = userA;
    }

    public void setUserB(UserB userB) {
        this.userB = userB;
    }

    @Override
    public void sendMessage(String message, Colleague colleague) {
        if (colleague == userA) {
            userB.receive(message);
        } else if (colleague == userB) {
            userA.receive(message);
        }
    }
}
