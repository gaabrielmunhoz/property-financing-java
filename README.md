#  Simulador de Financiamento de Imóveis em Java 🏡

Este é um projeto acadêmico desenvolvido como parte de uma disciplina de **Poogramação Orientada a Objetos** na linguagem **Java**.
é uma aplicação simples que simula o financiamento de diferentes tipos de imóveis (Casa, Apartamento e Terreno),
utilizando os princípios da Programação Orientada a Objetos (POO).

## 🧠Funcionalidades

- Cálculo do valor total a ser pago no financiamento.
- Entrada de dados via terminal:
  - Tipo do imóvel
  - Valor do imóvel
  - Valor de entrada
  - Taxa de juros
  - Desconto
  - Número de parcelas
- Validação de dados de entrada.
- Lançamento de exceção personalizada caso o desconto seja maior que a taxa de juros.

## 🧱 Estrutura do Projeto
O projeto está dividido em três pacotes principais dentro da pasta `src`:

- `main/`
  - `Main.java`: ponto de entrada da aplicação.
  
- `modelo/`
  - `Financiamento.java`: classe abstrata base.
  - `Casa.java`, `Apartamento.java`, `Terreno.java`: subclasses com características específicas.

- `util/`
  - `InterfaceUsuario.java`: interações com o usuário via terminal.
  - `DescontoMaiorDoQueJurosException.java`: exceção personalizada para validar os dados.
