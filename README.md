# 📦 EstoqueBackEnd

Backend para controle de produtos e matérias-primas com cálculo
inteligente de produção.

------------------------------------------------------------------------

## 📖 Sumário

-   [Visão Geral](#-visão-geral)
-   [Tecnologias](#-tecnologias-utilizadas)
-   [Arquitetura](#-arquitetura)
-   [Regra de Negócio](#-regra-de-negócio---cálculo-de-produção)
-   [Banco de Dados](#-banco-de-dados)
-   [Execução com Docker](#-execução-com-docker)
-   [Como Executar](#-como-executar)
-   [Endpoints](#-principais-endpoints)
-   [Testes](#-execução-de-testes)

------------------------------------------------------------------------

## 🚀 Visão Geral

Este projeto representa o backend de um sistema de controle de estoque.

A aplicação é responsável por:

-   ✅ Gerenciar produtos
-   ✅ Gerenciar matérias-primas
-   ✅ Controlar associações entre produtos e matérias-primas
-   ✅ Calcular sugestões de produção com base no estoque disponível
-   ✅ Priorizar produtos de maior valor
-   ✅ Simular produção sem alterar o estoque real

A aplicação segue o padrão REST e foi desenvolvida com **Quarkus**.

------------------------------------------------------------------------

## 🛠 Tecnologias Utilizadas

-   Java 17
-   Quarkus
-   Hibernate ORM com Panache
-   PostgreSQL
-   Maven
-   Docker
-   Docker Compose

------------------------------------------------------------------------

## 🏗 Arquitetura

O projeto segue arquitetura em camadas:

resource → service → repository → database

### Camadas

-   **Resource** → Endpoints REST
-   **Service** → Regras de negócio
-   **Repository** → Acesso ao banco
-   **Entity** → Mapeamento JPA
-   **DTO** → Transferência de dados

------------------------------------------------------------------------

## 🧠 Regra de Negócio - Cálculo de Produção

O algoritmo de sugestão de produção:

1.  Ordena os produtos por preço (ordem decrescente).
2.  Cria uma cópia virtual do estoque.
3.  Para cada produto:
    -   Calcula a quantidade máxima possível de produção.
    -   Atualiza o estoque virtual.
4.  Calcula o valor total da produção.
5.  Mantém o estoque real inalterado.

🎯 Objetivo: Maximizar o valor total da produção.

------------------------------------------------------------------------

## 🗄 Banco de Dados

### PostgreSQL

-   Banco: `inventory_db`
-   Usuário: `postgres`
-   Senha: `postgres`

------------------------------------------------------------------------

## ⚙ Configuração

### application.properties

``` properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=${QUARKUS_DATASOURCE_USERNAME:postgres}
quarkus.datasource.password=${QUARKUS_DATASOURCE_PASSWORD:postgres}
quarkus.datasource.jdbc.url=${QUARKUS_DATASOURCE_JDBC_URL:jdbc:postgresql://localhost:5432/inventory_db}

quarkus.hibernate-orm.database.generation=update
quarkus.hibernate-orm.log.sql=true

quarkus.swagger-ui.always-include=true
```

------------------------------------------------------------------------

## 🐳 Execução com Docker

### Dockerfile

``` dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/quarkus-app/lib/ /app/lib/
COPY target/quarkus-app/*.jar /app/
COPY target/quarkus-app/app/ /app/app/
COPY target/quarkus-app/quarkus/ /app/quarkus/

EXPOSE 8080

CMD ["java", "-jar", "quarkus-run.jar"]
```

------------------------------------------------------------------------

### docker-compose.yml

``` yaml
version: '3.8'

services:

  postgres:
    image: postgres:15
    container_name: inventory_postgres
    restart: always
    environment:
      POSTGRES_DB: inventory_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  backend:
    build:
      context: .
    container_name: inventory_backend
    depends_on:
      - postgres
    environment:
      QUARKUS_DATASOURCE_JDBC_URL: jdbc:postgresql://postgres:5432/inventory_db
      QUARKUS_DATASOURCE_USERNAME: postgres
      QUARKUS_DATASOURCE_PASSWORD: postgres
      QUARKUS_HIBERNATE_ORM_DATABASE_GENERATION: update
    ports:
      - "8080:8080"

volumes:
  postgres_data:
```

------------------------------------------------------------------------

## ▶ Como Executar

### 1️⃣ Gerar o build

``` bash
./mvnw clean package
```

### 2️⃣ Subir containers

``` bash
docker-compose up --build
```

------------------------------------------------------------------------

## 🌐 Acesso

-   API: http://localhost:8080
-   Swagger: http://localhost:8080/q/swagger-ui

------------------------------------------------------------------------

## 📡 Principais Endpoints

### Produtos

-   `GET /products`
-   `POST /products`
-   `PUT /products/{id}`
-   `DELETE /products/{id}`

### Matérias-Primas

-   `GET /raw-materials`
-   `POST /raw-materials`
-   `PUT /raw-materials/{id}`
-   `DELETE /raw-materials/{id}`

### Associação Produto x Matéria-Prima

-   `POST /product-raw-materials`
-   `GET /product-raw-materials/product/{productId}`
-   `DELETE /product-raw-materials/{id}`

### Sugestão de Produção

-   `GET /production/suggestion`

Retorna:

-   Produtos possíveis
-   Quantidade possível
-   Valor unitário
-   Valor total por produto
-   Valor total geral da produção

------------------------------------------------------------------------

## 🧪 Execução de Testes

``` bash
./mvnw test
```