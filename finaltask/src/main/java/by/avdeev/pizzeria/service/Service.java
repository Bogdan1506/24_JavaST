package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.Type;
import by.avdeev.pizzeria.transaction.Transaction;

import java.util.Map;

public interface Service {
    void setTransaction(Transaction transaction);

    void setDAOType(Type type);
}
