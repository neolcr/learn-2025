package org.neolcr.theory;

/**
 * Generics in Java is a powerful feature that allows developers to create classes, interfaces, and methods with a placeholder
 * for the type of data they operate on. This enables code reusability, type safety, and flexibility.
 *
 * Key concepts of Generics include:
 *
 * 1. Type Parameters: Generics use type parameters (e.g., <T>,
 * E>, <K, V>) to define a class, interface, or method that can operate on different data types.
 *
 *
 * 2. Type Safety: Generics provide compile-time type checking, reducing the risk of
 * ClassCastException at runtime.
 *
 * 3. Code Reusability: With Generics, you can write a single class
 * or method that works with different data types, promoting code reuse.
 *
 * 4. Bounded Type Parameters: You can restrict the types that can be used as
 * type parameters using bounds (e.g., <T extends Number>).
 *
 * 5. Wildcards: Generics support wildcards (e.g., <?>, <?
 * extends T>, <? super T>) to allow for more flexible method parameters and return types.
 *
 * 6. Generic Methods: Methods can also be generic, allowing them to operate on different types without being tied to a specific class.

 */
public class Generics {
    public static void main(String[] args) {
        // Example usage of a generic class
        Box<Integer> intBox = new Box<>();
        intBox.setItem(123);
        System.out.println("Integer Box contains: " + intBox.getItem());

        Box<String> strBox = new Box<>();
        strBox.setItem("Hello Generics");
        System.out.println("String Box contains: " + strBox.getItem());

        // Example usage of a generic method
        String[] stringArray = {"A", "B", "C"};
        Integer[] intArray = {1, 2, 3};
        printArray(stringArray);
        printArray(intArray);
    }

    // Generic class
    static class Box<T> {
        private T item;

        public void setItem(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    }

    // Generic method
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
