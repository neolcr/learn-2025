package org.neolcr.ddd;

public interface Repository<T extends AggregateRoot> {
    T findById(Long id);
    void save(T aggregate);
    void delete(T aggregate);
}

