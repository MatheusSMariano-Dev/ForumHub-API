# 🗣️ ForumHub API

![Java 17](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java) 
![Spring Boot 4.0.3](https://img.shields.io/badge/Spring%20Boot-4.0.3-green?style=for-the-badge&logo=springboot) 
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql) 
![JWT](https://img.shields.io/badge/JWT-Auth0-red?style=for-the-badge&logo=jsonwebtokens)

---

## 📖 Sobre o Projeto
ForumHub é uma API REST desenvolvida para simular o funcionamento de um fórum de discussões, onde usuários podem criar tópicos, visualizar, atualizar e deletar. A API implementa autenticação JWT garantindo segurança nas operações.

---

## ✨ Funcionalidades
- ✅ Cadastro de usuários com senha criptografada (BCrypt)
- ✅ Autenticação JWT com token Bearer
- ✅ CRUD completo de tópicos
- ✅ Cadastro e listagem de cursos
- ✅ Validação de dados
- ✅ Proteção contra tópicos duplicados
- ✅ Apenas o autor pode editar/deletar seu tópico

---

## 🛠️ Tecnologias Utilizadas
- Java 17  
- Spring Boot 4.0.3  
- Spring Security  
- Spring Data JPA  
- PostgreSQL  
- JWT (Auth0)  
- Maven  

---

## 📡 Endpoints da API

### 🔓 Públicos

| Método | Endpoint      | Descrição               |
|--------|---------------|------------------------|
| POST   | /usuarios     | Cadastrar usuário      |
| POST   | /login        | Autenticar e obter token |
| GET    | /topicos      | Listar tópicos         |
| GET    | /topicos/{id} | Buscar tópico por ID   |
| GET    | /cursos       | Listar cursos          |
| POST   | /cursos       | Cadastrar curso        |

### 🔐 Protegidos (requer token)

| Método | Endpoint      | Descrição                |
|--------|---------------|-------------------------|
| POST   | /topicos      | Criar tópico            |
| PUT    | /topicos/{id} | Atualizar tópico (autor)|
| DELETE | /topicos/{id} | Deletar tópico (autor)  |

---

## 🚀 Como Executar

```bash
# Clone o repositório
git clone https://github.com/MatheusSMariano-Dev/forumhub.git

# Configure o banco PostgreSQL no application.properties

# Execute
./mvnw spring-boot:run
📝 Exemplos de Uso

Cadastrar Usuário
POST /usuarios

{
  "nome": "João",
  "email": "joao@email.com",
  "senha": "123456"
}

Login
POST /login

{
  "email": "joao@email.com",
  "senha": "123456"
}

Criar Tópico (com token)
POST /topicos
Header:

Authorization: Bearer <token>

Body:

{
  "titulo": "Dúvida Spring Boot",
  "mensagem": "Como configurar?",
  "nomeCurso": "Java"
}
👨‍💻 Autor

Desenvolvido por Matheus S Mariano.
https://www.linkedin.com/in/matheus-s-mariano
