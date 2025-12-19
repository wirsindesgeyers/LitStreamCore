package com.biblioteca_api.biblioteca.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BookResponseDTO(

        Long id,
        String title,
        String isbn,
        BigDecimal price,
        LocalDate publishedDate


) {
}
