package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String author;
    private String category;
    private boolean availability;
}
