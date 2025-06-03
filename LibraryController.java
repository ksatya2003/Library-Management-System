package com.example.library.controller;

import com.example.library.entity.*;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired private LibraryService libraryService;

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        return libraryService.addBook(book);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        libraryService.deleteBook(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return libraryService.addUser(user);
    }

    @PostMapping("/lend")
    public Transaction lendBook(@RequestParam Long bookId, @RequestParam Long userId) {
        return libraryService.lendBook(bookId, userId);
    }

    @PostMapping("/return/{txnId}")
    public Transaction returnBook(@PathVariable Long txnId) {
        return libraryService.returnBook(txnId);
    }

    @GetMapping("/history/{userId}")
    public List<Transaction> getUserHistory(@PathVariable Long userId) {
        return libraryService.getUserHistory(userId);
    }

    @GetMapping("/overdue")
    public List<Transaction> getOverdueBooks() {
        return libraryService.getOverdueBooks();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String query) {
        return libraryService.searchBooks(query);
    }
}
