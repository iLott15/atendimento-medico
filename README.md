# 🩺 Sistema de Agendamento Médico

Projeto fullstack construído como parte de um desafio técnico, com foco em qualidade de código, autenticação segura, e arquitetura escalável. Permite gerenciar médicos, pacientes e agendamentos.

---

## 🧰 Tecnologias Utilizadas

### 🔙 Backend (Java)
- Java 17
- Spring Boot 3.4.7
- Spring Data JPA
- Spring Security + JWT
- PostgreSQL
- Swagger (Documentação de API)
- JUnit + Mockito (Testes unitários e integração)
- Maven

### 🔜 Frontend (Vue)
- Vue 3 + Composition API
- Vue Router
- Pinia (State Management)
- Prettier
- Vite
- Estilo noturno personalizado

---

## ⚙️ Instalação e Execução

### ✅ Pré-requisitos
- Node.js 18+
- Vue CLI (opcional, mas útil)
- JDK 17
- PostgreSQL
- Maven

---

### 🔧 Configuração do Ambiente Backend
1. Clone o projeto:
```bash
git clone https://github.com/seuusuario/agendamento-medico.git
cd agendamento-medico/backend
```

2. Configure o banco no PostgreSQL:
```bash
createdb agendamento_db
createuser seu-nome --superuser
```
Ou via `psql`:
```sql
CREATE USER seu-nome WITH PASSWORD 'atendimentomedico';
CREATE DATABASE agendamento_db OWNER seu-nome;
```

3. Edite o arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/agendamento_db
spring.datasource.username=seu-nome
spring.datasource.password=atendimentomedico
```

4. Execute a aplicação:
```bash
./mvnw spring-boot:run
# ou
mvn spring-boot:run
```

---

## 🌐 Testando o Backend
- Swagger: http://localhost:8080/swagger-ui/index.html
- Exemplo de Login via API:
```json
POST /login
{
  "login": "admin",
  "senha": "123456"
}
```

---

## 💻 Configuração do Frontend
1. Vá para o diretório do frontend:
```bash
cd ../frontend
```

2. Instale as dependências:
```bash
npm install
```

3. Rode o projeto:
```bash
npm run dev
```

4. Acesse:  
http://localhost:5174

---

## 🛠️ Funcionalidades
- Login via JWT
- Listagem, cadastro, edição e deleção de médicos
- Listagem, cadastro, edição e deleção de pacientes
- Agendamento de consultas
- Filtros por data, médico e paciente
- Autorização por perfis (admin, médico, paciente)
- Testes unitários com JUnit e Mockito
- Proteção de rotas frontend com token
- Tema escuro estilizado

---

## 🎨 Tema Escuro

Design inspirado no universo do U2 — elegante, escuro, impactante:
- Background geral: `#121212`
- Painéis e caixas: `#1e1e1e`
- Textos: `#f5f5f5`
- Destaques: vermelho profundo `#950707`

---

## 📁 Estrutura de Diretórios

```
agendamento-medico/
├── backend/
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── repository/
│   ├── service/
│   ├── security/
│   └── config/
└── frontend/
    ├── src/
    │   ├── assets/
    │   ├── views/
    │   ├── router/
    │   ├── stores/
    │   ├── services/
    │   └── App.vue
    └── vite.config.js
```

---

## 🧪 Testes Automatizados

1. Execute os testes com:
```bash
mvn test
```

2. Testes presentes para:
- Médicos
- Pacientes
- Agendamentos
- Controllers com `@SpringBootTest`

---

## 🔐 Autenticação JWT

### Exemplo de login:
```json
POST /login
{
  "login": "admin",
  "senha": "123456"
}
```

### Resposta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

### Envie o token nas requisições protegidas:
```
Authorization: Bearer SEU_TOKEN
```

---

## 🧾 Tabelas

- `usuarios`: login, senha (criptografada), role, paciente_id, medico_id, admin_id  
- `medicos/pacientes/admin`: dados pessoais  
- `agendamentos`: data, descrição, médico, paciente  

---

## 🎯 Próximos Passos
- Listagem de agendamentos com visual agradável
- Responsividade no frontend
- Deploy em ambiente cloud (Render, Railway, etc.)
- Criação de painel do médico e paciente

---

## 🚀 Rodando o Projeto do Zero

```bash
# BACKEND
cd backend
mvn spring-boot:run

# FRONTEND
cd ../frontend
npm install
npm run dev
```

---

## 💻 Desenvolvido por

**Ivan Lott** — com café, código e muitas horas escutando música.