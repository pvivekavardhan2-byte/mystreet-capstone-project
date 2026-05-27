# MyStreeT Sneaker Shopping Application

Full-stack capstone project using Angular frontend, Spring Boot REST backend, JWT authentication, H2/PostgreSQL database, Swagger, JUnit5, Mockito, and Postman.

## Versions Used

- Java: 21
- Spring Boot: 3.3.5
- Node: v24.11.1 supported project setup
- npm: 11.6.2 supported project setup
- Angular: 20.x

## Features

- User register/login with JWT
- BCrypt password hashing
- Product catalog with brand and size filter
- Product detail page
- Cart using localStorage
- Checkout with mock payment
- Order creation with status `PLACED`
- Admin product CRUD
- H2 for development and PostgreSQL profile for final
- Swagger/OpenAPI documentation
- JUnit5 + Mockito sample tests

## Backend Run Steps

```bash
cd mystreet-backend
mvn clean install
mvn spring-boot:run
```

Backend runs at:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

H2 console:

```text
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:mystreetdb
Username: sa
Password: empty
```

## Frontend Run Steps

```bash
cd mystreet-frontend
npm install
npm start
```

Angular app runs at:

```text
http://localhost:4200
```

## Default Admin Login

```text
Email: admin@mystreet.com
Password: admin123
```

## Normal User Flow

1. Open product catalog
2. View product details
3. Add product to cart
4. Register/login
5. Go to checkout
6. Enter shipping address
7. Select mock payment
8. Place order
9. View order confirmation in My Orders

## Admin Flow

1. Login using admin account
2. Go to Admin menu
3. Add/edit/delete products
4. Product catalog updates immediately

## PostgreSQL Profile

Create database:

```sql
CREATE DATABASE mystreet_db;
```

Update credentials in:

```text
mystreet-backend/src/main/resources/application-prod.properties
```

Run with PostgreSQL:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Important APIs

### Auth

```http
POST /api/auth/register
POST /api/auth/login
```

### Products

```http
GET /api/products
GET /api/products/{id}
GET /api/products?brand=Nike
GET /api/products?size=9
POST /api/products
PUT /api/products/{id}
DELETE /api/products/{id}
```

### Orders

```http
POST /api/orders
GET /api/orders/mine
GET /api/orders/{id}
```

## Sprint Plan

### Sprint 1: Foundation and Product Catalog

- Backend setup
- Product entity/repository/service/controller
- Seed data
- Angular product list and detail pages

### Sprint 2: Authentication and Cart

- User registration/login
- JWT security
- Angular guards/interceptor
- Cart with localStorage

### Sprint 3: Checkout and Admin CRUD

- Order and order item entities
- Checkout API
- My orders API
- Admin product management screen
- Unit tests and Swagger/Postman documentation

## Testing

Run backend tests:

```bash
cd mystreet-backend
mvn test
```

