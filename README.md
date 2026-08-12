# E.V.A. — Estoque Inteligente | MARK I

> Sistema de controle de estoque desenvolvido em Java para aplicar Programação Orientada a Objetos, validações, regras de negócio e separação de responsabilidades.

**E.V.A.** significa **Entidade Virtual Adaptativa**. O nome representa a visão de longo prazo do projeto: evoluir gradualmente de um sistema de gestão para uma assistente pessoal com módulos, automações e interação natural.

Este repositório apresenta a **MARK I**, primeira versão funcional e estruturada da E.V.A.

## Estado atual

| Versão | Status | Escopo |
| --- | --- | --- |
| **MARK I** | ✅ Concluída em 11/08/2026 | Estoque funcional em memória |
| **MARK II** | ⏳ Próxima etapa | Persistência e recuperação dos dados |

Na MARK I, a aplicação é executada pelo terminal e mantém os produtos apenas durante a execução. Essa é uma limitação planejada da versão, não uma funcionalidade inacabada.

## Demonstração do fluxo

Ao iniciar o programa, o usuário acessa o menu principal:

```text
1 — Cadastrar produto
2 — Vender produto
3 — Repor produto
4 — Editar produto
5 — Exibir resumo geral
6 — Listar produtos
0 — Encerrar o sistema
```

As operações são validadas antes de alterar o estoque. O sistema impede, por exemplo, produtos duplicados, valores inválidos e vendas superiores à quantidade disponível.

## Funcionalidades

- Cadastro, busca e listagem de produtos
- Proteção contra nomes duplicados
- Venda com verificação de estoque disponível
- Reposição de produtos
- Edição de nome, preços, quantidade e estoque mínimo
- Identificação de produtos com estoque baixo
- Resumo do custo, valor de venda e lucro possível do estoque
- Cálculo de lucro por unidade
- Validação de textos, inteiros e valores decimais
- Tratamento de entradas inválidas
- Cancelamento em operações específicas

## Como executar

### Pré-requisitos

- Java JDK instalado
- IDE com suporte a Java ou acesso ao terminal

> O JDK configurado deve ser compatível com o alvo de compilação definido no projeto.

### Pela IDE

1. Clone ou baixe este repositório.
2. Abra o projeto no IntelliJ IDEA, Eclipse, VS Code ou outra IDE Java.
3. Configure o JDK do projeto.
4. Execute o método `main` da classe `Main`.

### Pelo terminal

Acesse a pasta que contém os arquivos `.java` e execute:

```bash
javac *.java
java Main
```

## Organização do código

| Classe | Responsabilidade |
| --- | --- |
| `Main` | Inicializar a aplicação |
| `SistemaEstoque` | Coordenar menu, casos de uso e mensagens |
| `EntradaConsole` | Ler, converter e validar os dados digitados |
| `Estoque` | Gerenciar produtos, buscas, operações e cálculos gerais |
| `Produto` | Representar um produto e proteger suas regras e seu estado |

O fluxo principal pode ser resumido assim:

```text
Main
 └── SistemaEstoque
      ├── EntradaConsole
      └── Estoque
           └── Produto
```

## Decisões técnicas

- **Separação entre domínio e interação:** `Estoque` e `Produto` não leem dados do terminal nem exibem mensagens.
- **Estado protegido:** alterações nos dados de um produto passam pelos métodos e validações da própria entidade.
- **Resultados explícitos:** o enum `ResultadoVenda` diferencia os possíveis resultados de uma venda sem depender de textos.
- **Coleção protegida:** `Estoque` mantém os produtos em uma `ArrayList` e fornece uma cópia defensiva da coleção.
- **Entrada centralizada:** `EntradaConsole` concentra leitura, conversão, validação e repetição após entradas inválidas.
- **Refatoração incremental:** o `Main` foi reduzido à inicialização, enquanto cada responsabilidade foi direcionada à classe apropriada.

## Próxima etapa — MARK II

A MARK II terá como objetivo fazer os dados sobreviverem ao encerramento da aplicação sem acoplar o armazenamento às regras de negócio já construídas.

Escopo previsto:

- Salvar e carregar os produtos
- Introduzir identificadores persistentes
- Separar domínio e acesso a dados por meio de repositórios
- Tratar dados ausentes, vazios, inválidos ou indisponíveis
- Preservar os fluxos existentes da MARK I
- Criar testes para regras críticas

## Visão de longo prazo

Depois da persistência, a E.V.A. poderá receber interface gráfica, banco de dados relacional, novos módulos de gestão, APIs, automações e recursos de assistência pessoal.

Essas capacidades representam a direção do projeto, não funcionalidades já implementadas. Cada MARK terá um escopo verificável e somente será considerada concluída quando estiver funcional e demonstrável.

## Tecnologias utilizadas na MARK I

- Java
- Java Collections
- Terminal / Console
- Git
- GitHub

Tecnologias futuras serão adicionadas à documentação apenas quando fizerem parte do código implementado.

## Motivação

A E.V.A. nasceu da ideia de aprender construindo um projeto contínuo, em vez de desenvolver somente exercícios isolados.

Cada MARK registra uma etapa real da minha evolução em programação e Engenharia de Software. O objetivo não é construir tudo de uma vez, mas melhorar progressivamente a arquitetura, as funcionalidades, os testes e a qualidade das decisões técnicas.

> **E.V.A. — Entidade Virtual Adaptativa**
>
> Um sistema em evolução, uma versão de cada vez.
