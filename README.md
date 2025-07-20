# 🩺 Sistema de Agendamento Médico

Projeto fullstack construído como parte de um desafio técnico, com foco em qualidade de código, autenticação segura e arquitetura escalável. Permite gerenciar médicos, pacientes e agendamentos.

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

### 🔜 Frontend (Vue 3)
- Vue 3 + Composition API
- Vue Router
- Pinia (State Management)
- Vite
- Prettier
- SweetAlert2 (alertas modernos)
- Toastify (notificações)
- Estilo escuro personalizado 🎸

---

## ⚙️ Instalação e Execução

### ✅ Pré-requisitos
- Node.js 18+
- JDK 17
- PostgreSQL
- Maven

---

### 🔧 Backend

```bash
git clone https://github.com/seuusuario/agendamento-medico.git
cd agendamento-medico/backend
```

1. Crie o banco de dados:

```bash
createdb agendamento_db
createuser seu-usuario --superuser
```

Ou via SQL:

```sql
CREATE USER seu-usuario WITH PASSWORD 'atendimentomedico';
CREATE DATABASE agendamento_db OWNER seu-usuario;
```

2. Configure `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/agendamento_db
spring.datasource.username=seu-usuario
spring.datasource.password=atendimentomedico
```

3. Rode a aplicação:

```bash
./mvnw spring-boot:run
# ou
mvn spring-boot:run
```

---

### 🌐 Teste o Backend

- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Login exemplo:

```json
POST /login
{
  "login": "admin",
  "senha": "123456"
}
```

---

### 💻 Frontend

```bash
cd ../frontend
npm install
npm run dev
```

Acesse via navegador:
[http://localhost:5174](http://localhost:5174)

---

## 🛠️ Funcionalidades

- 🔐 Login JWT com autenticação segura
- 👨‍⚕️ CRUD de médicos
- 🧍 CRUD de pacientes (com endereço completo)
- 📅 Agendamentos com filtro por data, médico e paciente
- 🛡️ Autorização por perfis (`admin`, `medico`, `paciente`)
- 🧪 Testes com JUnit e Mockito
- 🔒 Rotas protegidas no frontend
- 🖤 Tema escuro com visual inspirado no U2
- 💾 Validação e conversão automática de campos de formulário
- ✏️ Edição de paciente com modal estilizado
- 🗑 Confirmação de exclusão via SweetAlert2

---

## 📦 Estrutura do Projeto

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
    │   ├── components/
    │   ├── router/
    │   ├── stores/
    │   ├── services/
    │   └── App.vue
```

---

## 🔐 JWT - Exemplo de Uso

```http
POST /login
{
  "login": "admin",
  "senha": "123456"
}
```

Resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

Use em chamadas autenticadas:

```
Authorization: Bearer SEU_TOKEN
```

---

## 🧾 Tabelas Principais

- `usuarios`: login, senha, role, vinculação com paciente/médico/admin  
- `pacientes`: dados pessoais + endereço completo (logradouro, número, bairro, cidade, UF, cep)  
- `medicos`: nome, especialidade, email, telefone, horários  
- `agendamentos`: data, descrição, médico, paciente  

---

## 🧪 Testes Automatizados

```bash
mvn test
```

Cobertura para:
- CRUDs
- Validações
- Agendamento
- Autenticação

---

## 🎨 Tema Visual

Inspirado na estética do U2:
- Fundo: `#000000`
- Cartões: `#ffffff` com sombras suaves
- Botões: `#007bff`, `#ffc107`, `#dc3545`
- Texto: `#333`, `#fff` em destaque
- Alertas: SweetAlert estilizado

---

## 📌 Próximas Features

- Tela de dashboard com indicadores
- Tela de agenda semanal do médico
- Vínculo direto de pacientes a médicos
- Responsividade mobile
- Deploy no Railway, Vercel, ou Render

---

## 💻 Executando o Projeto

```bash
# Backend
cd backend
mvn spring-boot:run

# Frontend
cd ../frontend
npm install
npm run dev
```

---

## 👨‍💻 Desenvolvido por

**Ivan Lott**  
Com café, código, e o som de *Beautiful Day* no repeat. ☕🎧