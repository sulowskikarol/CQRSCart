# CQRSCart – Shopping Cart Service (CQRS + Clean Architecture)

An educational project developed as part of the *Advanced Programming Techniques* course.
A shopping cart service built with a focus on **scalability**, **clean separation of concerns**, and **modularity**, based on **CQRS**, the **Mediator pattern**, and **Clean Architecture** principles.

---

## Features

* Create a new cart
* Add and remove products
* View cart contents and total value
* Finalize the cart and submit for order processing
* HTTP integration with external **Product Service**

---

## Tech stack & design patterns

* **Spring Boot 3**
* **Java 17**
* **CQRS** – Command Query Responsibility Segregation
* **Mediator Pattern** – custom implementation
* **Clean Architecture** – domain-centric, layered
* **Docker & Docker Compose**
* **PostgreSQL** – relational storage
* **RestTemplate** – HTTP client for service-to-service calls

---

## Project structure

```
cart-service/
├── domain/            → Domain model, entities, repositories
├── application/       → Commands, queries, handlers, mediators
├── infrastructure/    → JPA mappings, HTTP clients, configuration
├── presentation/      → REST API (controllers, exceptions)
└── docker-compose.yml → Local environment (Postgres + Product mock)
```

---

## REST API Endpoints

| Method  | Endpoint                  | Description                |
| ------- | ------------------------- | -------------------------- |
| `POST`  | `/carts`                  | Create a new cart          |
| `POST`  | `/carts/product`          | Add product to cart        |
| `DELETE`| `/carts/product`          | Remove product from cart   |
| `GET`   | `/carts`                  | View cart contents         |
| `PATCH` | `/carts/finalize`         | Finalize and lock the cart |

---

## Product Service integration

While adding a product to the cart, the application will contact the Product Service to get the price:

```
GET http://product-service:8080/product/{productId}/price
```

A simple mock version of the Product Service is provided and started via Docker Compose.

---
