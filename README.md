# Learn 2025 Java/Kotlin Playground

A minimal educational sandbox demonstrating:
- Domain-Driven Design (DDD) building blocks
- Classic GoF design patterns (structural, behavioral, creational placeholders)
- SOLID vs non-SOLID examples
- Simple Maven project layout

## 1. Project Structure
```
root
 ├─ pom.xml
 ├─ src/main/java/org/neolcr
 │   ├─ Main.java
 │   ├─ ddd/                (DDD building blocks + example)
 │   ├─ designpatterns/     (patterns grouped by category)
 │   └─ solidpackage/       (solid & nosolid comparisons)
 └─ src/test/java           (tests placeholder)
```

## 2. DDD Components (package: org.neolcr.ddd)
| Component | Purpose | Implementation Notes |
|-----------|---------|----------------------|
| Entity | Object with identity | Abstract base with id + equality by id |
| ValueObject | Immutable value semantics | Equality by value field |
| AggregateRoot | Consistency boundary | Extends Entity; placeholder for domain events list |
| Repository | Persistence abstraction | Generic interface (find, save, delete) |
| DomainService | Domain logic not natural in Entity/VO | Interface placeholder |
| ApplicationService | Orchestrates use cases | Interface placeholder |
| DomainEvent | Captures something that happened | Timestamped abstract base |
| DDDExample | Demonstrates interaction (expand as needed) | Educational only |

Extension idea: Implement concrete aggregate (e.g., Order), value objects (Money, ProductCode), repository adapter (in-memory), and publish DomainEvents via a simple dispatcher.

## 3. Design Patterns (package: org.neolcr.designpatterns)
Implemented examples (non-exhaustive list based on compiled classes):
- Structural: Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy
- Behavioral: Chain of Responsibility, Command, Iterator, Mediator
(Placeholders for additional patterns can be added under their category folders.)

Each example aims to keep classes small and focused so the intent of the pattern is visible.

## 4. SOLID Examples (package: org.neolcr.solidpackage)
- solid/: Illustrations of principles applied
- nosolid/: Contrasting anti-examples (e.g., missing abstractions, tight coupling)

Suggested learning flow: read nosolid version first, list issues, then compare with solid version.

## 5. Build & Run
Requirements:
- JDK 17+ (adjust if pom.xml differs)
- Maven 3.8+

Commands:
```
mvn clean package
java -cp target/learn-2025-1.0-SNAPSHOT.jar org.neolcr.Main
```
(Adjust final artifact name if the pom version changes.)

## 6. Adding a Concrete DDD Example (Quick Recipe)
1. Create Value Objects (immutable, validate in constructor)
2. Create Entity / AggregateRoot subclass
3. Add domain methods that enforce invariants internally
4. Record DomainEvents inside aggregate (collect, then flush after save)
5. Implement Repository (start with in-memory Map<Long, Aggregate>)
6. Create an ApplicationService that:
   - Loads aggregate via repository
   - Invokes domain method(s)
   - Persists changes
   - Publishes collected DomainEvents
7. Add tests around invariants and event emission

## 7. Domain Events Handling (Suggested Enhancement)
Add to AggregateRoot:
- List<DomainEvent> domainEvents
- methods: addDomainEvent(e), pullDomainEvents()
Create simple EventDispatcher interface and InMemoryEventBus implementation.

## 8. Coding Guidelines
- Keep constructors validating invariants early
- Favor Value Objects for ubiquitous language terms
- Keep infrastructure concerns (persistence, serialization) out of entities
- One responsibility per class (SOLID SRP alignment)

## 9. Suggested Next Steps
- Add unit tests (JUnit + AssertJ)
- Add logging (SLF4J + simple backend)
- Implement a concrete aggregate (Order) + events (OrderPlaced)
- Introduce a simple event bus
- Optionally add persistence layer (H2 + JPA) behind repository

## 10. License
Add a LICENSE file (e.g., MIT) if intending to share publicly.

## 11. Disclaimer
Educational scaffolding only; not production-hardened.

---
Concise, extensible foundation for practicing DDD, design patterns, and SOLID.

