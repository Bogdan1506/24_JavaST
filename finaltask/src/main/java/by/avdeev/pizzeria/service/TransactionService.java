package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.DAOType;
import by.avdeev.pizzeria.transaction.Transaction;

import java.util.Map;

public abstract class TransactionService {
    protected Transaction transaction;
    protected DAOType daoType;
    protected Map<String, Object> parameters;

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public void setDAOType(DAOType daoType) {
        this.daoType = daoType;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
