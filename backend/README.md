<h3> :rocket: Tecnologias usadas </h3>

- Java 8, utilizando recursos da versão.
- Springboot - 2.2.6 
- Lombok - 1.18.12 
- Mysql - 8.0.19 

<h3>Configurando a base de dados</h3>

Existem duas maneiras de configurar a base de dados:

<h4>Primeira: </h4>

- Executando o arquivo dentro desta pasta chamado docker-compose.yml com o docker.
- Senha de acesso default: `p4tr1m0n10`
- Caso queira alterar a senha, mude dentro do arquivo `application.properties` 

<h4>Segunda: </h4>

- Executando dentro do seu SGBD o script de restauração da base de dados.
- O script encontra-se dentro da pasta `db_script/db_patrimonio.sql`
- É necessário deixar criado a base de dados com o nome `db_patrimonio`
- Senha de acesso default: `p4tr1m0n10`
- Caso queira alterar a senha, mude dentro do arquivo `application.properties` 