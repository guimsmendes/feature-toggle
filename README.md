# Serviço de Feature Toggle
## Descrição
Este repositório contém o código de _kickstart_ do desafio de criação de um Serviço de Feature Toggle simples.
## Status



[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=FEATURE_TOGGLE_V1)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=security_rating)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=security_rating)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=ncloc)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=bugs)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=code_smells)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=FEATURE_TOGGLE_V1&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1)

![Maven Package](https://github.com/guimsmendes/feature-toggle/workflows/Maven%20Package/badge.svg?branch=1.0.2)
<img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/guimsmendes/feature-toggle">
<img alt="GitHub Release Date" src="https://img.shields.io/github/release-date/guimsmendes/feature-toggle">


## Tabela de Conteúdo
## Descrição

## Tecnologias utilizadas

#### Desenvolvimento
* Java JDK 1.8
* Lombok
* H2 Database
* SpringBoot
* Hibernate - EntityManager
* 
Lombok
Rest


#### Build/Packaging
* Maven

#### Tests
* JUnit
* Mockito

#### Code Review/Inspection
* SonarCloud

#### API Documentation
* Swagger

#### Deploy
* AWS Beanstalk

#### Observability
* AWS Cloudwatch - Kinesis



## Layout/Deploy da aplicação

## Pré-requisitos

## Dependências e Libs instaladas
## Como rodar a aplicação

### API
* AWS:

* LocalHost:

### Package
Para realizar a instalação da dependência **itau-desafio-feature-toggle** no seu projeto, primeiro é necessário gerar um token de acesso via Github.
* Siga o passo a passo abaixo utilizando como escopo `read:packages`
https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/creating-a-personal-access-token

Com o token em mãos, será necessário criar um arquivo `settings.xml` na sua pasta **~/.m2** conforme exemplo abaixo:
* Substitua o campo `USERNAME` pelo seu usuário Github
* Substitua o campo `TOKEN` pelo token gerado no passo anterior

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
          <releases><enabled>true</enabled></releases>
          <snapshots><enabled>true</enabled></snapshots>
        </repository>
        <repository>
          <id>github</id>
          <name>GitHub guimsmendes Apache Maven Packages</name>
          <url>https://maven.pkg.github.com/guimsmendes/feature-toggle</url>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
```
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
https://sonarcloud.io/dashboard?id=FEATURE_TOGGLE_V1


## Desenvolvedores

[<img src="https://ibb.co/k8Ck2Qk" width=115 > <br> <sub> Guilherme Mendes </sub>](https://github.com/guimsmendes) |
| :---: |  


## Solução de Problemas

### Release 1.0.3

 https://stackoverflow.com/questions/53679361/cant-import-classes-intellij-showing-boot-inf-prefix-and-it-seems-to-be-relate

### Release 1.0.2

https://medium.com/swlh/sonarqube-sonarscanner-setup-1a633b654828

https://softwareengineer.medium.com/code-coverage-in-sonarqube-for-maven-projects-56f7a1a4d496

### Release 1.0.1

https://github.com/Nallamachu/SwaggerConfiguration



## Links úteis
