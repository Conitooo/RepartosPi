# RepartosPi

RepartosPi es una app full stack de pedidos en la que estoy trabajando para practicar desarrollo web con backend y frontend separados.

El proyecto está dividido en dos partes:

- `RepartosPiBackend` → backend con Spring Boot
- `RepartosPiFront` → frontend con Angular

## Tecnologías

- Java 21
- Spring Boot
- PostgreSQL
- Angular
- TypeScript
- Docker

## Estructura del proyecto

```bash
RepartosPi/
├── RepartosPiBackend/
├── RepartosPiFront/
└── docker-compose.yml
```

```bash
git clone https://github.com/Conitooo/RepartosPi.git
cd RepartosPi
```

## Levantar el backend

```bash
cd RepartosPiBackend
```
Windows
```bash
mvnw.cmd spring-boot:run
```

Linux o macOS
```bash
./mvnw spring-boot:run
```

El backend arranca en 

```bash
http://localhost:8080
```

## Levantar el frontend

```bash
cd RepartosPiFront
npm install
npx ng serve
```
El frontend arranca en

```bash
http://localhost:4200
```

## Docker

Desde la raíz del proyecto

```bash
docker compose up --build
```

Y para pararlo

```bash
docker compose down
```



