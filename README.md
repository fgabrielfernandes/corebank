# Core Bank API 🏦

API RESTful de um **Core Bancário** desenvolvida com foco em resiliência financeira, integridade de dados e isolamento de domínio através da **Arquitetura Hexagonal (Ports and Adapters)**.

---

## 🏛️ Arquitetura e Design

O projeto adota a Arquitetura Hexagonal para garantir que as regras de negócio permaneçam puras e independentes de frameworks externos, banco de dados ou interfaces de entrada.

- **Domínio Isolado**: Regras de saldo, limites e validações financeiras sem anotações de frameworks.
- **SOLID & Clean Code**: Responsabilidades bem definidas, uso de DTOs desacoplados e mapeamento manual explícito (`fromDomain` / `toDomain`).
- **Integridade Financeira**: Operações atômicas via `@Transactional` com suporte a Ledger imutável para auditoria.
- **Tratamento de Erros Profissional**: Handler global interceptando exceções de negócio e retornando respostas semânticas em JSON (`HTTP 400`).

---

## 🛠️ Tecnologias Utilizadas

| Categoria | Tecnologia |
| :--- | :--- |
| **Linguagem & Framework** | Java 17+, Spring Boot 3 |
| **Persistência** | Spring Data JPA, PostgreSQL |
| **Segurança** | Spring Security, JJWT (JSON Web Token) |
| **Documentação** | Swagger / OpenAPI 3 |
| **Utilitários & Build** | Lombok, Maven |

---

## 🚀 Endpoints da API

### Autenticação & Contas
- `POST /api/auth/login` - Autenticação de usuário e emissão de JWT.
- `POST /api/contas` - Criação e vinculação de contas bancárias.

### Transações Financeiras
- `POST /api/transacoes/deposito` - Depósito em conta ativa.
- `POST /api/transacoes/saque` - Saque com validação de saldo e limite de cheque especial.
- `POST /api/transacoes/transferencia` - Transferência atômica entre contas.
- `GET /api/transacoes/extrato/{contaId}` - Consulta do histórico imutável de movimentações.

---

## 📂 Estrutura de Pastas (Hexagonal)

```text
src/main/java/com/agenciabancaria/corebank/
├── application/         # Casos de Uso (ConsultarExtratoUseCase, etc.)
├── domain/              # Modelos, Exceções e Portas (Interfaces de Repositório)
├── infrastructure/      # Adaptadores JPA, Configurações de Segurança e JJWT
└── presentation/        # Controllers REST, DTOs e GlobalExceptionHandler