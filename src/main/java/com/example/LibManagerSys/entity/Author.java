package com.example.LibManagerSys.entity;

// Import JPA annotations, Lombok, and Java utility classes
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Marks this class as a JPA entity mapped to the 'authors' table
@Entity
@Table(name = "authors")
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
public class Author {

    // Primary key for the Author entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the author
    private String name;

    // Birth date of the author
    private LocalDate birthDate;

    // Country of the author
    private String country;

    // One-to-many relationship with Book entity; an author can have multiple books
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
}