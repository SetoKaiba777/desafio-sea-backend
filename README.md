# Desafio Back-End SEA Solutions

## Descrição do problema

O desafio consiste em construir um backend para o front-end no link abaixo:
[Front-End:Desafio SpringBoot](https://www.figma.com/file/Xksngjqmb9KNJ6lnsGLK1x/Desafio-React?node-id=0:1)

O Projeto utilizou as seguintes tecnologias:

- Spring Boot 2.5.4
- Java 11 
- PostgreSQL
- Docker
- Gradle

## Modelagem Do Banco De Dados

Para modelar o banco de dados, utilizei a seguinte estrutura  de relacionamentos:
- Um Setor pode ter de 0 à N Cargos vinculados a ele.
- Um Cargo deve ter EXTAMENTE 1 único setor associado, e pode ter de 0 à N funcionários vinculados.
- Um funcionário deve ser vinculado a EXTAMENTE 1 único cargo.

Além disso, coloquei os seguintes vínculos nas entidades:

- O Nome de cada setor é único.
- O CPF de cada Trabalhador é único.

## Executando o projeto
Para executar o projeto será necessário subir o container do banco de dados através do docker-compose. O script está contido na pasta docker do projeto podendo ser executado através do seguinte comando


    docker-compose up

Após subir os containters, para a primeira execução do projeto será necessário executar o processo de build da aplicação através do comando

    ./gradlew build

Após a conclusão dessa etapa, basta subir a aplicação através do comando

    ./gradlew bootRun

## Acesso à documentação e detalhes técnicos

Com o objetivo de melhorar visualização do funcionamento do projeto, criei uma massa de dados inicial a qual reinicia o banco de dados a cada execução da aplicação. É possivel
verificar o comportamento do banco através do adminer, contido no docker-compose. Sua uri é dada por:
-   http://localhost:80/

E os dados para acesso ao do banco são:

- System: PostgreSQL
- Server: db
- Username: postgres
- Password: postgres
- Database: figma_database

Para acessar a documentação swagger da aplicação e os endpoints, basta acessar o swagger através da url:
-   http://localhost:8080/swagger-ui-figma

Desenvolvi um tratamento de erros personalizado através de annotation @ControlerAdvice, permitindo melhor compreensão dos erros mostrados na tela do usuário.

Vale ressaltar que em nenhum momento da entrada ou saída de dados da implementação, 
o usuário tem acesso à entidade do banco de dados propriamente dito. Tudo é feito através de DTOs, o que pode ser conferido através do Swagger.

Realizei também a cobertura dos testes unitários das classes de Controller e Service.
