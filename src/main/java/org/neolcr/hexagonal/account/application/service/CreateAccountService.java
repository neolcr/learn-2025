package org.neolcr.hexagonal.account.application.service;

import org.neolcr.hexagonal.account.domain.model.Account;
import org.neolcr.hexagonal.account.domain.port.in.CreateAccountUseCase;
import org.neolcr.hexagonal.account.domain.port.out.AccountRepository;

import java.math.BigDecimal;

public class CreateAccountService implements CreateAccountUseCase {

    private final AccountRepository repository;

    public CreateAccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long createAccount(String ownerName, BigDecimal initialBalance) {
        Account account = Account.newAccount(ownerName, initialBalance);
        Account persisted = repository.save(account);
        return persisted.getId();
    }
}

