package by.avdeev.pizzeria.service.impl;

import by.avdeev.pizzeria.dao.Transaction;

public abstract class ServiceImpl {
    Transaction transaction;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
