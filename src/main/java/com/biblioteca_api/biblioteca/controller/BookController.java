package com.biblioteca_api.biblioteca.controller;


import com.biblioteca_api.biblioteca.dto.BookResponseDTO;
import com.biblioteca_api.biblioteca.entities.Book;
import com.biblioteca_api.biblioteca.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // (GET) - Livro pelo ID.
    @GetMapping("{/id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        BookResponseDTO response = new BookResponseDTO(book);
        return ResponseEntity.ok(response);
    }

    //(GET) - Todos os livros
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks (){
        List<Book> books = bookService.getAllBooks();
        List<BookResponseDTO> response = books.stream()
                .map(BookResponseDTO::new)
                .toList();
        return ResponseEntity.ok(response);
    }

}
