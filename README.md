# Fórum Hub

FórumHub é uma API REST para a criação e gerenciamento de tópicos de um fórum da ALURA, utilizando Spring Boot e JWT para autenticação, essa é uma aplicação sem a parte de FRONT-END, funciona com a IDE, banco de dados e o Postman/Insomnia.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Flyway Migration
- MySQL
- Maven

## Funcionalidades

- Criação de tópicos
- Listagem de todos os tópicos
- Detalhamento de um tópico específico
- Atualização de tópicos
- Exclusão de tópicos
- Autenticação de usuários via JWT

## Configuração do Ambiente

### Pré-requisitos

- Java 17 ou superior
- Maven
- MySQL

### Passos para Configuração

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/forumhub.git
    cd forumhub
    ```

2. Configure o banco de dados MySQL:
    ```sql
    CREATE DATABASE forumhub;
    ```

3. Atualize o arquivo `src/main/resources/application.properties` com as suas configurações de banco de dados:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
    spring.datasource.username=root
    spring.datasource.password=${DB_USER} - cadastre a váriavel de ambiente com sua senha.
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.show-sql=true

    jwt.secret=segredo
    jwt.expiration=86400000
    ```

4. Execute as migrações do Flyway:
    ```bash
    ./mvnw flyway:migrate
    ```

5. Compile e execute a aplicação:
    ```bash
    ./mvnw spring-boot:run
    ```

## Endpoints

### Autenticação

#### Login
- **URL:** `/auth/login`
- **Método:** `POST`
- **Corpo da Requisição:**
    ```json
    {
        "email": "seu.email@alura.com",
        "senha": "suaSenha"
    }
    ```
- **Resposta:**
    ```json
    {
        "token": "seuTokenJWT"
    }
    ```

### Tópicos

#### Criar Tópico
- **URL:** `/topicos`
- **Método:** `POST`
- **Cabeçalho:**
    ```
    Authorization: Bearer <seuTokenJWT>
    ```
- **Corpo da Requisição:**
    ```json
    {
        "titulo": "Título do Tópico",
        "mensagem": "Mensagem do Tópico",
        "curso": "Nome do Curso"
    }
    ```

#### Listar Tópicos
- **URL:** `/topicos`
- **Método:** `GET`
- **Cabeçalho:**
    ```
    Authorization: Bearer <seuTokenJWT>
    ```

#### Detalhar Tópico
- **URL:** `/topicos/{id}`
- **Método:** `GET`
- **Cabeçalho:**
    ```
    Authorization: Bearer <seuTokenJWT>
    ```

#### Atualizar Tópico
- **URL:** `/topicos/{id}`
- **Método:** `PUT`
- **Cabeçalho:**
    ```
    Authorization: Bearer <seuTokenJWT>
    ```
- **Corpo da Requisição:**
    ```json
    {
        "titulo": "Novo Título do Tópico",
        "mensagem": "Nova Mensagem do Tópico",
        "curso": "Nome do Curso"
    }
    ```

#### Excluir Tópico
- **URL:** `/topicos/{id}`
- **Método:** `DELETE`
- **Cabeçalho:**
    ```
    Authorization: Bearer <seuTokenJWT>
    ```

## Contribuição

Se desejar contribuir com o projeto, por favor faça um fork do repositório e envie um pull request com suas alterações.

## Licença

Este projeto é licenciado sob os termos da licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
