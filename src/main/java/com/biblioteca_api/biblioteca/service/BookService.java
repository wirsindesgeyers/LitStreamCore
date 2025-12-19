package com.biblioteca_api.biblioteca.service;


import com.biblioteca_api.biblioteca.dto.BookRequestDTO;
import com.biblioteca_api.biblioteca.entities.Book;
import com.biblioteca_api.biblioteca.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    // RETORNA O LIVRO COM O ID ESPECIFICADO
    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    // RETORNA TODOS OS LIVROS
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }


    //CRIA O LIVRO
    public Book createBook(BookRequestDTO dto){
        return bookRepository.save(dto.toEntity());
    }

    //DELETA O LIVRO PELO ID
    public void deleteBookById(Long id){
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    //EDITA O LIVRO (PUT)
    public Book updateBook(Long id, BookRequestDTO dto){
        Book book = getBookById(id);
        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setPrice(dto.price());
        book.setPublishedDate(dto.publishedDate());
        return bookRepository.save(book);

    }



}
