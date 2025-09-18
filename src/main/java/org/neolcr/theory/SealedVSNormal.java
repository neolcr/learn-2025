package org.neolcr.theory;

public class SealedVSNormal {
    public static void main(String[] args) {
        // Normal class hierarchy: any code can extend Animal (if accessible)
        Animal a = new Dog();
        a = new Cat();

        // Sealed class hierarchy: only permitted types may extend Shape
        Shape s = new CircleShape();
        s = new RectangleShape();
        s = new FancyRectangle(); // allowed because RectangleShape is non-sealed
    }
}

// -------- Normal (unrestricted) hierarchy --------
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }
class Wolf extends Animal { } // freely added later

// -------- Sealed hierarchy (Java 17+) --------
// Only the classes listed in 'permits' can directly extend Shape.
sealed class Shape permits CircleShape, RectangleShape, OtherShape { }

// Final: cannot be subclassed further.
final class CircleShape extends Shape { }

// non-sealed: re-opens the hierarchy beneath it.
non-sealed class RectangleShape extends Shape { }
class FancyRectangle extends RectangleShape { } // allowed because parent is non-sealed

// Another final permitted subtype.
final class OtherShape extends Shape { }

// The following would NOT compile if uncommented because it's not in the permits list:
// class TriangleShape extends Shape { }
