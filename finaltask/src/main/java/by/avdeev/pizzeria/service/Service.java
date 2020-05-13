package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.Type;
import by.avdeev.pizzeria.transaction.Transaction;

public interface Service {
    void setTransaction(Transaction transaction);

    void setDAOType(Type type);
}
