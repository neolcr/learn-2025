package org.neolcr.solidpackage.solid;

/**
 * Open/Closed Principle (OCP) states that software entities (classes, modules, functions, etc.) should be open for extension
 * but closed for modification.
 *
 * This means that we should be able to add new functionality to a system without changing
 * existing code, which helps to prevent bugs and maintain stability.
 */
public class OpenClosedPrinciple {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        System.out.println("Area of Circle: " + AreaCalculator.calculateArea(circle));
        System.out.println("Area of Rectangle: " + AreaCalculator.calculateArea(rectangle));
    }
}

// Shape interface
interface Shape {
    double area();
}

// Circle class implements Shape interface
class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// Rectangle class implements Shape interface
class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

// AreaCalculator class that works with Shape interface
class AreaCalculator {
    public static double calculateArea(Shape shape) {
        return shape.area();
    }
}

// OCP violation: must edit this class for every new Shape
//public class _AreaCalculator {
//    public static double area(Shape shape) {
//        if (shape instanceof Circle c) {
//            return Math.PI * c.getRadius() * c.getRadius();
//        } else if (shape instanceof Rectangle r) {
//            return r.getWidth() * r.getHeight();
//        }
//        throw new IllegalArgumentException("Unsupported shape type: " + shape.getClass());
//    }
//}
