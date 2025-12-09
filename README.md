# Automação de Testes de API - Petstore (Módulo Store)

Este projeto consiste em uma suíte de testes automatizados para a API pública [Swagger Petstore](https://petstore.swagger.io/), focando especificamente nas operações do módulo **Store** (Loja).

O projeto valida o ciclo de vida de um pedido (criação, consulta e exclusão) e a verificação de inventário, garantindo a integridade dos dados e o status das respostas HTTP.

## 🛠 Tecnologias Utilizadas

*   **[Java](https://www.java.com/)** (JDK 8+)
*   **[RestAssured](https://rest-assured.io/)**: Framework para testes e validação de APIs REST.
*   **[JUnit 5](https://junit.org/junit5/)**: Framework de testes para execução, asserções e ordenação.
*   **[Allure Report](https://docs.qameta.io/allure/)**: Framework para geração de relatórios de execução detalhados.
*   **[Hamcrest](http://hamcrest.org/)**: Biblioteca de matchers para asserções fluentes.
*   **Maven**: Gerenciamento de dependências e build.

## 📋 Cenários de Teste

A classe `TestStore` utiliza a anotação `@TestMethodOrder` para garantir uma execução sequencial lógica, simulando o fluxo real de um usuário:

1.  **Criar Pedido (POST)**: Envia um payload JSON para criar um novo pedido de venda.
2.  **Consultar Pedido (GET)**: Busca o pedido recém-criado utilizando o ID extraído dinamicamente.
3.  **Verificar Inventário (GET)**: Valida se o endpoint de inventário retorna um mapa de status.
4.  **Deletar Pedido (DELETE)**: Remove o pedido criado e valida a mensagem de confirmação.

## ⚙️ Configuração e Estrutura

### Massa de Dados
Os dados utilizados nos testes são externalizados em um arquivo JSON para facilitar a manutenção.

*   **Arquivo**: `src/test/resources/json/store.json`
*   **Uso**: O teste lê este arquivo para enviar no corpo da requisição (POST) e para validar se a resposta (GET) contém os dados esperados.

### Estrutura de Pastas
```text
src
├── test
│   ├── java
│   │   └── TestStore.java          # Lógica principal dos testes
│   └── resources
│       └── json
│           └── store.json          # Massa de dados (Payload)
```

## 🚀 Como Executar

### Pré-requisitos
*   Java JDK 8 ou superior instalado.
*   Maven instalado e configurado nas variáveis de ambiente.

### Executando via Terminal

Para rodar todos os testes:

```bash
mvn test
```

### Executando via IDE (IntelliJ / Eclipse / VS Code)
1.  Abra o arquivo `src/test/java/TestStore.java`.
2.  Clique no ícone de "Run" ao lado da classe `TestStore`.

## 📊 Relatórios (Allure)

O projeto está configurado com o listener do Allure (`AllureRestAssured`), o que permite visualizar detalhes das requisições e respostas nos relatórios.

Para visualizar o relatório após a execução dos testes, execute no terminal:

```bash
mvn allure:serve
```

*Isso abrirá automaticamente uma página web com gráficos, tempo de execução e detalhes de cada passo.*

## 🔍 Detalhes da Implementação

### Leitura de Arquivos
Foi implementado um método utilitário para ler a massa de dados JSON:

```java
public static String lerArquivoJson(String arquivoJson) throws IOException {
    return new String(Files.readAllBytes(Paths.get(arquivoJson)));
}
```

### Setup Dinâmico
Utilizamos o `@BeforeEach` para garantir que o ID do pedido esteja sempre sincronizado com o arquivo JSON antes de cada teste:

```java
@BeforeEach
public void setup() throws IOException {
    String json = lerArquivoJson(pathJson);
    orderId = String.valueOf(JsonPath.from(json).getInt("id"));
}
```

---
Desenvolvido para fins de estudo em QA Automation.