package com.example.LibManagerSys.entity;

// Import JPA annotations, Lombok, and Java utility classes
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Marks this class as a JPA entity mapped to the 'publishers' table
@Entity
@Table(name = "publishers")
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
public class Publisher {

    // Primary key for the Publisher entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the publisher
    private String name;

    // Year the publisher was established
    private LocalDate establishmentYear;

    // Address of the publisher
    private String address;

    // One-to-many relationship with Book; a publisher can have multiple books
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();
}