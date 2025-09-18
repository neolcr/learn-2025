package org.neolcr.hexagonal.account.domain.port.out;

import org.neolcr.hexagonal.account.domain.model.Account;

public interface AccountRepository {
    Account save(Account account);
}

