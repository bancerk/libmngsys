package com.example.LibManagerSys.controller;

import com.example.LibManagerSys.dto.request.PublisherSaveRequest;
import com.example.LibManagerSys.dto.response.PublisherResponse;
import com.example.LibManagerSys.service.interfaces.IPublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final IPublisherService publisherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherResponse save(@RequestBody @Valid PublisherSaveRequest request) {
        return publisherService.save(request);
    }

    @GetMapping
    public List<PublisherResponse> getAll() {
        return publisherService.getAll();
    }

    @GetMapping("/{id}")
    public PublisherResponse getById(@PathVariable Long id) {
        return publisherService.getById(id);
    }

    @PutMapping("/{id}")
    public PublisherResponse update(@PathVariable Long id, @RequestBody @Valid PublisherSaveRequest request) {
        return publisherService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        publisherService.delete(id);
        return ResponseEntity.ok("Yayınevi silindi.");
    }
}

