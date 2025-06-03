@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired private LibraryService service;

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) { return service.addBook(book); }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable Long id) { service.removeBook(id); }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) { return service.addUser(user); }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) { service.removeUser(id); }

    @PostMapping("/lend")
    public Transaction lendBook(@RequestParam Long bookId, @RequestParam Long userId) {
        return service.lendBook(bookId, userId);
    }

    @PostMapping("/return")
    public Transaction returnBook(@RequestParam Long transactionId) {
        return service.returnBook(transactionId);
    }

    @GetMapping("/overdue")
    public List<Transaction> overdueBooks() { return service.overdueReports(); }

    @GetMapping("/history/{userId}")
    public List<Transaction> userHistory(@PathVariable Long userId) {
        return service.userHistory(userId);
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam String type, @RequestParam String keyword) {
        return service.searchBooks(keyword, type);
    }
}
