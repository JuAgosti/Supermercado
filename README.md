# 🛒 Supermercado - API de Vendas e Controle de Estoque

Sistema web desenvolvido utilizando **Java, Spring Boot e PostgreSQL**.

---

## Sobre o projeto

Este projeto foi desenvolvido como prática de estudos em Java e Spring Boot, com foco em APIs RESTful, controle transacional e organização em camadas (Controller, Service, Repository e Entity).

A aplicação tem como objetivo simular o funcionamento de um supermercado, permitindo o gerenciamento de clientes, produtos, estoque, carrinhos de compras e vendas, garantindo a consistência dos dados durante todo o fluxo de compra.

---

## Funcionalidades

- Cadastro de clientes com validação de CPF único
- Cadastro de produtos com validação de preço e data de validade
- Controle de entrada e saída de estoque
- Criação de carrinhos de compras
- Adição e remoção de produtos do carrinho
- Finalização de vendas com cálculo automático do valor total
- Liberação automática de carrinhos abandonados após 15 minutos
- Listagem de clientes, produtos, carrinhos e vendas

---

## Modelagem de Dados

O sistema é composto pelas seguintes entidades:

- Cliente
- Produto
- Carrinho
- ItemCarrinho
- Venda

Os relacionamentos entre as entidades são gerenciados pelo **Spring Data JPA**, garantindo a integridade entre clientes, produtos, carrinhos e vendas.

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Boot Validation (Bean Validation)
- PostgreSQL
- Maven
- Lombok
- Hibernate

---

## Arquitetura do Projeto

- **Controller** → responsável pelos endpoints da API e recebimento das requisições HTTP
- **Service** → implementação das regras de negócio e controle transacional (`@Transactional`)
- **Repository** → abstração do acesso ao banco de dados
- **Entity** → modelagem das tabelas do banco de dados
- **Enum** → gerenciamento dos estados do carrinho

---

## Melhorias Implementadas

- Separação da aplicação em camadas (Controller, Service, Repository e Entity)
- Controle automático da movimentação de estoque
- Implementação de transações utilizando `@Transactional`
- Validação de CPF duplicado
- Validação de produtos vencidos
- Validação de estoque disponível
- Utilização de `Optional` para buscas seguras
- Utilização de `BigDecimal` para operações financeiras
- Agendamento automático utilizando `@Scheduled` para expiração de carrinhos abandonados
- Padronização das respostas da API utilizando `ResponseEntity`

---

## Próximas melhorias

- Documentação da API com Swagger/OpenAPI
- Implementação de DTOs
- Autenticação e autorização com Spring Security + JWT
- Testes unitários
- Docker e Docker Compose
- Relatórios e dashboard de vendas

---

## Como Executar

1. Clone o repositório.
2. Configure um banco PostgreSQL.
3. Ajuste o arquivo `application.properties`.
4. Execute a aplicação Spring Boot.
5. Acesse a API em:

```
http://localhost:8080
```

---

## 👩‍💻 Autora

**Júlia Agosti**

Projeto desenvolvido para fins de estudo e evolução em desenvolvimento backend com Java e Spring Boot.