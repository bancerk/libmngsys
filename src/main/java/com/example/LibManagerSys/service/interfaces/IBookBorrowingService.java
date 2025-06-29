package com.example.LibManagerSys.service.interfaces;

import com.example.LibManagerSys.dto.request.BookBorrowingSaveRequest;
import com.example.LibManagerSys.dto.response.BookBorrowingResponse;

import java.time.LocalDate;
import java.util.List;

public interface IBookBorrowingService {

    BookBorrowingResponse save(BookBorrowingSaveRequest request);

    List<BookBorrowingResponse> getAll();

    BookBorrowingResponse returnBook(Long id, LocalDate returnDate);
}

