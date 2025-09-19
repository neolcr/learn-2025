package org.neolcr.hexagonal.account.adapter.out.persistence;

import org.neolcr.hexagonal.account.domain.model.Account;
import org.neolcr.hexagonal.account.domain.port.out.AccountRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<Long, Account> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Account save(Account account) {
        if (account.getId() == null) {
            account.setIdIfNull(sequence.getAndIncrement());
        }
        store.put(account.getId(), account);
        return account;
    }
}
