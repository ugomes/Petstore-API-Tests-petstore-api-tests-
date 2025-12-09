# Petstore-API-Tests

Automação de Testes de API para a Swagger Petstore (projeto de portfólio).

Uma suíte de testes em Java que valida os principais fluxos do endpoint **Store** (Loja) da API pública: criação de pedidos, consulta, verificação de inventário e exclusão.

Badges (substitua os links/IDs pelo seu repositório / CI quando disponível):

- Build: ![build](https://img.shields.io/badge/build-pending-lightgrey)
- Testes: ![tests](https://img.shields.io/badge/tests-passing-brightgreen)
- Allure: ![allure](https://img.shields.io/badge/allure-report-available-blue)

Resumo rápido (para recrutadores)

- Objetivo: Demonstrar habilidade em automação de APIs REST usando Java, RestAssured e JUnit 5.
- O que mostrar: execução dos testes, relatório Allure com evidências e um README claro que explique o que foi testado e como reproduzir.
- Impacto: valida fluxos end-to-end (criação → leitura → inventário → deleção) em um serviço real de referência (Swagger Petstore).

Principais destaques técnicos

- Linguagem: Java (JDK 8+)
- Frameworks: RestAssured, JUnit 5, Hamcrest
- Build: Maven
- Estrutura de testes organizada com payloads em `src/test/resources/json`
- Relatórios: Allure (instruções abaixo para geração local)

Estrutura do projeto

- Testes: `src/test/java` (contendo `TestPet`, `TestStore`, `TestUser`)
- Dados de teste (JSON): `src/test/resources/json`
- Arquivo principal de interesse: `src/test/java/TestStore.java`

Por que este projeto é relevante para um recrutador

- Projetos de portfólio devem ser fáceis de executar e demonstrar resultados visuais (relatórios). Este repositório mostra testes automatizados funcionais sobre uma API pública e instruções para gerar um relatório Allure, o que facilita a inspeção por parte de avaliadores técnicos.
- Inclui estratégias comuns: massa de dados externa, testes ordenados quando necessário e validações claras de respostas HTTP.

Como rodar (Windows - cmd.exe)

Pré-requisitos:
- Java JDK 8 ou superior instalado e configurado no PATH
- Maven instalado e configurado no PATH

Passos básicos:

1. Abra o Prompt de Comando na raiz do projeto (`D:\Estudo_QA\api-petstore-tests\petstore`).
2. Execute os testes com Maven:

```bat
mvn test
```

Geração de relatório Allure (opções)

Opção A — Usando o Allure CLI (recomendado para desenvolver localmente):

1. Instale o Allure CLI (ex.: Chocolatey):

```bat
choco install allure
```

2. Rode os testes e sirva o relatório localmente:

```bat
mvn test
allure serve target/allure-results
```

Ou gere o relatório estático:

```bat
mvn test
allure generate target/allure-results -o target/allure-report --clean
```

Opção B — Usando o plugin Maven Allure (se preferir integrar ao pom.xml):
- Adicione o plugin `io.qameta.allure:allure-maven` ao `pom.xml` e depois execute `mvn allure:report` (veja a documentação do Allure Maven Plugin para versões e configuração).

Observação: se você não tiver o Allure instalado, as instruções acima mostram alternativas; o comando `allure serve` exige a instalação do Allure CLI.

O que procurar no relatório Allure

- Visão geral dos testes (pass/fail)
- Execuções detalhadas com steps, requests/responses (se instrumentado) e anexos
- Históricos/Trends (quando integrado ao CI)

Sugestões rápidas para deixar o repositório mais atraente

- Adicionar GitHub Actions que rodem `mvn test` e publiquem os resultados do Allure (ou o XML/artefatos) em cada push/PR.
- Incluir um screenshot ou GIF curto no README mostrando o relatório Allure localmente (ajuda recrutadores a entenderem imediatamente o que será mostrado).
- Adicionar `CONTRIBUTING.md` com guidelines e um `CHANGELOG.md` simples se você pretende iterar no projeto.

Exemplos de comandos úteis (Windows):

- Rodar testes e gerar report estático:

```bat
mvn test && allure generate target/allure-results -o target/allure-report --clean
```

- Servir o relatório (abre um browser):

```bat
allure serve target/allure-results
```

Contato / quem fez este projeto

- Nome: (adicione seu nome)
- LinkedIn / Email: (adicione informações de contato)

Próximos passos que posso fazer por você

- Adicionar um badge de build / GitHub Actions YAML que rode os testes e exporte os resultados do Allure.
- Incluir um exemplo `.github/workflows/ci.yml` que executa `mvn test` e salva `target/allure-results` como artifact.

---

Se quiser, eu já crio o workflow do GitHub Actions para gerar e publicar os artefatos do Allure; diga se prefere que eu o adicione ao repositório agora.
