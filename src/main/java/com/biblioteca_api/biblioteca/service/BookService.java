package com.biblioteca_api.biblioteca.service;

import com.biblioteca_api.biblioteca.dto.BookRequestDTO;
import com.biblioteca_api.biblioteca.entities.Author;
import com.biblioteca_api.biblioteca.entities.Book;
import com.biblioteca_api.biblioteca.infra.exceptions.BookAlreadyExistsException;
import com.biblioteca_api.biblioteca.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    // RETORNA O LIVRO COM O ID ESPECIFICADO
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    // RETORNA TODOS OS LIVROS
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // CRIA O LIVRO
    @Transactional
    public Book createBook(BookRequestDTO dto) {

        if (bookRepository.existsByIsbn(dto.isbn())) {
            throw new BookAlreadyExistsException("Já existe um livro cadastrado com este ISBN.");
        }

        Author author = authorService.getAuthorById(dto.authorId());

        Book book = new Book();
        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setPrice(dto.price());
        book.setPublishedDate(dto.publishedDate());

        book.setAuthor(author);

        return bookRepository.save(book);
    }

    // DELETA O LIVRO PELO ID
    @Transactional
    public void deleteBookById(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    // EDITA O LIVRO (PUT)
    @Transactional
    public Book updateBook(Long id, BookRequestDTO dto) {
        Book book = getBookById(id);
        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setPrice(dto.price());
        book.setPublishedDate(dto.publishedDate());

        if (book.getAuthor() == null || !book.getAuthor().getId().equals(dto.authorId())) {
            Author newAuthor = authorService.getAuthorById(dto.authorId());
            book.setAuthor(newAuthor);
        }
        return bookRepository.save(book);

    }

    // ALTERA AUTOR DO LIVRO (PATCH)
    @Transactional
    public Book alterAuthor(Long authorId, Long bookId) {
        Book book = getBookById(bookId);

        Author author = authorService.getAuthorById(authorId);

        book.setAuthor(author);

        return bookRepository.save(book);
    }

}
