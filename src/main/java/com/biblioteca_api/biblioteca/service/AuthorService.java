package com.biblioteca_api.biblioteca.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.biblioteca_api.biblioteca.dto.AuthorRequestDTO;
import com.biblioteca_api.biblioteca.entities.Author;
import com.biblioteca_api.biblioteca.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // PEGA O AUTOR PELO ID
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado"));

    }

    // DELETE AUTOR PELO ID
    @Transactional
    public void deleteAuthorById(Long id) {
        getAuthorById(id);
        authorRepository.deleteById(id);
    }

    // CRIA UM AUTOR

    @Transactional
    public Author createAuthor(AuthorRequestDTO dto) {
        Author author = new Author();
        author.setName(dto.name());
        author.setBirthdate(dto.birthDate());

        return authorRepository.save(author);
    }

}
