package org.neolcr.ddd;

import java.time.Instant;

public abstract class DomainEvent {
    private final Instant occurredOn = Instant.now();

    public Instant getOccurredOn() {
        return occurredOn;
    }
}

