# Estoque Ágil MEI

![Build Status](https://github.com/dudamarquesst/estoque-agil-mei/actions/workflows/ci.yml/badge.svg)

Sistema de controle de estoque simplificado desenvolvido para microempreendedores que precisam gerenciar insumos e receber alertas de reposição.

## Tecnologias Utilizadas
- **Java 17**
- **Maven** (Gerenciamento de dependências)
- **H2 Database** (Persistência de dados local)
- **JUnit 5** (Testes automatizados)
- **GitHub Actions** (Integração Contínua)
- **Checkstyle** (Análise estática de código)

## Funcionalidades
- Cadastro de produtos com ID, Nome, Quantidade Atual e Mínima.
- Listagem de estoque com alertas visuais [⚠️ REPOR ESTOQUE!].
- Persistência em arquivo local (os dados não somem ao fechar).

## Como Rodar o Projeto
1. Clone o repositório.
2. Certifique-se de ter o Java 17 instalado.
3. No terminal, execute:
   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.estoque.ui.Main"
