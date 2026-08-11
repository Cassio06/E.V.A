# E.V.A. — Estoque Inteligente | MARK I

A **E.V.A.** é um projeto pessoal desenvolvido em Java com o objetivo de evoluir gradualmente de um sistema de organização e gerenciamento para uma assistente pessoal mais completa.

O projeto começou como um sistema de controle de estoque voltado ao estudo e à aplicação prática de conceitos de **Programação Orientada a Objetos, lógica de programação e organização de software**.

Este repositório representa a **MARK I da E.V.A.**, a primeira versão funcional e estruturada do sistema.

---

## Objetivo do Projeto

A proposta da E.V.A. é evoluir aos poucos de um sistema simples de estoque para uma plataforma pessoal capaz de auxiliar em áreas como:

* Organização
* Controle financeiro
* Revenda
* Produtividade
* Automação
* Tomada de decisão

Na MARK I, o principal objetivo foi construir uma base sólida em Java e aprender a estruturar um sistema real de forma progressiva.

Durante o desenvolvimento foram aplicados conceitos como:

* Classes e objetos
* Encapsulamento
* Métodos
* Construtores
* Listas de objetos
* Enums
* Validação de entrada
* Tratamento de entradas inválidas
* Controle de fluxo
* Regras de negócio
* Separação de responsabilidades
* Organização e refatoração de código

---

# Funcionalidades da MARK I

A primeira versão da E.V.A. possui:

* Cadastro de produtos
* Proteção contra produtos duplicados
* Busca de produtos por nome
* Listagem de produtos cadastrados
* Venda de produtos
* Controle de estoque insuficiente
* Reposição de estoque
* Edição de produtos
* Alteração do nome do produto
* Proteção contra nomes duplicados durante edição
* Alteração do preço de compra
* Alteração do preço de venda
* Alteração do estoque mínimo
* Alteração da quantidade em estoque
* Resumo geral do estoque
* Cálculo do valor total de venda do estoque
* Cálculo do custo total do estoque
* Cálculo do lucro total possível
* Cálculo do lucro por unidade
* Identificação de produtos com baixo estoque
* Validação de valores numéricos
* Validação de textos vazios
* Suporte a cancelamento em operações específicas

---

# Estrutura do Sistema

Ao longo do desenvolvimento da MARK I, o projeto foi refatorado para separar melhor as responsabilidades de cada classe.

A estrutura atual é composta principalmente por cinco classes.

## `Main`

Responsável apenas por inicializar e executar o sistema.

```java
public static void main(String[] args){
    SistemaEstoque sistema = new SistemaEstoque();
    sistema.executar();
}
```

O `Main` foi propositalmente mantido simples, deixando o controle da aplicação para outras classes.

---

## `SistemaEstoque`

Responsável pelo fluxo principal da aplicação e pela interação com o usuário.

Entre suas responsabilidades estão:

* Exibir o menu
* Coordenar cadastro de produtos
* Coordenar vendas
* Coordenar reposições
* Coordenar edição de produtos
* Exibir mensagens ao usuário
* Listar produtos
* Exibir o resumo geral do estoque

Essa classe funciona como a camada responsável por conectar a entrada do usuário às operações do sistema.

---

## `EntradaConsole`

Responsável por centralizar a leitura e validação dos dados digitados pelo usuário.

Possui métodos específicos para diferentes tipos de entrada, como:

* Texto
* Números inteiros positivos
* Números inteiros maiores ou iguais a zero
* Valores `double` positivos
* Entradas com possibilidade de cancelamento

Essa separação evita repetir lógica de validação em diferentes partes do sistema.

---

## `Estoque`

Responsável pelo gerenciamento da coleção de produtos.

Entre suas responsabilidades estão:

* Armazenar produtos
* Adicionar produtos
* Impedir produtos duplicados
* Buscar produtos
* Realizar vendas
* Realizar reposições
* Calcular o valor total do estoque
* Calcular o custo total do estoque
* Calcular o lucro total possível

A classe não é responsável pela interação direta com o usuário.

---

## `Produto`

Representa individualmente um produto armazenado no estoque.

Cada produto possui:

* Nome
* Preço de compra
* Preço de venda
* Quantidade em estoque
* Estoque mínimo

Também concentra regras relacionadas ao próprio produto, incluindo:

