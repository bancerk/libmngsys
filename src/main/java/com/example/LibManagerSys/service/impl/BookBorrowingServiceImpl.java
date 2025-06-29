package com.example.LibManagerSys.service.impl;

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

@Service
@RequiredArgsConstructor
public class BookBorrowingServiceImpl implements IBookBorrowingService {

    private final BookRepository bookRepository;
    private final BookBorrowingRepository bookBorrowingRepository;
    private final ModelMapper modelMapper;

    @Override
    public BookBorrowingResponse save(BookBorrowingSaveRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found: " + request.getBookId()));

        if (book.getStock() <= 0) {
            throw new RuntimeException("Book has no stock left");
        }

        BookBorrowing borrowing = new BookBorrowing();
        borrowing.setBook(book);
        borrowing.setBorrowerName(request.getBorrowerName());
        borrowing.setBorrowerEmail(request.getBorrowerEmail());
        borrowing.setBorrowingDate(request.getBorrowingDate());
        borrowing.setReturnDate(null);

        book.setStock(book.getStock() - 1);

        bookRepository.save(book);
        return modelMapper.map(bookBorrowingRepository.save(borrowing), BookBorrowingResponse.class);
    }

    @Override
    public List<BookBorrowingResponse> getAll() {
        return bookBorrowingRepository.findAll().stream()
                .map(b -> {
                    BookBorrowingResponse res = modelMapper.map(b, BookBorrowingResponse.class);
                    res.setBookName(b.getBook().getName());
                    return res;
                }).toList();
    }

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
