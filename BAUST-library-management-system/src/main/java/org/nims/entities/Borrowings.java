package org.nims.entities;

import java.time.LocalDate;

public class Borrowings {
    private Book book;
    private LocalDate dueDate;
    private String borrower;

    private Borrowings(BorrowingsBuilder borrowingsBuilder) {
        this.book = borrowingsBuilder.book;
        this.dueDate = borrowingsBuilder.dueDate;
        this.borrower =borrowingsBuilder.borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrower() {
        return borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Book: " +"{ id=" + book.getId() + ", title="+ book.getTitle()+", author= "+book.getAuthor() +"}"+
                ", Due Date: " + dueDate +
                ", Borrower: " + borrower;
    }

    public void setId(String id) {
    }

    /**
     * Builder Pattern implementation for BorrowingBook entity
     */

     public static class BorrowingsBuilder {
        private Book book;
        private LocalDate dueDate;
        private String borrower;

        public BorrowingsBuilder book(Book book) {
            this.book = book;
            return this;
        }

        public BorrowingsBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public BorrowingsBuilder borrower(String borrower) {
            this.borrower = borrower;
            return this;
        }
        public Borrowings build() {
            return new Borrowings(this);
        }
    }

}
