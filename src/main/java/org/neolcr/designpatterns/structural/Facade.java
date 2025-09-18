package org.neolcr.designpatterns.structural;

/**
 * Facade Design Pattern
 * The Facade pattern provides a simplified interface to a complex subsystem.
 * It defines a higher-level interface that makes the subsystem easier to use.
 */
public class Facade {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startComputer();
        computer.stopComputer();
    }
}


// subsystem classes
class CPU {
    public void start() {
        System.out.println("CPU started.");
    }

    public void stop() {
        System.out.println("CPU stopped.");
    }
}

class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Loading data into memory at position: " + position);
    }
}

class Disk {
    public byte[] read(long lba, int size) {
        System.out.println("Reading " + size + " bytes from disk at LBA: " + lba);
        return new byte[size]; // Simulate reading data
    }
}

// Facade
class Computer {
    private final CPU cpu;
    private final Memory memory;
    private final Disk disk;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.disk = new Disk();
    }

    public void startComputer() {
        cpu.start();
        memory.load(0, disk.read(0, 1024));
        System.out.println("Computer started.");
    }

    public void stopComputer() {
        cpu.stop();
        System.out.println("Computer stopped.");
    }
}