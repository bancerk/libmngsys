package com.example.LibManagerSys.controller;

// Import necessary classes for DTOs, service interface, validation, formatting, and Spring annotations
import com.example.LibManagerSys.dto.request.BookBorrowingSaveRequest;
import com.example.LibManagerSys.dto.response.BookBorrowingResponse;
import com.example.LibManagerSys.service.interfaces.IBookBorrowingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// Marks this class as a REST controller and sets the base request mapping for book borrowing endpoints
@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor // Generates a constructor for required fields (final fields)
public class BookBorrowingController {

    // Service layer dependency for book borrowing operations, injected via constructor
    private final IBookBorrowingService bookBorrowingService;

    // Handles POST requests to create a new book borrowing record
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 on success
    public BookBorrowingResponse save(@RequestBody @Valid BookBorrowingSaveRequest request) {
        return bookBorrowingService.save(request);
    }

    // Handles GET requests to retrieve all book borrowing records
    @GetMapping
    public List<BookBorrowingResponse> getAll() {
        return bookBorrowingService.getAll();
    }

    // Handles PUT requests to return a borrowed book by updating its return date
    @PutMapping("/return/{id}")
    public BookBorrowingResponse returnBook(@PathVariable Long id, @RequestParam("returnDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {
        return bookBorrowingService.returnBook(id, returnDate);
    }
}

