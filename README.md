Documentação Técnica - Sistema de Agendamento Médico
Instalação de Ferramentas
1. Node.js (requisito para Vue.js):
- Site: https://nodejs.org
- Verificação após instalação:
$ node -v
$ npm -v
2. Vue CLI (interface de criação de projetos Vue):
- Instalação global:
$ npm install -g @vue/cli
- Verificação:
$ vue --version
3. Java (JDK 17):
- Instalação via Homebrew:
$ brew install openjdk@17
- Adição ao PATH:
$ echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
$ source ~/.zshrc
- Verificação:
$ java -version
4. PostgreSQL:
- Instalação via Homebrew:
Documentação Técnica - Sistema de Agendamento Médico
$ brew install postgresql
- Inicialização do serviço:
$ brew services start postgresql
- Criação de usuário e banco:
$ createuser -s postgres
$ createdb agendamento_db
- Acesso:
$ psql postgres
Documentação Técnica - Sistema de Agendamento Médico
Criação do Projeto Spring Boot
1. Acessado: https://start.spring.io
2. Configurações utilizadas:
- Project: Maven
- Language: Java
- Spring Boot: 3.4.7
- Group: com.agendamento
- Artifact: backend
- Nome do projeto: backend
- Java version: 17
- Packaging: Jar
3. Dependências adicionadas:
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Spring Security
- DevTools
- Validation
4. Projeto gerado e salvo dentro da pasta `agendamento-medico/backend`.
Documentação Técnica - Sistema de Agendamento Médico
Configuração do Banco de Dados
1. Arquivo editado: src/main/resources/application.properties
2. Conteúdo adicionado:
spring.datasource.url=jdbc:postgresql://localhost:5432/agendamento_db
spring.datasource.username=ivanlott
spring.datasource.password=atendimentomedico
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=8080
Documentação Técnica - Sistema de Agendamento Médico
Execução do Projeto
1. Dentro da pasta backend:
$ ./mvnw spring-boot:run
2. Caso o mvnw não esteja presente, instalar Maven globalmente:
$ brew install maven
$ mvn spring-boot:run
3. O backend ficará disponível em: http://localhost:8080
Passo a Passo – Preenchimento do Banco via API (Spring Boot + Insomnia)
1. Criar entidade Medico (Medico.java)
- Campos: id, nome, email, crm, especialidade, endereco (@Embedded)
2. Criar entidade Endereco (Endereco.java)
- Campos: logradouro, bairro, cep, cidade, uf, numero, complemento (@Embeddable)
3. Criar MedicoRepository (interface JpaRepository<Medico, Long>)
4. Criar MedicoService com métodos:
- listarTodos, buscarPorId, salvar, atualizar, deletar
5. Criar MedicoController com endpoints:
- GET /medicos, GET /medicos/{id}, POST /medicos, PUT /medicos/{id}, DELETE /medicos/{id}
6. Substituir EntityNotFoundException por ResponseStatusException(HttpStatus.NOT_FOUND, ...)
7. Testar com Insomnia:
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
PUT http://localhost:8080/medicos/2
(mesmo corpo do POST)
Exemplo de Registro Adicionado via PUT:
PUT http://localhost:8080/medicos/2
Content-Type: application/json
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