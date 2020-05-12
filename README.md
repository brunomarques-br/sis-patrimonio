<h4>Interface JSF e API REST para o gerenciamento de patrimônios de uma empresa</h4>

- O guia de configuração de cada ambiente `Backend` e `Frontend` encontra-se em suas rescpectivas pastas seguidas por um arquivo README.md

<h3>Endpoints da API Restful</h3>

<h4>Marcas</h4>

- GET marcas/ - Obter todas as marcas.
- GET marcas/page - Obter todas as marcas utilizando paginador.
- GET marcas/{id} - Obter a marca por um id.
- GET marcas/{id}/patrimonios – Obter todos os patrimônios de uma marca
- POST marcas - Inserir uma nova marca
- PUT marca/{id} - Alterar os dados de uma marca
- DELETE marca/{id} - Excluir uma marca

<h4>Patrimônios</h4>

- GET patrimonios/ - Obter todos os patrimônios
- GET patrimonios/page - Obter todas as marcas utilizando paginador.
- GET patrimonios/{id} - Obter um patrimônio por ID
- POST patrimonios - Inserir um novo patrimônio
- PUT patrimonios/{id} - Alterar os dados de um patrimônio
- DELETE patrimonios/{id} - Excluir um patrimônio

<h3>:rocket: Bibliotecas - Front-end</h3>

 - Apache Tomcat - 9.0.34 
 - Jersey - 2.29.1 
 - Java Server Faces - 2.2 
 - Gson - 2.8.5 
 - Lombok - 1.18.10 
 - Primefaces - 6.2 
 
 <h3>:rocket: Bibliotecas - Back-end</h3>

- Java 8, utilizando recursos da versão.
- Springboot - 2.2.6 
- Lombok - 1.18.12 
- Mysql - 8.0.19 
