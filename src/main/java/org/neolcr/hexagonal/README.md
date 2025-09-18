# Hexagonal Architecture (Ports & Adapters) Example

This module demonstrates a minimal hexagonal (clean) architecture slice for an Account context.

Package layout (by intent):
account/
  domain/
    model/          -> Pure domain model (Account)
    port/
      in/           -> Driving (input) ports (CreateAccountUseCase)
      out/          -> Driven (output) ports (AccountRepository)
  application/
    service/        -> Use case implementations (CreateAccountService) implementing input ports
  adapter/
    in/
      cli/          -> Primary (driving) adapter (Console / CLI demo)
    out/
      persistence/  -> Secondary (driven) adapter (InMemoryAccountRepository)

Flow (Create Account):
CLI Adapter (driving) -> CreateAccountUseCase (port) -> CreateAccountService (use case) -> Account (domain) -> AccountRepository (port) -> InMemoryAccountRepository (adapter)

Key Principles:
- Domain model has no dependencies on frameworks or adapters.
- Use case (application service) depends only on ports + domain, never on concrete adapters.
- Adapters implement ports or invoke them; they depend inward (towards domain), never outward.
- Easy to replace adapters (e.g., swap InMemoryAccountRepository with JPA/DB) without touching domain or use case.

Next Ideas:
- Add query use case (GetAccountBalanceUseCase + port + adapter)
- Add validation / domain events
- Add another outbound port (e.g., AuditLogPort) with a logging adapter

Minimal; extend consciously to retain separation.

