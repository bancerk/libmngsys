package com.example.LibManagerSys.service.interfaces;

import com.example.LibManagerSys.dto.request.BookSaveRequest;
import com.example.LibManagerSys.dto.response.BookResponse;

import java.util.List;

public interface IBookService {

    BookResponse save(BookSaveRequest request);

    List<BookResponse> getAll();

    BookResponse getById(Long id);

    BookResponse update(Long id, BookSaveRequest request);

    void delete(Long id);
}

