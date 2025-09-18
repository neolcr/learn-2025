package org.neolcr.ddd;

import java.util.List;

public abstract class AggregateRoot extends Entity {
    protected AggregateRoot(Long id) {
        super(id);
    }
    // Example: List of domain events
    private List<DomainEvent> domainEvents;
    // ...methods to add, remove, and get events...
}

