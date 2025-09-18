package org.neolcr.solidpackage.solid;

/**
 * Interface Segregation Principle (ISP) states that no client should be forced to depend on methods it does not use.
 * This principle encourages the creation of smaller, more specific interfaces rather than large, general-purpose ones.
 * By adhering to ISP, we can reduce the impact of changes and improve the maintainability of the code.
 */
public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {
        // Using a printer that only supports printing
        Printer printer = new Printer();
        printer.print("Hello, ISP!");

        // Using a multi-function printer that supports both printing and scanning
        MultiFunctionPrinter multiFunctionPrinter = new MultiFunctionPrinter();
        multiFunctionPrinter.print("Hello, ISP with MFP!");
        multiFunctionPrinter.scan("Document to scan");
    }
}

// Segregated interfaces
interface IPrinter {
    void print(String document);
}

interface IScanner {
    void scan(String document);
}

// Printer class implements only the IPrinter interface

class Printer implements IPrinter {
    @Override
    public void print(String document) {
        System.out.println("Printing: " + document);
    }
}

// MultiFunctionPrinter class implements both IPrinter and IScanner interfaces
class MultiFunctionPrinter implements IPrinter, IScanner {
    @Override
    public void print(String document) {
        System.out.println("MFP Printing: " + document);
    }

    @Override
    public void scan(String document) {
        System.out.println("MFP Scanning: " + document);
    }
}