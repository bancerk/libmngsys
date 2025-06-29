package com.example.LibManagerSys.service.impl;

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

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookResponse save(BookSaveRequest request) {
        Book book = modelMapper.map(request, Book.class);

        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found: " + request.getAuthorId()));

        Publisher publisher = publisherRepository.findById(request.getPublisherId())
                .orElseThrow(() -> new NotFoundException("Publisher not found: " + request.getPublisherId()));

        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategories(categories);

        return modelMapper.map(bookRepository.save(book), BookResponse.class);
    }

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

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book not found: " + id);
        }
        bookRepository.deleteById(id);
    }
}