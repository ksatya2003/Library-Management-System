package com.example.library.repository;

import com.example.library.entity.Transaction;
import com.example.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
    List<Transaction> findByReturnDateBeforeAndStatusNot(java.time.LocalDate date, String status);
}
