package com.example.LibManagerSys.entity;

// Import JPA annotations, Lombok, and Java time classes
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Marks this class as a JPA entity mapped to the 'book_borrowings' table
@Entity
@Table(name = "book_borrowings")
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
public class BookBorrowing {

    // Primary key for the BookBorrowing entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the person borrowing the book
    private String borrowerName;

    // Email of the person borrowing the book
    private String borrowerEmail;

    // Date when the book was borrowed
    private LocalDate borrowingDate;

    // Date when the book was returned
    private LocalDate returnDate;

    // Many-to-one relationship with Book; each borrowing is for one book
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}

