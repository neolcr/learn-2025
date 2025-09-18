package org.neolcr.solidpackage.solid;

/**
 * Single Responsibility Principle (SRP) states that a class should have only one reason to change,
 * meaning it should have only one job or responsibility.
 *
 * This principle helps to keep classes
 * focused and manageable, making the code easier to understand, maintain, and test.
 */
public class SingleResponsibilityPrinciple {
    public static void main(String[] args) {
        Invoice i = new Invoice(99.0);
        new InvoiceRepository().save(i);
        new InvoicePrinter().print(i);
    }

}

// Data (no behavior beyond holding state)
class Invoice {
    final double amount;
    Invoice(double amount) { this.amount = amount; }
}

// Responsibility #1: persistence
class InvoiceRepository {
    void save(Invoice i) {
        System.out.println("Saving invoice: " + i.amount);
    }
}

// Responsibility #2: presentation
class InvoicePrinter {
    void print(Invoice i) {
        System.out.println("Invoice amount: " + i.amount);
    }
}

