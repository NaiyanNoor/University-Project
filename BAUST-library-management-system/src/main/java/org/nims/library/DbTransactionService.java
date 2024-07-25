package org.nims.library;

import org.nims.db.StaticDb;
import org.nims.entities.Book;
import org.nims.entities.Borrowings;
import org.nims.utils.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbTransactionService implements Transaction{
    private final StaticDb repository;
    private final Logger logger;
    public DbTransactionService(StaticDb repository, Logger logger){
        this.repository = repository;

        this.logger = logger;
    }
    @Override
    public void addBook(String title, String author) {
        String sql = "INSERT INTO books (title, author, isBorrowed) VALUES ('%s', '%s', %s)".formatted(title, author, false);
        try {
            quaryRunner(repository.connection,sql);
            logger.info("new book has been added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeBook(Integer id) {
        String Sql = "DELETE FROM books WHERE id = %d".formatted(id);
        try {
            quaryRunner(repository.connection,Sql);
            logger.info("book has been removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void borrowBook(Integer id, String borrower) {
        Optional<Book> book = Optional.ofNullable(getBookById(id));
        book.filter(b -> !b.isBorrowed())
                .ifPresentOrElse(b -> {
                    String booksql = "UPDATE books SET isBorrowed = true WHERE id = " + id;
                    String borrowsql = "INSERT INTO borrowings (book, dueDate, borrower) VALUES (%d, '%s', '%s')".formatted(getBookById(id).getId(), LocalDate.now().plusDays(1), borrower);
                    try {
                        quaryRunner(repository.connection, booksql);
                        quaryRunner(repository.connection, borrowsql);
                        logger.info("book has been borrowed");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }, () -> {
                    logger.info("Book is not available for borrowing");
                });
    }

    @Override
    public void returnBook(Integer id) {
        String booksql = "UPDATE books SET isBorrowed = false WHERE id = "+id;
        String borrowsql = "DELETE FROM borrowings WHERE book = %d".formatted(getBookById(id).getId(), getBookById(id).getAuthor());
        try {
            quaryRunner(repository.connection,booksql);
            quaryRunner(repository.connection,borrowsql);
            logger.info("book has been returned");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> availableBooks() {
        logger.info("available books");
        String sql = "SELECT * FROM books WHERE isBorrowed = false";
        try {
            return getBookData(repository.connection,sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Borrowings> borrowedBooks() {
        logger.info("borrowed books");
        String sql = "SELECT * FROM borrowings";
        try {
            return getBorrowData(repository.connection,sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Borrowings> overdueBooks() {
        logger.info("overdue books");
        String sql = "SELECT * FROM borrowings WHERE DATE(dueDate) < CURRENT_DATE";
        try {
            return getBorrowData(repository.connection,sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getBookById(Integer id) {
        String sql = "SELECT * FROM books WHERE id = "+id;
        try {
            return getBookData(repository.connection, sql).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void quaryRunner (Connection connection, String sql) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }

    public static List<Book> getBookData(Connection connection,String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Book> books = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            boolean isBorrowed = resultSet.getBoolean("isBorrowed");
            Book book = new Book.BookBuilder()
                    .title(title)
                    .author(author)
                    .isBorrowed(isBorrowed)
                    .build();
            book.setId(id);
            books.add(book);
        }

        return books;
    }
    public static List<Borrowings> getBorrowData(Connection connection,String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<Borrowings> borrowings = new ArrayList<>();
        while (resultSet.next()) {
            int bookid = resultSet.getInt("book");
            Book book = getBookData(connection,"SELECT * FROM books WHERE id = "+bookid).get(0);
            LocalDate dueDate = LocalDate.parse(resultSet.getString("dueDate"));
            String borrower = resultSet.getString("borrower");
            Borrowings borrowing = new Borrowings.BorrowingsBuilder()
                    .book(book)
                    .dueDate(dueDate)
                    .borrower(borrower)
                    .build();
            borrowing.setId(String.valueOf(bookid));
            borrowings.add(borrowing);
        }

        return borrowings;
    }
}
