package com.example.LibManagerSys.controller;

import com.example.LibManagerSys.dto.request.BookSaveRequest;
import com.example.LibManagerSys.dto.response.BookResponse;
import com.example.LibManagerSys.service.interfaces.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse save(@RequestBody @Valid BookSaveRequest request) {
        return bookService.save(request);
    }

    @GetMapping
    public List<BookResponse> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @RequestBody @Valid BookSaveRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Kitap silindi.");
    }
}

