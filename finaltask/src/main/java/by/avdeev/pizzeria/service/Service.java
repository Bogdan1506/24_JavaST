package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.DAOType;
import by.avdeev.pizzeria.transaction.Transaction;

public interface Service {
    void setTransaction(Transaction transaction);

    void setDAOType(DAOType daoType);
}
