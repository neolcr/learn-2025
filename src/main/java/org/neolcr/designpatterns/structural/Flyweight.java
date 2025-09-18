package org.neolcr.designpatterns.structural;

/**
 * Flyweight Design Pattern
 * The Flyweight pattern is a structural design pattern that allows programs to support a large number of objects
 * efficiently by sharing common parts of state between multiple objects instead of keeping all data in each object.
 *
 * Key Concepts:
 * 1. Flyweight: The shared object that contains intrinsic state (state that is independent of the context).
 * 2. Extrinsic State: The state that is dependent on the context and must be supplied by the client.
 * 3. Flyweight Factory: A factory that creates and manages flyweight objects, ensuring that shared objects are reused.
 *
 *
 * Example Use Cases:
 * - Text editors, where characters can be shared to reduce memory usage.
 */
public class Flyweight {
    public static void main(String[] args) {
        // It is like singleton but for every different state
        FontFactory fontFactory = new FontFactory();

        Font arialFont1 = fontFactory.getFont("Arial");
        arialFont1.apply("Hello, World!");

        Font arialFont2 = fontFactory.getFont("Arial");
        arialFont2.apply("Flyweight Pattern");

        Font timesFont = fontFactory.getFont("Times New Roman");
        timesFont.apply("Design Patterns");
    }
}

interface Font {
    void apply(String text);
}

class ConcreteFont implements Font {

    private final String fontName;

    public ConcreteFont(String fontName) {
        this.fontName = fontName;
    }

    @Override
    public void apply(String text) {
        System.out.println("Applying font: " + fontName + " to text: " + text);
    }
}

class FontFactory {
    private final java.util.Map<String, Font> fontMap = new java.util.HashMap<>();

    public Font getFont(String fontName) {
        Font font = fontMap.get(fontName);
        if (font == null) {
            font = new ConcreteFont(fontName);
            fontMap.put(fontName, font);
            System.out.println("Creating new font: " + fontName);
        } else {
            System.out.println("Reusing existing font: " + fontName);
        }
        return font;
    }
}
