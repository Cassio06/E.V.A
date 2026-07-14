# EVA - Estoque Inteligente Mark 1

A **EVA** é um projeto pessoal de assistente/sistema de organização desenvolvido em Java, criado inicialmente como um módulo de controle de estoque para estudo de Programação Orientada a Objetos, lógica de programação e construção gradual de sistemas reais.

Este repositório representa a **Mark 1** da EVA: a primeira versão funcional do sistema, focada em cadastro, controle e gerenciamento básico de produtos.

## Objetivo do Projeto

O objetivo da EVA é evoluir aos poucos de um simples sistema de estoque para uma assistente pessoal mais completa, capaz de ajudar em organização, controle financeiro, revenda, produtividade e tomada de decisão.

Nesta primeira fase, o foco é construir uma base sólida em Java, aplicando conceitos como:

- Classes e objetos
- Encapsulamento
- Métodos
- Listas de objetos
- Validação de entrada
- Controle de fluxo
- Regras de negócio
- Organização de código

## Funcionalidades Atuais

A Mark 1 atualmente possui:

- Cadastro de produtos
- Bloqueio de produtos duplicados
- Listagem de produtos cadastrados
- Venda de produtos
- Reposição de estoque
- Edição de produtos
- Edição de nome com proteção contra duplicidade
- Edição de preço de compra
- Edição de preço de venda
- Edição de estoque mínimo
- Edição de quantidade em estoque
- Resumo geral do estoque
- Cálculo de valor total em estoque
- Cálculo de custo total
- Cálculo de lucro possível
- Identificação de baixo estoque
- Validação contra entradas inválidas

## Estrutura do Sistema

O projeto é dividido principalmente em três partes:

### Produto

Representa um produto do estoque, armazenando informações como:

- Nome
- Preço de compra
- Preço de venda
- Quantidade
- Estoque mínimo

Também possui métodos para calcular lucro, alterar dados e controlar quantidade.

### Estoque

Gerencia a lista de produtos cadastrados.

É responsável por operações como:

- Adicionar produto
- Buscar produto
- Listar produtos
- Vender produto
- Repor produto
- Exibir resumo geral

### Main

Contém o menu principal e a interação com o usuário pelo terminal.

Também possui métodos auxiliares para leitura segura de dados, evitando erros com entradas inválidas.

## Status Atual

A EVA Mark 1 está em desenvolvimento.

A versão atual já possui as principais funções de controle de estoque funcionando, com várias validações para evitar erros comuns durante o uso.

## Próximos Passos

Os próximos objetivos são:

- Adicionar opção de cancelar operações em andamento
- Melhorar loops de cadastro, venda, reposição e edição
- Implementar salvamento dos produtos em arquivo
- Implementar carregamento automático dos produtos ao iniciar o sistema
- Melhorar organização do código
- Evoluir a interface no terminal
- Preparar futuras versões da EVA com novos módulos

## Roadmap

### Mark 1

Base do sistema de estoque.

- Cadastro
- Venda
- Reposição
- Edição
- Resumo
- Validações
- Controle de estoque mínimo

### Mark 2

Persistência de dados.

- Salvar produtos em arquivo
- Carregar produtos ao iniciar
- Melhorar experiência de uso
- Cancelamento de operações

### Mark 3

Organização e expansão.

- Melhor separação de responsabilidades
- Código mais limpo
- Possível uso de menus mais organizados
- Preparação para novos módulos

## Tecnologias Utilizadas

- Java
- Programação Orientada a Objetos
- Terminal/Console
- Git e GitHub

## Motivação

A EVA nasceu como um projeto de estudo, mas com a intenção de evoluir para algo maior: um sistema pessoal inteligente, construído passo a passo, acompanhando minha evolução em programação, engenharia de software e desenvolvimento de soluções reais.

A ideia é que cada versão da EVA represente uma etapa de aprendizado e crescimento técnico.

## Observação

Este projeto ainda está em fase inicial e serve como laboratório de aprendizado, testes e evolução contínua.
