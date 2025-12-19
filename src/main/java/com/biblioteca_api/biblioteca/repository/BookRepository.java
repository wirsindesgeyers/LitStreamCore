package com.biblioteca_api.biblioteca.repository;

import com.biblioteca_api.biblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {
}
