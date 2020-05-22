package by.avdeev.pizzeria.transaction;

import by.avdeev.pizzeria.service.ServiceException;

public interface TransactionFactory extends AutoCloseable {
    /**
     * Creates ${@link Transaction}.
     *
     * @return instance ${@link Transaction}.
     */
    Transaction createTransaction();

    /**
     * Commit and closes connection.
     *
     * @throws ServiceException If method is failed.
     */
    void close() throws Exception;
}
