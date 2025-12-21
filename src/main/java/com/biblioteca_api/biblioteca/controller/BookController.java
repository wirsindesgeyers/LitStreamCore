package com.biblioteca_api.biblioteca.controller;


import com.biblioteca_api.biblioteca.dto.BookRequestDTO;
import com.biblioteca_api.biblioteca.dto.BookResponseDTO;
import com.biblioteca_api.biblioteca.entities.Book;
import com.biblioteca_api.biblioteca.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // (GET) - Livro pelo ID.
    @Operation(summary = "Retorna o livro pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        BookResponseDTO response = new BookResponseDTO(book);
        return ResponseEntity.ok(response);
    }

    //(GET) - Todos os livros
    @GetMapping
    @Operation(summary = "Retorna todos os livros")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks (){
        List<Book> books = bookService.getAllBooks();
        List<BookResponseDTO> response = books.stream()
                .map(BookResponseDTO::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    //(POST) - Criar livro
    @Operation(summary = "Cria um livro")
    @PostMapping
    public ResponseEntity<BookResponseDTO> postBook(@RequestBody @Valid BookRequestDTO dto){
        Book book = bookService.createBook(dto);

        BookResponseDTO response = new BookResponseDTO(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //(PUT) - EDITAR LIVRO
    @Operation(summary = "Edita um livro (por completo)")
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> editBook(@RequestBody @Valid BookRequestDTO dto, @PathVariable Long id){
        Book book = bookService.updateBook(id, dto);

        BookResponseDTO response = new BookResponseDTO(book);

        return ResponseEntity.ok(response);
    }

    //(DELETE) - DELETAR LIVRO
    @Operation(summary = "Deleta um livro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
