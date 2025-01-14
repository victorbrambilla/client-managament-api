# Client Management API

## Descrição
Este projeto é uma API para gerenciamento de clientes, e-mails e categorias. Ele utiliza Spring Boot, Spring Security, OAuth2, JPA, PostgreSQL e outras tecnologias para fornecer uma solução robusta e segura.

## Estrutura do Projeto
A estrutura do projeto é a seguinte:
```
src/
  main/
    java/
      com/example/client_management/
        auth/
        config/
        controller/
        dto/
        entity/
        enums/
        exception/
        mapper/
        repository/
        service/
        specification/
    resources/
      application.properties
  test/
    java/
      com/example/client_management/
        ClientManagementApplicationTests.java
```

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4.1
- Spring Security
- Spring Data JPA
- PostgreSQL
- MapStruct
- Lombok
- Swagger/OpenAPI
- Cognito

## Configuração

### Banco de Dados
A aplicação utiliza PostgreSQL como banco de dados. As configurações de conexão estão no arquivo `application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/client_management
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

### Segurança
A aplicação utiliza OAuth2 com o Cognito da AWS para autenticação e autorização. As configurações estão no arquivo `application.properties`:
```
spring.security.oauth2.client.registration.cognito.client-id=your-client-id
spring.security.oauth2.client.registration.cognito.client-secret=your-client-secret
spring.security.oauth2.client.registration.cognito.scope=email,openid,phone
spring.security.oauth2.client.registration.cognito.redirect-uri=https://your-redirect-uri
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=https://cognito-idp.your-region.amazonaws.com/your-user-pool-id/.well-known/jwks.json
spring.security.oauth2.client.provider.cognito.issuerUri=https://cognito-idp.your-region.amazonaws.com/your-user-pool-id
```

## Endpoints

### Usuários
- **POST /users**: Cria um novo usuário.
  - Headers: `X-API-KEY`
  - Body: `CognitoUserRequestDTO`
  - Descrição: Após o cadastro no Cognito, uma Lambda chama esta rota para criar o usuário na aplicação.

### Clientes
- **GET /clientes**: Retorna uma lista de clientes com filtros e paginação.
- **GET /clientes/{id}**: Retorna um cliente pelo ID.
- **POST /clientes**: Cria um novo cliente.
- **PUT /clientes/{id}**: Atualiza um cliente pelo ID.
- **DELETE /clientes/{id}**: Remove um cliente pelo ID.

### E-mails
- **GET /emails/cliente/{clienteId}**: Retorna todos os e-mails de um cliente pelo ID.
- **GET /emails/{id}**: Retorna um e-mail pelo ID.
- **POST /emails**: Cria um novo e-mail.
- **PUT /emails/{id}**: Atualiza um e-mail pelo ID.
- **DELETE /emails/{id}**: Remove um e-mail pelo ID.

### Categorias
- **GET /categorias**: Retorna todas as categorias.
- **GET /categorias/{id}**: Retorna uma categoria pelo ID.
- **POST /categorias**: Cria uma nova categoria.
- **PUT /categorias/{id}**: Atualiza uma categoria pelo ID.
- **DELETE /categorias/{id}**: Remove uma categoria pelo ID.

## Executando a Aplicação
Para executar a aplicação, utilize o Maven Wrapper:
```
./mvnw spring-boot:run
```

## Testes
Para executar os testes, utilize o comando:
```
./mvnw test
```

## Contribuindo
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença
Este projeto está licenciado sob a Licença Apache 2.0. Veja o arquivo LICENSE para mais detalhes.
