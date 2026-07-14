# Supermercado - API de Vendas e Controle de Estoque

Sistema web desenvolvido utilizando **Java, Spring Boot e PostgreSQL**.

## Sobre o projeto

Este projeto foi desenvolvido como prática de estudos em Java e Spring Boot, com foco em arquitetura em camadas, encapsulamento de regras de negócio complexas e consistência de dados transacionais.

A aplicação simula o fluxo de compras de um supermercado, onde a principal inteligência reside na integração entre a gestão de estoque dos produtos, o cadastro de clientes e o ciclo de vida do carrinho de compras.

---

## Funcionalidades principais

* **Cadastro de produtos com validações lógicas:** Proteção contra cadastro de produtos já vencidos ou com preços inválidos.
* **Gerenciamento de estoque seguro (Fail-Fast):** Processo automatizado para dar entrada (abastecimento) e baixa de estoque conforme as ações do cliente.
* **Controle transacional robusto (@Transactional):** Garantia de que a retirada de estoque só seja efetivada se o produto for adicionado ao carrinho com sucesso (evitando perda ou inconsistência de dados).
* **Cadastro único de clientes:** Validação de unicidade para evitar a duplicação de cadastros com o mesmo CPF.
* **Manipulação resiliente de carrinho de compras:**
    * Busca segura e tratamento contra exceções usando a classe `Optional`.
    * Validação refinada ao remover itens: o sistema barra tentativas de remover mais unidades do que as atualmente contidas no carrinho.

---

## Tecnologias Utilizadas

* **Java 21** 
* **Spring Boot 4.1.0**
* **Spring Data JPA**
* **Spring Boot Validation** 
* **PostgreSQL**
* **Maven**
* **Lombok** 

---

## Arquitetura do Projeto

* **Controller (Próxima etapa):** Responsável por expor as rotas HTTP e receber as requisições da API de forma semântica.
* **Service:** Camada dedicada a conter todas as validações, regras de negócio e controle de consistência transacional do estoque.
* **Repository:** Abstração do acesso ao banco de dados com JPA/Hibernate, utilizando consultas otimizadas e geração de queries automáticas pelo Spring Data.
* **Entity / Model:** Representação e mapeamento puro das tabelas do banco de dados relacional.

---

## Diferenciais e Regras de Negócio Implementadas

* **Prevenção de Falhas com `Optional`:** Uso sistemático do fluxo funcional `.orElseThrow()` e `.isPresent()` para prevenir o temido `NullPointerException` (o famoso desmaio do sistema por caixas vazias).
* **Precisão Centesimal:** Uso de `BigDecimal` em conjunto com `.compareTo()` para a validação precisa e segura de preços e valores monetários.
* **Sincronia entre Estoque e Carrinho:** Implementação de acoplamento limpo no qual o `CarrinhoService` utiliza o `ProdutoService` para orquestrar a movimentação do inventário físico em tempo real.
* **Defesa contra inconsistências de quantidade:** Validação estrita na remoção de itens do carrinho, impedindo a diminuição para quantidades negativas.

---

## Próximas melhorias

* Implementação de **Java Records** e **DTOs** para o tráfego seguro de dados entre as camadas externas e internas da API.
* Criação do robô agendado (`@Scheduled`) para liberar automaticamente os produtos de carrinhos abandonados de volta ao estoque após o período limite de 15 minutos.
* Documentação interativa dos endpoints com **Swagger/OpenAPI**.
* Autenticação e Autorização de usuários com **Spring Security + JWT**.
---

## Como Executar

1. Clone o repositório em sua máquina local.
2. Certifique-se de ter um banco de dados **PostgreSQL** ativo.
3. Ajuste as credenciais de acesso ao banco no arquivo `src/main/resources/application.properties`.
4. Configure as variáveis de ambiente necessárias:
    * `DB_URL`
    * `DB_USER`
    * `DB_PASS`
5. Execute a aplicação utilizando sua IDE favorita ou via terminal com `./mvnw spring-boot:run`.

---

## 👩‍💻 Autora

**Júlia Agosti**  
*Projeto desenvolvido para fins de estudo, consolidação de boas práticas em backend utilizando a stack Java.*