package org.neolcr.theory;

/**
 * Records in Java are a concise way to declare immutable data carrier types.
 * They automatically provide:
 *  - private final fields for each component
 *  - canonical constructor
 *  - accessors with the same name as components (no getX())
 *  - equals / hashCode / toString implementations based on state
 *
 * Lifecycle & JDK timeline:
 *  - JDK 14: Preview feature (first appearance)
 *  - JDK 15: Second preview (refinements)
 *  - JDK 16: Became a standard language feature (no longer preview)
 *
 * When to use:
 *  - Domain model value objects / DTOs that are immutable
 *  - Return types bundling several related values
 *  - As keys in maps / sets where structural equality matters
 *
 * When NOT to use:
 *  - Mutable entities with identity separate from their state
 *  - Types needing inheritance (records implicitly extend java.lang.Record and cannot extend other classes)
 *  - Complex behavioral classes where data is not the primary concern
 *
 * Customization:
 *  - You can add methods
 *  - You can override the canonical constructor (or use a compact constructor) for validation
 *  - You can define static members
 *  - You can override equals/hashCode/toString if really necessary (rare)
 */
public class Records {
    public static void main(String[] args) {
        // Using the record
        PersonRecord r = new PersonRecord("Alice", 30);
        System.out.println(r); // auto toString
        System.out.println("Name accessor: " + r.name());
        System.out.println("Age accessor:  " + r.age());
        System.out.println("Greeting:      " + r.greet());

        // Equivalent manual POJO usage
        PersonPojo p = new PersonPojo("Alice", 30);
        System.out.println(p); // custom toString
        System.out.println("Name getter:   " + p.getName());
        System.out.println("Age getter:    " + p.getAge());
        System.out.println("Equals comparison (record vs pojo by state not automatic): " + r.name().equals(p.getName()));

        // Demonstrate structural equality of records
        PersonRecord r2 = new PersonRecord("Alice", 30);
        System.out.println("r equals r2? " + r.equals(r2)); // true

        // Validation example (will throw IllegalArgumentException)
        try {
            new PersonRecord("", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation triggered: " + e.getMessage());
        }
    }
}

// ---------------- Traditional mutable / verbose POJO ----------------
class PersonPojo {
    private final String name;
    private final int age;

    PersonPojo(String name, int age) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name blank");
        if (age < 0) throw new IllegalArgumentException("age negative");
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override public String toString() { return "PersonPojo{" + name + "," + age + '}'; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonPojo that)) return false;
        return age == that.age && java.util.Objects.equals(name, that.name);
    }

    @Override public int hashCode() { return java.util.Objects.hash(name, age); }
}

// ---------------- Record variant (immutable data carrier) ----------------
record PersonRecord(String name, int age) {
    // Compact constructor for validation (parameters implicitly defined)
    PersonRecord {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name blank");
        if (age < 0) throw new IllegalArgumentException("age negative");
    }

    String greet() { return "Hi, I'm " + name + " and I'm " + age + " years old"; }

    // Example of a derived value method
    int ageInMonths() { return age * 12; }
}
