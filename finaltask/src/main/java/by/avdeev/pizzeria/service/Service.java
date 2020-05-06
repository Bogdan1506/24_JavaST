package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.DAOType;
import by.avdeev.pizzeria.transaction.Transaction;

import java.util.Map;

public interface Service {
    void setTransaction(Transaction transaction);

    void setDAOType(DAOType daoType);

    void setParameters(Map<String, Object> parameters);
}
