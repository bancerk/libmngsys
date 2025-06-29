package com.example.LibManagerSys.controller;

// Import necessary classes for request/response DTOs, service interface, validation, and Spring annotations
import com.example.LibManagerSys.dto.request.AuthorSaveRequest;
import com.example.LibManagerSys.dto.response.AuthorResponse;
import com.example.LibManagerSys.service.interfaces.IAuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST controller and sets the base request mapping for author-related endpoints
@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor // Generates a constructor for required fields (final fields)
public class AuthorController {

    // Service layer dependency for author operations, injected via constructor
    private final IAuthorService authorService;

    // Handles POST requests to create a new author
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 on success
    public AuthorResponse save(@RequestBody @Valid AuthorSaveRequest request) {
        return authorService.save(request);
    }

    // Handles GET requests to retrieve all authors
    @GetMapping
    public List<AuthorResponse> getAll() {
        return authorService.getAll();
    }

    // Handles GET requests to retrieve a specific author by ID
    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    // Handles PUT requests to update an existing author by ID
    @PutMapping("/{id}")
    public AuthorResponse update(@PathVariable Long id, @RequestBody @Valid AuthorSaveRequest request) {
        return authorService.update(id, request);
    }

    // Handles DELETE requests to remove an author by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok("Yazar silindi."); // Returns a success message
    }
}

