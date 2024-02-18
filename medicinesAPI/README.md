<h1>API para gerência de uma farmácia</h1>

<h2>Sobre a ferramenta</h2>

<h2>Algumas explicações</h2>

### Diretório Controller

Serve para mapear o site, deixando assim os métodos mais organizados

### Diretório DTO
O Data Transfer Object é um padrão de projetos que serve para transportar dados entre vários componentes que compõem um sistema. 

Como em um software é normal termos variáveis usadas e classes em vários locais, é compreensível a possibildade de torná-las públicas. Entretanto, algumas partes dessas entidades podem ser sensíveis e não podem ter essa mesma visibilidade, como senhas ou id por exemplo. 

O DTO serve exatamente para isso: pode ser considerado como um "montador sem dados sensíveis de uma entidade". Se quer listar usuários por exemplo: não é necessário listar todos os dados de todos, até porque é perigoso. 