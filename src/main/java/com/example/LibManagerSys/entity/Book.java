package com.example.LibManagerSys.entity;

// Import JPA annotations, Lombok, and Java utility classes
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Marks this class as a JPA entity mapped to the 'books' table
@Entity
@Table(name = "books")
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
public class Book {

    // Primary key for the Book entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the book
    private String name;

    // Publication year of the book
    private LocalDate publicationYear;

    // Number of books in stock
    private int stock;

    // Many-to-one relationship with Author; each book has one author
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Many-to-one relationship with Publisher; each book has one publisher
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    // Many-to-many relationship with Category; a book can belong to multiple categories
    @ManyToMany
    @JoinTable(name = "book_categories", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    // One-to-many relationship with BookBorrowing; a book can have multiple borrowings
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookBorrowing> borrowings = new ArrayList<>();
}
