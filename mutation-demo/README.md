# mutation-demo — Exemplo de Teste de Mutação com Pitest

Projeto de demonstração para o Trabalho 1 de Verificação e Validação de Software (PUCRS).  
Alunos: Bianca Piassine, Maria Eduarda Melloni, Tarciso Mota, Sthafany Marques da Fonseca.
Turma: 30
materia: Verificação e validação de software.


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
        │   ├── ProdutoTest.java                   
        │   └── ContaTest.java                     
        ├── service/
        │   ├── CarrinhoServiceTest.java            
        │   ├── NotaFiscalImpostosTest.java         
        │   └── NotaFiscalMultaTest.java            
        └── util/
            ├── CalculadoraOperacoesBasicasTest.java 
            ├── CalculadoraAvancadaTest.java         
            ├── TrianguloClassificacaoTest.java      
            ├── TrianguloMedidasTest.java            
            └── SenhaValidacaoTest.java              
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
