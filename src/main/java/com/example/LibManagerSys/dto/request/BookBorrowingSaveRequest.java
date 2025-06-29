package com.example.LibManagerSys.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookBorrowingSaveRequest {

    @NotBlank
    private String borrowerName;

    @Email
    private String borrowerEmail;

    @NotNull
    private LocalDate borrowingDate;

    @NotNull
    private Long bookId;
}

