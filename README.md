Objective 1: Efficient management of library books and transactions
Approach:

Use a relational database (H2) to store data.

Use Spring Boot for rapid application development.

Create REST APIs for CRUD operations on Book, User, and Transaction.

Use Spring Data JPA to interact with the database easily.

🔸 Objective 2: Track book availability and lending history
Approach:

Maintain a field availability in the Book entity.

Create a Transaction entity that records every lending or return.

Each transaction logs: book, user, issueDate, returnDate, and status (Issued, Returned).

🔸 Objective 3: Allow librarians to manage books and users
Approach:

Provide REST APIs for:

Adding/removing books.

Adding/removing users.

Secure access using Spring Security (with hardcoded in-memory user: "librarian").

🔸 Objective 4: Provide search and report functionalities
Approach:

Create custom query methods in BookRepository to search by:

Title

Author

Category

Generate reports:

Overdue books: use TransactionRepository with a method that finds transactions past due date and not returned.

User borrowing history: get all transactions by user ID.

🔶 2. Requirements & Design Approach
✅ Entities and Relationships
Entity	Description
Book	Stores title, author, category, and availability.
User	Represents a library user (student/staff).
Transaction	Represents each lending or returning of a book.

Approach:

Use JPA annotations to define entities.

Transaction has @ManyToOne relationships with both Book and User.
| Feature                 | Implementation Steps                                                  |
| ----------------------- | --------------------------------------------------------------------- |
| Add/Remove Book         | `POST` and `DELETE` in `LibraryController`.                           |
| Add/Remove User         | `POST` and `DELETE` if needed.                                        |
| Lend Book               | Update book availability and create a `Transaction`.                  |
| Return Book             | Update return date in `Transaction` and set book as available again.  |
| Search Books            | Define custom JPA queries in `BookRepository`.                        |
| View Borrowing History  | Filter transactions by `userId`.                                      |
| Overdue Books Report    | Filter transactions with returnDate < today and status != "Returned". |
| Secure Librarian Access | Use Spring Security with Basic Auth and in-memory user.               |


| Tech                | Purpose                                            |
| ------------------- | -------------------------------------------------- |
| **Spring Boot**     | Rapid development with REST API support.           |
| **Spring Data JPA** | Simplified DB interaction.                         |
| **H2 Database**     | Lightweight, in-memory DB for development/testing. |
| **Spring Security** | To restrict access to authorized librarians.       |
| **Lombok**          | Reduce boilerplate (`@Data`, `@Getter`, etc.).     |

