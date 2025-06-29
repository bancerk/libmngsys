package com.example.LibManagerSys.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorSaveRequest {

    @NotBlank
    private String name;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String country;
}

