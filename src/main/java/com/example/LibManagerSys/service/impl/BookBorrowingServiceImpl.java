package com.example.LibManagerSys.service.impl;

// Import exception, DTOs, entities, repositories, service interface, Lombok, ModelMapper, and Spring annotations
import com.example.LibManagerSys.core.exception.NotFoundException;
import com.example.LibManagerSys.dto.request.BookBorrowingSaveRequest;
import com.example.LibManagerSys.dto.response.BookBorrowingResponse;
import com.example.LibManagerSys.entity.Book;
import com.example.LibManagerSys.entity.BookBorrowing;
import com.example.LibManagerSys.repository.BookBorrowingRepository;
import com.example.LibManagerSys.repository.BookRepository;
import com.example.LibManagerSys.service.interfaces.IBookBorrowingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// Marks this class as a Spring service and generates a constructor for required fields
@Service
@RequiredArgsConstructor
public class BookBorrowingServiceImpl implements IBookBorrowingService {

    // Repository for Book entity operations, injected via constructor
    private final BookRepository bookRepository;
    // Repository for BookBorrowing entity operations, injected via constructor
    private final BookBorrowingRepository bookBorrowingRepository;
    // ModelMapper for mapping between DTOs and entities, injected via constructor
    private final ModelMapper modelMapper;

    // Saves a new book borrowing record, updates book stock, and returns the response DTO
    @Override
    public BookBorrowingResponse save(BookBorrowingSaveRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found: " + request.getBookId()));

        // Check if the book is in stock
        if (book.getStock() <= 0) {
            throw new RuntimeException("Book has no stock left");
        }

        // Create a new borrowing record
        BookBorrowing borrowing = new BookBorrowing();
        borrowing.setBook(book);
        borrowing.setBorrowerName(request.getBorrowerName());
        borrowing.setBorrowerEmail(request.getBorrowerEmail());
        borrowing.setBorrowingDate(request.getBorrowingDate());
        borrowing.setReturnDate(null);

        // Decrease the book stock by 1
        book.setStock(book.getStock() - 1);

        // Save updated book and borrowing record
        bookRepository.save(book);
        return modelMapper.map(bookBorrowingRepository.save(borrowing), BookBorrowingResponse.class);
    }

    // Retrieves all book borrowing records and maps them to response DTOs, including book name
    @Override
    public List<BookBorrowingResponse> getAll() {
        return bookBorrowingRepository.findAll().stream()
                .map(b -> {
                    BookBorrowingResponse res = modelMapper.map(b, BookBorrowingResponse.class);
                    res.setBookName(b.getBook().getName());
                    return res;
                }).toList();
    }

    // Updates a borrowing record as returned, sets return date, and increases book stock
    @Override
    public BookBorrowingResponse returnBook(Long id, LocalDate returnDate) {
        BookBorrowing borrowing = bookBorrowingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No record found: " + id));

        borrowing.setReturnDate(returnDate);

        Book book = borrowing.getBook();
        book.setStock(book.getStock() + 1);

        bookRepository.save(book);
        return modelMapper.map(bookBorrowingRepository.save(borrowing), BookBorrowingResponse.class);
    }
}
