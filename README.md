# 📝 TO-DO LIST API

Uma API RESTful desenvolvida com **Spring Boot** para gerenciamento de tarefas, seguindo a arquitetura **MVC** e utilizando práticas modernas de desenvolvimento backend. O projeto permite criar, listar, atualizar, concluir e deletar tarefas, com autenticação via **OAuth2 (Google)** e documentação com **Swagger**.

---

## 📌 Funcionalidades

- ✅ Criar tarefas  
- ✅ Listar todas as tarefas  
- ✅ Buscar tarefa por ID  
- ✅ Listar tarefas **concluídas**  
- ✅ Listar tarefas **pendentes**  
- ✅ Atualizar tarefa por ID  
- ✅ Concluir tarefa por ID (usando `enum` para status)  
- ✅ Deletar tarefa  

---

## 🚀 Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Security + JWT + OAuth2(Google)  
- PostgreSQL
- Flyway 
- Swagger
- Docker
- Lombok
- Maven
- DTOs
- ResponseEntity   

---

## 🧱 Estrutura do Projeto

### ✳️ Entity

A entidade principal representa uma tarefa, contendo:

- `nome`: Nome da tarefa  
- `descricao`: Descrição da tarefa  
- `dataCriacao`: Data de criação  
- `status`: Enum (`PENDENTE`, `CONCLUIDO`)  

### 📦 DTOs

- `RequestDTO`: Dados de entrada da tarefa  
- `ResponseDTO`: Dados de saída  
- `Mapper`: Conversões entre entidade e DTO  

### 📂 Repository

Camada de acesso a dados com **Spring Data JPA**.

### 🧠 Service

Regras de negócio e processamento da aplicação.

### 🎮 Controller

Camada de controle com os seguintes endpoints:

| Método | Endpoint                  | Descrição                      | Status HTTP             |
|--------|---------------------------|-------------------------------|-------------------------|
| GET    | `/tarefas`                | Listar todas as tarefas        | `200 OK`                |
| GET    | `/tarefas/{id}`           | Buscar tarefa por ID           | `200 OK` / `404 NotFound` |
| GET    | `/tarefas/concluidas`     | Listar tarefas concluídas      | `200 OK`                |
| GET    | `/tarefas/pendentes`      | Listar tarefas pendentes       | `200 OK`                |
| POST   | `/tarefas`                | Criar uma nova tarefa          | `201 Created`           |
| PUT    | `/tarefas/{id}/concluir`  | Concluir tarefa por ID         | `200 OK` / `404 NotFound` |
| PUT    | `/tarefas/{id}`           | Atualizar tarefa por ID        | `200 OK` / `404 NotFound` |
| DELETE | `/tarefas/{id}`           | Deletar tarefa por ID          | `204 No Content`        |

---

## 🔐 Autenticação

- Implementada autenticação via **Token JWT**, **OAuth2 Resource Server(Google)** utilizando **Spring Security**.

---

## 🗃️ Banco de Dados

- Banco: **PostgreSQL** 
- Contêiner gerenciado com **Docker**  
- Migrações de schema com **Flyway**

---

## ⚙️ Configuração

- Configurações centralizadas em arquivos `.yml`  
- Uso de **variáveis de ambiente** para dados sensíveis  
- Dependências gerenciadas com **Maven**  
- Uso de **Lombok** para reduzir código repetitivo  

---

## 📑 Documentação da API

- API documentada com **Swagger(OpenAPI)**  

---



