package org.nims.library;

import org.nims.entities.Book;
import org.nims.entities.Borrowings;

import java.util.List;

public interface Transaction {
    void addBook(String title,String author);
    void removeBook(Integer id);
    void borrowBook(Integer id,String borrower);
    void returnBook(Integer id);
    List<Book> availableBooks();
    List<Borrowings> borrowedBooks();
    List<Borrowings> overdueBooks();
    Book getBookById(Integer id);

}
