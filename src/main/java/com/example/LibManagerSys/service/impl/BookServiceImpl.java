package com.example.LibManagerSys.service.impl;

// Import exception, DTOs, entities, repositories, service interface, Lombok, ModelMapper, and Spring annotations
import com.example.LibManagerSys.core.exception.NotFoundException;
import com.example.LibManagerSys.dto.request.BookSaveRequest;
import com.example.LibManagerSys.dto.response.BookResponse;
import com.example.LibManagerSys.entity.Author;
import com.example.LibManagerSys.entity.Book;
import com.example.LibManagerSys.entity.Category;
import com.example.LibManagerSys.entity.Publisher;
import com.example.LibManagerSys.repository.AuthorRepository;
import com.example.LibManagerSys.repository.BookRepository;
import com.example.LibManagerSys.repository.CategoryRepository;
import com.example.LibManagerSys.repository.PublisherRepository;
import com.example.LibManagerSys.service.interfaces.IBookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

// Marks this class as a Spring service and generates a constructor for required fields
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    // Repository for Book entity operations, injected via constructor
    private final BookRepository bookRepository;
    // Repository for Author entity operations, injected via constructor
    private final AuthorRepository authorRepository;
    // Repository for Publisher entity operations, injected via constructor
    private final PublisherRepository publisherRepository;
    // Repository for Category entity operations, injected via constructor
    private final CategoryRepository categoryRepository;
    // ModelMapper for mapping between DTOs and entities, injected via constructor
    private final ModelMapper modelMapper;

    // Saves a new book using data from BookSaveRequest and returns the response DTO
    @Override
    public BookResponse save(BookSaveRequest request) {
        Book book = modelMapper.map(request, Book.class);

        // Find and set the author, throw exception if not found
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found: " + request.getAuthorId()));

        // Find and set the publisher, throw exception if not found
        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new NotFoundException("Publisher not found: " + request.getPublisherId()));

        // Find and set the categories
        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategories(categories);

        return modelMapper.map(bookRepository.save(book), BookResponse.class);
    }

    // Retrieves all books and maps them to response DTOs, including author, publisher, and category names
    @Override
    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream()
                .map(book -> {
                    BookResponse response = modelMapper.map(book, BookResponse.class);
                    response.setAuthorName(book.getAuthor().getName());
                    response.setPublisherName(book.getPublisher().getName());
                    response.setCategoryNames(book.getCategories().stream().map(Category::getName).toList());
                    return response;
                }).toList();
    }

    // Retrieves a book by ID, throws NotFoundException if not found, and maps to response DTO
    @Override
    public BookResponse getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found: " + id));
        BookResponse response = modelMapper.map(book, BookResponse.class);
        response.setAuthorName(book.getAuthor().getName());
        response.setPublisherName(book.getPublisher().getName());
        response.setCategoryNames(book.getCategories().stream().map(Category::getName).toList());
        return response;
    }

    // Updates an existing book by ID with new data from BookSaveRequest
    @Override
    public BookResponse update(Long id, BookSaveRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found: " + id));

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found: " + request.getAuthorId()));

        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new NotFoundException("Publisher not found: " + request.getPublisherId()));

        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

        book.setName(request.getName());
        book.setStock(request.getStock());
        book.setPublicationYear(request.getPublicationYear());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategories(categories);

        return modelMapper.map(bookRepository.save(book), BookResponse.class);
    }

    // Deletes a book by ID, throws NotFoundException if not found
    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book not found: " + id);
        }
        bookRepository.deleteById(id);
    }
}