package org.nims.library;

import org.nims.entities.Book;
import org.nims.entities.Borrowings;

import java.util.List;

public class Library {
    private final Transaction transactionService;

    public Library(Transaction transactionService) {
        this.transactionService = transactionService;
    }

    public void addBook(String title,String author) {
        transactionService.addBook(title,author);
    }

    public void removeBook(Integer id) {
        transactionService.removeBook(id);
    }

    public void borrowBook(Integer id,String borrower) {
        transactionService.borrowBook(id,borrower);
    }

    public void returnBook(Integer id) {
        transactionService.returnBook(id);
    }

    public List<Book> availableBooks(){
       return transactionService.availableBooks();
    }

    public List<Borrowings> borrowedBooks(){
        return transactionService.borrowedBooks();
    }
    public List<Borrowings> overdueBooks(){
        return transactionService.overdueBooks();
    }
}

