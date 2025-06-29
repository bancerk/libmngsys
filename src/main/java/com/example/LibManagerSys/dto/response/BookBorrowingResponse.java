package com.example.LibManagerSys.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookBorrowingResponse {

    private Long id;
    private String borrowerName;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private String bookName;
}

