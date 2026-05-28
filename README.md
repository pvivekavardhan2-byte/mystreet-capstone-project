# MyStreeT Sneaker Shopping Application

A full-stack capstone project built for the **MyStreeT Foundation Certification**.

This project is a simple sneaker shopping web application where users can browse sneakers, view product details, add products to cart, checkout using mock payment, and admins can manage products.

---

## 1. Project Overview

**MyStreeT Sneaker Shopping Application** is a full-stack e-commerce application developed using **Angular**, **Spring Boot REST APIs**, and **H2/PostgreSQL database**.

The project covers the complete shopping flow:

1. User registration and login
2. Product catalog browsing
3. Product detail view
4. Cart management
5. Checkout with mock payment
6. Order creation
7. Admin product management
8. API testing and unit testing

---

## 2. Technology Stack

| Layer | Technology |
|---|---|
| Frontend | Angular, TypeScript, HTML, CSS/SCSS |
| Backend | Java 21, Spring Boot, REST APIs |
| Database | H2 for local development, PostgreSQL for final setup |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security, JWT, BCrypt |
| API Documentation | Swagger / OpenAPI |
| Testing | JUnit5, Mockito, Postman |
| Build Tools | Maven, npm |
| Version Control | Git, GitHub |

---

## 3. Required Versions

```bash
java -version
# Java 21

node -v
# v24.11.1

npm -v
# 11.6.2
```

Recommended tools:

```text
Java 21
Maven
Node.js v24.11.1
npm 11.6.2
Angular CLI
PostgreSQL
Git
Postman
VS Code / IntelliJ IDEA
```

---

## 4. Features

### 4.1 Authentication

- User registration
- User login
- JWT token generation
- Password hashing using BCrypt
- Logout from frontend
- Admin flag validation

User types:

- Normal User
- Admin User

Admin is handled using a simple boolean field:

```java
private boolean isAdmin;
```

Default admin:

```text
Email: admin@mystreet.com
Password: admin123
```

---

### 4.2 Product Catalog

- View all sneakers
- View product details
- Filter products by brand
- Filter products by size
- Display product image, price, description, available sizes, and stock

---

### 4.3 Shopping Cart

- Add product to cart
- Remove product from cart
- Update quantity
- Calculate total amount
- Store cart in browser localStorage
- Cart persists after page refresh

---

### 4.4 Checkout

- Enter shipping address
- Select mock payment mode
- Create order
- Order status is set to `PLACED`
- Show order confirmation page
- Display order ID and item summary

Payment modes:

```text
CASH_ON_DELIVERY
MOCK_UPI
```

---

### 4.5 Admin Product Management

- Add product
- Edit product
- Delete product
- View products in admin dashboard
- Only admin users can access admin screens and admin APIs

---

## 5. Architecture

```text
Angular Frontend
        |
        | REST API Calls
        |
Spring Boot Backend
        |
        | Spring Data JPA / Hibernate
        |
H2 / PostgreSQL Database
```

---

## 6. Backend Folder Structure

```text
mystreet-backend/
│
├── src/main/java/com/mystreet
│   ├── controller
│   │   ├── AuthController.java
│   │   ├── ProductController.java
│   │   └── OrderController.java
│   │
│   ├── service
│   │   ├── AuthService.java
│   │   ├── ProductService.java
│   │   └── OrderService.java
│   │
│   ├── repository
│   │   ├── UserRepository.java
│   │   ├── ProductRepository.java
│   │   ├── OrderRepository.java
│   │   └── OrderItemRepository.java
│   │
│   ├── entity
│   │   ├── User.java
│   │   ├── Product.java
│   │   ├── Order.java
│   │   └── OrderItem.java
│   │
│   ├── dto
│   │   ├── LoginRequest.java
│   │   ├── RegisterRequest.java
│   │   ├── AuthResponse.java
│   │   ├── ProductRequest.java
│   │   ├── ProductResponse.java
│   │   ├── OrderRequest.java
│   │   └── OrderResponse.java
│   │
│   ├── security
│   │   ├── JwtUtil.java
│   │   ├── JwtAuthenticationFilter.java
│   │   └── SecurityConfig.java
│   │
│   ├── exception
│   │   ├── GlobalExceptionHandler.java
│   │   └── ErrorResponse.java
│   │
│   └── config
│       └── DataLoader.java
│
├── src/test/java/com/mystreet
│   └── service
│       ├── ProductServiceTest.java
│       ├── AuthServiceTest.java
│       └── OrderServiceTest.java
│
├── pom.xml
└── README.md
```

---

## 7. Frontend Folder Structure

