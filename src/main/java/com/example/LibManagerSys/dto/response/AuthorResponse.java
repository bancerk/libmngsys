package com.example.LibManagerSys.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorResponse {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private String country;
}

