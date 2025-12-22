package com.biblioteca_api.biblioteca.repository;

import com.biblioteca_api.biblioteca.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
