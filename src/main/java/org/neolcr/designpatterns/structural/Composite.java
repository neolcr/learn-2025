package org.neolcr.designpatterns.structural;

/**
 * Composite Design Pattern
 *
 * The Composite Pattern is a structural design pattern that allows you to compose objects into tree structures
 * to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.
 *
 * Key Concepts:
 * 1. Component: An interface or abstract class that defines the common operations for both leaf and composite objects.
 * 2. Leaf: A class that represents the end objects in a composition. A leaf cannot have any children.
 * 3. Composite: A class that represents a group of components. It implements methods to manage its children and to perform operations on them.
 *
 * Example Use Cases:
 * - File systems, where files and directories can be treated uniformly.
 * */

public class Composite {
    public static void main(String[] args) {
        FileSystemItem file1 = new File("file1.txt");
        FileSystemItem file2 = new File("file2.txt");
        Directory dir1 = new Directory("dir1");
        dir1.addItem(file1);
        dir1.addItem(file2);

        FileSystemItem file3 = new File("file3.txt");
        Directory rootDir = new Directory("root");
        rootDir.addItem(dir1);
        rootDir.addItem(file3);

        rootDir.display();
    }
}

// Component
interface FileSystemItem {
    void display();
}

// Leaf
class File implements FileSystemItem {
    private final String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("File: " + name);
    }
}

// Composite
class Directory implements FileSystemItem {
    private final String name;
    private final java.util.List<FileSystemItem> items = new java.util.ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addItem(FileSystemItem item) {
        items.add(item);
    }

    public void removeItem(FileSystemItem item) {
        items.remove(item);
    }

    @Override
    public void display() {
        System.out.println("Directory: " + name);
        for (FileSystemItem item : items) {
            item.display();
        }
    }
}