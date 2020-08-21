### Relatório de compras do governo do Brasil

## Dependências

 - java 8
 - maven 3.5 >
 - docker 19 >
 - docker-compose 1.26 >
 
## Executando na máquina local
 - Necessário uma versão recente do mysql 5.7 ou superior
 
 O primeiro passo é criar o banco "conquer" e a table "contrato", utilize o arquivo *src/resources/sql/schema.sql* para criá-los. 
 
 Com a table criada já é possível subir o projeto:
 ```cmd
 mvn spring-boot:run 
```
Neste momento a table contrato estará vazia, para populá-la com os dados da api do governo
existe um endpoint especial para isso:

```cmd
curl --location --request POST 'localhost:9090/api/contrato/sync'
```

## Executando com o Docker
 Basta executar:
 ```cmd
docker-compose build
```
e depois um:
```cmd
docker-compose up
```
O mysql deverá estar rodando na porta 9096 e o Spring boot
na 8182

Acesse a aplicação pelo endereço: 
http://localhost:8182


