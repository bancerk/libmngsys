# Library Management System  

This project is a library management system developed using Spring Boot. It supports CRUD operations, book borrowing scenarios, stock control, data validation, and other functionalities.  

## Technologies Used  

- Java 17+  
- Spring Boot  
- Spring Data JPA  
- Hibernate  
- Lombok  
- ModelMapper  
- PostgreSQL  
- Bean Validation 
- RESTful Web Services  


## Layered Architecture  

- `Entity`: Database tables  
- `DTO`: Request/response data models  
- `Repository`: JPA database operations  
- `Service`: Business logic  
- `Controller`: API endpoints  
- `Exception`: Global error handling  


## Validations  

- Data validation at the DTO level using annotations such as `@NotNull`, `@NotBlank`, `@Email`, `@Min`, etc.  
- Invalid requests return a detailed JSON response with `400 Bad Request`.  


## Business Rules  

- Borrowing is blocked if the book is out of stock.  
- A category cannot be deleted if it has linked books.  
- The stock count automatically increases when a book is returned.  


## Global Exception Handling  

All `NotFoundException`, `ValidationException`, and other errors are handled centrally via `@RestControllerAdvice`.  


## API Endpoints

### Authors
- **GET** `/api/authors` - Lists all authors
- **GET** `/api/authors/{id}` - Retrieves a specific author
- **POST** `/api/authors` - Creates a new author
- **PUT** `/api/authors/{id}` - Updates an author
- **DELETE** `/api/authors/{id}` - Deletes an author

### Books
- **GET** `/api/books` - Lists all books
- **GET** `/api/books/{id}` - Retrieves a specific book by ID
- **POST** `/api/books` - Creates a new book
- **PUT** `/api/books/{id}` - Updates book details
- **DELETE** `/api/books/{id}` - Deletes a book
  
### Borrowings
- **GET** `/api/borrowings` - Lists all borrowing records
- **POST** `/api/borrowings` - Borrows a book (checks stock availability)
- **PUT** `/api/borrowings/return/{id}?returnDate=yyyy-MM-dd` - Updates the book return date

### Categories
- **GET** `/api/categories` - Lists all categories
- **GET** `/api/categories/{id}` - Retrieves a specific category
- **POST** `/api/categories` - Creates a new category
- **PUT** `/api/categories/{id}` - Updates a category
- **DELETE** `/api/categories/{id}` - Deletes a category (prevents deletion if linked books exist)

### Publishers
- **GET** `/api/publishers` - Lists all publishers
- **GET** `/api/publishers/{id}` - Retrieves a specific publisher
- **POST** `/api/publishers` - Creates a new publisher
- **PUT** `/api/publishers/{id}` - Updates a publisher
- **DELETE** `/api/publishers/{id}` - Deletes a publisher
