package org.neolcr.hexagonal.account.domain.port.in;

import java.math.BigDecimal;

public interface CreateAccountUseCase {
    Long createAccount(String ownerName, BigDecimal initialBalance);
}