```text
mystreet-frontend/
│
├── src/app
│   ├── core
│   │   ├── guards
│   │   │   ├── auth.guard.ts
│   │   │   └── admin.guard.ts
│   │   │
│   │   ├── interceptors
│   │   │   └── auth.interceptor.ts
│   │   │
│   │   └── services
│   │       ├── auth.service.ts
│   │       ├── product.service.ts
│   │       ├── cart.service.ts
│   │       └── order.service.ts
│   │
│   ├── features
│   │   ├── auth
│   │   │   ├── login
│   │   │   └── register
│   │   │
│   │   ├── products
│   │   │   ├── product-list
│   │   │   └── product-detail
│   │   │
│   │   ├── cart
│   │   ├── checkout
│   │   ├── orders
│   │   └── admin
│   │       └── product-management
│   │
│   ├── shared
│   │   ├── models
│   │   └── components
│   │
│   └── app.routes.ts
│
├── package.json
└── README.md
```

---

## 8. Database Design

### 8.1 users

```text
id
email
password_hash
is_admin
created_at
```

### 8.2 product

```text
id
name
brand
description
price
image_url
sizes_csv
stock_qty
created_at
```

### 8.3 orders

```text
id
user_id
shipping_address
payment_mode
status
total_amount
created_at
```

### 8.4 order_item

```text
id
order_id
product_id
size
quantity
price
```

---

## 9. API Endpoints

### 9.1 Authentication APIs

#### Register User

```http
POST /api/auth/register
```

Request:

```json
{
  "email": "user@gmail.com",
  "password": "password123"
}
```

#### Login User

```http
POST /api/auth/login
```

Request:

```json
{
  "email": "admin@mystreet.com",
  "password": "admin123"
}
```

Response:

```json
{
  "token": "jwt-token",
  "email": "admin@mystreet.com",
  "admin": true
}
```

---

### 9.2 Product APIs

```http
GET /api/products
GET /api/products/{id}
GET /api/products?brand=Nike
GET /api/products?size=9
POST /api/products
PUT /api/products/{id}
DELETE /api/products/{id}
```

Admin APIs:

```text
POST /api/products
PUT /api/products/{id}
DELETE /api/products/{id}
```

Sample product request:

```json
{
  "name": "Air Max 90",
  "brand": "Nike",
  "description": "Classic retro sneaker",
  "price": 119.99,
  "imageUrl": "https://picsum.photos/seed/airmax/400",
  "sizesCsv": "7,8,9,10",
  "stockQty": 50
}
```

---

### 9.3 Order APIs

```http
POST /api/orders
GET /api/orders/mine
GET /api/orders/{id}
```

Sample order request:

```json
{
  "items": [
    {
      "productId": "product-id",
      "size": "9",
      "quantity": 2
    }
  ],
  "shippingAddress": "Bangalore, Karnataka",
  "paymentMode": "MOCK_UPI"
}
```

Sample response:

```json
{
  "orderId": "order-id",
  "status": "PLACED",
  "totalAmount": 239.98
}
```

---

## 10. Error Response Format

```json
{
  "timestamp": "2026-05-28T10:30:00Z",
  "path": "/api/orders",
  "error": "VALIDATION_ERROR",
  "message": "Shipping address is required"
}
```

---

## 11. Security

Security features:

- BCrypt password hashing
- JWT authentication
- Authorization header for secured APIs
- Admin route protection
- Backend admin validation
- Input validation
- Global exception handling

Authorization header:

```http
Authorization: Bearer <jwt-token>
```

---

## 12. Backend Setup

Go to backend folder:

```bash
cd mystreet-backend
```

Run backend:

```bash
mvn spring-boot:run
```

Backend URL:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

H2 Console:

```text
http://localhost:8080/h2-console
```

---

## 13. PostgreSQL Setup

Create database:

```sql
CREATE DATABASE mystreet_db;
```

