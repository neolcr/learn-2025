package org.neolcr.designpatterns.behavioral;

/**
 * Iterator Design Pattern
 * The Iterator pattern provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
 * This pattern is particularly useful when you want to traverse a collection of objects without needing to understand the details of how the collection is implemented.
 * Key Components:
 * Iterator: An interface that defines methods for accessing and traversing elements.
 * ConcreteIterator: A class that implements the Iterator interface and keeps track of the current position in the traversal of the aggregate.
 * Aggregate: An interface that defines a method for creating an iterator.
 * ConcreteAggregate: A class that implements the Aggregate interface and returns an instance of the ConcreteIterator.
 * Client: The object that uses the iterator to traverse the aggregate.
 * Example Use Cases:
 * Traversing a list, set, or map without exposing the underlying data structure.
 */
public class Iterator {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        SimpleIntArray simpleIntArray = new SimpleIntArray(numbers);
        IntIterator iterator = simpleIntArray.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

interface IntIterator {
    boolean hasNext();
    int next();
}

class SimpleIntArray {
    private final int[] data;

    public SimpleIntArray(int[] data) {
        this.data = data;
    }

    public IntIterator createIterator() {
        return new SimpleIntArrayIterator(data);
    }
}

class SimpleIntArrayIterator implements IntIterator {
    private final int[] data;
    private int index = 0;

    SimpleIntArrayIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return index < data.length;
    }

    @Override
    public int next() {
        return data[index++];
    }
}