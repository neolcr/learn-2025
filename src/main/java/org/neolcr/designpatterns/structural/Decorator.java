package org.neolcr.designpatterns.structural;

/**
 * Decorator Design Pattern
 * The Decorator Pattern allows behavior to be added to an individual object, either statically or dynamically,
 * without affecting the behavior of other objects from the same class.
 */
public class Decorator {
    public static void main(String[] args) {
        Text plainText = new PlainText("Hello, World!");
        Text boldText = new BoldText(plainText);
        Text italicBoldText = new ItalicText(boldText);

        System.out.println(plainText.format());          // Output: Hello, World!
        System.out.println(boldText.format());           // Output: <b>Hello, World!</b>
        System.out.println(italicBoldText.format());     // Output: <i><b>Hello, World!</b></i>
    }
}

// Component
interface Text {
    String format();
}

// Concrete Component
class PlainText implements Text {
    private final String text;

    public PlainText(String text) {
        this.text = text;
    }

    @Override
    public String format() {
        return text;
    }
}

// Decorator
class BoldText implements Text {
    private final Text text;

    public BoldText(Text text) {
        this.text = text;
    }

    @Override
    public String format() {
        return "<b>" + text.format() + "</b>";
    }
}

// Another Decorator
class ItalicText implements Text {
    private final Text text;

    public ItalicText(Text text) {
        this.text = text;
    }

    @Override
    public String format() {
        return "<i>" + text.format() + "</i>";
    }
}