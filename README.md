<img src = "https://i.ibb.co/2jfpZSR/guimsmendes-feature-toggle-dark.png">

[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=FEATURE_TOGGLE_V1)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=security_rating)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=ncloc)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=bugs)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=code_smells)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)

![Maven Package](https://github.com/guimsmendes/feature-toggle/workflows/Maven%20Package/badge.svg?branch=1.0.2)
<img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/guimsmendes/feature-toggle">
<img alt="GitHub Release Date" src="https://img.shields.io/github/release-date/guimsmendes/feature-toggle">


## Tópicos
* [Descrição](#descrição)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Pré Requisitos](#pré-requisitos)
* [Como rodar a aplicação](#como-rodar-a-aplicação)
* [Observability](#observability)
* [Desenvolvedores](#desenvolvedores)
* [Solução de Problemas](#solução-de-problemas)


## Descrição

Este projeto implanta um recurso de **Feature Toggle** utilizando Clean Architecture com objetivo de alternar entre diferentes caminhos o código fonte de forma que funcionalidades possam ser testadas e completadas antes de serem lançadas em produção.

A aplicação poderá ser utilizada de duas formas: via **API** ou instalada no seu projeto como uma **dependência**.

* Para as chamadas via **API**, o usuário poderá chamar o caminho de três formas, ou via domínio implantado pela **AWS Elastic Container Service** por integração contínua (**URL:** http://awseb-awseb-9rii3cdahste-1914091692.us-east-2.elb.amazonaws.com/), via imagem **Docker** ou via **local host** (**URL:** http://localhost:8080). Confira o passo a passo para se conectar com a API no tópico [Como rodar a aplicação: API](#api).
* Para ser instalada a **dependência** no seu projeto, confira o passo a passo para instalar o pacote no tópico [Como rodar a aplicação: Package](#package)

### Recursos

A API irá expôr 3 endpoints a fim de realizar o **cadastro**, **verificar** e **deletar** uma Feature Flag.


- **POST /toggle** - deverá receber as informações enviadas no Body da solicitação em Json e registrar a feature toggle no banco de dados em memória contido na aplicação.
- **GET /toggle/{nome}?valor=[valor]** - deverá receber o nome e opcionalmente, o valor da feature toggle. Caso exista um feature toggle cadastrado no banco de dados em memória, caso o tipo seja **TOGGLE**, deverá ser retornado considerado como **ATIVO** (retornando como solicitação com sucesso). Caso o tipo seja **VALUE**, o valor inserido deverá ser comparado com o valor em memória, e caso o valor inserido seja maior, deverá ser retornado como **ATIVO**. Caso contrário, como **INATIVO**.
- **DELETE /toggle/{nome}** - deverá receber o nome da feature toggle, e caso exista no banco de dados em memória, a feature toggle deverá ser excluída do banco de dados.

Além dos recursos informados, a rota também contém um endpoint do **Swagger** que expõe a documentação da API:
> http://feature-toggle.us-east-2.elasticbeanstalk.com/swagger-ui/index.html

Os métodos chamados via API, também foram disponibilizados para ser integrado na sua aplicação pelo package contido neste repositório. Após a instalação da dependência, poderão ser chamados os métodos registrarFeatureFlag(), verificarFeatureFlag() e apagarFeatureFlag() a partir do Controller da aplicação (FeatureToggleController)
 

## Tecnologias utilizadas

#### Desenvolvimento: [<img src="https://img.shields.io/static/v1?label=spring&message=2.3.4&color=brightgreen&style=for-the-badge&logo=SPRING" width = 120>](https://spring.io/) [<img src="https://img.shields.io/static/v1?label=jdk&message=1.8.0_271&color=orange&style=for-the-badge&logo=JAVA" width = 125>](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)

* [Lombok](https://projectlombok.org/)
* [H2 Database](https://www.h2database.com/html/main.html)
* [Hibernate](https://hibernate.org/)


#### Build/Packaging : [<img src="https://img.shields.io/static/v1?label=apache&message=maven&color=red&style=for-the-badge&logo=apachemaven" width = 120>](https://maven.apache.org/)

#### Tests: [<img src="https://img.shields.io/static/v1?label=junit&message=5&color=green&style=for-the-badge&logo=" width = 70>](https://junit.org/junit5/)

#### Code Review/Inspection: [<img src="https://img.shields.io/static/v1?label=sonarsource&message=3.7.0&color=blue&style=for-the-badge&logo=sonarsource" width = 150>](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)


#### API Documentation: [<img src="https://img.shields.io/static/v1?label=swagger&message=3.0.0&color=brightgreen&style=for-the-badge&logo=swagger" width = 125>](http://feature-toggle.us-east-2.elasticbeanstalk.com/swagger-ui/index.html)

#### Integração Contínua: [<img src="https://img.shields.io/static/v1?label=docker&message=hub&color=blue&style=for-the-badge&logo=docker" width = 120>](https://aws.amazon.com/pt/elasticbeanstalk/)


#### Deploy: [<img src="https://img.shields.io/static/v1?label=aws&message=ECS&color=orange&style=for-the-badge&logo=amazonaws" width = 80>](https://aws.amazon.com/pt/ecs/?whats-new-cards.sort-by=item.additionalFields.postDateTime&whats-new-cards.sort-order=desc&ecs-blogs.sort-by=item.additionalFields.createdDate&ecs-blogs.sort-order=desc)

#### Observability: [<img src="https://img.shields.io/static/v1?label=spring&message=actuator&color=brightgreen&style=for-the-badge&logo=SPRING" width = 130>](http://feature-toggle.us-east-2.elasticbeanstalk.com/actuator)


## Pré requisitos

Para poder rodar a aplicação na sua máquina, é necessário ter as seguintes tecnologias instaladas:

* [Java JDK 1.8.0_271](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
* [SpringBoot](https://spring.io/)
* Maven

Para rodar a imagem da aplicação, é necessário apenas ter instalado na sua máquina:
* [Docker](https://www.docker.com/)


## Como rodar a aplicação

### API

**Via AWS Elastic Container Service:**
* Para rodar a API, foi disponibilizado um host via deploy na AWS ECS para acessar facilmente os endpoints via Postman:
	* > http://awseb-awseb-9rii3cdahste-1914091692.us-east-2.elb.amazonaws.com/
	
**Via imagem Docker:**
* Para rodar a API pela imagem Docker, faça o download da imagem no link:

* Caso já tenha o Docker instalado em sua máquina, execute o comando:
* `docker pull docker.io/guimsmendes/feature-toggle-flag`
* `docker run -p 8080:8080 docker.io/guimsmendes/feature-toggle-flag`
* **Pronto!** Agora a aplicação já está rodando no seu: 
	* > https://localhost:8080/

**LocalHost:**
* Para rodar a API via localhost, será necessário realizar o clone da aplicação via **cmd**:
	1. Selecione a pasta que deseja instalar a aplicação 
	2. `git clone https://github.com/guimsmendes/feature-toggle.git`
	3. `cd feature-toggle`
	4. `mvn install`
	5. `cd target`
	6. `java -jar itau-desafio-feature-toggle-1.0.3.jar`
* **Pronto!** Agora a aplicação já está rodando no seu: 
	* > https://localhost:8080/

**Endpoints criados:**
* `/actuator` - Retorna os endpoints com informações de saúde da aplicação
* `/swagger-ui/index.html` - Retorna a documentação da API
* POST `/toggle` - Realiza o registro das informações inseridas no body seguindo o modelo JSON:
	* {"nome": "nome-do-feature-toggle","tipo": "value","valor": 123.45,"ligado": true}
* GET `/toggle/{nome}?valor=[valor]` - Verifica se existe um feature toggle com o nome inserido. Caso o feature toggle seja do tipo Value, deverá ser adicionado ao campo opcional "valor" um valor maior que o registrado para que seja verificado com sucesso.
* DELETE `/toggle/{nome}` - Deleta o feature toggle com o nome inserido.
		


### Package
Para realizar a instalação da dependência **itau-desafio-feature-toggle** no seu projeto, primeiro é necessário gerar um token de acesso via Github.
* Siga o [passo a passo](https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/creating-a-personal-access-token) utilizando como escopo `read:packages`


Com o token em mãos, será necessário criar um arquivo `settings.xml` na sua pasta **~/.m2** conforme esta [documentação](https://docs.github.com/en/free-pro-team@latest/packages/guides/configuring-apache-maven-for-use-with-github-packages#authenticating-to-github-packages).
* Substitua o campo `OWNER` por guimsmendes
* Substitua o campo `REPOSITORY` por feature-toggle
* Substitua o campo `USERNAME` pelo seu usuário Github
* Substitua o campo `TOKEN` pelo token gerado no passo anterior

**PRONTO!** Agora é só importar a dependência conforme o package disponível neste **repositório** no `pom.xml` do seu projeto.

Para utilizar os recursos desta biblioteca, será necessário importar o pacote FeatureToggleController na sua classe:
* `import br.com.itau.seguros.desafio.entrypoint.FeatureToggleController;`

Instanciar a classe FeatureToggleController:
* `FeatureToggleController featureToggleController;`

E a partir disso, você está pronto para utilizar os recursos gerados nesta biblioteca:
* `featureToggleController.registrarFeatureFlag(FeatureToggleRequest featureToggleRequest);`
* `featureToggleController.verificarFeatureFlag(String nome, String valor);`
* `featureToggleController.apagarFeatureFlag(String nome);`

## Database

As informações inseridas via API ao realizar um post serão incluídas em um embedded database do **H2 database** na aplicação, que será deletada na próxima vez que a aplicação for executada.

Abaixo segue o modelo de dados deste banco de dados:

### FeatureToggle: 
|nome|tipo|valor|ligado|
| -------- | -------- | -------- |-------- |
|**(String)** "nome-do-feature-toggle" |**(TipoToggleEnum)** VALUE or TOGGLE|**(BigDecimal)** 123.45|**(boolean)** true or false|

## Observability

A fim de acompanhar as métricas da **saúde** da aplicação, foram disponibilizadas métricas via [Actuator](http://feature-toggle.us-east-2.elasticbeanstalk.com/actuator) e seus respectivos endpoints.

## Desenvolvedores

[<img src="https://i.ibb.co/swYk9yk/IMG-20200826-164306.jpg" width=115 > <br> <sub> Guilherme Mendes </sub>](https://github.com/guimsmendes) |
| :---: |  



## Solução de Problemas

###

### Release 1.0.7
O recurso **AWS ECR** para disponibilização de uma imagem Docker só está conseguindo subir para imagens privadas. O comando `aws ecr-public` não está mapeado nas versões da aws-cli por mais que esteja sendo indicado o uso nas documentações.
Desta forma, para poder disponibilizar uma imagem pública para o usuário, foi substituído o recurso AWS ECR para uma imagem no **DockerHub**.

### Release 1.0.6
Ajustado erro de cluster no deploy da **AWS ECS** no arquivo `aws.yml`

### Release 1.0.5
Na Release 1.0.5 foi substituído o serviço **AWS Beanstalk** para hospedagem da API pelo recurso **AWS Elastic Container Service**, a fim de facilitar a integração contínua. Foi adicionado um `aws.yml` para que toda vez que uma alteração seja disponibilizada em uma Release, um pipeline seja acionado e a imagem Docker seja atualizada, assim como o serviço hospedado no domínio da AWS ECS.

### Release 1.0.4

Na Release 1.0.4 foi adicionado o arquivo **Dockerfile** para subir a imagem da aplicação Docker via workflow **AWS** para **ECR** (Elastic Container Registry) a fim de hospedar a imagem na cloud pública para facilitar a execução da aplicação em outras máquinas.

### Release 1.0.3

Na Release 1.0.3 foi adicionado o `<classifier> exec </classifier>` no **spring-boot-maven-plugin** a fim de possibilitar o import dos pacotes SpringBoot pela dependência gerada
> https://stackoverflow.com/questions/53679361/cant-import-classes-intellij-showing-boot-inf-prefix-and-it-seems-to-be-relate

### Release 1.0.2

Na Release 1.0.2 foram ajustados bugs e code smells do código a partir da análise via **SonarQube**

> https://medium.com/swlh/sonarqube-sonarscanner-setup-1a633b654828

> https://softwareengineer.medium.com/code-coverage-in-sonarqube-for-maven-projects-56f7a1a4d496

### Release 1.0.1

Na Release 1.0.1 foram configuradas as dependências para documentação da API via **Swagger**

> https://github.com/Nallamachu/SwaggerConfiguration

