package com.example.LibManagerSys.controller;

import com.example.LibManagerSys.dto.request.AuthorSaveRequest;
import com.example.LibManagerSys.dto.response.AuthorResponse;
import com.example.LibManagerSys.service.interfaces.IAuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor

public class AuthorController {

    private final IAuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse save(@RequestBody @Valid AuthorSaveRequest request) {
        return authorService.save(request);
    }

    @GetMapping
    public List<AuthorResponse> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PutMapping("/{id}")
    public AuthorResponse update(@PathVariable Long id, @RequestBody @Valid AuthorSaveRequest request) {
        return authorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok("Yazar silindi.");
    }
}

