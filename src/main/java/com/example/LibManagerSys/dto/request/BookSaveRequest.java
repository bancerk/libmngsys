package com.example.LibManagerSys.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookSaveRequest {

    @NotBlank
    private String name;

    @NotNull
    private LocalDate publicationYear;

    @Min(0)
    private int stock;

    @NotNull
    private Long authorId;

    @NotNull
    private Long publisherId;

    @NotEmpty
    private List<Long> categoryIds;
}
