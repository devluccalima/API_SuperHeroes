# Characters Manager API




Esta é uma API RESTful desenvolvida em Java com Spring Boot, projetada para gerenciar informações de personagens. A aplicação permite listar, adicionar, buscar por ID e buscar por nome personagens, simulando um sistema de gerenciamento de super-heróis ou personagens de ficção. A API utiliza Spring Data JPA para persistência de dados e H2 Database como banco de dados em memória para desenvolvimento e testes.




## Tecnologias Utilizadas

*   Java 24
*   Spring Boot 3.5.3
*   Spring Web
*   Spring Data JPA
*   H2 Database (em memória)
*   Maven
*   Jakarta Validation




## Configuração e Execução

Para configurar e executar este projeto localmente, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/devluccalima/Characters_Manager.git
    ```
2.  **Navegue até o diretório do projeto:**
    ```bash
    cd Characters_Manager
    ```
3.  **Compile e execute o projeto usando Maven:**
    ```bash
    ./mvnw spring-boot:run
    ```
    A aplicação será iniciada na porta padrão do Spring Boot (geralmente 8080).




## Endpoints da API

A API expõe os seguintes endpoints:

*   `GET /api/personagens`: Retorna uma lista de todos os personagens cadastrados.
*   `POST /api/personagens`: Adiciona um novo personagem. O corpo da requisição deve conter um JSON com os dados do personagem.
*   `GET /api/{id}`: Busca um personagem pelo seu ID.
*   `GET /api/personagens/nome/{nome}`: Busca personagens cujo nome contenha a string fornecida.

### Exemplo de corpo de requisição para `POST /api/personagens`:

```json
{
    "nome": "Homem de Ferro",
    "universo": "Marvel",
    "habilidades": ["Inteligência Genial", "Traje de Batalha Avançado"],
    "poder": 9
}
```




## Banco de Dados

Esta aplicação utiliza o H2 Database, um banco de dados em memória. Os dados são carregados inicialmente a partir do arquivo `personagens_simplificados.json` localizado em `src/main/resources`. Para acessar o console do H2 durante a execução da aplicação, navegue para `http://localhost:8080/h2-console` no seu navegador. As credenciais padrão são:

*   **JDBC URL:** `jdbc:h2:mem:testdb`
*   **User Name:** `sa`
*   **Password:** (deixe em branco)




## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.



