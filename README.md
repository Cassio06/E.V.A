# E.V.A. v0.2.0-SNAPSHOT — MARK II

Sistema de estoque em Java em migração para persistência no PostgreSQL, desenvolvido como base para a evolução gradual da E.V.A. em uma assistente pessoal e operacional local-first.

> A versão numérica identifica tecnicamente o software. `MARK II` é o codinome da geração atual.

## Status atual

### E.V.A. v0.1.0 — MARK I ✅

Primeira versão funcional, concluída com armazenamento em memória.

### E.V.A. v0.2.0-SNAPSHOT — MARK II 🚧

Versão em desenvolvimento, responsável por migrar o armazenamento para Maven, JDBC e PostgreSQL.

A persistência de cadastro, listagem, busca e edição de produtos já foi implementada. Venda, reposição, resumo, inativação e histórico de movimentações ainda estão sendo migrados ou desenvolvidos.

---

## Objetivo do projeto

A E.V.A. começou como um sistema de controle de estoque voltado ao estudo e à aplicação prática de conceitos de programação e Engenharia de Software.

O objetivo de longo prazo é evoluir o projeto gradualmente para uma assistente pessoal e operacional capaz de auxiliar em áreas como:

- organização;
- controle de estoque;
- produtividade;
- automação;
- geração de informações;
- tomada de decisão.

Cada versão acrescenta uma camada coerente ao sistema, preservando o aprendizado e evitando tentar construir toda a assistente de uma vez.

---

## Versionamento

O projeto utiliza versões numéricas como identificação técnica e mantém as MARKs como codinomes das grandes gerações.

| Geração | Versão planejada | Objetivo principal |
|---|---:|---|
| MARK I | `v0.1.0` | Base do sistema em memória |
| MARK II | `v0.2.0` | Persistência e integridade dos dados |
| MARK III | `v0.3.0` | Contexto temporal e relatórios |
| MARK IV | `v0.4.0` | Organização pessoal |
| MARK V | `v0.5.0` | Aplicação desktop |
| MARK VI | `v0.6.0` | Proatividade e notificações |
| MARK VII | `v0.7.0` | Comandos textuais controlados |
| MARK VIII | `v0.8.0` | Interação por voz |
| MARK IX | `v0.9.0` | Linguagem natural com IA |
| MARK X | `v1.0.0` | Consolidação e edição de TCC |

Enquanto uma versão está em desenvolvimento, o Maven utiliza o sufixo `-SNAPSHOT`. Por isso, a versão atual é `0.2.0-SNAPSHOT`. A futura release concluída será publicada como `0.2.0`.

---

## MARK I — v0.1.0

A MARK I estabeleceu o domínio inicial e as regras de negócio do estoque.

### Funcionalidades concluídas

- cadastro de produtos;
- proteção contra produtos duplicados;
- busca por nome;
- listagem;
- venda;
- controle de estoque insuficiente;
- reposição;
- edição;
- alteração de nome, preços, quantidade e estoque mínimo;
- validação de entradas;
- suporte a cancelamento em operações específicas;
- controle de estoque mínimo;
- identificação de produtos com baixo estoque;
- resumo geral;
- cálculo de custo, valor de venda, lucro por unidade e lucro total possível;
- armazenamento em memória durante a execução.

### Conceitos aplicados

- classes e objetos;
- encapsulamento;
- construtores;
- coleções;
- enums;
- validação;
- regras de negócio;
- separação inicial de responsabilidades;
- menus de console.

A principal limitação da `v0.1.0` é que os dados desaparecem quando a aplicação é encerrada. Essa limitação motivou a MARK II.

---

## MARK II — v0.2.0-SNAPSHOT

O objetivo da MARK II é tornar o PostgreSQL a fonte persistente dos dados da E.V.A.

### Implementado

- migração do projeto para Maven;
- conexão JDBC com PostgreSQL;
- senha obtida pela variável de ambiente `EVA_DB_PASSWORD`;
- ID automático representado por `Long` em `Produto`;
- inserção de produtos;
- listagem de produtos ativos;
- busca de produto por ID;
- conversão de registros do banco em objetos Java;
- atualização da quantidade no Repository;
- edição persistente dos dados do produto;
- edição parcial, permitindo pressionar Enter para manter o valor atual;
- testes manuais das operações do Repository.

