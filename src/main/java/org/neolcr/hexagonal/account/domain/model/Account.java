package org.neolcr.hexagonal.account.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private Long id; // assigned by repository (identity)
    private final String ownerName;
    private BigDecimal balance;

    public Account(Long id, String ownerName, BigDecimal balance) {
        if (ownerName == null || ownerName.isBlank()) throw new IllegalArgumentException("ownerName blank");
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("balance negative");
        this.id = id; // may be null before persistence
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public static Account newAccount(String ownerName, BigDecimal initialBalance) {
        return new Account(null, ownerName, initialBalance == null ? BigDecimal.ZERO : initialBalance);
    }

    public Long getId() { return id; }
    public String getOwnerName() { return ownerName; }
    public BigDecimal getBalance() { return balance; }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("amount <= 0");
        balance = balance.add(amount);
    }

    public void setIdIfNull(Long id) {
        if (this.id == null) this.id = id; // only first assignment
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}

