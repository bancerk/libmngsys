package com.example.LibManagerSys.entity;

// Import JPA annotations, Lombok, and Java utility classes
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Marks this class as a JPA entity mapped to the 'categories' table
@Entity
@Table(name = "categories")
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
public class Category {

    // Primary key for the Category entity, auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the category
    private String name;

    // Description of the category
    private String description;

    // Many-to-many relationship with Book; a category can include multiple books
    @ManyToMany(mappedBy = "categories")
    private List<Book> books = new ArrayList<>();
}
