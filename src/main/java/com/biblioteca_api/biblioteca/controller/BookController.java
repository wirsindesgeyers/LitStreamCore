package com.biblioteca_api.biblioteca.controller;


import com.biblioteca_api.biblioteca.dto.BookResponseDTO;
import com.biblioteca_api.biblioteca.entities.Book;
import com.biblioteca_api.biblioteca.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // (GET) - Livro pelo ID.
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        BookResponseDTO response = new BookResponseDTO(book);

        return ResponseEntity.ok(response);
    }

}
