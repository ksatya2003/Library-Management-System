@Service
public class LibraryService {
    @Autowired private BookRepository bookRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private TransactionRepository transRepo;

    public Book addBook(Book book) { return bookRepo.save(book); }
    public void removeBook(Long id) { bookRepo.deleteById(id); }

    public User addUser(User user) { return userRepo.save(user); }
    public void removeUser(Long id) { userRepo.deleteById(id); }

    public Transaction lendBook(Long bookId, Long userId) {
        Book book = bookRepo.findById(bookId).orElseThrow();
        if (!book.isAvailability()) throw new RuntimeException("Book not available");
        book.setAvailability(false);
        bookRepo.save(book);

        Transaction tx = new Transaction();
        tx.setBookId(bookId);
        tx.setUserId(userId);
        tx.setIssueDate(LocalDate.now());
        tx.setStatus("Issued");
        return transRepo.save(tx);
    }

    public Transaction returnBook(Long txId) {
        Transaction tx = transRepo.findById(txId).orElseThrow();
        tx.setReturnDate(LocalDate.now());
        tx.setStatus("Returned");

        Book book = bookRepo.findById(tx.getBookId()).orElseThrow();
        book.setAvailability(true);
        bookRepo.save(book);

        return transRepo.save(tx);
    }

    public List<Transaction> overdueReports() {
        return transRepo.findByReturnDateBeforeAndStatus(LocalDate.now(), "Issued");
    }

    public List<Transaction> userHistory(Long userId) {
        return transRepo.findByUserId(userId);
    }

    public List<Book> searchBooks(String key, String type) {
        return switch (type.toLowerCase()) {
            case "title" -> bookRepo.findByTitleContainingIgnoreCase(key);
            case "author" -> bookRepo.findByAuthorContainingIgnoreCase(key);
            case "category" -> bookRepo.findByCategoryContainingIgnoreCase(key);
            default -> List.of();
        };
    }
}
