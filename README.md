# 📚 Biblioteca API

A modern RESTful API for library management built with **Spring Boot 4** and **Java 21**.

## ✨ Features

- **Book Management** – Full CRUD operations for books with ISBN validation
- **Author Management** – Create and manage authors with their bibliographies
- **User Management** – User registration with email validation
- **Loan System** – Track book loans with expiration dates and return management
- **API Documentation** – Interactive Swagger UI with OpenAPI 3.0
- **Database Migrations** – Version-controlled schema with Flyway

## 🛠️ Tech Stack

| Category | Technology |
|----------|------------|
| **Framework** | Spring Boot 4.0.1 |
| **Language** | Java 21 |
| **Database** | PostgreSQL |
| **ORM** | Spring Data JPA / Hibernate |
| **Migrations** | Flyway |
| **API Docs** | SpringDoc OpenAPI 3.0 |
| **Validation** | Jakarta Bean Validation |
| **Utilities** | Lombok |

## 📋 Prerequisites

- Java 21+
- PostgreSQL 15+
- Maven 3.9+

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/Biblioteca-api.git
cd Biblioteca-api
```

### 2. Configure the database

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8081`

## 📖 API Documentation

Once running, access the interactive API documentation:

- **Swagger UI**: http://localhost:8081/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8081/v3/api-docs

## 🔗 API Endpoints

### Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/books` | Get all books |
| `GET` | `/api/books/{id}` | Get book by ID |
| `POST` | `/api/books` | Create a new book |
| `PUT` | `/api/books/{id}` | Update a book |
| `DELETE` | `/api/books/{id}` | Delete a book |
| `PATCH` | `/api/books/{bookId}/author/{authorId}` | Assign author to book |

### Authors

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/author` | Create a new author |

## 🏗️ Project Structure

```
src/main/java/com/biblioteca_api/biblioteca/
├── BibliotecaApplication.java    # Application entry point
├── controller/                   # REST controllers
│   ├── BookController.java
│   └── AuthorController.java
├── dto/                          # Data Transfer Objects
│   ├── BookRequestDTO.java
│   ├── BookResponseDTO.java
│   ├── AuthorRequestDTO.java
│   └── AuthorResponseDTO.java
├── entities/                     # JPA entities
│   ├── Book.java
│   ├── Author.java
│   ├── User.java
│   └── Loan.java
├── infra/                        # Infrastructure & configs
│   ├── GlobalExceptionHandler.java
│   └── exceptions/
├── repository/                   # Spring Data repositories
└── service/                      # Business logic layer
```

## 📝 Data Models

### Book
- `id` – Unique identifier
- `title` – Book title (max 150 chars)
- `isbn` – ISBN-13 (unique)
- `price` – Book price
- `publishedDate` – Publication date
- `author` – Associated author

### Author
- `id` – Unique identifier
- `name` – Author name (max 100 chars)
- `birthdate` – Date of birth
- `books` – List of authored books

### User
- `id` – Unique identifier
- `email` – User email (unique)
- `name` – User name
- `password` – Encrypted password

### Loan
- `id` – Unique identifier
- `user` – Borrowing user
- `book` – Borrowed book
- `loanPrice` – Loan fee
- `loanDate` – Start date
- `expirationDate` – Due date
- `returnDate` – Return date (nullable)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<div align="center">
  
**Made with ❤️ using Spring Boot**

</div>
