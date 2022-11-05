### **GFT MILHAS - SPRING MVC**

------

Objetivo: aplicar os conceitos da arquitetura MVC (Model-View-Controller) aprendidos durante o treinamento de Starter da GFT Brasil.


<br>
**PROJETO FINAL**

<img src="https://i.postimg.cc/4dm5QY6y/Meu-projeto-1.jpg" alt="GFTMilhas"/>



**:hammer_and_pick: FERRAMENTAS UTILIZADAS**

- Ambiente de desenvolvimento Eclipse IDE
- Spring Tools 4 para Eclipse
- MySQL Community Server
- HeidiSQL



**:blue_book: FRAMEWORKS / TECNOLOGIAS / BIBLIOTECAS**

- Spring Boot, Spring MVC, Spring Security, Spring Validation, Spring Dev Tools e Spring Data JPA)
- Thymeleaf, HTML5, CSS.
- Bootstrap
- WebJars
- Hibernate
- Linguagem de programação: Java



**:package: ESTRUTURA DO PROJETO**

As classes deste projeto estão organizadas em pacotes, de acordo com o papel de cada uma delas como componente do MVC. 

🔹 O pacote com.gft.projeto.**entities** contém as classes que representam entidades em nossa aplicação;

🔹 O pacote **[...]repositories** contém interfaces que estendem a JpaRepository, com a finalidade de facilitar a parte de persistência de dados da aplicação, quando utilizamos esse framework o mesmo já traz alguns métodos prontos para persistirmos os dados da nossa aplicação;

🔹 O pacote **[...]services** contém a implementação dos métodos de CRUD (Create, Read, Update e Delete) além de algumas operações que contém as regras de negócio da aplicação;

🔹 O pacote **[...]dto** contém classes para transporte de informações, mais especificamente do gerenciamento de eventos (páginas de marcação de atividades feitas, faltas, presenças, etc) para o controlador do evento.

🔹 O pacote **[...]controller** contém os controllers da aplicação, que vão receber as requisições feitas pelo usuário e devolver para ele respostas.

🔹 O diretório de **resources** contém a camada de view do sistema, composta por páginas escritas em HTML5, CSS, e template engine Thymeleaf, esse último nos oferece recursos para criação de loops, estruturas condicionais, entre outras funções. 



**:warning: IMPORTANTE**

- Antes de iniciar o projeto, aguarde o Maven fazer o download de todas as dependências do projeto;
- O diretório nomeado **"DumpMySQL",** contém scripts de banco de dados para agilizar os testes da aplicação.



------



:computer: Autoria do código:

Ana Beatriz Andrade - ana.andrade@gft.com :e-mail:

Ana Beatriz Cirino - ana.cirino@gft.com :e-mail:

Fernanda Cardinaly -  fernanda.silva@gft.com :e-mail:

Karen Escobedo -  karen.escobedo@gft.com :e-mail:





