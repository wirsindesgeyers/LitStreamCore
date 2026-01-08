package com.biblioteca_api.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O autor deve possuir um nome.")
    @Size(max = 100)
    private String name;

    @Column(name = "birth_date")
    @NotBlank
    @PastOrPresent
    private LocalDate birthdate;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
