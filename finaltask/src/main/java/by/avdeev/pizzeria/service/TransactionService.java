package by.avdeev.pizzeria.service;

import by.avdeev.pizzeria.transaction.DAOType;
import by.avdeev.pizzeria.transaction.Transaction;

public abstract class TransactionService {
    protected Transaction transaction;
    protected DAOType daoType;

    public void setDAOType(DAOType daoType) {
        this.daoType = daoType;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
