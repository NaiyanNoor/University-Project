package org.nims;

import org.nims.commands.Commander;
import org.nims.db.DynamicDb;
import org.nims.db.StaticDb;
import org.nims.library.*;
import org.nims.ui.UI;
import org.nims.ui.UIContract;
import org.nims.ui.UIFactory;
import org.nims.utils.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();

        DynamicDb dataRepo = DynamicDb.getInstance();
//        StaticDb dataRepo = StaticDb.getInstance();
        Transaction transactionService = TransactionFactory.TransactionFactory(logger,dataRepo);

        Library library = new Library(transactionService);
        Commander manager = new Commander();

        library.addBook("title 1","author 1");
//        library.addBook("title 2","author 2");
//        library.addBook("title 3","author 3");
//        library.addBook("title 4","author 4");
//        library.addBook("title 5","author 5");

        UIFactory uiFactory = new UIFactory(library, manager);
        UIContract ui = uiFactory.getUI(UI.CLI,manager);
        ui.show();

    }

}