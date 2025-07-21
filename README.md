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

Certifique-se de instalar os seguintes requisitos antes de rodar o projeto:

- **Node.js 18+**  
  Baixe em [nodejs.org](https://nodejs.org/).  
  Ou instale via terminal:

  ```bash
  # macOS (Homebrew)
  brew install node@18

  # Ubuntu/Debian
  curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
  sudo apt-get install -y nodejs

  # Windows (Chocolatey)
  choco install nodejs-lts
  ```

- **JDK 17**  
  Baixe em [Oracle](https://www.oracle.com/java/technologies/downloads/) ou [Adoptium](https://adoptium.net/).  
  Ou instale via terminal:

  ```bash
  # macOS (Homebrew)
  brew install openjdk@17

  # Ubuntu/Debian
  sudo apt-get install openjdk-17-jdk

  # Windows (Chocolatey)
  choco install openjdk17
  ```

- **PostgreSQL**  
  Baixe em [postgresql.org/download](https://www.postgresql.org/download/).  
  Ou instale via terminal:

  ```bash
  # macOS (Homebrew)
  brew install postgresql

  # Ubuntu/Debian
  sudo apt-get install postgresql postgresql-contrib

  # Windows (Chocolatey)
  choco install postgresql
  ```

- **Maven**  
  Baixe em [maven.apache.org](https://maven.apache.org/download.cgi).  
  Ou instale via terminal:

  ```bash
  # macOS (Homebrew)
  brew install maven

  # Ubuntu/Debian
  sudo apt-get install maven

  # Windows (Chocolatey)
  choco install maven
  ```

Após instalar, verifique as versões com:

```bash
node -v
java -version
psql --version
mvn -v
```

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

- `usuarios`: id, nome, role, email e telefone, e data da criação
- `pacientes`: dados pessoais + endereço completo (logradouro, número, bairro, cidade, UF, cep)  
- `medicos`: nome, especialidade, email, telefone, horários  
- `agendamentos`: data, descrição, médico, paciente  
- `admin`: login, senha, role, vinculação com paciente/médico/admin  

1. Tabela usuarios
Armazena informações de login, senha, papel do usuário e vínculo com paciente, médico ou admin.

  CREATE TABLE usuarios (
      id SERIAL PRIMARY KEY,
      login VARCHAR(50) UNIQUE NOT NULL,
      senha VARCHAR(255) NOT NULL,
      role VARCHAR(20) NOT NULL, -- Ex: 'paciente', 'medico', 'admin'
      paciente_id INTEGER,       -- FK opcional
      medico_id INTEGER,         -- FK opcional
      -- FKs para vinculação
      FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
      FOREIGN KEY (medico_id) REFERENCES medicos(id)
  );

2. Tabela pacientes
Armazena dados pessoais e endereço completo.

  CREATE TABLE pacientes (
      id SERIAL PRIMARY KEY,
      nome VARCHAR(100) NOT NULL,
      data_nascimento DATE,
      cpf VARCHAR(14) UNIQUE,
      logradouro VARCHAR(100),
      numero VARCHAR(10),
      bairro VARCHAR(50),
      cidade VARCHAR(50),
      uf CHAR(2),
      cep VARCHAR(9)
  );

3. Tabela medicos
Armazena informações do médico.

  CREATE TABLE medicos (
      id SERIAL PRIMARY KEY,
      nome VARCHAR(100) NOT NULL,
      especialidade VARCHAR(50),
      email VARCHAR(100) UNIQUE,
      telefone VARCHAR(20),
      horarios TEXT -- Pode ser ajustado para uma tabela separada se necessário
  );

4. Tabela agendamentos
Relaciona médicos e pacientes em um agendamento.

  CREATE TABLE agendamentos (
      id SERIAL PRIMARY KEY,
      data TIMESTAMP NOT NULL,
      descricao TEXT,
      medico_id INTEGER NOT NULL,
      paciente_id INTEGER NOT NULL,
      FOREIGN KEY (medico_id) REFERENCES medicos(id),
      FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
  );

5. Tabela admin

  CREATE TABLE admin (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );

Dicas:

Ajuste os tipos de dados conforme a necessidade do seu projeto.
Use SERIAL (PostgreSQL) ou AUTO_INCREMENT (MySQL) para IDs automáticos.
Considere normalizar horários dos médicos em uma tabela separada se for necessário detalhar mais.
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

Inspirado na estética mais escura:
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
Com café, código, reggae, rock, e as vezes um rap, no repeat. ☕🎧