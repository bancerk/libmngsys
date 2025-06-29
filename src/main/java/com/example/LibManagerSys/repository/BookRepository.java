package com.example.LibManagerSys.repository;

import com.example.LibManagerSys.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByCategories_Id(Long categoryId);
}

