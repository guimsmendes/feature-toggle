# Desafio: Feature Toggle

Neste desafio, vamos implementar um serivço simples de Feature Toggling.

## 1. Introdução

Feature toggle é um recurso no desenvolvimento de software que tenta prover uma forma dinâmica de se alternar entre diferentes caminhos no código fonte de forma que funcionalidades possam ser testadas e completadas antes de serem lançadas em produção.

Vamos implementar um servidor que irá suportar esse recurso em outras aplicações. Para esse fim, vamos manter uma lista de feature toggles e o estado dos mesmos. Além disso, também teremos uma forma de verificar se um determinado feature toggle está ativo ou inativo.

>Estamos provendo um projeto com um esqueleto desta aplicação para você finalizar, caso queira. Esse projeto está disponível em [https://gitlab.com/g0dkar/itau-desafio-feature-toggle](https://gitlab.com/g0dkar/itau-desafio-feature-toggle) - Este projeto contém controllers, uma suíte de testes integrados para testar sua API e algumas outras coisas que podém ajudar no desenvolvimento.
>
>_Nota:_ O projeto usa Lombok. Verifique se sua IDE está com o Lombok instalado e ativo caso apareçam erros ao abrir o projeto :)

## 2. Definição do desafio

Você deve criar uma API REST que segue **exatamente** a especificação do exercício.

>**ATENÇÃO:** Boa parte da avaliação é feita através de uma suíte de testes automatizados implementada para testar a API descrita aqui e, por esta razão, se sua API não se estiver como especificado aqui ela poderá ser desclassificada.

### 2.1. Restrições

Este projeto pode ser implementado em Java 8+ (preferencialmente 11+) ou Kotlin. O projeto deve seguir o padrão Clean Code, SOLID e/ou ou Hexagonal.

Para este desafio a única restrição é que esta aplicação não pode usar sistemas externos a ela. Isso significa que ela não pode usar banco de dados externos como MySQL, PostgreSQL, MS SQL e similares. O mesmo vale para sistemas como Redis, Memcached, etc.

Caso queira utilizar um banco de dados, recomenda-se o H2 que é um banco de dados em memória que é embutido em sua aplicação. Ou algo como um _embedded_ MongoDB, por exemplo.

A intenção é que seja possível simplesmente clonar o repositório da sua aplicação e executá-la imediatamente, sem a necessidade de instalar/configurar nenhum novo software.

### 2.2. `POST /toggle`

Este endpoint irá receber feature toggles e o estado deles. Caso o feature toggle não exista ele deverá ser criado. Caso o feature toggle já exista, o estado dele deverá ser sobrescrito. Caso o toggle seja gravado com sucesso este endpoint deve retornar `200 OK`.

>**Dica:** Neste desafio você pode usar um banco de dados em memória como o H2 para manter seus dados.

Este endpoint receberá feature toggles como JSON no seguinte formato:

```json
{
    "nome": "nome-do-feature-toggle",
    "tipo": "value",
    "valor": 123.45,
    "ligado": true
}
```

Os campos no JSON acima significam o seguinte:

| Campo    | Significado                                                                                                  | Obrigatório? |
|----------|--------------------------------------------------------------------------------------------------------------|--------------|
| `nome`   | Nome do Feature Toggle. **Deve ser formado apenas de letras, números, hífens e sublinhados. Deve ser único** | Sim          |
| `tipo`   | Tipo do Feature Toggle. Valores possíveis: `toggle` e `value`                                                | Sim          |
| `valor`  | Valor relacionado ao tipo. **Obrigatório apenas para o tipo `value`**                                    | Dinâmico     |
| `ligado` | Indica se o Feature Toggle está ou não ligado                                                                | Sim          |

Os feature toggles suportados poderão ser de dois tipos:

#### 2.2.1. Tipo `toggle`

Essa feature apresenta apenas dois estados: ligado e desligado. Caso a feature esteja ligada, todas as verificações desta feature retornarão como ATIVO. Caso esteja desligada, as verificações sempre retornarão INATIVO.

#### 2.2.2. Tipo `value`

Este tipo de feature toggle tem relação com um valor e por esta razão o campo `valor` se torna obrigatório. No momento da verificação o toggle será considerado ATIVO caso ele esteja ligado **E** o valor de referência seja **igual ou maior** que o valor do toggle. Caso contrário ele será considerado INATIVO.

>Por exemplo, imagine um toggle chamado `exemplo-value` do tipo `value` cujo valor é `5` e que está ligado:
>
>- Verificação para o valor `4`: Toggle retorna como INATIVO, pois apesar de estar ligado o valor é **menor** que o valor do toggle.
>- Verificação para o valor `7`: Toggle retorna como ATIVO, pois ele está ligado e o valor é **maior** que o valor do toggle.

