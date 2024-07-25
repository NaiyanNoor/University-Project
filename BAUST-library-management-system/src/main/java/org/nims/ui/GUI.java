package org.nims.ui;

import org.nims.commands.Commands;
import org.nims.commands.GuiCommands;
import org.nims.commands.Commander;
import org.nims.library.Library;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUI implements UIContract {
    private final Library library;
    private final Commander commander;
    public GUI(Library library, Commander commander){
        this.library = library;
        this.commander = commander;
    }

    @Override
    public void show() {
        SwingUtilities.invokeLater(this::initiateUI);
    }
    private void initiateUI() {
        /*----------------------------------------- Begin UI Template ------------------------------------- */
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel books = new DefaultTableModel();
        books.addColumn("ID");
        books.addColumn("Title");
        books.addColumn("Author");

        JTable booksTable = new JTable(books);
        JScrollPane booksScrollPane = new JScrollPane(booksTable);

        DefaultTableModel borrowedBooks = new DefaultTableModel();
        borrowedBooks.addColumn("ID");
        borrowedBooks.addColumn("Title");
        borrowedBooks.addColumn("Borrower");
        borrowedBooks.addColumn("Due Date");

        JTable borrowedBooksTable = new JTable(borrowedBooks);
        JScrollPane borrowedScrollPane = new JScrollPane(borrowedBooksTable);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(450,200));
        panel1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Available Books", TitledBorder.CENTER,
                TitledBorder.TOP));
        panel1.add(booksScrollPane);

        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(450,200));
        panel2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Borrowed Books", TitledBorder.CENTER,
                TitledBorder.TOP));
        panel2.add(borrowedScrollPane);

        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Book");
        controlPanel.add(addButton);
        JButton borrowButton = new JButton("Borrow Book");
        controlPanel.add(borrowButton);
        JButton removeButton = new JButton("Remove Book");
        controlPanel.add(removeButton);

        JPanel controlPanel2 = new JPanel(new FlowLayout());
        JButton returnBook = new JButton("Return Book");
        controlPanel2.add(returnBook);
        JButton overdueBooks = new JButton("Overdue Books");
        controlPanel2.add(overdueBooks);

        container.add(controlPanel);
        container.add(panel1);
        container.add(controlPanel2);
        container.add(panel2);
        frame.getContentPane().add(container, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /*----------------------------------------- End UI Template ------------------------------------- */

        /* Initializing commands */
        Commands guiCommands = new GuiCommands(
                frame,
                library,
                booksTable,
                borrowedBooksTable
        );

        addButton.addActionListener(e -> commander.getCommand(guiCommands::addBook));
        borrowButton.addActionListener(e -> commander.getCommand(guiCommands::borrowBook));
        removeButton.addActionListener(e -> commander.getCommand(guiCommands::removeBook));
        returnBook.addActionListener(e -> commander.getCommand(guiCommands::returnBook));
        overdueBooks.addActionListener(e -> commander.getCommand(guiCommands::overdueBooks));

        /* Data initialization */
        guiCommands.availableBooks();
        guiCommands.borrowedBooks();
    }
}
