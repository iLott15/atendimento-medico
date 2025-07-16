Sistema de Agendamento Médico 🩺

Este é um projeto fullstack desenvolvido como parte de um desafio técnico. Seu objetivo é permitir o agendamento de consultas médicas de forma simples, segura e escalável.

⸻

🧰 Tecnologias Utilizadas

Backend
	•	Java 17
	•	Spring Boot 3.4.7
	•	Spring Data JPA
	•	Spring Security + JWT
	•	PostgreSQL
	•	Maven
	•	Swagger (Documentação de API)
	•	JUnit + Mockito (Testes)

Frontend (em breve)
	•	Vue.js

⸻

⚙️ Instalação de Ferramentas

1. Node.js (Vue)

$ node -v
$ npm -v

Instale em: https://nodejs.org

2. Vue CLI

$ npm install -g @vue/cli
$ vue --version

3. Java (JDK 17)

$ brew install openjdk@17
$ echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
$ source ~/.zshrc
$ java -version

4. PostgreSQL

$ brew install postgresql
$ brew services start postgresql
$ createuser -s postgres
$ createdb agendamento_db
$ psql postgres


⸻

🏗️ Criação do Projeto Spring Boot
	•	Site: start.spring.io
	•	Project: Maven
	•	Language: Java
	•	Spring Boot: 3.4.7
	•	Group: com.agendamento
	•	Artifact: backend
	•	Nome do projeto: backend
	•	Java version: 17
	•	Packaging: Jar

Dependências adicionadas:
	•	Spring Web
	•	Spring Data JPA
	•	PostgreSQL Driver
	•	Spring Security
	•	DevTools
	•	Validation

⸻

🛠️ Configuração do Banco de Dados

src/main/resources/application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/agendamento_db
spring.datasource.username=ivanlott
spring.datasource.password=atendimentomedico
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=8080


⸻

▶️ Execução do Projeto

$ ./mvnw spring-boot:run

Caso necessário:

$ brew install maven
$ mvn spring-boot:run

Backend disponível em: http://localhost:8080

⸻

✅ Funcionalidades Implementadas
	•	Cadastro, listagem, edição e remoção de médicos, pacientes e agendamentos
	•	Filtros por médico, paciente e data
	•	Validações com @Valid
	•	Autenticação via JWT (com login e geração de token)
	•	Controle de acesso por perfil (ROLE_ADMIN, ROLE_MEDICO, ROLE_PACIENTE)
	•	Testes com JUnit e Mockito
	•	Documentação com Swagger (/swagger-ui/index.html)

⸻

🔐 Autenticação com JWT
	•	Rota pública:

POST /login
{
  "login": "usuario",
  "senha": "123456"
}

	•	Resposta:

{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}

	•	Para autenticar, envie o token no header:

Authorization: Bearer SEU_TOKEN_AQUI


⸻

📄 Swagger

Acesse:

http://localhost:8080/swagger-ui/index.html


⸻

🔁 Passo a Passo – Cadastro via API (Insomnia ou Postman)

Criar Médico

POST http://localhost:8080/medicos

{
  "nome": "The Edge",
  "email": "the.edge@u2.com",
  "crm": "777",
  "especialidade": "NEUROCIRURGIA",
  "endereco": {
    "logradouro": "Rua Joshua Tree",
    "bairro": "Rockville",
    "cep": "4321-000",
    "cidade": "Dublin",
    "uf": "IR",
    "numero": "1",
    "complemento": "Estúdio 360"
  }
}

Atualizar Médico

PUT http://localhost:8080/medicos/2

{
  "id": 2,
  "nome": "Bono",
  "email": "bono@u2.com",
  "crm": "12314",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Rua Cedarwood",
    "bairro": "Rockville",
    "cep": "32412-000",
    "cidade": "Dublin",
    "uf": "IE",
    "numero": "2",
    "complemento": "Estúdio HTDAB"
  }
}


⸻

🧪 Testes Automatizados

Testes criados:
	•	MedicoServiceTest
	•	PacienteServiceTest
	•	AgendamentoServiceTest
	•	AgendamentoControllerTest

⸻

🧾 Estrutura das Tabelas
	•	usuarios: id, login, senha, role, paciente_id, medico_id, admin_id
	•	medicos, pacientes, admin: informações pessoais
	•	agendamentos: data, motivo, descrição, FK para médico e paciente

⸻

🧭 Organização do Projeto

agendamento-medico/
│
├── backend/
│   ├── controller/
│   ├── dto/
│   ├── model/
│   ├── repository/
│   ├── service/
│   ├── config/
│   └── security/
└── frontend/ (em breve)


⸻

📌 Observações Finais
	•	Projeto com arquitetura limpa (Controller → Service → Repository)
	•	Separação por perfis (ROLE_ADMIN, ROLE_MEDICO, ROLE_PACIENTE)
	•	Validações robustas e tratamento de exceções

⸻

🎯 Próximos passos
	•	Finalizar frontend em Vue.js
	•	Adicionar testes de integração
	•	Realizar deploy (Render, Railway ou outro)

⸻

Developed with ☘️, rock and code — by Ivan Lott