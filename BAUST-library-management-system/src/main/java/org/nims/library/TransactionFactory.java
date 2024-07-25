package org.nims.library;

import org.nims.db.Db;
import org.nims.db.DynamicDb;
import org.nims.db.StaticDb;
import org.nims.utils.Logger;

public class TransactionFactory {
    public static Transaction TransactionFactory(Logger logger, Db dataRepo) {
        if (dataRepo instanceof DynamicDb) {
            return new TransactionService((DynamicDb) dataRepo,logger);
        }else{
            return new DbTransactionService((StaticDb) dataRepo,logger);
        }
    }
}
