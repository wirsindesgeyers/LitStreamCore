package com.biblioteca_api.biblioteca.repository;

import com.biblioteca_api.biblioteca.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
