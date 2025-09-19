package org.neolcr.theory;

/**
 * This class is a placeholder to discuss the history of interfaces in Java Development Kit (JDK).
 *
 * Interfaces were introduced in JDK 1.0 as a way to achieve abstraction and multiple inheritance in Java.
 * They allow developers to define methods that must be implemented by any class that chooses to implement the interface.
 *
 * Over the years, interfaces have evolved significantly:
 * - JDK 1.2 introduced the Collections Framework, which heavily relies on interfaces like List, Set, and Map.
 * - JDK 5 added generics, allowing interfaces to be parameterized with types, enhancing type safety.
 * - JDK 8 introduced default methods, enabling interfaces to have method implementations, which helped in evolving APIs without breaking existing implementations.
 * - JDK 8 also introduced static methods in interfaces, allowing utility and factory methods to live alongside the interface contract without requiring an implementation class.
 * - JDK 9 added private methods in interfaces, allowing code reuse within the interface itself.
 *
 * Interfaces play a crucial role in designing flexible and maintainable software systems, promoting loose coupling and adherence to the Dependency Inversion Principle.
 * They are widely used in various design patterns and architectural styles, including Hexagonal Architecture, as seen in the provided code snippets.
 *
 * Understanding the history and evolution of interfaces helps developers appreciate their significance and leverage their capabilities effectively in modern Java applications.
 */
public class HistoryOfInterfacesByJdk implements Jdk5, Jdk8, Jdk9 {
    public static void main(String[] args) {
        System.out.println("History of Interfaces in JDK");
        new HistoryOfInterfacesByJdk().generics("Test");
        Jdk8.staticMethod();
        new HistoryOfInterfacesByJdk().defaultMethod();
        new HistoryOfInterfacesByJdk().usePrivateMethod();

    }

    @Override
    public <T> void generics(T item) {
        System.out.println("Generic method called with: " + item);
    }
}

interface Jdk5 {
    <T> void generics(T item);
}

interface Jdk8 {
    default void defaultMethod() {
        System.out.println("Default method in interface (JDK 8+)");
    }

    static void staticMethod() {
        System.out.println("Static method in interface (JDK 8+)");
    }
}

interface Jdk9 {
    private void privateHelper() {
        System.out.println("Private method in interface (JDK 9+)");
    }

    default void usePrivateMethod() {
        privateHelper();
    }
}
