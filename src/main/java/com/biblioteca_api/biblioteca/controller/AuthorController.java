package com.biblioteca_api.biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biblioteca_api.biblioteca.dto.AuthorRequestDTO;
import com.biblioteca_api.biblioteca.dto.AuthorResponseDTO;
import com.biblioteca_api.biblioteca.entities.Author;
import com.biblioteca_api.biblioteca.service.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @Operation(summary = "Cria um autor")
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody @Valid AuthorRequestDTO data) {

        Author author = authorService.createAuthor(data);

        AuthorResponseDTO response = new AuthorResponseDTO(author);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();

        return ResponseEntity.created(location).body(response);

    }

    @Operation(summary = "Busca os dados de um autor")
    @GetMapping("{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthor(@RequestBody Long id) {
        Author author = authorService.getAuthorById(id);

        AuthorResponseDTO authorDTO = new AuthorResponseDTO(author);

        return ResponseEntity.ok(authorDTO);

    }

    @Operation(summary = "Apaga um autor")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor(@RequestBody Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Retorna todos os autores")
    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        List<AuthorResponseDTO> response = authors.stream().map(AuthorResponseDTO::new).toList();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Editar um autor por completo")
    @PutMapping("{id}")
    public ResponseEntity<AuthorResponseDTO> editAuthor(@RequestBody Long id,
            @RequestBody @Valid AuthorRequestDTO data) {

        Author author = authorService.editAuthor(data, id);

        AuthorResponseDTO response = new AuthorResponseDTO(author);

        return ResponseEntity.ok(response);
    }
}
