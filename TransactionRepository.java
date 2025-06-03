public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByReturnDateBeforeAndStatus(LocalDate date, String status);
}
