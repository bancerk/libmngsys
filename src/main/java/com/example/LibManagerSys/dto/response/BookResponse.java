package com.example.LibManagerSys.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookResponse {

    private Long id;
    private String name;
    private LocalDate publicationYear;
    private int stock;
    private String authorName;
    private String publisherName;
    private List<String> categoryNames;
}
