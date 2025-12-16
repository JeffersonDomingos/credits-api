# 💳 Credits API

API RESTful desenvolvida em **Spring Boot** para consulta de créditos constituídos, permitindo buscas por **número da NFS-e** ou **número do crédito**, conforme especificação do desafio técnico.

---

🧾 Funcionalidades

- 🔍 Consultar créditos por **número da NFS-e**
- 🔎 Consultar crédito por **número do crédito**
- 🚫 Tratamento de exceções com respostas padronizadas
- 🧪 Testes unitários e de controller com **JUnit 5** e **Mockito**
- 🐳 Banco de dados **PostgreSQL** containerizado com Docker

---

🛠️ Tecnologias Utilizadas

Back-end
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Hibernate
- Lombok

Banco de Dados
- PostgreSQL

Testes
- JUnit 5
- Mockito
- Spring Boot Test
- MockMvc

2.4 Infraestrutura
- Docker
- Docker Compose

---

📐 Arquitetura

O projeto segue uma separação clara de responsabilidades:

```text
src/main/java/com/jeffersonmorais/creditsapi

├── api
│ ├── controller
│ ├── dto
│ └── mapper
├── domain
│ ├── entity
│ ├── repository
│ └── service
├── exception
│ └── GlobalExceptionHandler
```
**Controller**: camada de entrada HTTP
- **Service**: regras de negócio
- **Repository**: acesso a dados
- **DTO + Mapper**: isolamento da entidade de domínio
- **Exception Handler**: tratamento global de erros

---

🔹 Buscar créditos por número da NFS-e

- **Controller**: camada de entrada HTTP
- **Service**: regras de negócio
- **Repository**: acesso a dados
- **DTO + Mapper**: isolamento da entidade de domínio
- **Exception Handler**: tratamento global de erros

---

📡 Endpoints

🔹 Buscar créditos por número da NFS-e

```http
GET /api/creditos/{numeroNfse}

  {
    "numeroCredito": "123456",
    "numeroNfse": "7891011",
    "dataConstituicao": "2024-02-25",
    "valorIssqn": 1500.75,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 5.0,
    "valorFaturado": 30000.00,
    "valorDeducao": 5000.00,
    "baseCalculo": 25000.00
  }
  ```



```http
api/creditos/credito/{numeroCredito}

{
  "numeroCredito": "123456",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1500.75,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.0,
  "valorFaturado": 30000.00,
  "valorDeducao": 5000.00,
  "baseCalculo": 25000.00
}
```

❌ Tratamento de Erros

A API retorna erros padronizados:

    {
      "timestamp": "2025-12-16T17:39:21",
      "status": 404,
      "error": "Not Found",
      "message": "Crédito não encontrado para o número: 000000",
      "path": "/api/creditos/credito/000000"
    }

🧪 Testes

O projeto possui testes unitários para:

Service (CreditoServiceTest)

Controller (CreditoControllerTest)

Executar os testes: 

./mvnw test

🐳 Executando o Projeto

Subir o banco de dados:

    docker-compose up -d

Rodar a aplicação:

    ./mvnw spring-boot:run

A API estará disponível em:

    http://localhost:8080

🗃️ Banco de Dados

Scripts utilizados:

    schema.sql — criação da tabela

    data.sql — carga inicial de dados

O Hibernate está configurado com:

    spring.jpa.hibernate.ddl-auto=validate

🔁 Controle de Versão

O projeto utiliza Git Flow simplificado, com:

main

develop

branches de feature, fix e test

Commits seguem padrão semântico:

feature:

fix:

test:

docs:

🚀 Melhorias Futuras

Integração com Kafka para publicação de eventos de consulta

Front-end em Angular

Autenticação e autorização

Paginação e filtros avançados

👤 Autor

Jefferson Domingos de Morais
GitHub: https://github.com/JeffersonDomingos