#### 2.2.3. Erros de validação

Erros de validação devem retornar `400 Bad Request` **sem nenhum corpo**.

### 2.3. `GET /toggle/{nome}?valor=[valor]`

Este endpoint irá fazer a verificação de feature toggles. Este endpoint receberá o nome do feature toggle como parte da URL e **opcionalmente** um valor como parâmetro. Ele retornará apenas respostas ATIVO ou INATIVO:

- Caso um feature toggle seja considerado ATIVO este endpoint DEVE retornar `200 OK` **sem nenhum corpo**. Um toggle é consderado ATIVO quando todos estes são verdade:
  1. **Existe** um feature toggle com o nome especificado na URL;
  2. O feature toggle **está ligado**;
  3. **Caso o tipo seja `value`:** o `valor` passado como parâmetro deve ser **igual ou maior** que o `valor` vinculado ao feature toggle.
- Caso um feature toggle seja considerado INATIVO este endpoint DEVE retornar `404 Not Found` **sem nenhum corpo**. Um toggle é consderado INATIVO quando ele não for considerado ATIVO, ou seja:
  - Se o toggle **não existe** ou...
  - Se o toggle **existe mas está desligado** ou...
  - Se o toggle **é do tipo `value`, está ligado mas o `valor` especificado como parâmetro é _menor_ que o `valor` do toggle** ou...
  - Se o toggle **é do tipo `value` mas não há um `valor` especificado como parâmetro**

### 2.4. `DELETE /toggle/{nome}`

Este endpoint apaga um toggle dos registros a partir do seu nome, que será recebido como parte da URL.

### 2.5. Documentação

Neste desafio, gostaríamos que implementasse as APIs do Swagger para documentação geração da documentação automática da API. Além disso, uma documentação básica no seu `README.md` explicando o seguinte:

- **Tecnologias:** Quais tecnologias você está utilizando?
  - Dica: Tente encontrar um meio-termo entre simplicidade e completude, ou seja, é importante especificar que é necessário o Java e a versão do Java, mas é realmente necessário dizer que você está usando o SLF4J para logs?
- **Construção:** Como construir sua aplicação?
  - Dica: A maioria das aplicações no Maven pode ser construída por um simples comando `mvn package`.
- **Execução:** Depois de construída, como executar sua aplicação?
  - Dica: A maioria das aplicações Spring Boot geram um _Fat JAR_ que pode ser executado diretamente com um comando semelhante a `java -jar nome-do-arquivo-construido.jar`

### 2.6. Pontos Extras

Esta sessão apresenta desafios extras para que você teste seus conhecimentos ao máximo, ou simplesmente queira fazer uma API com uma qualidade excepcional! São pontos desejáveis, **mas não são obrigatórios**:

- **Testes Unitários:** Suas classes com regras de negócio tem testes unitários?
  - **Atenção:** Testes que usam `@SpringBootTest` ou `@WebMvcTest` **não** são testes unitários adequados, pois são mais próximos de testes de integração/funcionais!
- **Tratamento de Erros:** Tratar erros usando recursos do próprio Spring Boot
- **Modernização:** Containerizar a aplicação para ser possível construir e executar a aplicação utilizando docker
  - **Dica/Atenção:** Se você implementar isto, lembre também de adicionar instruções para construção e execução da sua aplicação usando docker
- **Observabilidade:** Sua aplicação tem um endpoint de checagem de saúde da API (healthcheck)? Você publica métricas do sistema?
  - **Atenção:** Caso você use o Spring Actuator, não é recomendado ativar todos os endpoints disponívels. O recomendado é ativar apenas o que você precisa.
- **Logs:** Sua aplicação está informando corretamente sobre seu funcionamento e as operações que estão sendo executadas? Quando acontece um erro, é possível e/ou fácil de encontrar o erro para corrigi-lo?

### 2.7. Diferencial / Ponto super-extra

Caso você esteja buscando algo para tornar seu código excepcional, sugerimos que você implemente um **cliente** para seu serviço: uma biblioteca que pode ser importada por outras pessoas para que elas implementem feature toggle no código delas usando o seu serviço!

## 3. Critérios de Avaliação e Desempate

Todas as submissões serão avaliadas através da execução de uma suíte de testes automáticos criados no Postman os quais testarão a API da forma como ela foi descrita aqui.

Além disso, uma leitura do código implementado também será feita para uma melhor compreensão do nível de conhecimento e corretude da implementação do(a) autor(a) da submissão.

Como critérios de desempate, serão observados detalhes como formatação do código, facilidade de leitura e compreensão da implementação, qualidade da implementação, qualidade da documentação, entre outros. Em linhas gerais: códigos que empatarem terão como critério de desempate a qualidade.

-------------------------------------------

_Autor: LINSRAF Rafael M. Lins, 2020-09-28_
