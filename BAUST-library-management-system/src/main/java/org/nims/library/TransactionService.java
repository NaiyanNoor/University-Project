package org.nims.library;

import org.nims.db.DynamicDb;
import org.nims.entities.Book;
import org.nims.entities.Borrowings;
import org.nims.exceptions.BookNotFoundException;
import org.nims.utils.Logger;

import java.time.LocalDate;
import java.util.List;

/**
 * This service class includes the functionalities for basic transactions with the library
 */
public class TransactionService implements Transaction {
    private final DynamicDb bookRepository;
    private final Logger logger;

    public TransactionService(DynamicDb bookRepository, Logger logger) {
        this.bookRepository = bookRepository;
        this.logger = logger;
    }

    /**
     * This method add books to the library
     *
     * @param title  : String
     * @param author : String
     */
    @Override
    public void addBook(String title, String author) {
        Book newBook = new Book.BookBuilder().title(title).author(author).build();
        bookRepository.books.add(newBook);
        logger.info("new book has been added");
    }

    /**
     * This method remove books from the library
     *
     * @param id : Integer
     */
    @Override
    public void removeBook(Integer id) {
        Book book = getBookById(id);
        bookRepository.books.remove(book);
        logger.info("book has been removed");
    }

    /**
     * This method illustrates borrowing a book from the library
     *
     * @param id : Integer
     */
    @Override
    public void borrowBook(Integer id, String borrower) {
        try {
            Book book = getBookById(id);
            if (book == null) {
                throw new Exception("Book ID not found");
            }
            
            bookRepository.books.stream()
                .filter(a -> a.getId() == book.getId())
                .findAny()
                .ifPresent(a -> a.setBorrowed(true));
    
            Borrowings borrowings = new Borrowings.BorrowingsBuilder()
                .book(book)
                .borrower(borrower)
                .dueDate(LocalDate.now().plusDays(1))
                .build();
                    
            bookRepository.borrowings.add(borrowings);
            logger.info("book has been borrowed");
        } catch (Exception e) {
            // code to handle the exception
            System.out.println(e.getMessage());
        }
    }
    

    /**
     * This method illustrates returning borrowed book to the library
     *
     * @param id : Integer
     */
    @Override
    public void returnBook(Integer id) {
        Book book = getBookById(id);
        bookRepository.books.stream()
                .filter(a -> a.getId() == book.getId())
                .findAny()
                .ifPresent(a -> a.setBorrowed(false));

        bookRepository.borrowings.remove(
                bookRepository.borrowings.stream()
                        .filter(a -> a.getBook() == book)
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("no such borrowings")));

        logger.info("book has been returned");
    }

    /**
     * This method retrieves all the available books in the library
     *
     * @return List<Book>
     */
    @Override
    public List<Book> availableBooks() {
        logger.info("available books");
        return bookRepository.books.stream()
                .filter(a -> !a.isBorrowed())
                .toList();
    }

    /**
     * This method retrieves all the borrowed books from the library
     *
     * @return : List<Book>
     */
    @Override
    public List<Borrowings> borrowedBooks() {
        logger.info("borrowed books");
        return bookRepository.borrowings.stream()
                .filter(a -> a.getBook().isBorrowed())
                .toList();
    }

    /**
     * This method retrieves overdue Books
     *
     * @return : List<Borrowings>
     */
    @Override
    public List<Borrowings> overdueBooks() {
        logger.info("overdue books");
        LocalDate today = LocalDate.now();
        return bookRepository.borrowings.stream()
                .filter(borrowedBook -> borrowedBook.getDueDate().isBefore(today))
                .toList();
    }

    /**
     * This method retrieves book by its id
     *
     * @param id : Integer
     * @return : Book
     */
    @Override
    public Book getBookById(Integer id) {
        return bookRepository.books.stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElseThrow(() -> new BookNotFoundException("Book not Found"));
    }
}
