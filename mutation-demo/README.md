# mutation-demo — Exemplo de Teste de Mutação com Pitest

Projeto de demonstração para o Trabalho 1 de Verificação e Validação de Software (PUCRS).  
Utiliza **JUnit 5** para testes e **Pitest** como ferramenta de teste de mutação.

---

## Estrutura do Projeto

```
mutation-demo/
├── pom.xml
└── src/
    ├── main/java/com/mutacao/
    │   ├── model/
    │   │   ├── Produto.java          # Modelo de produto com controle de estoque
    │   │   └── Conta.java            # Conta bancária (depósito, saque, transferência)
    │   ├── service/
    │   │   ├── CarrinhoService.java  # Carrinho de compras com desconto
    │   │   └── NotaFiscalService.java# Impostos e multa por atraso
    │   └── util/
    │       ├── Calculadora.java      # Operações aritméticas
    │       ├── Triangulo.java        # Classificação e medidas de triângulos
    │       └── Senha.java            # Validação e força de senhas
    └── test/java/com/mutacao/
        ├── model/
        │   ├── ProdutoTest.java                   # Teste 6
        │   └── ContaTest.java                     # Teste 7
        ├── service/
        │   ├── CarrinhoServiceTest.java            # Teste 8
        │   ├── NotaFiscalImpostosTest.java         # Teste 9
        │   └── NotaFiscalMultaTest.java            # Teste 10
        └── util/
            ├── CalculadoraOperacoesBasicasTest.java # Teste 1
            ├── CalculadoraAvancadaTest.java         # Teste 2
            ├── TrianguloClassificacaoTest.java      # Teste 3
            ├── TrianguloMedidasTest.java            # Teste 4
            └── SenhaValidacaoTest.java              # Teste 5
```

---

## Pré-requisitos

- Java 17+
- Maven 3.8+

---

## Comandos

### Executar apenas os testes JUnit
```bash
mvn test
```

### Executar o teste de mutação com Pitest
```bash
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```

O relatório HTML é gerado em:
```
target/pit-reports/<timestamp>/index.html
```
Abra esse arquivo no navegador para visualizar:
- **Quais mutantes foram mortos** (killed) pelos testes;
- **Quais sobreviveram** (survived), indicando lacunas nos testes;
- **Mutation score** geral do projeto.

---

## Operadores de Mutação Utilizados (DEFAULTS do Pitest)

| Operador | O que faz |
|---|---|
| `ArithmeticOperatorReplacement` | Troca `+` por `-`, `*` por `/`, etc. |
| `ConditionalsBoundary` | Troca `<` por `<=`, `>` por `>=` |
| `Increments` | Troca `++` por `--` |
| `InvertNegatives` | Inverte sinal de literais numéricos |
| `NegateConditionals` | Inverte condicionais (`==` → `!=`, `<` → `>=`) |
| `ReturnValues` | Substitui valor de retorno (ex.: `true` → `false`) |
| `VoidMethodCalls` | Remove chamadas a métodos void |

---

## Referências

DeMillo, R. A.; Lipton, R. J.; Sayward, F. G. **Hints on Test Data Selection: Help for the Practicing Programmer**. *Computer*, v. 11, n. 4, p. 34-41, 1978. Disponível em: https://ieeexplore.ieee.org/document/1646911. Acesso em: mai. 2026.

Pitest. **PIT Mutation Testing**. Disponível em: https://pitest.org. Acesso em: mai. 2026.
