package org.nims.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StaticDb implements Db{
    private static StaticDb instance;
    public Connection connection;
    private StaticDb() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
        String username = "user";
        String password = "mypassword";
        String databaseName = "testdb50";

        try{
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            statement.executeUpdate("USE " + databaseName);
            this.connection = connection;
            createStorage();
        } catch (SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
        }
    }
    public static synchronized StaticDb getInstance(){
        if (instance == null) {
            try {
                instance = new StaticDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
    @Override
    public void createStorage() {
        try {
            createBookTable(this.connection);
            createBorrowingsTable(this.connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createBookTable(Connection connection) throws SQLException {
        String tableName = "books";
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                + "id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "title VARCHAR(255),"
                + "author VARCHAR(255),"
                + "isBorrowed INT(1)"
                + ");";
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public static void createBorrowingsTable(Connection connection) throws SQLException {

        String tableName = "borrowings";
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                + "id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "dueDate DATE,"
                + "borrower VARCHAR(255),"
                + "book INT(11) NOT NULL,"
                + "FOREIGN KEY (book) REFERENCES books(id)"
                + ");";
        Statement statement = connection.createStatement();
        statement.execute(sql);

    }
}
