package org.nims.entities;


import org.nims.utils.IdGenerator;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isBorrowed = false;

    public int getId() {
        return id;
    }

    private Book(BookBuilder bookBuilder) {
        this.id = IdGenerator.generateId();
        this.title = bookBuilder.title;
        this.author = bookBuilder.author;
        this.isBorrowed = bookBuilder.isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isBorrowed=" + isBorrowed +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Builder Pattern implementation for Book entity
     */
    public static class BookBuilder {
        private String title;
        private String author;
        private boolean isBorrowed;

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder isBorrowed(boolean isBorrowed) {
            this.isBorrowed = isBorrowed;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
