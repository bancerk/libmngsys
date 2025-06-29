package com.example.LibManagerSys.controller;

// Import necessary classes for DTOs, service interface, validation, and Spring annotations
import com.example.LibManagerSys.dto.request.CategorySaveRequest;
import com.example.LibManagerSys.dto.response.CategoryResponse;
import com.example.LibManagerSys.service.interfaces.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST controller and sets the base request mapping for category-related endpoints
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor // Generates a constructor for required fields (final fields)
public class CategoryController {

    // Service layer dependency for category operations, injected via constructor
    private final ICategoryService categoryService;

    // Handles POST requests to create a new category
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 on success
    public CategoryResponse save(@RequestBody @Valid CategorySaveRequest request) {
        return categoryService.save(request);
    }

    // Handles GET requests to retrieve all categories
    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }

    // Handles GET requests to retrieve a specific category by ID
    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    // Handles PUT requests to update an existing category by ID
    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody @Valid CategorySaveRequest request) {
        return categoryService.update(id, request);
    }

    // Handles DELETE requests to remove a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = categoryService.delete(id);
        return ResponseEntity.ok(message); // Returns a success message from the service
    }
}

