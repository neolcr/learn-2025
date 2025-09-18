package org.neolcr.designpatterns.structural;

/**
 * Bridge Design Pattern
 * The Bridge pattern is a structural design pattern that decouples an abstraction from its implementation,
 * allowing the two to vary independently. This is particularly useful when both the abstraction and the implementation
 * are expected to change frequently or when they need to be extended in different ways.
 */
public class Bridge {
    /**
     * In this example, we have a Shape abstraction that uses a Renderer implementor to render different shapes.
     * The Circle and Square classes are refined abstractions that extend the Shape class.
     * The ConsoleRenderer is a concrete implementor that implements the Renderer interface.
     * This allows us to change the rendering implementation without affecting the shape abstraction.
     */
    public static void main(String[] args) {
        Renderer consoleRenderer = new ConsoleRenderer();

        Shape circle = new Circle(consoleRenderer);
        circle.draw(); // Output: Rendering Circle on Console

        Shape square = new Square(consoleRenderer);
        square.draw(); // Output: Rendering Square on Console
    }
}


// Implementor
interface Renderer {
    void render(String shape);
}

// Concrete Implementor
class ConsoleRenderer implements Renderer {
    @Override
    public void render(String shape) {
        System.out.println("Rendering " + shape + " on Console");
    }
}

// Abstraction
abstract class Shape {
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();
}

// Refined Abstraction
class Circle extends Shape {
    public Circle(Renderer renderer) {
        super(renderer);
    }

    @Override
    public void draw() {
        renderer.render("Circle");
    }
}

class Square extends Shape {
    public Square(Renderer renderer) {
        super(renderer);
    }

    @Override
    public void draw() {
        renderer.render("Square");
    }
}