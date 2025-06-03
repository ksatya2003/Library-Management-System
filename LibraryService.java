package com.example.library.service;

import com.example.library.entity.*;
import com.example.library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryService {

    @Autowired private BookRepository bookRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private TransactionRepository txnRepo;

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepo.deleteById(bookId);
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public Transaction lendBook(Long bookId, Long userId) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        if (!book.isAvailability()) throw new RuntimeException("Book not available");
        User user = userRepo.findById(userId).orElseThrow();

        Transaction txn = new Transaction();
        txn.setBook(book);
        txn.setUser(user);
        txn.setIssueDate(LocalDate.now());
        txn.setStatus("Issued");
        book.setAvailability(false);
        bookRepo.save(book);
        return txnRepo.save(txn);
    }

    public Transaction returnBook(Long txnId) {
        Transaction txn = txnRepo.findById(txnId).orElseThrow();
        txn.setReturnDate(LocalDate.now());
        txn.setStatus("Returned");
        Book book = txn.getBook();
        book.setAvailability(true);
        bookRepo.save(book);
        return txnRepo.save(txn);
    }

    public List<Transaction> getUserHistory(Long userId) {
        User user = userRepo.findById(userId).orElseThrow();
        return txnRepo.findByUser(user);
    }

    public List<Transaction> getOverdueBooks() {
        return txnRepo.findByReturnDateBeforeAndStatusNot(LocalDate.now(), "Returned");
    }

    public List<Book> searchBooks(String query) {
        return bookRepo.findByTitleContainingIgnoreCase(query);
    }
}
