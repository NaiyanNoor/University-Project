package org.nims.ui;

import org.nims.commands.CliCommands;
import org.nims.commands.Commands;
import org.nims.commands.Commander;
import org.nims.constants.Constants;
import org.nims.library.Library;
import org.nims.utils.Printer;

import java.util.Scanner;

public class CLI implements UIContract {
    Printer printer = new Printer();
    int choice;
    private final Commands cliCommands;
    private final Commander commander;
    Scanner scanner = new Scanner(System.in);

    public CLI(Library library, Commander commander) {
        this.cliCommands = new CliCommands(scanner, library,printer);
        this.commander = commander;
    }

    @Override
    public void show() {

        try {
            do {
                printer.print(Constants.PROMPT);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> commander.execute(cliCommands::addBook);
                    case 2 -> commander.execute(cliCommands::removeBook);
                    case 3 -> commander.execute(cliCommands::availableBooks);
                    case 4 -> commander.execute(cliCommands::borrowBook);
                    case 5 -> commander.execute(cliCommands::returnBook);
                    case 6 -> commander.execute(cliCommands::borrowedBooks);
                    case 7 -> commander.execute(cliCommands::overdueBooks);
                    default -> printer.print("Invalid Input");
                }
            } while (choice != 8);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
