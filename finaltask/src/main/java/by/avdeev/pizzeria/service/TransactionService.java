package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.Transaction;

public abstract class TransactionService {
    protected Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
