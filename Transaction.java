@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long bookId;
    private Long userId;

    private LocalDate issueDate;
    private LocalDate returnDate;

    private String status; // "Issued", "Returned", "Overdue"

    // Getters & Setters
}
