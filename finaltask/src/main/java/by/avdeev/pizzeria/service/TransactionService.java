package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.Type;
import by.avdeev.pizzeria.transaction.Transaction;

public abstract class TransactionService {
    protected Transaction transaction;
    protected Type type;

    public void setDAOType(Type type) {
        this.type = type;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