### Em desenvolvimento

- venda persistente;
- reposição persistente;
- resumo calculado com dados do PostgreSQL;
- inativação e consulta de produtos inativos;
- histórico de movimentações;
- transações para manter estoque e histórico consistentes;
- testes automatizados;
- documentação reproduzível do schema do banco;
- tratamento e acabamento final da versão.

### Critério de conclusão

A `v0.2.0` será considerada concluída quando todos os fluxos do estoque utilizarem o PostgreSQL, os dados permanecerem consistentes após reinicializações e as operações críticas estiverem testadas e documentadas.

---

## Arquitetura atual

```text
Main
 └── Inicializa a aplicação

SistemaEstoque
 ├── Controla o menu e os fluxos
 ├── Coordena a entrada do usuário
 └── Encaminha operações ao domínio ou ao Repository

EntradaConsole
 └── Lê e valida entradas do usuário

Produto
 ├── Representa os dados do produto
 ├── Protege o próprio estado
 └── Contém regras relacionadas ao produto

ProdutoRepository
 ├── Executa SQL com JDBC
 ├── Insere, lista, busca e atualiza produtos
 └── Converte ResultSet em Produto

ConexaoBanco
 └── Cria conexões com o PostgreSQL

Estoque
 └── Estrutura em memória da MARK I ainda presente durante a migração
```

Durante a MARK II, algumas operações já utilizam `ProdutoRepository`, enquanto venda, reposição e resumo ainda dependem da estrutura em memória. A conclusão da versão eliminará essa fonte paralela de armazenamento dos fluxos reais.

---

## Principais classes

### `Main`

Inicializa o sistema e delega a execução para `SistemaEstoque`.

### `SistemaEstoque`

Controla o menu, coordena as entradas e apresenta os resultados ao usuário.

### `EntradaConsole`

Centraliza a leitura e a validação dos dados digitados no console.

### `Produto`

Representa um produto e mantém validações e regras relacionadas ao seu estado.

### `ProdutoRepository`

Concentra o acesso ao PostgreSQL usando JDBC, `PreparedStatement`, `ResultSet` e `try-with-resources`.

### `ConexaoBanco`

Centraliza a criação de conexões com o banco de dados.

### `Estoque`

Representa a coleção em memória criada na MARK I. Sua participação nos fluxos reais será removida conforme a migração para o PostgreSQL for concluída.

---

## Tecnologias utilizadas

- Java;
- Maven;
- JDBC;
- PostgreSQL;
- Programação Orientada a Objetos;
- Java Collections;
- Git e GitHub;
- aplicação de console.

---

## Configuração sensível

A senha do banco não fica armazenada no código. A aplicação obtém a credencial pela variável de ambiente:

```text
EVA_DB_PASSWORD
```

Essa variável deve ser configurada localmente e nunca commitada no repositório.

---

## Roadmap resumido

```text
v0.1.0 — MARK I    → domínio e regras em memória
v0.2.0 — MARK II   → persistência e integridade
v0.3.0 — MARK III  → tempo e relatórios
v0.4.0 — MARK IV   → tarefas e organização
v0.5.0 — MARK V    → interface desktop
v0.6.0 — MARK VI   → alertas e proatividade
v0.7.0 — MARK VII  → comandos textuais
v0.8.0 — MARK VIII → voz
v0.9.0 — MARK IX   → IA e linguagem natural
v1.0.0 — MARK X    → consolidação e TCC
```

Os escopos futuros podem ser refinados conforme o projeto e os conhecimentos adquiridos evoluírem. A prioridade atual permanece exclusivamente na conclusão da `v0.2.0 — MARK II`.

---

## Motivação

Em vez de desenvolver apenas exercícios isolados, a proposta é aplicar os conhecimentos adquiridos em um projeto de longo prazo que evolui junto com os estudos.

Cada versão registra uma etapa do aprendizado em programação, banco de dados, arquitetura, testes e Engenharia de Software. As MARKs preservam a identidade das gerações, enquanto as versões numéricas identificam tecnicamente cada release.

O objetivo é construir uma solução simples, correta, justificável e progressivamente mais madura.
