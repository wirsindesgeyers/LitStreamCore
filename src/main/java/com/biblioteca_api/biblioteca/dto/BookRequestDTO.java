package com.biblioteca_api.biblioteca.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookRequestDTO(

        @NotBlank(message = "O livro precisa ter um titulo.")
        @Size(max = 150, message = "Tamanho de caracteres excedido")
        String title,

        @ISBN
        @Size(max = 13)
        @NotNull(message = "isbn é obrigatorio")
        String isbn,

        @Min(value = 0, message = "O valor não pode ser menor que 0.")
        @NotNull(message = "O livro precisa ter um preço")
        BigDecimal price,

        @NotNull(message = "O livro precisa de data de publicação")
        @PastOrPresent(message = "O livro não pode ser publicado no futuro")
        LocalDate publishedDate


) {
}
