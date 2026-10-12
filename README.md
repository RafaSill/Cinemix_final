# 🎬 Cinemix

Sistema web de gerenciamento e comercialização de filmes, desenvolvido como projeto acadêmico. Esta versão representa a integração final do Cinemix, reunindo **front-end, back-end e banco de dados** em uma única aplicação.

O projeto foi desenvolvido com o objetivo de aplicar conhecimentos de desenvolvimento web, programação orientada a objetos, persistência de dados e integração entre diferentes camadas de um sistema.

## 📌 Sobre o projeto

O Cinemix é um sistema web que permite o cadastro e o gerenciamento de usuários e filmes, além de disponibilizar funcionalidades relacionadas à compra, à locação e ao histórico de transações.

A aplicação integra uma interface desenvolvida com HTML, CSS e JavaScript, um back-end construído com Java e Spring Boot e um banco de dados MySQL, responsável pelo armazenamento das informações.

## 🚀 Funcionalidades

### 👤 Gerenciamento de usuários
- Cadastro de usuários.
- Autenticação por e-mail e senha.
- Gerenciamento e atualização dos dados cadastrais.
- Diferenciação entre usuários comuns e administradores.
- Controle de acesso às funcionalidades de acordo com o tipo de usuário.

### 🎞️ Gerenciamento de filmes
- Cadastro de filmes.
- Listagem e consulta do catálogo.
- Busca e filtragem de filmes por gênero.
- Atualização das informações dos filmes.
- Exclusão de filmes.
- Controle de funcionalidades administrativas.

### 🛒 Compras e locações
- Seleção de filmes para operações de compra ou locação.
- Integração com as informações de usuários, filmes e formas de pagamento.
- Registro de compras no banco de dados.
- Consulta ao histórico de compras e locações.

### 💳 Formas de pagamento
- Cadastro e consulta das formas de pagamento disponíveis.
- Associação da forma de pagamento à operação realizada.

### 🔗 Integração com API REST
- Implementação de controladores REST para operações relacionadas a filmes, usuários e pagamentos.
- Comunicação entre as camadas da aplicação e o banco de dados.

## 🛠️ Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| Java 21 | Linguagem de programação do back-end |
| Spring Boot | Estrutura principal da aplicação |
| Spring MVC | Controle das requisições e navegação entre páginas |
| Spring Data JPA | Persistência e acesso aos dados |
| Hibernate | Mapeamento objeto-relacional (ORM) |
| Thymeleaf | Integração entre o back-end e as páginas HTML |
| HTML5 | Estrutura das páginas |
| CSS3 | Estilização e layout da interface |
| JavaScript | Interatividade e funcionalidades do front-end |
| MySQL | Banco de dados relacional |
| Maven | Gerenciamento de dependências e build |
| Lombok | Redução de código repetitivo nas classes Java |
| Jakarta Validation | Validação de dados |
| NetBeans | Ambiente de desenvolvimento utilizado |

## 🏗️ Arquitetura do projeto

O projeto está organizado em camadas, separando as responsabilidades de cada parte da aplicação.

```text
cinemix/
├── src/
│   ├── main/
│   │   ├── java/com/PI/cinemix/
│   │   │   ├── controller/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── CinemixApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   ├── img/
│   │       │   ├── icon/
│   │       │   └── JS/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

### Responsabilidade das camadas

- **Controller:** recebe as requisições, controla a navegação e conecta a interface às regras de negócio.
- **Entity:** representa as entidades do sistema e seus respectivos mapeamentos para o banco de dados.
- **Repository:** disponibiliza operações de persistência e consulta utilizando Spring Data JPA.
- **Service:** concentra as operações e regras de negócio relacionadas aos usuários, filmes, compras e locações.
- **Templates:** contém as páginas HTML processadas pelo Thymeleaf.
- **Static:** armazena os arquivos estáticos, como folhas de estilo CSS, scripts JavaScript, imagens e ícones.

## 🗄️ Banco de dados

O sistema utiliza o MySQL para armazenar os dados da aplicação. A integração é realizada por meio do Spring Data JPA e do Hibernate.

Entre as principais entidades do sistema estão:

- `UsersEntity`: representa os usuários.
- `FilmesEntity`: representa os filmes cadastrados.
- `TipoPagamentoEntity`: representa as formas de pagamento.
- `ComprarFilmesEntity`: representa os registros de compra.
- `AlugarFilmesEntity`: representa os registros relacionados à locação.

O Hibernate está configurado para atualizar o esquema do banco de dados conforme os mapeamentos das entidades, por meio da propriedade `spring.jpa.hibernate.ddl-auto=update`.

## ⚙️ Como executar o projeto

### Pré-requisitos

Antes de iniciar, certifique-se de possuir:

- JDK 21 ou compatível com a configuração do projeto.
- Apache Maven, caso não utilize o Maven Wrapper incluído no repositório.
- MySQL Server.
- Uma IDE compatível com projetos Maven, como o NetBeans.

### 1. Clone o repositório

```bash
git clone URL_DO_REPOSITORIO
```

Entre na pasta do projeto:

```bash
cd cinemix
```

Substitua `URL_DO_REPOSITORIO` pelo endereço do repositório GitHub do projeto.

### 2. Configure o banco de dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE cinemix_refatorado;
```

Em seguida, abra o arquivo:

```text
src/main/resources/application.properties
```

Configure as credenciais de acesso ao banco:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cinemix_refatorado
spring.datasource.username=root
spring.datasource.password=root

server.port=8080

spring.jpa.hibernate.ddl-auto=update
```

Os valores de usuário e senha acima correspondem à configuração atual do projeto. Altere-os conforme o seu ambiente local e evite utilizar credenciais pessoais em repositórios públicos.

### 3. Execute a aplicação

Pelo NetBeans, abra o projeto Maven e execute a classe principal `CinemixApplication`.

Também é possível executar pelo terminal, na raiz do projeto:

**Windows:**

```bash
mvnw.cmd spring-boot:run
```

**Linux ou macOS:**

```bash
./mvnw spring-boot:run
```

### 4. Acesse o sistema

Com a aplicação em execução, abra o navegador e acesse:

http://localhost:8080/

A página inicial corresponde à tela de login do Cinemix.

## 🎓 Objetivo acadêmico

O Cinemix foi desenvolvido com finalidade acadêmica, permitindo consolidar conhecimentos de programação orientada a objetos, desenvolvimento de aplicações web, arquitetura em camadas, integração com banco de dados relacionais, persistência de informações e implementação de controladores MVC e REST.

Esta versão reúne as partes desenvolvidas ao longo do projeto em uma aplicação integrada, aproximando o sistema de uma solução web completa.

## ⚠️ Observações

- O projeto tem finalidade acadêmica e não deve ser considerado um sistema pronto para uso comercial em produção.
- A execução depende da configuração correta do Java, do MySQL e das credenciais de conexão.
- Funcionalidades relacionadas a pagamentos não representam processamento financeiro real.
- Para uso em produção, seriam necessários aprimoramentos de segurança, validação, tratamento de erros e proteção de credenciais.

## 👨‍💻 Autor

Desenvolvido por **Rafael Silva**, como projeto acadêmico.
