# 🛒 Supermercado - API de Vendas e Controle de Estoque

Sistema web desenvolvido utilizando **Java, Spring Boot, PostgreSQL e Docker**.

---

# 📖 Sobre o Projeto

Este projeto foi desenvolvido como prática de estudos em **Java** e **Spring Boot**, com foco na construção de APIs RESTful, controle transacional, conteinerização de aplicações e organização em camadas (**Controller, Service, Repository e Entity**).

A aplicação simula o funcionamento de um supermercado, permitindo o gerenciamento de clientes, produtos, estoque, carrinhos de compras e vendas, garantindo a consistência dos dados durante todo o fluxo de compra.

---

# 🚀 Funcionalidades

- Cadastro de clientes com validação de CPF único
- Cadastro de produtos com validação de preço e data de validade
- Controle de entrada e saída de estoque
- Criação de carrinhos de compras
- Adição e remoção de produtos do carrinho
- Finalização de vendas com cálculo automático do valor total
- Liberação automática de carrinhos abandonados após 15 minutos
- Listagem de clientes, produtos, carrinhos e vendas

---

# 🗂️ Modelagem de Dados

O sistema é composto pelas seguintes entidades:

- Cliente
- Produto
- Carrinho
- ItemCarrinho
- Venda

Os relacionamentos entre as entidades são gerenciados pelo **Spring Data JPA**, garantindo a integridade entre clientes, produtos, carrinhos e vendas.

---

# 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Validation (Bean Validation)
- PostgreSQL
- Docker
- Docker Compose
- Maven
- Lombok
- Hibernate

---

# 🏛️ Arquitetura do Projeto

O projeto segue a arquitetura em camadas:

- **Controller** → Responsável pelos endpoints da API.
- **Service** → Contém as regras de negócio e controle transacional (`@Transactional`).
- **Repository** → Responsável pelo acesso aos dados.
- **Entity** → Representação das tabelas do banco de dados.
- **Enum** → Gerenciamento dos estados do carrinho.

---

# ✨ Melhorias Implementadas

- Ambiente conteinerizado utilizando **Docker** e **Docker Compose**
- Separação da aplicação em camadas
- Controle automático da movimentação de estoque
- Implementação de transações com `@Transactional`
- Validação de CPF duplicado
- Validação de estoque disponível
- Validação de produtos antes da venda
- Utilização de `Optional` para consultas seguras
- Utilização de `BigDecimal` para operações financeiras
- Agendamento automático utilizando `@Scheduled` para expiração de carrinhos abandonados
- Padronização das respostas utilizando `ResponseEntity`

---

# 🐳 Docker

O projeto já possui toda a estrutura necessária para execução utilizando **Docker** e **Docker Compose**.

Atualmente, a configuração dos containers está funcionando corretamente. Entretanto, durante o desenvolvimento da aplicação, os testes ainda estão sendo realizados utilizando uma instância local do **PostgreSQL**, por facilitar o processo de desenvolvimento e depuração.

Em versões futuras, toda a aplicação será executada diretamente pelo ambiente Docker.

---

# ▶️ Como Executar

## Pré-requisitos

- Java 21
- Maven
- PostgreSQL

### Clonar o projeto

```bash
git clone https://github.com/SEU-USUARIO/Supermercado.git
```

```bash
cd Supermercado
```

---

## Executando Localmente

Configure o banco PostgreSQL no arquivo:

```text
src/main/resources/application.properties
```

Depois execute:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

---

## Executando com Docker

Após clonar o projeto, execute:

```bash
docker compose up --build
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

Para finalizar os containers:

```bash
docker compose down
```

> **Observação:** Embora o ambiente Docker esteja configurado e funcional, o desenvolvimento atual continua utilizando o PostgreSQL instalado localmente para realização dos testes.

---

# 🔮 Próximas Melhorias

- Interface Web (Front-end)
- Documentação da API com Swagger/OpenAPI
- Implementação de DTOs
- Spring Security + JWT
- Testes Unitários
- Relatórios de vendas
- Dashboard administrativo

---

# 👩‍💻 Autora

**Júlia Agosti**

Projeto desenvolvido para fins de estudo e aprimoramento em desenvolvimento Backend utilizando **Java**, **Spring Boot**, **PostgreSQL** e **Docker**.