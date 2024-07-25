package org.nims.commands;

/**
 * This interface has been used to create a contract for gui and cli commands
 */
public interface Commands {
    /**
     * This method is responsible for adding books to the library
     */
    void addBook();

    /**
     * This method is responsible for removing books from the library
     */
    void removeBook();
    /**
     * This method is responsible for borrowing books from the library
     */
    void borrowBook();
    /**
     * This method is responsible for returning books back to the library
     */
    void returnBook();
    /**
     * This method is responsible for showing available books
     */
    void availableBooks();
    /**
     * This method is responsible for showing borrowed books
     */
    void borrowedBooks();
    /**
     * This method is responsible for showing overdue books
     */
    void overdueBooks();
}
