# uShorter API

## Visão geral
O projeto uShorter é um aplicativo de encurtamento de URL que permite aos usuários converter URLs longas em versões encurtadas. Ele oferece uma solução eficaz para compartilhar links longos de forma mais conveniente e fácil de lembrar. Além disso, o sistema fornece estatísticas detalhadas sobre o uso de cada URL encurtada, incluindo contagem de cliques.

## Características
- Gerar URL curta
- Fazer redirecionamento
- Mostrar quantidade de cliques em uma URL

## Começando

### Requisitos

- Java: 17
- Maven: 3.8.5 +
- Sua IDE (IntelliJ IDEA, STS, etc.)
- Docker e Docker Compose

### Instalação

#### 1) Utilizando Docker e Docker Compose

 - Pegar o repositório do git e extrair em uma pasta local
     ```bash
    git clone https://github.com/CharlesHenr1queDEV/uShorter.git 
    ```
- Navegar até a pasta 
    ```bash
    cd uShorter
    ```
- Builda o projeto:
    ```bash
    mvn clean install
    ```
- Gerar uma imagem docker
     ```bash
     docker build --tag=ushorter:latest .
    ```
- Execute o docker-compose 
     ```bash
    docker-compose up ou docker-compose up -d
    ```
    - Certifique-se de ter o Docker e docker-compose instalado em sua maquina
    - Certifique-se de que as portas configuradas não estejam sendo utilizadas.
    - Configure as conexões do MySQL e Redis de acordo com sua necessidade.  Obs: Por padrão já está tudo configurado.

##### 2) Utilizando uma IDE

- Pegar o repositório do git e extrair em uma pasta local
     ```bash
    git clone git clone https://github.com/CharlesHenr1queDEV/uShorter.git 
    ```
- Navegar até a pasta 
    ```bash
    cd uShorter
    ```
- Atualizar as dependências
    ```bash
    mvn clean install
    ```
- Importar o projeto em sua IDE
    - Import existing maven project

- Executar o docker-compose somente o MySQL e Redis, remova do compose a aplicação.

- Run ou Run Debug

##### 3) Utilizando somente Docker compose

- Baixar o arquivo docker-compose ou copiar o conteúdo
   [Link: docker-compose]()
- Executar o arquivo
    ```bash
    docker-compose up 
    ```
    - A imagem docker já está no docker hub aberta, com isso nesse arquivo já tem toda a configuração para execução do projeto.

### Testes
O projeto inclui testes de integração da api para garantir o funcionamento da aplicação. Execute os testes usando:
```bash
  mvn test
 ```

### Uso
 #####  Acesse a documentação do swagger para obter detalhes sobre a API
     ``http://localhost:8080/swagger-ui/index.html``

### Documentação da API

#### Gerar Url curta
Endpoint:/

Method: POST

Parameters:
- url:  Url que você deseja encurtar

Request Body:
- Content Type: application/json

Example Obj:
```json
{
  "url": "http://www.google.com",
}
```

#### Redirecionar url
Endpoint: /{shortUrl}

Method: GET

Parameters:
- shortUrl:  Url curta

Path variable parm:
- shortUrl: ``http://localhost:8080/xysenas"``


#### Buscar quantidade de cliques em uma URL
Endpoint: /url-click

Method: GET

Parameters:
- shortUrl:  Url curta

Request Header:
- shortUrl: ``http://localhost:8080/xysenas"``

# Equipe de desenvolvedores

* Charles Henrique  - [Linkedin](https://www.linkedin.com/in/charles-henrique-5b432a143/)
