package com.example.LibManagerSys.controller;

// Import necessary classes for DTOs, service interface, validation, and Spring annotations
import com.example.LibManagerSys.dto.request.BookSaveRequest;
import com.example.LibManagerSys.dto.response.BookResponse;
import com.example.LibManagerSys.service.interfaces.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST controller and sets the base request mapping for book-related endpoints
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor // Generates a constructor for required fields (final fields)
public class BookController {

    // Service layer dependency for book operations, injected via constructor
    private final IBookService bookService;

    // Handles POST requests to create a new book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 on success
    public BookResponse save(@RequestBody @Valid BookSaveRequest request) {
        return bookService.save(request);
    }

    // Handles GET requests to retrieve all books
    @GetMapping
    public List<BookResponse> getAll() {
        return bookService.getAll();
    }

    // Handles GET requests to retrieve a specific book by ID
    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    // Handles PUT requests to update an existing book by ID
    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @RequestBody @Valid BookSaveRequest request) {
        return bookService.update(id, request);
    }

    // Handles DELETE requests to remove a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Kitap silindi."); // Returns a success message
    }
}

