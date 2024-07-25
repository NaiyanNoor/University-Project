package org.nims.commands;

import org.nims.entities.Book;
import org.nims.entities.Borrowings;
import org.nims.library.Library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 * This class holds all the commands for gui
 */
public class GuiCommands implements Commands {

    private final JFrame frame;
    private final Library library;
    private final JTable booksTable;
    private final JTable borrowedBooksTable;

    public GuiCommands(JFrame frame, Library library, JTable booksTable, JTable borrowedBooksTable) {
        this.frame = frame;
        this.library = library;
        this.booksTable = booksTable;
        this.borrowedBooksTable = borrowedBooksTable;
    }
    @Override
    public void addBook() {
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        Object[] inputFields = {"Title:", title, "Author:", author};
        int result = JOptionPane.showConfirmDialog
                (frame, inputFields, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            library.addBook(title.getText(), author.getText());
            availableBooks();
        }
    }

    @Override
    public void removeBook() {
        int selectedRow = booksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book !");
            return;
        }
        int bookId = (int) booksTable.getValueAt(selectedRow, 0);

        int result = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to remove this book?",
                "Remove Book",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            library.removeBook(bookId);
            availableBooks();
        }
    }
    @Override
    public void borrowBook() {
        int selectedRow = booksTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to borrow.");
            return;
        }
        int bookId = (int) booksTable.getValueAt(selectedRow, 0);

        JTextField borrower = new JTextField();
        Object[] inputFields = {"Borrower's Name:", borrower};
        int result = JOptionPane.showConfirmDialog
                (frame, inputFields, "Borrow Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            library.borrowBook(bookId,borrower.getText());
            availableBooks();
            borrowedBooks();
        }

    }
    @Override
    public void returnBook() {
        int selectedRow = borrowedBooksTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to return.");
            return;
        }
        int bookId = (int) borrowedBooksTable.getValueAt(selectedRow, 0);
        int result = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to return this book?",
                "Return Book",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            library.returnBook(bookId);
            availableBooks();
            borrowedBooks();
        }
    }

    @Override
    public void availableBooks() {
        List<Book> allBooks = library.availableBooks();
        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
        model.setRowCount(0);
        for (Book book : allBooks) {
            model.addRow(new Object[]{
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.isBorrowed()});
        }
    }

    @Override
    public void borrowedBooks() {
        List<Borrowings> borrowings = library.borrowedBooks();
        DefaultTableModel borrowedModel = (DefaultTableModel) borrowedBooksTable.getModel();
        borrowedModel.setRowCount(0);
        for (Borrowings a : borrowings) {
            borrowedModel.addRow(new Object[]{
                    a.getBook().getId(),
                    a.getBook().getTitle(),
                    a.getBorrower(),
                    a.getDueDate()});
        }
    }
    @Override
    public void overdueBooks() {
        List<Borrowings> overdueBooks = library.overdueBooks();
        DefaultTableModel borrowedModel = (DefaultTableModel) borrowedBooksTable.getModel();
        borrowedModel.setRowCount(0);
        for (Borrowings a : overdueBooks) {
            borrowedModel.addRow(new Object[]{
                    a.getBook().getId(),
                    a.getBook().getTitle(),
                    a.getBorrower(),
                    a.getDueDate()});
        }
    }
}
