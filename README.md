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

Infraestrutura
- Docker
- Docker Compose
- Zookeeper
- Kafka

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

📨 Mensageria (Kafka)

Como desafio extra, a aplicação implementa **mensageria assíncrona utilizando Apache Kafka**.

Sempre que uma consulta de crédito é realizada, um **evento é publicado** no tópico Kafka `credit-events`, simulando um cenário real de auditoria, rastreamento ou integração com outros sistemas.

🔔 Evento Publicado

O evento representa uma consulta realizada na API e contém:

- Tipo da consulta (`NFSE` ou `Credito`)
- Valor consultado
- Data e hora da consulta

Exemplo do payload publicado:

```json
{
  "tipoConsulta": "Credito",
  "valorConsultado": "123456",
  "timestamp": "2025-12-17T00:03:35.608814"
}


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
  

🔹 Buscar créditos por número do crédito

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

Subir os containers banco de dados e do kafka:

    docker-compose up -d

Criar o tópico Kafka:

    docker exec -it credits-kafka kafka-topics \
      --create \
      --topic credit-events \
      --bootstrap-server localhost:9092 \
      --partitions 1 \
      --replication-factor 1

Listar tópicos existentes:

    docker exec -it credits-kafka kafka-topics \
      --list \
      --bootstrap-server localhost:9092

Rodar a aplicação:

    ./mvnw spring-boot:run

A API estará disponível em:

    http://localhost:8080

📡 Testando a mensageria

Após subir a aplicação e realizar uma requisição de consulta de crédito, o evento será automaticamente publicado no Kafka.

O consumo pode ser observado nos logs da aplicação, por exemplo:

**Evento Recebido -> Tipo: NFSE | Valor: 7891011 | Data: 2025-12-17T00:19:37.692540**

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

Front-end em Angular

Autenticação e autorização

Paginação e filtros avançados

👤 Autor

Jefferson Domingos de Morais
GitHub: https://github.com/JeffersonDomingos
