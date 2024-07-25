package org.nims.db;

import org.nims.entities.Book;
import org.nims.entities.Borrowings;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DynamicDb implements Db{
    private static DynamicDb instance;
    public List<Book> books;
    public List<Borrowings> borrowings;
    private DynamicDb() {
        createStorage();
    }
    public static synchronized DynamicDb getInstance() {
        if (instance == null) {
            instance = new DynamicDb();
        }
        return instance;
    }

    @Override
    public void createStorage() {
        books = new ArrayList<>();
        borrowings = new ArrayList<>();
    }
}
