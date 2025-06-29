package com.example.LibManagerSys.controller;

// Import necessary classes for DTOs, service interface, validation, and Spring annotations
import com.example.LibManagerSys.dto.request.PublisherSaveRequest;
import com.example.LibManagerSys.dto.response.PublisherResponse;
import com.example.LibManagerSys.service.interfaces.IPublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks this class as a REST controller and sets the base request mapping for publisher-related endpoints
@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor // Generates a constructor for required fields (final fields)
public class PublisherController {

    // Service layer dependency for publisher operations, injected via constructor
    private final IPublisherService publisherService;

    // Handles POST requests to create a new publisher
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Returns HTTP 201 on success
    public PublisherResponse save(@RequestBody @Valid PublisherSaveRequest request) {
        return publisherService.save(request);
    }

    // Handles GET requests to retrieve all publishers
    @GetMapping
    public List<PublisherResponse> getAll() {
        return publisherService.getAll();
    }

    // Handles GET requests to retrieve a specific publisher by ID
    @GetMapping("/{id}")
    public PublisherResponse getById(@PathVariable Long id) {
        return publisherService.getById(id);
    }

    // Handles PUT requests to update an existing publisher by ID
    @PutMapping("/{id}")
    public PublisherResponse update(@PathVariable Long id, @RequestBody @Valid PublisherSaveRequest request) {
        return publisherService.update(id, request);
    }

    // Handles DELETE requests to remove a publisher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.ok("Yayınevi silindi."); // Returns a success message
    }
}

