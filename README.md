# Estoque Ágil MEI

![Build Status](https://github.com/dudamarquesst/estoque-agil-mei/actions/workflows/ci.yml/badge.svg)

Sistema de controle de estoque simplificado desenvolvido para microempreendedores que precisam gerenciar insumos e receber alertas de reposição.

## Deploy

Aplicação disponível em: **https://estoque-agil-mei.onrender.com**

## Equipe

| Integrante | GitHub |
|------------|--------|
| Duda Marques | [@dudamarquesst](https://github.com/dudamarquesst) |
| Murilo | [@jorgemuriloceub](https://github.com/jorgemuriloceub) |
| Lucas Gabriel | [@LucasGabrielPaes](https://github.com/LucasGabrielPaes) |
| Miguel | [@filemoura](https://github.com/filemoura) |

## Banco de Dados

O sistema utiliza **PostgreSQL hospedado no Render** (DBaaS gratuito).
As credenciais de acesso são gerenciadas via variáveis de ambiente e GitHub Secrets, garantindo segurança das informações sensíveis.

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (Framework web)
- **Thymeleaf** (Template engine)
- **Maven** (Gerenciamento de dependências)
- **PostgreSQL** (Banco de dados em nuvem via Render)
- **JUnit 5** (Testes automatizados)
- **GitHub Actions** (Integração Contínua)
- **Checkstyle** (Análise estática de código)
- **API ViaCEP** (Integração de endereços)
- **Docker** (Containerização para deploy)
- **Render** (Hospedagem da aplicação e banco de dados)

## Funcionalidades

- Cadastro de produtos com Nome, Quantidade Atual e Mínima
- Listagem de estoque com alertas visuais ⚠️ REPOR / ✅ OK
- Dashboard com estatísticas de estoque
- Integração ViaCEP: busca automática de endereço pelo CEP
- Persistência em banco de dados PostgreSQL na nuvem

## Como Rodar Localmente

1. Clone o repositório:
```bash
   git clone https://github.com/dudamarquesst/estoque-agil-mei.git
```

2. Configure as variáveis de ambiente:
```bash
   export DB_URL=sua_url_do_banco
   export DB_USER=seu_usuario
   export DB_PASS=sua_senha
```

3. Execute o projeto:
```bash
   mvn spring-boot:run
```

4. Acesse em: http://localhost:10000