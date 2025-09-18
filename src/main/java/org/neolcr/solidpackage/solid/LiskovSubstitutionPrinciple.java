package org.neolcr.solidpackage.solid;

/**
 * Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of a subclass
 * without affecting the correctness of the program. In other words, subclasses should extend the behavior of the superclass
 * without changing its original functionality.
 */
public class LiskovSubstitutionPrinciple {
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        Bird penguin = new Penguin();

        makeBirdFly(sparrow); // Works fine
        makeBirdFly(penguin); // Works fine, but penguins can't actually fly
    }

    public static void makeBirdFly(Bird bird) {
        bird.fly();
    }
}

// Base class
abstract class Bird {
    public abstract void fly();
}

// Subclass that adheres to LSP
class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow is flying.");
    }
}
// Subclass that violates LSP
class Penguin extends Bird {
    @Override
    public void fly() {
        // Penguins can't fly, so this method does nothing or throws an exception
        System.out.println("Penguins can't fly.");
    }
}