* Venda
* Reposição
* Alteração de preços
* Alteração de nome
* Alteração da quantidade
* Alteração do estoque mínimo
* Verificação de baixo estoque
* Cálculo de lucro
* Cálculo de custo
* Cálculo de valor em estoque

A própria classe protege seu estado contra valores inválidos.

---

# Organização Atual

Ao final da MARK I, a arquitetura básica ficou dividida desta forma:

```text
Main
 └── Inicialização da aplicação

SistemaEstoque
 ├── Fluxo do sistema
 ├── Menu
 ├── Operações
 └── Apresentação ao usuário

EntradaConsole
 └── Entrada e validação de dados

Estoque
 ├── Coleção de produtos
 ├── Busca
 ├── Cadastro
 ├── Venda
 ├── Reposição
 └── Cálculos gerais

Produto
 ├── Dados
 ├── Validações
 ├── Regras de negócio
 └── Cálculos individuais
```

---

# Status Atual

## ✅ E.V.A. MARK I — Concluída

A MARK I representa a primeira versão funcional da E.V.A.

Nesta etapa foram concluídos:

* Sistema de estoque funcional em memória
* Cadastro
* Venda
* Reposição
* Edição
* Listagem
* Resumos e cálculos
* Validações
* Encapsulamento das entidades
* Separação da entrada de dados
* Separação da apresentação
* Organização das responsabilidades entre as classes
* Refatoração e simplificação do `Main`

Os dados atualmente existem apenas durante a execução do programa.

Ao encerrar a aplicação, os produtos cadastrados não são preservados.

Essa limitação será o principal foco da próxima versão.

---

# Roadmap

## ✅ MARK I — Base do sistema

Primeira versão funcional do sistema de estoque.

### Concluído

* Cadastro
* Busca
* Venda
* Reposição
* Edição
* Listagem
* Resumo geral
* Validações
* Controle de estoque mínimo
* Organização das classes
* Separação de responsabilidades
* Entrada de dados centralizada
* Refatoração da estrutura inicial

---

## 🚧 MARK II — Persistência

A próxima etapa da E.V.A. terá como principal objetivo fazer com que os dados sobrevivam ao encerramento da aplicação.

Objetivos previstos:

* Implementar persistência dos produtos
* Salvar os dados cadastrados
* Carregar automaticamente os dados ao iniciar a aplicação
* Integrar a persistência à arquitetura atual
* Melhorar o tratamento de operações
* Expandir o sistema sem comprometer a organização construída na MARK I

---

## 🔮 MARK III — Expansão

Após a implementação da persistência, a E.V.A. poderá começar a evoluir além do sistema inicial de estoque.

Possíveis objetivos:

* Expandir os módulos existentes
* Melhorar a experiência de uso
* Evoluir a arquitetura conforme novas necessidades aparecerem
* Criar novos módulos
* Preparar integrações futuras
* Continuar evoluindo a E.V.A. como sistema pessoal

O escopo dessa fase poderá mudar conforme o projeto e os conhecimentos adquiridos evoluírem.

---

# Tecnologias Utilizadas

* Java
* Programação Orientada a Objetos
* Java Collections
* Terminal / Console
* Git
* GitHub

---

# Motivação

A E.V.A. nasceu como um projeto de estudo, mas com a intenção de evoluir para algo muito maior.

Em vez de desenvolver exercícios isolados apenas para praticar conceitos, a ideia é aplicar os conhecimentos adquiridos em um mesmo projeto de longo prazo, permitindo que sua arquitetura, funcionalidades e complexidade evoluam junto com meu aprendizado.

Cada MARK representa uma nova etapa dessa evolução.

A MARK I representa a construção da primeira base funcional.

A partir dela, novas versões deverão introduzir persistência, novos módulos, automações e funcionalidades progressivamente mais avançadas.

O objetivo é que a E.V.A. acompanhe minha evolução em **programação, engenharia de software e desenvolvimento de sistemas reais**.

---

## Observação

Este é um projeto pessoal de aprendizado e evolução contínua.

A arquitetura e as decisões técnicas poderão ser alteradas conforme novos conceitos forem estudados e novas necessidades surgirem.

A ideia não é construir tudo de uma vez, mas evoluir a E.V.A. progressivamente, mantendo cada versão como um registro do aprendizado adquirido durante o processo.
