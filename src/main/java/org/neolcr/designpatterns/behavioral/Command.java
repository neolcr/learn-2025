package org.neolcr.designpatterns.behavioral;

/**
 * Command Pattern
 * The Command Pattern is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request.
 * This transformation lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.
 *
 *  Key Components:
 * Command: An interface or abstract class that declares a method for executing a command.
 * ConcreteCommand: A class that implements the Command interface and defines the binding between a Receiver object and an action.
 * TODO Receiver: The object that knows how to perform the operations associated with the command.
 * Invoker: The object that holds and invokes the command.
 * Client: The object that creates a ConcreteCommand and sets its receiver.
 *
 */
public class Command {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();

        ICommand helloCommand = new HelloCommand("Alice");
        ICommand goodbyeCommand = new GoodbyeCommand("Bob");

        invoker.setCommand(helloCommand);
        invoker.executeCommand(); // Output: Hello, Alice!

        invoker.setCommand(goodbyeCommand);
        invoker.executeCommand(); // Output: Goodbye, Bob!
    }
}

// Command Interface
interface ICommand {
    void execute();
}

// Concrete Command for saying Hello
class HelloCommand implements ICommand {
    private final String name;

    public HelloCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Hello, " + name + "!");
    }
}

// Concrete Command for saying Goodbye
class GoodbyeCommand implements ICommand {
    private final String name;

    public GoodbyeCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        System.out.println("Goodbye, " + name + "!");
    }
}

class Invoker {
    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
        }
    }
}
