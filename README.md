# foreign-exchange-service

You can run application with two approach.

## First approach ##

Firstly, please change your current directory to docker inside.

> cd docker

Then, you should run the command below for running postgresql and liquibase on the postgresql.

> docker-compose -f docker-compose-postgres-only.yml up

Now you can run the application.

> ./mvnw spring-boot:run

If you want to remove all running containers, you can use the command below.

> docker-compose -f docker-compose-postgres-only.yml down -v

## Second approach ##

> cd docker

> docker-compose up -d

> docker-compose down -v

### API Documentation ###

http://localhost:8080/swagger-ui/index.html

Conversion Capability API

```bash
curl -i -X POST \
-H "Content-Type:application/json" \
-d \
'{
    "sourceAmount": 1,
    "sourceCurrency": "USD",
    "targetCurrency": "TRY"
}' \
 'http://localhost:8080/conversion-capability/convert'
```

Conversion Search API

```bash
curl http://localhost:8080/conversions?transactionId=f1cf34aa-fe5b-4926-82cf-f113ff969bed
```

```bash
curl http://localhost:8080/conversions?transactionDate=2023-06-05
```

Exchange Rate API

```bash
curl http://localhost:8080/exchange-rates/USD-TRY \
 -H "Accept: application/json"
```

This request will get an error because currency pairs has no valid pattern.

```bash
curl http://localhost:8080/exchange-rates/EEEBBB \
 -H "Accept: application/json"
```