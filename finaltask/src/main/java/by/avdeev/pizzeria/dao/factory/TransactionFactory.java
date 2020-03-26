package by.avdeev.pizzeria.dao.factory;

import by.avdeev.pizzeria.dao.Transaction;

public interface TransactionFactory {
    Transaction createTransaction();

    void close();
}
