package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    private LocalDate issueDate;
    private LocalDate returnDate;
    private String status;
}
