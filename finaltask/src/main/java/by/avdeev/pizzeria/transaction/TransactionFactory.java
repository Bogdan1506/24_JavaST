package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.service.ServiceException;

public interface TransactionFactory {
    Transaction createTransaction();

    void close() throws ServiceException;
}
