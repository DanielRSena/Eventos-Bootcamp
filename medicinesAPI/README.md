# API para gerência de uma farmácia 💊

## Sobre a ferramenta 💊💊

## Algumas explicações

### FlyWay

Serve para a criação de tabelas e contrle de migrações em um DB.

### Diretório Controller 🔗

Serve para mapear o site, deixando assim os métodos mais organizados

### Diretório DTO 🛠️
O Data Transfer Object é um padrão de projetos que serve para transportar dados entre vários componentes que compõem um sistema. 

Como em um software é normal termos variáveis e classes referenciadas em vários locais, é compreensível a possibildade de torná-las públicas em sua declaração. 

Entretanto, algumas de suas partes podem ser sensíveis e não podem ter essa mesma visibilidade, como senhas ou id por exemplo. Ex: Se quer listar todos os trabalhadores de uma empresa, como faremos nessa API, não é necessário listar todos os dados de cada pessoa, até porque é perigoso ter seu CPF ou endereço conseguido de forma tão fácil. 

O DTO serve exatamente para isso: pode ser considerado como um montador personalizado. 