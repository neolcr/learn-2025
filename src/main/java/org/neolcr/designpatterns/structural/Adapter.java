package org.neolcr.designpatterns.structural;

/**
 * Adapter Design Pattern
 * The Adapter pattern allows incompatible interfaces to work together.
 * It acts as a bridge between two incompatible interfaces.
 */
public class Adapter {
    /**
     * OldPrinter is the legacy interface.
     * OldPrinterImpl is the legacy implementation.
     * NewPrinter is the new interface expected by clients.
     * PrinterAdapter implements NewPrinter and internally uses an OldPrinter to adapt calls from the new interface to the old one.
     * In main, the client uses NewPrinter but can work with an OldPrinterImpl via the adapter.
     */
    public static void main(String[] args) {
        NewPrinter newPrinter = new PrinterAdapter(new OldPrinterImpl());
        newPrinter.print("Hello, Adapter Pattern!");
    }
}

interface NewPrinter {
    void print(String text);
}

interface OldPrinter {
    void oldPrint(String text);
}

class OldPrinterImpl implements OldPrinter {
    @Override
    public void oldPrint(String text) {
        System.out.println("OldPrinter: " + text);
    }
}

class PrinterAdapter implements NewPrinter {
    private final OldPrinter oldPrinter;

    public PrinterAdapter(OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void print(String text) {
        oldPrinter.oldPrint(text);
    }
}