Update PostgreSQL configuration in `application-prod.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mystreet_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

Run with PostgreSQL profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

---

## 14. Frontend Setup

Go to frontend folder:

```bash
cd mystreet-frontend
```

Install dependencies:

```bash
npm install
```

Run Angular application:

```bash
ng serve
```

Frontend URL:

```text
http://localhost:4200
```

---

## 15. Run Order

```text
1. Start database: H2 or PostgreSQL
2. Run Spring Boot backend
3. Verify APIs using Swagger or Postman
4. Run Angular frontend
5. Open http://localhost:4200
```

---

## 16. Testing

### Backend Unit Tests

Run tests:

```bash
cd mystreet-backend
mvn test
```

Target coverage:

```text
60% or above
```

Main test cases:

- Register user
- Login user
- Create product
- Get product by ID
- Filter products
- Place order
- Validate stock
- Admin product CRUD validation

---

### API Testing Using Postman

Import this file into Postman:

```text
MyStreeT.postman_collection.json
```

Test APIs in this order:

```text
1. Register User
2. Login User
3. Get All Products
4. Get Product By ID
5. Admin Create Product
6. Admin Update Product
7. Admin Delete Product
8. Place Order
9. Get My Orders
```

---

## 17. Swagger Testing

After starting backend, open:

```text
http://localhost:8080/swagger-ui/index.html
```

For secured APIs, add JWT token:

```text
Bearer <jwt-token>
```

---

## 18. GitHub Commands

Initialize Git:

```bash
git init
git add .
git commit -m "Initial commit - MyStreeT capstone project"
```

Add remote:

```bash
git remote add origin https://github.com/pvivekavardhan2-byte/mystreet-capstone-project.git
```

Push code:

```bash
git branch -M main
git push -u origin main
```

If wrong remote exists:

```bash
git remote remove origin
git remote add origin https://github.com/pvivekavardhan2-byte/mystreet-capstone-project.git
git push -u origin main
```

---

## 19. Sprint Delivery Plan

### Sprint 1: Foundation and Product Catalog

Backend:

- Create Spring Boot project
- Configure H2 database
- Configure PostgreSQL profile
- Create Product entity
- Create Product repository
- Create Product service
- Create Product controller
- Create product list API
- Create product detail API
- Add seed product data
- Add Swagger setup

Frontend:

- Create Angular project
- Create Angular routing
- Create product model
- Create product service
- Create product list component
- Create product detail component
- Display products in grid layout
- Connect Angular with backend APIs

Output:

- Product listing page works
- Product detail page works
- Product APIs tested in Swagger/Postman

---

### Sprint 2: Authentication and Cart

Backend:

- Create User entity
- Create Auth controller
- Create Auth service
- Create register API
- Create login API
- Add BCrypt password hashing
- Add JWT token generation
- Add Spring Security config
- Add JWT filter
- Add admin flag

Frontend:

- Create login page
- Create register page
- Create auth service
- Store JWT token
- Create auth guard
- Create admin guard
- Create auth interceptor
- Create cart service
- Add item to cart
- Remove item from cart
- Update quantity
- Store cart in localStorage

Output:

- User can register
- User can login
- JWT token is stored
- Cart persists after refresh
- Admin route protection works

---

### Sprint 3: Checkout and Admin CRUD

Backend:

- Create Order entity
- Create OrderItem entity
- Create order repository
- Create order service
- Create order controller
- Create place order API
- Create my orders API
- Add stock decrement logic
- Add admin product create API
- Add admin product update API
- Add admin product delete API
- Add validation
- Add global exception handling
- Add JUnit5 and Mockito test cases

Frontend:

- Create checkout page
- Create order confirmation page
- Create my orders page
- Create admin dashboard
- Create product add form
- Create product edit form
- Create delete product option
- Add success and error messages

Output:

- User can place order
- Order confirmation displays order ID
- Admin can manage products
- Unit tests are added
- README is completed
- Postman collection is ready

---

## 20. Acceptance Criteria

### Product Listing

- Guest can open home page
- At least 8 products are displayed
- Each product shows name, price, and image
- User can filter products by brand and size

### Authentication

- User can register with new email
- User can login with valid credentials
- JWT token is stored after login
- User remains logged in after refresh

### Cart

- User can add items to cart
- User can update quantity
- User can remove items
- Cart total updates instantly
- Cart persists in localStorage

### Checkout

- Shipping address is mandatory
- Payment mode is mandatory
- Order is created successfully
- Order status is PLACED
- Confirmation page shows order ID

### Admin

- Only admin can access admin product management
- Admin can add product
- Admin can update product
- Admin can delete product
- Product changes reflect in catalog

---

## 21. Definition of Ready

A user story is ready when:

- Requirement is clear
- Acceptance criteria is defined
- API changes are identified
- Database changes are identified
- UI reference or basic wireframe is available

---

## 22. Definition of Done

A feature is done when:

- Code compiles successfully
- Backend unit tests pass
- API tested using Postman or Swagger
- UI tested manually
- No major console errors
- No critical lint errors
- README is updated
- Code is pushed to GitHub

---

## 23. Non-Functional Requirements

### Performance

- API response time should be less than 1 second in local development
- Frontend page should load within 5 seconds on localhost

### Security

- Passwords must be hashed
- Admin APIs must be protected
- User input must be validated
- JWT token must be verified

### Logging

- Console logs for basic debugging
- Backend request/error logs

### Documentation

- README file
- Swagger API documentation
- Postman collection
- Setup steps
- Seed data details

---

## 24. Future Enhancements

- Product pagination
- Product search bar
- Product sorting
- Wishlist
- Real payment gateway
- Email order confirmation
- Inventory management
- Order status tracking
- Docker deployment
- CI/CD pipeline

---

## 25. Project Author

```text
Name: Vivekavardhan
Project: MyStreeT Sneaker Shopping Application
Type: Foundation Capstone Project
Frontend: Angular
Backend: Spring Boot
Database: PostgreSQL / H2
Java Version: 21
Node Version: v24.11.1
npm Version: 11.6.2
```

---

## 26. Final Deliverables

```text
mystreet-backend/
mystreet-frontend/
README.md
MyStreeT.postman_collection.json
Swagger API Documentation
JUnit5 and Mockito Test Cases
GitHub Repository
Screenshots
```

---

## 27. License

This project is created for learning and certification purposes.
