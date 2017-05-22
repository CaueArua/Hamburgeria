# Hamburgeria

Documentação para Rodar: 
 
 * Fazer checkout do projeto.
 * Adicioná-lo ao eclipse.
 * Adicioná-lo ao Tomcat.
 * Executar o Tomcat.
 * O aplicativo estará rodando sob a url “/Hamburgeria”.
	
Justificativas para escolha do Spring MVC
  Escolhi como framework de desenvolvimento o Spring Mvc por se tratar de um framework completo que consegue gerenciar tanto a parte de web application quanto a parte de banco de dados, tornando assim mais fácil a evolução do código.

Justificativas para escolha do design de código

Dentre os padrões de projeto utilizados friso estes:
  
1. Factory Pattern: 
	Utilizei o padrão Factory para facilitar a criação de objetos que seriam re utilizado em diversos locais, Dentro do projeto essa situação se da na parte dos testes unitários. 

2. Builder Pattern
	Assim como as Factories utilizei Builders nos testes unitários para evitar replicação de código e facilitar a escrita dos testes
  
3. Template Pattern
  Uma vez que os descontos podem ser modificados, e ou adicionados novos criei modelos de templates para facilitar a evolução do código e diminuir o acoplamento do código,

4. MVC Pattern
	O padrão mvc Foi usado no desenvolvimento da tela, deste modo toda lógica de negócio está dentro do servidor, facilitando a manutenção e evitando que a tela execute ações que não são de sua alçada.

5. Data Access Object Pattern
	Separando as regras de negócio da implementação de acesso a estrutura de armazenamento de dados facilitei a  evolução do código tornando a conversão a utilização de um banco de dados mais simples e rápida. Esse padrão também garante um menor acoplamento no código.


