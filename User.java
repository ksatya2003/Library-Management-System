package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String membershipType;
}
